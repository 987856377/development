package com.spring.development.module.file;

import com.spring.development.common.ResultCode;
import com.spring.development.common.ResultJson;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
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
    private String path;

    @RequestMapping(value = "upload", method = RequestMethod.POST)
    public ResultJson upload(MultipartFile[] files) {
        if (files==null){
            return ResultJson.failure(ResultCode.BAD_REQUEST);
        }
        for (MultipartFile file:files) {
            File dir = new File(path);
            if (!dir.exists()){
                dir.mkdir();
            }
            String fileName = file.getOriginalFilename();
            String saveFileName = new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"@"+fileName;

            File upFile = new File(dir+"/"+saveFileName);
            try {
                file.transferTo(upFile);
            } catch (IOException e) {
                e.printStackTrace();
                return ResultJson.failure(ResultCode.INTERNAL_SERVER_ERROR);
            }
        }
        return ResultJson.success();
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
            map.put(file.getName(), filename);
        }
    }

    @RequestMapping(value = "downloadList",method = RequestMethod.GET)
    public ResultJson<Map> downloadList(){
        Map<String, Object> map = new HashMap<>();

        File dir = new File(path);

        getFiles(dir,map);

        return ResultJson.success(map);
    }

    @RequestMapping(value = "download",method = RequestMethod.GET,produces = "text/html;charset=utf-8")
    public ResponseEntity<byte[]> download(String filename) throws IOException{
        if (filename.equals(null) || filename == ""){
            return null;
        }
        File file = new File(path+File.separator+filename);

        HttpHeaders headers = new HttpHeaders();
        String downloadFileName = new String(filename.getBytes("UTF-8"),"iso-8859-1");

        headers.setContentDispositionFormData("attachment", downloadFileName);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),headers, HttpStatus.CREATED);
    }
}
