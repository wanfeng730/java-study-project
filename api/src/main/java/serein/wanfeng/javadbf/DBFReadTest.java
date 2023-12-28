package serein.wanfeng.javadbf;

import com.linuxense.javadbf.DBFDataType;
import com.linuxense.javadbf.DBFField;
import com.linuxense.javadbf.DBFReader;
import com.linuxense.javadbf.DBFWriter;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.springframework.core.io.DefaultResourceLoader;
import serein.wanfeng.entity.Archive;
import serein.wanfeng.factory.ExampleDataFactory;
import serein.wanfeng.util.DBFUtils;

import java.io.*;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @date: 2023-12-27 14:08
 * @author: luozh
 * @description:
 * @since:
 */
public class DBFReadTest {
    @Test
    public void readDBF(){
        String dbfFilePath = "src/main/resources/dbf-file/730测试门类档案目录.dbf";
        InputStream inputStream = null;

        String[][] data = DBFUtils.readDBFDataByFilePath(dbfFilePath);

        System.out.println();

    }
}
