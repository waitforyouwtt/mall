package com.mowa.content.pojo;

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
@ApiModel(description = "Content",value = "Content")
@Data
@Table(name="tb_content")
public class Content implements Serializable{

	@ApiModelProperty(value = "id",required = false)
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private Integer id;

	@ApiModelProperty(value = "内容类目ID",required = false)
    @Column(name = "category_id")
	private Integer categoryId;

	@ApiModelProperty(value = "内容标题",required = false)
    @Column(name = "title")
	private String title;

	@ApiModelProperty(value = "链接",required = false)
    @Column(name = "url")
	private String url;

	@ApiModelProperty(value = "图片路径",required = false)
    @Column(name = "pic")
	private String pic;

	@ApiModelProperty(value = "状态：0无效 1 有效",required = false)
    @Column(name = "status")
	private Integer status;

	@ApiModelProperty(value = "排序",required = false)
    @Column(name = "sort_order")
	private Integer sortOrder;

	@ApiModelProperty(value = "创建人",required = false)
    @Column(name = "create_by")
	private String createBy;

	@ApiModelProperty(value = "修改人",required = false)
    @Column(name = "update_by")
	private String updateBy;

	@ApiModelProperty(value = "创建时间",required = false)
    @Column(name = "create_time")
	private Date createTime;

	@ApiModelProperty(value = "更新时间",required = false)
    @Column(name = "update_time")
	private Date updateTime;

}
