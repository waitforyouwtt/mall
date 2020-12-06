package com.mowa.search.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * @author 凤凰小哥哥
 * @date 2020-12-06
 */
@Data
public class SkuInfo implements Serializable {

    @ApiModelProperty(value = "主键id")
    private Long id;

    @ApiModelProperty(value = "sku名称")
    private String name;

    @ApiModelProperty(value = "商品价格：元")
    private Long price;

    @ApiModelProperty(value = "库存数量")
    private Integer num;

    @ApiModelProperty(value = "商品图片")
    private String image;

    @ApiModelProperty(value = "商品状态：0正常，1 下架 2，删除")
    private String status;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "是否默认")
    private String isDefault;

    @ApiModelProperty(value = "spuId")
    private Long spuId;

    @ApiModelProperty(value = "类目id")
    private Long categoryId;

    @ApiModelProperty(value = "类目名称")
    private String categoryName;

    @ApiModelProperty(value = "品牌名称")
    private String brandName;

    @ApiModelProperty(value = "规格")
    private String spec;

    @ApiModelProperty(value = "规格参数")
    private Map<String,Object> specMap;

}
