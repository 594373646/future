package com.lgl.amap.utils;

import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.common.core.build.MyMap;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * 发起请求工具类
 *
 * @author lgl
 * @Date 2023/9/13 1:35
 */
public class ApiCaller {

    public ApiCaller() {

    }

    /**
     * 发起 GET 请求
     *
     * @param url         请求的 URL
     * @param queryParams 请求的参数
     * @return 请求结果的字符串表示
     */
    public static JSONObject requestGet(String url, Map<String, Object> queryParams) {
        RestTemplate restTemplate = new RestTemplate();
        String res = restTemplate.getForObject(url, String.class, queryParams);
        return JSONObject.parseObject(res);
    }

    /**
     * 发起 POST 请求
     *
     * @param url         请求的 URL
     * @param queryParams 请求的参数
     * @return 请求结果的字符串表示
     */
    public static JSONObject requestPost(String url, Map<String, Object> queryParams) {
        RestTemplate restTemplate = new RestTemplate();
        // 设置请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        // 设置请求体
        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(queryParams, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(url, requestEntity, String.class);
        return JSONObject.parseObject(response.getBody());
    }

    public static void main(String[] args) {
        new MyMap();
    }
}
