package com.mowa.goods.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2020/11/22 13:47
 * @Version: 1.0
 * @Email: 15290810931@163.com
 */
@Table(name = "tb_category")
@Data
public class Category implements Serializable {

    @ApiModelProperty(value = "分类id")
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ApiModelProperty(value = "分类名称")
    @Column(name = "name")
    private String name;

    @ApiModelProperty(value = "商品数量")
    @Column(name = "goods_num")
    private Integer goodsNum;

    @ApiModelProperty(value = "是否显示")
    @Column(name = "is_show")
    private String isShow;

    @ApiModelProperty(value = "是否导航")
    @Column(name = "is_menu")
    private String isMenu;

    @ApiModelProperty(value = "排序")
    @Column(name = "seq")
    private Integer seq;

    @ApiModelProperty(value = "上级ID")
    @Column(name = "parent_id")
    private Integer parentId;

    @ApiModelProperty(value = "模板ID")
    @Column(name = "template_id")
    private Integer templateId;
}
