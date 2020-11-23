package com.mowa.goods.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2020/11/22 14:25
 * @Version: 1.0
 * @Email: 15290810931@163.com
 */
@Table(name = "tb_template")
@Data
public class Template implements Serializable {

    @ApiModelProperty(value = "id")
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ApiModelProperty(value = "模板名称")
    @Column(name = "name")
    private String name;

    @ApiModelProperty(value = "规格数量")
    @Column(name = "spec_num")
    private Integer specNum;

    @ApiModelProperty(value = "参数数量")
    @Column(name = "para_num")
    private Integer paraNum;
}
