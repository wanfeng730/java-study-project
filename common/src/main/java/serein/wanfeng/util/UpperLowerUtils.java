package serein.wanfeng.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @date: 2024-07-23 16:18
 * @author: luozh
 * @description: 大小写转换工具类
 */
public class UpperLowerUtils {

    public static void stringToUpperCase(String value) {
        value = value.toUpperCase();
    }

    public static void mapKeyToUpperCase(Map<String, Object> map) {
        List<String> keyList = new ArrayList<>(map.keySet());
        keyList.forEach(key -> {
            String upperKey = key.toUpperCase();
            map.put(upperKey, map.get(key));
            map.remove(key);
        });
    }

    public static void mapKeyToLowerCase(Map<String, Object> map) {
        List<String> keyList = new ArrayList<>(map.keySet());
        keyList.forEach(key -> {
            String lowerKey = key.toLowerCase();
            map.put(lowerKey, map.get(key));
            map.remove(key);
        });
    }

    public static void mapListKeyToUpperCase(List<Map<String, Object>> mapList) {
        mapList.forEach(UpperLowerUtils::mapKeyToUpperCase);
    }

    public static void mapListKeyToLowerCase(List<Map<String, Object>> mapList) {
        mapList.forEach(UpperLowerUtils::mapKeyToLowerCase);
    }

}
