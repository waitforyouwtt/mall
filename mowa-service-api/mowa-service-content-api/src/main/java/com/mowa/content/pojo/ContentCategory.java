package com.mowa.content.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2020/11/22 17:12
 * @Version: 1.0
 * @Email: 15290810931@163.com
 */
@ApiModel(description = "ContentCategory",value = "ContentCategory")
@Data
@Table(name="tb_content_category")
public class ContentCategory implements Serializable{

	@ApiModelProperty(value = "",required = false)
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private Integer id;

	@ApiModelProperty(value = "分类名称",required = false)
    @Column(name = "name")
	private String name;

}
