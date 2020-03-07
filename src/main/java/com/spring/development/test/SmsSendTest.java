package com.spring.development.test;

import com.alibaba.fastjson.JSONObject;
import com.spring.development.util.HttpUtil;

/**
 * @Description
 * @Project development
 * @Package com.spring.development.test
 * @Author xuzhenkui
 * @Date 2020/3/7 17:33
 */
public class SmsSendTest {

    public static void main(String[] args) {

        String url = "http://127.0.0.1:8090/pcp/organization/getOrgByName";

        JSONObject param = new JSONObject();
        param.put("name","潍坊市人民医院");
//            JSONArray jsonArray = new JSONArray();
//            jsonArray.add(param);

        JSONObject jsonObject1 = HttpUtil.doPostJSONRequest(url, param);
        System.out.println(jsonObject1.toJSONString());

        String post = cn.hutool.http.HttpUtil.post(url, param.toJSONString());
        System.out.println(post);
    }

}
