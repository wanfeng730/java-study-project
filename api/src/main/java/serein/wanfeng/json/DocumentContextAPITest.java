package serein.wanfeng.json;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;
import org.junit.Test;
import serein.wanfeng.util.JSONUtils;

/**
 * @date: 2023-12-01 17:43
 * @author: luozh
 * @description:
 * @since:
 */
public class DocumentContextAPITest {

    public static final String METADATA_JSON_RESOURCE_PATH = "jsonfile/metadata.json";

    @Test
    public void testJsonDataGet() {
        String jsonContent = JSONUtils.getJsonStringFormResourceFile(METADATA_JSON_RESOURCE_PATH);

        DocumentContext documentContext = JsonPath.parse(jsonContent);
        //获取record.block下的所有信息 JSONArray
        Object block = documentContext.read("$.record.block");

        //获取所有key为title的值
        Object allBlock = documentContext.read("$..title");

        //获取block数组中name为“归档信息”的对象
        Object guidangObject = documentContext.read("$.record.block[?(@.name == '归档信息')].*");

        //遍历list（file数组）下的所有map
        JSONArray blockFile = documentContext.read("$..block[?(@.name == '电子文件')].block..file");
        Integer size = blockFile.size();
        documentContext.put("$..block[?(@.name == '电子文件')].block..file[0:" + size + "]", "test", true);


        System.out.println(0);
    }
}
