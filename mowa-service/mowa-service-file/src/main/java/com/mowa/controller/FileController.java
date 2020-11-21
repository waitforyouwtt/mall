package com.mowa.controller;

import com.mowa.Result;
import com.mowa.file.DownloadFile;
import com.mowa.file.FastDFSClient;
import com.mowa.file.FastDFSFile;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2020/11/21 12:43
 * @Version: 1.0
 * @Email: 15290810931@163.com
 */
@RestController
@RequestMapping("/file")
public class FileController {

    @Value( "${md5.password}" )
    private String md5;

    /**
     * 文件上传
     * @param file
     * @param author
     * @return
     */
    @PostMapping("/upload")
    public Result uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("author") String author){
        try{
            //判断文件是否存在
            if (file == null){
                throw new RuntimeException("文件不存在");
            }
            //获取文件的完整名称
            String originalFilename = file.getOriginalFilename();
            if (StringUtils.isEmpty(originalFilename)){
                throw new RuntimeException("文件不存在");
            }
            //获取文件的扩展名称  abc.jpg   jpg
            String extName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
            //获取文件内容
            byte[] content = file.getBytes();

            //创建文件上传的封装实体类
            FastDFSFile fastDFSFile = new FastDFSFile(originalFilename,content,extName);
            fastDFSFile.setMd5( md5 );
            fastDFSFile.setAuthor( author );

            //基于工具类进行文件上传,并接受返回参数  String[]
            String[] uploadResult = FastDFSClient.upload(fastDFSFile);

            //封装返回结果
            String url = FastDFSClient.getTrackerUrl()+uploadResult[0]+"/"+uploadResult[1];
            return new Result(true,200,"文件上传成功",url);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new Result(false, 400,"文件上传失败");
    }
    /**
     * 查看文件
     */
    @GetMapping("/getFile")
    public Result getFile(@RequestParam("group") String group,@RequestParam("dir") String dir){
        return Result.success( FastDFSClient.getFile( group,dir ) );
    }

    /**
     * 下载文件
     * @param downloadFile
     * @return
     */
    @PostMapping("/downloadFile")
    public Result downloadFile(@RequestBody DownloadFile downloadFile){
        if (Objects.isNull(downloadFile)){
            return Result.error("下载失败，参数为空");
        }
        if (StringUtils.isEmpty( downloadFile.getPath() )){
            String user = System.getProperty("user.name");
            downloadFile.setPath( "C:\\Users\\"+user +"\\Downloads\\" );
        }
        if (StringUtils.isEmpty( downloadFile.getName() )){
            downloadFile.setName( randomFileName() );
        }
        InputStream is = FastDFSClient.downFile( downloadFile.getGroup(),downloadFile.getDir() );
        try{
            FileOutputStream os = new FileOutputStream( downloadFile.getPath()+downloadFile.getName() );
            //定义一个缓冲区
            byte[] buffer = new byte[1024];
            while (is.read(buffer) != -1){
                os.write( buffer );
            }
            os.flush();
            os.close();
            is.close();
            return Result.success(  );
        }catch (Exception e){
            return Result.error( "文件下载失败" );
        }
    }

    /**
     * 删除文件
     * @return
     */
    @PostMapping( "/delete" )
    public Result delete(@RequestParam("group") String group,@RequestParam("dir") String dir){
        try{
           FastDFSClient.deleteFile( group,dir );
        }catch (Exception e){
            e.printStackTrace();
            return Result.error( "删除文件失败");
        }
        return Result.success();
    }

    private String randomFileName(){
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(currentTime);
        String random = RandomStringUtils.randomAlphanumeric(8);
        return dateString+"_"+random+".jpg";
    }

}
