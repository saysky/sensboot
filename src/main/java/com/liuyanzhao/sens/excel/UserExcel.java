package com.liuyanzhao.sens.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 言曌
 * @date 2020-01-02 11:39
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@HeadRowHeight(value = 40)
public class UserExcel {

    /**
     * 用户名
     */
    @ExcelProperty(value = "用户名", index = 0)
    @ColumnWidth(value = 15)
    private String username;

    /**
     * 显示名称
     */
    @ExcelProperty(value = "昵称", index = 1)
    @ColumnWidth(value = 15)
    private String nickname;

    /**
     * 密码
     */
    @ExcelProperty(value = "密码", index = 2)
    @ColumnWidth(value = 20)
    private String password;

    /**
     * 邮箱
     */
    @ExcelProperty(value = "邮箱", index = 3)
    @ColumnWidth(value = 20)
    private String email;

    /**
     * 头像
     */
    @ExcelProperty(value = "头像", index = 4)
    @ColumnWidth(value = 20)
    private String avatar;

    /**
     * 0 正常
     * 1 禁用
     */
    @ExcelProperty(value = "状态\r0正常,1 禁用", index = 5)
    @ColumnWidth(value = 20)
    private String status;

    /**
     * 注册时间 yyyy-MM-dd HH:mm:ss格式
     */
    @ExcelProperty(value = "注册时间", index = 6)
    @ColumnWidth(value = 20)
    private String createdTime;
}
