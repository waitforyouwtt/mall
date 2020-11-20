package com.mowa.goods.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2020/11/20 23:59
 * @Version: 1.0
 * @Email: 15290810931@163.com
 */
@Table(name="tb_brand")
@Data
public class Brand {

    @ApiModelProperty(value = "品牌id")
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ApiModelProperty(value = "品牌名称")
    @Column(name = "name")
    private String name;

    @ApiModelProperty(value = "品牌图片地址")
    @Column(name = "image")
    private String image;

    @ApiModelProperty(value = "品牌的首字母")
    @Column(name = "letter")
    private String letter;

    @ApiModelProperty(value = "品牌排序")
    @Column(name = "seq")
    private Integer seq;
}
