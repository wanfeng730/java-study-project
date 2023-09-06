package serein.wanfeng.register;

import serein.wanfeng.common.URL;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Date: 2023-09-05 20:23
 * @Author: luozh
 * @Description:
 */

public class RemoteRegister {
    /**
     * Map < 接口名, URL集合 >
     *     每个url对应一个服务器，url集合代表服务器集群
     */
    private static Map<String, List<URL>> urlMap = new HashMap<>();

    public static void register(String interfaceName, URL url){
        if(urlMap.get(interfaceName) == null){
            urlMap.put(interfaceName, Collections.singletonList(url));
        }else{
            urlMap.get(interfaceName).add(url);
        }

        saveFile();
    }

    public static List<URL> get(String interfaceName){
        getFile();

        return urlMap.get(interfaceName);
    }

    /**
     * 将map保存到文件
     */
    public static void saveFile(){
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("rpc/wanfengrpc/src/main/resources/map.txt"));
            objectOutputStream.writeObject(urlMap);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 将文件内容转换为map
     */
    public static void getFile(){
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("rpc/wanfengrpc/src/main/resources/map.txt"));
            urlMap = (Map<String, List<URL>>) objectInputStream.readObject();
        } catch (Exception e) {
            System.out.println("服务发现失败，请检查服务是否注册！");
        }
    }


}
