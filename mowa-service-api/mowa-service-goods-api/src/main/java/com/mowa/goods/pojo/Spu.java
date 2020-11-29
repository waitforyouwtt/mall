package com.mowa.goods.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2020/11/22 14:13
 * @Version: 1.0
 * @Email: 15290810931@163.com
 */
@Table(name = "tb_spu")
@Data
public class Spu implements Serializable {

    @ApiModelProperty(value = "分类id")
    @Id
    @Column(name = "id")
    private String id;

    @ApiModelProperty(value = "货号")
    @Column(name = "sn")
    private String sn;

    @ApiModelProperty(value = "SPU名")
    @Column(name = "name")
    private String name;

    @ApiModelProperty(value = "副标题")
    @Column(name = "caption")
    private String caption;

    @ApiModelProperty(value = "品牌ID")
    @Column(name = "brand_id")
    private Integer brandId;

    @ApiModelProperty(value = "一级分类")
    @Column(name = "category1_id")
    private Integer category1Id;

    @ApiModelProperty(value = "二级分类")
    @Column(name = "category2_id")
    private Integer category2Id;

    @ApiModelProperty(value = "三级分类")
    @Column(name = "category3_id")
    private Integer category3Id;

    @ApiModelProperty(value = "模板ID")
    @Column(name = "template_id")
    private Integer templateId;

    @ApiModelProperty(value = "运费模板id")
    @Column(name = "freight_id")
    private Integer freightId;

    @ApiModelProperty(value = "图片")
    @Column(name = "image")
    private String image;

    @ApiModelProperty(value = "图片列表")
    @Column(name = "images")
    private String images;

    @ApiModelProperty(value = "售后服务")
    @Column(name = "sale_service")
    private String saleService;

    @ApiModelProperty(value = "介绍")
    @Column(name = "introduction")
    private String introduction;

    @ApiModelProperty(value = "规格列表")
    @Column(name = "spec_items")
    private String specItems;

    @ApiModelProperty(value = "参数列表")
    @Column(name = "para_items")
    private String paraItems;

    @ApiModelProperty(value = "销量")
    @Column(name = "sale_num")
    private Integer saleNum;

    @ApiModelProperty(value = "评论数")
    @Column(name = "comment_num")
    private Integer commentNum;

    @ApiModelProperty(value = "是否上架")
    @Column(name = "is_marketable")
    private String isMarketAble;

    @ApiModelProperty(value = "是否启用规格")
    @Column(name = "is_enable_spec")
    private String isEnableSpec;

    @ApiModelProperty(value = "是否删除")
    @Column(name = "is_delete")
    private String isDelete;

    @ApiModelProperty(value = "审核状态")
    @Column(name = "status")
    private String status;
}
