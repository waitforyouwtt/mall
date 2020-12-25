package com.mowa.page;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2020/12/8 22:06
 * @Version: 1.0
 * @Email: 15290810931@163.com
 */
@Data
public class EsPageResult<T> implements Serializable {

    @ApiModelProperty("总条数")
    private long total;

    @ApiModelProperty("当前页")
    private long pageNum;

    @ApiModelProperty("每页大小")
    private long pageSize;

    @ApiModelProperty("总页数")
    private long pages;

    @ApiModelProperty("返回数据集合")
    private List<T> list;

    public EsPageResult(List<T> list, long total, long pageSize, long pageNum) {
        this.list = list;
        this.total = total;
        this.pageSize = pageSize;
        this.pageNum = pageNum;
        this.pages = (long)Math.ceil((double)total / (double)pageSize);
    }

    public EsPageResult(){}
}
