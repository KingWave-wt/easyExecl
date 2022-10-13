package com.example.easyexecldemo.controller;

import com.alibaba.excel.util.FileUtils;
import com.example.easyexecldemo.po.ScoreData;
import com.example.easyexecldemo.utils.DefaultExcelListener;
import com.example.easyexecldemo.utils.EasyExcelUtil;
import com.example.easyexecldemo.utils.FileUtil;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.fileupload.FileItem;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

@Api(tags = "文件导入导出") //  tags：你可以当作是这个组的名字。
@RequestMapping("/execl")
@RestController
@Slf4j
public class RoleController {

    @PostMapping("/import")
    public void importUser(@RequestPart("file") MultipartFile multipartFile1) throws Exception {
        //MultipartFileToFile转File
        File file = FileUtil.MultipartFileToFile(multipartFile1);

        //File转MultipartFileToFile,pom引入commons-fileupload包
        FileItem fileItem = FileUtil.getMultipartFile(file, "tempFileItem");
        MultipartFile multipartFile2 = new CommonsMultipartFile(fileItem);

        //读取File文件
        EasyExcelUtil.asyncReadModel(file, new DefaultExcelListener<>(), ScoreData.class, 0, 1);

    }

    @PostMapping("/download")
    public void download(HttpServletRequest request, HttpServletResponse response) throws IOException {
        File file = new File("C:\\Users\\16138\\Desktop\\2.xls");
        writeResponse(file,response);
    }



    public static void writeResponse(File file, HttpServletResponse response) throws IOException {
        OutputStream out = null;
        try {
            response.reset();
            response.setContentType("application/octet-stream");
            response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(file.getName(), "UTF-8"));
            long fileLength = file.length();
            String length = String.valueOf(fileLength);
            response.setHeader("Content_Length", length);
            response.setHeader("code", "0");
            out = response.getOutputStream();
            out.write(FileUtils.readFileToByteArray(file));
            out.flush();
        } catch (IOException e) {
            log.info("文件下载，操作结果返回","异常",e);
            throw e;
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    log.info("文件下载", "下载异常", e);
                    throw e;
                }
            }
        }
    }
}