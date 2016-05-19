package kaze.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.List;

public class FastJsonTest {
    public static void main(String[] args) {
        String json = "{\"test1\":\"null\", \"test2\":\"hahah\"}";
//        List<HashMap> res = JSON.parseArray(json, HashMap.class);
        JSONObject jo =JSON.parseObject(json);
        System.out.println(jo);

    }
}
