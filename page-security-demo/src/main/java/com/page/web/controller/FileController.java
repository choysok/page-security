package com.page.web.controller;

import com.page.domain.FileInfo;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;

@RestController
@RequestMapping("/file")
public class FileController {



     //文件夹  文件夹   文件夹  文件夹路径
     private   String folderPath = "C:\\Page\\IDEA\\Workspace\\page-security\\page-security-demo\\src\\main\\java\\com\\page\\web\\controller";


        //文件的上传
       @PostMapping
       public FileInfo upload(MultipartFile multipartFile) throws Exception {

           String multipartFileName = multipartFile.getName();
           String originalFilename = multipartFile.getOriginalFilename();
           long size = multipartFile.getSize();
           System.out.println("MultipartFile接口的实例化对象的名称（也是作为方法的形参）:"+multipartFileName+"\n"+"文件原始名字:"+originalFilename+"\n"+"原始文件大小:"+size);


           //InputStream multipartFileInputStream = multipartFile.getInputStream();

           //文件  文件  文件  文件路径 和 文件名字
           File localFilePath =  new File(folderPath,new Date().getTime()+".txt");

           //上传的文件传输到指定的路径（已指定 路径 和 文件名称）
           multipartFile.transferTo(localFilePath);

           FileInfo fileInfo =  new FileInfo(localFilePath.getAbsolutePath());


           return fileInfo;
       }



       //文件的下载.  id 实际是时间戳
        @GetMapping("/{id}")
        public void  download(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) throws IOException {
           // 如果有这一行代码，会在此路径下创建一个文件，因为id是时间戳。这里是模拟要下载的文件的路径。每次重新打包也会产生新的文件。
           // File file = new File(folderPath, id + ".txt");
           // new File(folderPath, id + ".txt")

            //jdk7之后刘放在try括号后，JDK自动会关闭流。
            try (FileInputStream fileInputStream = new FileInputStream( new File(folderPath, id + ".txt"));
                 OutputStream outputStream = response.getOutputStream();) {
                response.setContentType("application/x-download");
                response.addHeader("Content-Disposition","attachment;filename=hello.txt");
                IOUtils.copy(fileInputStream,outputStream);
                outputStream.flush();

            }
        }
}
