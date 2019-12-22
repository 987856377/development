package com.spring.development.module.file;

import com.spring.development.common.ResultCode;
import com.spring.development.common.ResultJson;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @Project development
 * @Package com.spring.development.module.user.controller
 * @Author xuzhenkui
 * @Date 2019/9/19 9:33
 */
@RestController
@RequestMapping("file")
public class FileController {

    @Value("${spring.servlet.multipart.location}")
    private String UPLOADED_PATH;

    @RequestMapping(value = "upload", method = RequestMethod.POST)
    public ResultJson upload(MultipartFile[] files) {
        if (files==null){
            return ResultJson.failure(ResultCode.BAD_REQUEST);
        }
        File dir = new File(UPLOADED_PATH);
        if (!dir.exists()){
            dir.mkdir();
        }
        for (MultipartFile file:files) {
            String fileName = new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"@"+file.getOriginalFilename();
            File upFile = new File(dir + fileName);
            try {
                file.transferTo(upFile);
            } catch (IOException e) {
                e.printStackTrace();
                return ResultJson.failure(ResultCode.INTERNAL_SERVER_ERROR);
            }
        }
        return ResultJson.success();
    }

    @RequestMapping(value = "/singleFileUpload", method = RequestMethod.POST)
    public ResultJson singleFileUpload(MultipartFile file) {
        if (file.isEmpty()) {
            return ResultJson.failure(ResultCode.BAD_REQUEST);
        }
        // Get the file and save it somewhere
        String fileName = new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"@"+file.getOriginalFilename();
        Path path = Paths.get(UPLOADED_PATH + fileName);
        try {
            Files.write(path, file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
            return ResultJson.failure(ResultCode.INTERNAL_SERVER_ERROR);
        }
        return ResultJson.success();
    }

    @RequestMapping(value = "/mulFileUpload", method = RequestMethod.POST)
    public ResultJson mulFileUpload(MultipartFile[] files) {
        if (files == null) {
            return ResultJson.failure(ResultCode.BAD_REQUEST);
        }
        for (MultipartFile file:files) {
            // Get the file and save it somewhere
            String fileName = new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"@"+file.getOriginalFilename();
            Path path = Paths.get(UPLOADED_PATH + fileName);
            try {
                Files.write(path, file.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
                return ResultJson.failure(ResultCode.INTERNAL_SERVER_ERROR);
            }
        }
        return ResultJson.success();
    }

    @RequestMapping(value = "fileList",method = RequestMethod.GET)
    public ResultJson downloadFileList() throws IOException {
        return ResultJson.success(Files.list(Paths.get(UPLOADED_PATH)));
    }

    @RequestMapping(value = "downloadFile",method = RequestMethod.GET)
    public ResultJson downloadFile(@RequestParam("filename") String filename) throws IOException {
        return ResultJson.success(Files.readAllBytes(Paths.get(UPLOADED_PATH + File.separator+filename)));
    }

    public void getFiles(File file, Map<String, Object> map){
        if(!file.isFile()){
            File[] files = file.listFiles();
            for(File f : files){
                getFiles(f,map);
            }
        }
        else{
            String filename = file.getName().substring(file.getName().lastIndexOf("@")+1);
            map.put(filename, file.getName());
        }
    }

    @RequestMapping(value = "downloadList",method = RequestMethod.GET)
    public ResultJson<Map> downloadList(){
        Map<String, Object> map = new HashMap<>();

        File dir = new File(UPLOADED_PATH);

        getFiles(dir,map);

        return ResultJson.success(map);
    }

    @RequestMapping(value = "download",method = RequestMethod.GET,produces = "text/html;charset=utf-8")
    public ResponseEntity<byte[]> download(@RequestParam("filename") String filename) throws IOException{
        if (filename == null || "".equals(filename)){
            return null;
        }
        File file = new File(UPLOADED_PATH + File.separator+filename);

        HttpHeaders headers = new HttpHeaders();
        String downloadFileName = new String(filename.getBytes("UTF-8"),"iso-8859-1");

        headers.setContentDispositionFormData("attachment", downloadFileName);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),headers, HttpStatus.CREATED);
    }
}
