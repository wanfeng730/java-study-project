package serein.wanfeng.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.DefaultResourceLoader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @date: 2023-12-01 17:44
 * @author: luozh
 * @description:
 * @since:
 */
public class JSONUtils {

    /**
     * 从resource文件中获取json字符串
     * @param resourceFilePath 资源文件路径
     * @return jsonContent
     */
    public static String getJsonStringFormResourceFile(String resourceFilePath){
        DefaultResourceLoader resourceLoader = new DefaultResourceLoader();
        InputStream inputStream = null;
        StringBuilder jsonContent = new StringBuilder();

        try {
            //获取json文件的输入流
            inputStream = resourceLoader.getResource(resourceFilePath).getInputStream();
            //构建BufferReader，用于读取行String
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            //逐行读取
            String lineContent = reader.readLine();
            while (StringUtils.isNotEmpty(lineContent)){
                jsonContent.append(lineContent);
                lineContent = reader.readLine();
            }
        } catch (IOException e) {
            System.out.println("json文件流获取失败");
            return null;
        }

        return jsonContent.toString();
    }
}
