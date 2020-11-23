package com.mowa.goods.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2020/11/22 13:58
 * @Version: 1.0
 * @Email: 15290810931@163.com
 */
@Table(name = "tb_sku")
@Data
public class Sku implements Serializable {

    @ApiModelProperty(value = "商品id")
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @ApiModelProperty(value = "商品条码")
    @Column(name = "sn")
    private String sn;

    @ApiModelProperty(value = "SKU名称")
    @Column(name = "name")
    private String name;

    @ApiModelProperty(value = "价格（分）")
    @Column(name = "price")
    private Integer price;

    @ApiModelProperty(value = "库存数量")
    @Column(name = "num")
    private Integer num;

    @ApiModelProperty(value = "库存预警数量")
    @Column(name = "alert_num")
    private Integer alertNum;

    @ApiModelProperty(value = "商品图片")
    @Column(name = "image")
    private String image;

    @ApiModelProperty(value = "商品图片列表")
    @Column(name = "images")
    private String images;

    @ApiModelProperty(value = "重量（克）")
    @Column(name = "weight")
    private Integer weight;

    @ApiModelProperty(value = "创建时间")
    @Column(name = "create_time")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    @Column(name = "update_time")
    private Date updateTime;

    @ApiModelProperty(value = "SPU_ID")
    @Column(name = "spu_id")
    private String spuId;

    @ApiModelProperty(value = "类目ID")
    @Column(name = "category_id")
    private Integer categoryId;

    @ApiModelProperty(value = "类目名称")
    @Column(name = "category_name")
    private String categoryName;

    @ApiModelProperty(value = "品牌名称")
    @Column(name = "brand_name")
    private String brandName;

    @ApiModelProperty(value = "规格")
    @Column(name = "spec")
    private String spec;

    @ApiModelProperty(value = "销量")
    @Column(name = "sale_num")
    private Integer saleNum;

    @ApiModelProperty(value = "评论数")
    @Column(name = "comment_num")
    private Integer commentNum;

    @ApiModelProperty(value = "商品状态 1-正常，2-下架，3-删除")
    @Column(name = "status")
    private String status;
}
