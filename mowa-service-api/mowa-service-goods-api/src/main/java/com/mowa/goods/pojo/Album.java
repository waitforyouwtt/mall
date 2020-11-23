package com.mowa.goods.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2020/11/21 17:19
 * @Version: 1.0
 * @Email: 15290810931@163.com
 */
@Table(name = "tb_album")
@Data
public class Album implements Serializable {

    @ApiModelProperty(value = "相册编号id")
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ApiModelProperty(value = "相册名称")
    @Column(name = "title")
    private String title;

    @ApiModelProperty(value = "相册封面")
    @Column(name = "image")
    private String image;

    @ApiModelProperty(value = "图片列表")
    @Column(name = "image_items")
    private String imageItems;
}
