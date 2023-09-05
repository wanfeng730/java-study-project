package serein.wanfeng.protocol;

import serein.wanfeng.common.Invocation;
import serein.wanfeng.entity.Archive;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * @Date: 2023-09-05 17:37
 * @Author: luozh
 * @Description:
 */

public class HttpClient {

    public Archive send(String hostname, Integer port, Invocation invocation){
        //读取用户的配置（发送请求方式）

        //发送请求（纯JDK发送）
        try {
            URL url = new URL("http", hostname, port, "/");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            //请求方式
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);

            //包装请求中的流为ObjectOutputStream，从而向流中写入invocation对象
            OutputStream outputStream = httpURLConnection.getOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(invocation);
            //发送请求
            objectOutputStream.flush();
            objectOutputStream.close();
            //获取流中的对象并返回
            InputStream inputStream = httpURLConnection.getInputStream();
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            Archive archive = (Archive) objectInputStream.readObject();

            return archive;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
