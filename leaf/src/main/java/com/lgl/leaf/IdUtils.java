package com.lgl.leaf;


import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lgl
 * @Date 2023/9/15 2:11
 */
public class IdUtils {
    public static String getId() {
        return get();
    }

    /**
     * 发起 GET 请求
     */
    private static String get() {
        Map<String,Object> queryParams = new HashMap<>();
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject("http://localhost:8080/api/segment/get/leaf-segment-test", String.class, queryParams);
    }
}
