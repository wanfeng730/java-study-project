package serein.wanfeng.register;

import jdk.nashorn.internal.runtime.Version;

import java.util.HashMap;
import java.util.Map;

/**
 * @Date: 2023-09-05 17:08
 * @Author: luozh
 * @Description: 服务本地注册（本质是保存接口名和对应实现类的Class对象）
 */

public class LocalRegister {

    private static Map<String, Class> classMap = new HashMap<>();

    public static void register(String interfaceName, Class implClass, String version){
        classMap.put(interfaceName + version, implClass);
    }

    public static Class get(String interfaceName, String version){
        return classMap.get(interfaceName + version);
    }

}
