package serein.wanfeng.xdocreport;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.Version;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;
import serein.wanfeng.factory.ExampleDataFactory;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * @date: 2023-11-17 16:06
 * @author: luozh
 * @description:
 * @since:
 */
public class XDocReportTest {


    private String freeMarkerVersion = "2.3.32";

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

    @Test
    public void test_export_doc() throws IOException, TemplateException {
        // 填充数据：占位符名称 -> String/ArrayList
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("borrowNo", "20231119");
        dataMap.put("borrowPerson", "luozh");
        dataMap.put("archiveList", ExampleDataFactory.generateExportArchiveInfoList());

        // 导出文件的路径
        String exportFilePath = "/Users/wanfeng/" + DATE_TIME_FORMATTER.format(LocalDateTime.now())  + ".doc";
        //模版名称，用于获取模版内容
        String templateName = "freemarker测试模版.ftl";

        // 设置FreeMarker的版本和编码格式
        Configuration configuration = new Configuration(new Version(freeMarkerVersion));
        configuration.setDefaultEncoding("UTF-8");

        // 设置FreeMarker生成Word文档所需要的模板的路径
        //      basePackagePath：模版文件的父级目录
        configuration.setClassForTemplateLoading(XDocReportTest.class, "/doctemplate");

        // 获取导出Word文档所需要的模板
        Template template = configuration.getTemplate(templateName, "UTF-8");

        // 创建一个Word文档的输出流
        File docFile = new File(exportFilePath);
        Writer writer = new BufferedWriter(new OutputStreamWriter(Files.newOutputStream(docFile.toPath()), StandardCharsets.UTF_8));

        // FreeMarker使用Word模板和数据生成Word文档
        template.process(dataMap, writer);

        //关闭流
        writer.flush();
        writer.close();

        //删除临时文件
        FileUtils.deleteQuietly(docFile);

    }
}
