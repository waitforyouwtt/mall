package com.mowa.goods.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2020/11/22 14:09
 * @Version: 1.0
 * @Email: 15290810931@163.com
 */
@Table(name = "tb_spec")
@Data
public class Spec implements Serializable {

    @ApiModelProperty(value = "id")
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ApiModelProperty(value = "名称")
    @Column(name = "name")
    private String name;

    @ApiModelProperty(value = "规格选项")
    @Column(name = "options")
    private String options;

    @ApiModelProperty(value = "排序")
    @Column(name = "seq")
    private Integer seq;

    @ApiModelProperty(value = "模板ID")
    @Column(name = "template_id")
    private Integer templateId;
}
