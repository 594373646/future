package com.lgl;

import com.ruoyi.common.core.build.MyMap;
import com.lgl.amap.utils.ApiCaller;
import org.junit.Test;

import static com.lgl.amap.constant.Urls.DISTRICT;

/**
 * @author lgl
 * @Date 2023/9/15 0:36
 */
public class doTest {
    @Test
    public void test(){
        MyMap myMap = new MyMap();
        /*myMap.put("city", "110101");
        JSONObject jsonObject = ApiCaller.requestGet(WEATHER, myMap);
        System.out.println(jsonObject);*/
        System.out.println(ApiCaller.requestGet(DISTRICT, myMap));
    }
}
