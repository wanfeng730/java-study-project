package serein.wanfeng.util;

import com.linuxense.javadbf.DBFReader;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @date: 2023-12-27 14:09
 * @author: luozh
 * @description:
 * @since:
 */
public class DBFUtils {
    /**
     * 防中文乱码编码格式
     */
    public static final String DBF_READ_CHINESE_CHARSET_NAME = "GBK";

    public static String[][] readDBFDataByFilePath(String dbfFilePath){
        List<String[]> dataList = new ArrayList<>();
        //资源在try语句中，执行结束可以自动关闭
        try(InputStream inputStream = Files.newInputStream(Paths.get(dbfFilePath));
            DBFReader reader = new DBFReader(inputStream, Charset.forName(DBF_READ_CHINESE_CHARSET_NAME))) {
            //获取字段名
            int fieldCount = reader.getFieldCount();
            String[] columnNames = new String[fieldCount];
            for (int i = 0; i < fieldCount; i++) {
                columnNames[i] = reader.getField(i).getName();
            }
            dataList.add(columnNames);
            //获取数据记录
            Object[] readValues;
            while ((readValues = reader.nextRecord()) != null){
                String[] rowValues = Arrays.stream(readValues).toArray(String[]::new);
                dataList.add(rowValues);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return dataList.toArray(new String[0][]);
    }
}
