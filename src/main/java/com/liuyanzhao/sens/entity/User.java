package com.liuyanzhao.sens.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

/**
 * <pre>
 *     博主信息
 * </pre>
 *
 * @author : saysky
 * @date : 2017/11/14
 */
@Data
@TableName("sens_user")
public class User implements Serializable {

    private static final long serialVersionUID = -5144055068797033748L;

    /**
     * 编号
     */
    @TableId(type = IdType.AUTO)
    private Long userId;

    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空")
    private String userName;

    /**
     * 显示名称
     */
    private String userDisplayName;

    /**
     * 密码
     */
    private String userPass;

    /**
     * 邮箱
     */
    @Email(message = "邮箱格式不正确")
    private String userEmail;

    /**
     * 头像
     */
    private String userAvatar;

    /**
     * 说明
     */
    private String userDesc;

    /**
     * 个人主页
     */
    private String userSite;

    /**
     * 是否禁用登录
     */
    private String loginEnable = "true";

    /**
     * 最后一次登录时间
     */
    private Date loginLast;

    /**
     * 登录错误次数记录
     */
    private Integer loginError = 0;

    /**
     * 是否验证邮箱
     */
    private String emailEnable = "false";

    /**
     * 0 正常
     * 1 禁用
     * 2 已删除
     */
    private Integer status = 0;

    /**
     * 注册时间
     */
    private Date createTime;

}
