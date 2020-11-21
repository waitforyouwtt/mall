package com.mowa.file;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2020/11/21 13:21
 * @Version: 1.0
 * @Email: 15290810931@163.com
 */
@Data
public class DownloadFile {

    @ApiModelProperty("下载保存的本地路径")
    private String path;

    @ApiModelProperty("下载保存到本地的文件命名")
    private String name;

    @ApiModelProperty("下载远程服务器的group")
    private String group;

    @ApiModelProperty("下载远程服务器的目录：M00/00/00/aaa.jpg")
    private String dir;
}
