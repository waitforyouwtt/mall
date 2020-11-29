package com.mowa.goods.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2020/11/28 19:55
 * @Version: 1.0
 * @Email: 15290810931@163.com
 */
@Data
public class Goods implements Serializable {

    @ApiModelProperty(value = "spu:商品公共属性")
    private Spu spu;

    @ApiModelProperty(value = "sku 商品私有属性")
    private List<Sku> skuList;
}
