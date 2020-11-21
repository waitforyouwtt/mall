package com.mowa.file;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2020/11/21 12:30
 * @Version: 1.0
 * @Email: 15290810931@163.com
 */
@Data
public class FastDFSFile implements Serializable {

    @ApiModelProperty(value = "文件名字")
    private String name;

    @ApiModelProperty(value = "文件内容")
    private byte[] content;

    @ApiModelProperty(value = "文件扩展名")
    private String ext;

    @ApiModelProperty(value = "文件MD5摘要值")
    private String md5;

    @ApiModelProperty(value = "文件创建作者")
    private String author;

    public FastDFSFile(String name, byte[] content, String ext, String author) {
        super();
        this.name = name;
        this.content = content;
        this.ext = ext;
        this.author = author;
    }

    public FastDFSFile(String name, byte[] content, String ext) {
        super();
        this.name = name;
        this.content = content;
        this.ext = ext;
    }
}
