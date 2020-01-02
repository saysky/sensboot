package com.liuyanzhao.sens.controller;

import cn.hutool.core.util.NumberUtil;
import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.plugins.Page;
import com.liuyanzhao.sens.annotation.SystemLog;
import com.liuyanzhao.sens.entity.User;
import com.liuyanzhao.sens.enums.LogType;
import com.liuyanzhao.sens.excel.UserExcel;
import com.liuyanzhao.sens.exception.GlobalException;
import com.liuyanzhao.sens.result.CodeMsg;
import com.liuyanzhao.sens.result.Result;
import com.liuyanzhao.sens.service.UserService;
import com.liuyanzhao.sens.utils.ExcelUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.util.NumberUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;


/**
 * 后台用户管理控制器
 * 异常不用捕获，用统一异常捕获处理
 *
 * @author liuyanzhao
 */
@Slf4j
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 最大导入数量
     */
    @Value("${user.import.max}")
    private Integer MAX_USER_IMPORT;
//    public static final int MAX_USER_IMPORT = 1000;


    public static final Pattern EMAIL_PATTERN = Pattern.compile("^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$");

    public static final DateFormat DATE_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    /**
     * 当前登录用户
     *
     * @param user 用户信息，由封装注入
     * @return
     */
    @RequestMapping("/current")
    @ResponseBody
    public Result<User> current(User user) {
        return Result.success(user);
    }

    /**
     * 获得用户列表
     *
     * @param page  页码
     * @param size  页大小
     * @param model model
     * @return JSON格式数据
     */
    @GetMapping
    @SystemLog(description = "查询用户列表", type = LogType.OPERATION)
    public Result<Page<User>> users(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                    @RequestParam(value = "size", defaultValue = "10") Integer size, Model model) {
        //用户列表
        Page pageable = new Page(page, size);
        Page<User> users = userService.findAll(pageable);
        return Result.success(users);
    }

    /**
     * 添加用户
     *
     * @param user 用户
     * @return JSON格式数据
     */
    @PostMapping
    public Result<Boolean> add(@RequestBody User user) {
        userService.insert(user);
        return Result.success(true);
    }

    /**
     * 更新用户
     *
     * @param user 用户
     * @return JSON格式数据
     */
    @PutMapping
    public Result<Boolean> update(@RequestBody User user) {
        userService.update(user);
        return Result.success(true);
    }

    /**
     * 删除用户
     *
     * @param id ID
     * @return JSON格式数据
     */
    @DeleteMapping("/{id}")
    public Result<Boolean> delete(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return Result.success(true);
    }

    /**
     * 根据ID查询用户
     *
     * @param id ID
     * @return JSON格式数据
     */
    @GetMapping("/{id}")
    public Result<User> get(@PathVariable("id") Long id) {
        User user = userService.findById(id);
        return Result.success(user);
    }

    /**
     * 下载Excel模板
     */
    @GetMapping("/excel/template")
    public void downloadTemplate(HttpServletResponse response) {
        String fileName = "导入用户模板";
        String sheetName = "导入用户模板";

        List<UserExcel> userList = new ArrayList<>();

        userList.add(new UserExcel("saysky", "言曌", "123456", "847064370@qq.com", "http://xxx.com/xx.jpg", "0", "2017-12-31 12:13:14"));
        userList.add(new UserExcel("qiqi", "琪琪", "123456", "666666@qq.com", "http://xxx.com/xx.jpg", "0", "2018-5-20 13:14:00"));

        try {
            ExcelUtil.writeExcel(response, userList, fileName, sheetName, UserExcel.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 导出
     */
    @GetMapping("/excel/export")
    public void exportData(HttpServletResponse response) {
        String fileName = "用户列表";
        String sheetName = "用户列表";

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<User> userList = userService.findAll();
        List<UserExcel> userExcelList = new ArrayList<>();
        for (User user : userList) {
            UserExcel userExcel = UserExcel.builder()
                    .username(user.getUsername())
                    .password(user.getPassword())
                    .nickname(user.getNickname())
                    .email(user.getEmail())
                    .avatar(user.getAvatar())
                    .status(String.valueOf(user.getStatus()))
                    .createdTime(dateFormat.format(user.getCreatedTime())).build();

            userExcelList.add(userExcel);

        }

        try {
            ExcelUtil.writeExcel(response, userExcelList, fileName, sheetName, UserExcel.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 导入：同步读，单sheet
     * 注意：这里为了介绍 excel导入导出，代码都写在 controller，实际项目开发中，校验和处理代码建议放到 service
     */
    @PostMapping("/excel/import")
    public void importData(MultipartFile file) throws ParseException {
        List<UserExcel> userExcelList = null;
        // 1.excel同步读取数据
        try {
            userExcelList = EasyExcel.read(new BufferedInputStream(file.getInputStream())).head(UserExcel.class).sheet().doReadSync();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 2.检查是否大于1000条
        if (userExcelList.size() > MAX_USER_IMPORT) {
            throw new GlobalException(CodeMsg.OVER_MAX_USER_IMPORT_LIMIT.fillArgs(MAX_USER_IMPORT));
        }

        // 3.导入校验所有行列格式
        checkImportData(userExcelList);

        // 4.将 userExcelList 转成 userList
        List<User> userList = userExcelList2UserList(userExcelList);

        // 5.入库操作
        userService.batchInsertOrUpdate(userList);


    }

    private void checkImportData(List<UserExcel> userExcelList) {
        // 行号从第2行开始
        int rowNo = 2;
        // 遍历校验所有行和列
        for (UserExcel userExcel : userExcelList) {
            // 1.校验用户名
            String username = userExcel.getUsername();
            if (StringUtils.isEmpty(username)) {
                throw new GlobalException(CodeMsg.IMPORT_FIELD_IS_EAMPTY.fillArgs(rowNo, "用户名"));
            }
            if (username.length() > 20 || username.length() < 4) {
                throw new GlobalException(CodeMsg.IMPORT_FIELD_FORMAT_ERROR.fillArgs(rowNo, "用户名"));
            }

            // 2.校验密码
            String password = userExcel.getPassword();
            if (StringUtils.isEmpty(password)) {
                throw new GlobalException(CodeMsg.IMPORT_FIELD_IS_EAMPTY.fillArgs(rowNo, "密码"));
            }
            if (password.length() > 100 || password.length() < 6) {
                throw new GlobalException(CodeMsg.IMPORT_FIELD_FORMAT_ERROR.fillArgs(rowNo, "密码"));
            }

            // 3.校验昵称
            String nickname = userExcel.getNickname();
            if (StringUtils.isEmpty(nickname)) {
                throw new GlobalException(CodeMsg.IMPORT_FIELD_IS_EAMPTY.fillArgs(rowNo, "昵称"));
            }
            if (nickname.length() > 20 || nickname.length() < 2) {
                throw new GlobalException(CodeMsg.IMPORT_FIELD_FORMAT_ERROR.fillArgs(rowNo, "昵称"));
            }

            // 4.校验Email
            String email = userExcel.getEmail();
            if (StringUtils.isEmpty(email)) {
                throw new GlobalException(CodeMsg.IMPORT_FIELD_IS_EAMPTY.fillArgs(rowNo, "邮箱"));
            }
            if (!EMAIL_PATTERN.matcher(email).matches()) {
                throw new GlobalException(CodeMsg.IMPORT_FIELD_FORMAT_ERROR.fillArgs(rowNo, "邮箱"));
            }

            // 5.校验状态
            String status = userExcel.getStatus();
            if (StringUtils.isEmpty(status)) {
                throw new GlobalException(CodeMsg.IMPORT_FIELD_IS_EAMPTY.fillArgs(rowNo, "状态"));
            }
            if (!"0".equals(status) && !"1".equals(status)) {
                throw new GlobalException(CodeMsg.IMPORT_FIELD_FORMAT_ERROR.fillArgs(rowNo, "状态"));
            }

            // 6.校验注册时间
            String createdTime = userExcel.getCreatedTime();
            if (StringUtils.isEmpty(createdTime)) {
                throw new GlobalException(CodeMsg.IMPORT_FIELD_IS_EAMPTY.fillArgs(rowNo, "注册时间"));
            }
            try {
                DATE_TIME_FORMAT.parse(createdTime);
            } catch (ParseException e) {
                throw new GlobalException(CodeMsg.IMPORT_FIELD_FORMAT_ERROR.fillArgs(rowNo, "注册时间"));
            }
        }
    }

    /**
     * userExcelList转成userList
     *
     * @param userExcelList
     * @return
     */
    private List<User> userExcelList2UserList(List<UserExcel> userExcelList) throws ParseException {
        Date now = new Date();
        List<User> userList = new ArrayList<>();
        for (UserExcel userExcel : userExcelList) {
            User user = User.builder()
                    .username(userExcel.getUsername())
                    .password(userExcel.getPassword())
                    .nickname(userExcel.getNickname())
                    .email(userExcel.getEmail())
                    .avatar(userExcel.getAvatar())
                    .status(Integer.valueOf(userExcel.getStatus()))
                    .createdTime(DATE_TIME_FORMAT.parse(userExcel.getCreatedTime())).build();

            user.setCreatedBy("import");
            user.setUpdatedBy("import");
            user.setUpdatedTime(now);
            userList.add(user);
        }
        return userList;
    }

}
