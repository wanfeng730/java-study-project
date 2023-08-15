package serein.wanfeng.json;

import com.alibaba.fastjson2.JSONObject;
import org.junit.Test;

/**
 * @Date: 2023-08-09 10:30
 * @Author: luozh
 * @Description:
 */

public class FastJsonAPITest {

    private String json = "{\n" +
            "  \"id\": 1,\n" +
            "  \"name\": \"lzh730\",\n" +
            "  \"unit\":{\n" +
            "    \"name\": \"amberdata\",\n" +
            "    \"code\": \"AM\",\n" +
            "    \"array\": [\n" +
            "      {\"id\": 3, \"code\": 4},\n" +
            "      {\"id\": 6, \"code\": 8}\n" +
            "      \n" +
            "    ],\n" +
            "    \"type\": {\n" +
            "      \"name\": \"互联网软件\",\n" +
            "      \"code\": \"IT\"\n" +
            "    }\n" +
            "  }\n" +
            "}";

    @Test
    public void analysisJsonObjectDataStructure(){
        //json文本解析为JSONObject对象
        JSONObject jsonObject = JSONObject.parseObject(json);
        //获取json数据
        Object name = jsonObject.get("name");
        JSONObject unit = (JSONObject) jsonObject.get("unit");
        System.out.println("please set breakpoint in this line");

        //修改json数据
        unit.fluentPut("name", "安铂数据");
        //添加json数据
        unit.fluentPut("create_time", "2018");
        String jsonString = jsonObject.toJSONString();
        System.out.println("please set breakpoint in this line");
    }
}
