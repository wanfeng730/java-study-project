package serein.wanfeng.easyexcel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.enums.WriteDirectionEnum;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.fill.FillConfig;
import org.apache.commons.compress.utils.Lists;
import org.junit.Test;
import org.springframework.core.io.DefaultResourceLoader;
import serein.wanfeng.easyexcel.exportdata.ArchiveExportData;
import serein.wanfeng.easyexcel.factory.ExcelCellStyleStrategyFactory;
import serein.wanfeng.entity.Archive;
import serein.wanfeng.factory.ArchiveFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @Date: 2023-10-11 15:21
 * @Author: luozh
 * @Description: easyexcel模版导出测试
 */

public class EasyExcelTemplateExportTest {

    public static final String ARCHIVE_TEMPLATE_FILE_NAME = "档案信息表";

    public static final String ARCHIVE_TEMPLATE_SUFFIX_FILE_NAME = "档案信息表.xlsx";

    public static final String EXCEL_TEMPLATE_FOLDER_PATH = "src/main/resources/exceltemplate";

    public static final String EXPORT_FOLDER_PATH = "src/main/resources/export";

    public static final DateTimeFormatter LONG_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");

    @Test
    public void test_templateExport(){
        //样例数据列表
        List<Archive> archiveList = ArchiveFactory.generateExampleArchiveList();
        //转换为可导出的字段格式
        List<ArchiveExportData> exportDataList = Lists.newArrayList();
        archiveList.forEach(archive -> {
            exportDataList.add(new ArchiveExportData(
                    archive.getId(), archive.getName(), archive.getType().asName()
            ));
        });


        //获取模版文件的流
        DefaultResourceLoader resourceLoader = new DefaultResourceLoader();
        InputStream templateFileInputStream = null;
        try {
            templateFileInputStream = resourceLoader.getResource("exceltemplate/" + ARCHIVE_TEMPLATE_SUFFIX_FILE_NAME).getInputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //模版文件路径
        String templateFilePath = EXCEL_TEMPLATE_FOLDER_PATH + File.separator + ARCHIVE_TEMPLATE_SUFFIX_FILE_NAME;
        //导出文件路径
        String exportExcelPath = EXPORT_FOLDER_PATH + File.separator + ARCHIVE_TEMPLATE_FILE_NAME + LONG_DATE_TIME_FORMATTER.format(LocalDateTime.now()) + ".xlsx";


        ExcelWriter excelWriter = null;
        try {
            /**
             * 构建ExcelWriter对象
             *  write：导出后的文件路径
             *  withTemplate：模版文件，可选文件路径、InputStream、File对象
             *  registerWriteHandler: 注册单元格策略
             */
            excelWriter = EasyExcel.write(exportExcelPath).withTemplate(templateFileInputStream)
                    .registerWriteHandler(ExcelCellStyleStrategyFactory.getAutoWrapHorizontalCellStyleStrategy())
                    .build();
            // LzhTODO: 设置行高
            WriteSheet writeSheet = EasyExcel.writerSheet().build();
            /**
             * 填充参数
             *  direction：排列方向
             *      WriteDirectionEnum.VERTICAL     纵向
             *      WriteDirectionEnum.HORIZONTAL   横向
             *  forceNewRow:
             */
            FillConfig fillConfig = FillConfig.builder().direction(WriteDirectionEnum.VERTICAL).forceNewRow(Boolean.TRUE).build();
            excelWriter.fill(exportDataList, fillConfig, writeSheet);
            System.out.println("导出文件：" + exportExcelPath);
        } finally {
            //关闭流
            if(excelWriter != null){
                excelWriter.finish();
            }
        }
    }
}
