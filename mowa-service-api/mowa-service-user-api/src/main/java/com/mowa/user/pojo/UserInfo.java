package com.mowa.user.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2020/11/22 17:12
 * @Version: 1.0
 * @Email: 15290810931@163.com
 */
@ApiModel(description = "User",value = "User")
@Data
@Table(name="tb_user")
public class UserInfo implements Serializable{

	@ApiModelProperty(value = "id",required = false)
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private Integer id;

	@ApiModelProperty(value = "用户id",required = false)
    @Column(name = "user_id")
	private String userId;

	@ApiModelProperty(value = "用户名",required = false)
    @Column(name = "username")
	private String username;

	@ApiModelProperty(value = "密码，加密存储",required = false)
    @Column(name = "password")
	private String password;

	@ApiModelProperty(value = "真实姓名",required = false)
    @Column(name = "name")
	private String name;

	@ApiModelProperty(value = "昵称",required = false)
    @Column(name = "nick_name")
	private String nickName;

	@ApiModelProperty(value = "注册手机号",required = false)
    @Column(name = "phone")
	private String phone;

	@ApiModelProperty(value = "注册邮箱",required = false)
    @Column(name = "email")
	private String email;

	@ApiModelProperty(value = "会员来源：1:PC，2：H5，3：Android，4：IOS",required = false)
    @Column(name = "source_type")
	private String sourceType;

	@ApiModelProperty(value = "使用状态（1正常 0非正常）",required = false)
    @Column(name = "status")
	private String status;

	@ApiModelProperty(value = "头像地址",required = false)
    @Column(name = "head_pic")
	private String headPic;

	@ApiModelProperty(value = "QQ号码",required = false)
    @Column(name = "qq")
	private String qq;

	@ApiModelProperty(value = "手机是否验证 （0否  1是）",required = false)
    @Column(name = "is_mobile_check")
	private String isMobileCheck;

	@ApiModelProperty(value = "邮箱是否检测（0否  1是）",required = false)
    @Column(name = "is_email_check")
	private String isEmailCheck;

	@ApiModelProperty(value = "性别，1男，0女",required = false)
    @Column(name = "gender")
	private String gender;

	@ApiModelProperty(value = "会员等级",required = false)
    @Column(name = "user_level")
	private Integer userLevel;

	@ApiModelProperty(value = "积分",required = false)
    @Column(name = "points")
	private Integer points;

	@ApiModelProperty(value = "经验值",required = false)
    @Column(name = "experience_value")
	private Integer experienceValue;

	@ApiModelProperty(value = "出生年月日",required = false)
    @Column(name = "birthday")
	private Date birthday;

	@ApiModelProperty(value = "创建时间",required = false)
    @Column(name = "create_time")
	private Date createTime;

	@ApiModelProperty(value = "修改时间",required = false)
    @Column(name = "update_time")
	private Date updateTime;

	@ApiModelProperty(value = "最后登录时间",required = false)
    @Column(name = "last_login_time")
	private Date lastLoginTime;
}
