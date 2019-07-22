package com.liuyanzhao.sens.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

/**
 * <pre>
 *     用户信息(MyBatisPlus+SpringDataJPA双支持)
 * </pre>
 *
 * @author : saysky
 * @date : 2017/11/14
 */
@Data
@TableName("user")
@Entity(name = "user")
public class User implements Serializable {

    private static final long serialVersionUID = -5144055068797033748L;

    /**
     * 编号，自增
     */
    @TableId(type = IdType.AUTO)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空")
    @Column(name = "user_name")
    private String userName;

    /**
     * 显示名称
     */
    @Column(name = "user_display_name")
    private String userDisplayName;

    /**
     * 密码
     */
    @Column(name = "user_pass")
    private String userPass;

    /**
     * 邮箱
     */
    @Email(message = "邮箱格式不正确")
    @Column(name = "user_email")
    private String userEmail;

    /**
     * 头像
     */
    @Column(name = "user_avatar")
    private String userAvatar;

    /**
     * 说明
     */
    @Column(name = "user_desc")
    private String userDesc;

    /**
     * 个人主页
     */
    @Column(name = "user_site")
    private String userSite;

    /**
     * 是否禁用登录
     */
    @Column(name = "login_enable")
    private String loginEnable = "true";

    /**
     * 最后一次登录时间
     */
    @Column(name = "login_last")
    private Date loginLast;

    /**
     * 登录错误次数记录
     */
    @Column(name = "login_error")
    private Integer loginError = 0;

    /**
     * 是否验证邮箱
     */
    @Column(name = "email_enable")
    private String emailEnable = "false";

    /**
     * 0 正常
     * 1 禁用
     * 2 已删除
     */
    @Column(name = "status")
    private Integer status = 0;

    /**
     * 注册时间
     */
    @Column(name = "create_time")
    private Date createTime;

}
