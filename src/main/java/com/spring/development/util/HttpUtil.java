package com.spring.development.util;

import com.alibaba.fastjson.JSONObject;
import com.spring.development.test.SmsSendTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @Description
 * @Project development
 * @Package com.spring.development.util
 * @Author xuzhenkui
 * @Date 2020/3/7 20:06
 */
public class HttpUtil {
    private final static Logger logger = LoggerFactory.getLogger(HttpUtil.class);

    public static JSONObject doPostJSONRequest(String url, JSONObject param) {
        JSONObject jsonObject;
        HttpURLConnection connection = null;
        PrintWriter printWriter = null;
        BufferedReader bReader = null;

        try {
            // 1.URL类封装了大量复杂的实现细节，这里将一个字符串构造成一个URL对象
            URL request = new URL(url);

            // 2.获取HttpURRLConnection对象
            connection = (HttpURLConnection) request.openConnection();

            // 3.调用connect方法连接远程资源
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestProperty("Content-Length", "" + param.size());
            connection.setRequestProperty("Content-Type","application/json;charset=UTF-8");
            connection.setRequestProperty("Authorization","Bearer eyJhbGciOiJIUzUxMiJ9.eyJyb2xlcyI6WyJBRE1JTiIsIkRCQSIsIkFVRElUIl0sInN1YiI6ImFkbWluIiwiZXhwIjoxNTgzNjYwMzcyfQ.Dwe0AvwvOJEHe9k8Cw0oxILt9ij4aJE_awTkMdx2NlSNzIchTfAhbEfE9n8EGDHpfY5Oar0_McY21ZtwpeTI4A");
            connection.connect();

            // 4.将json数据写入到输出流中以提交数据
            printWriter = new PrintWriter(connection.getOutputStream(),true);
            printWriter.print(param.toString());
            printWriter.flush();

            //获取响应状态
            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                logger.error("doPostJSONRequest: " + url + " " + param.toString() + ", connection failure");
                return null;
            }

            // 5.访问资源数据，使用getInputStream方法获取一个输入流用以读取信息
            bReader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream(), "UTF-8"));

            // 对数据进行访问
            String line;
            StringBuilder stringBuilder = new StringBuilder();
            while ((line = bReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            jsonObject = JSONObject.parseObject(stringBuilder.toString());
            return jsonObject;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            printWriter.close();
            // 关闭流
            try {
                bReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            // 关闭链接
            connection.disconnect();
        }
        return null;
    }
}
