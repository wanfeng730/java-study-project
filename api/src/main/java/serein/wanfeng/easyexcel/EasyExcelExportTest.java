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
import serein.wanfeng.easyexcel.factory.ExcelStrategyFactory;
import serein.wanfeng.easyexcel.infoclass.ArchiveExportInfo;
import serein.wanfeng.easyexcel.infoclass.BorrowItemExportInfo;
import serein.wanfeng.entity.Archive;
import serein.wanfeng.entity.BorrowItem;
import serein.wanfeng.factory.ExampleDataFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * @Date: 2023-10-11 15:21
 * @Author: luozh
 * @Description: easyexcel模版导出测试
 */

public class EasyExcelExportTest {

    public static final String ARCHIVE_TEMPLATE_FILE_NAME = "档案信息表";

    public static final String ARCHIVE_TEMPLATE_SUFFIX_FILE_NAME = "档案信息表.xlsx";
    public static final String ARCHIVE_SIMPLE_TEMPLATE_FILE_NAME = "档案信息表无占位符";

    public static final String ARCHIVE_SIMPLE_TEMPLATE_SUFFIX_FILE_NAME = "档案信息表无占位符.xlsx";

    public static final String EXCEL_TEMPLATE_FOLDER_PATH = "src/main/resources/exceltemplate";

    public static final String EXPORT_FOLDER_PATH = "src/main/resources/export";

    public static final DateTimeFormatter LONG_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");

    @Test
    public void test_templateExport(){
        //样例数据列表
        List<Archive> archiveList = ExampleDataFactory.generateExampleArchiveList();
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
                    .registerWriteHandler(ExcelStrategyFactory.getAutoWrapStrategy())
                    .registerWriteHandler(ExcelStrategyFactory.getRowHeightSettingStrategy(35f))
                    .build();
            WriteSheet writeSheet = EasyExcel.writerSheet().build();
            /**
             * 填充配置
             *  direction：排列方向
             *      WriteDirectionEnum. VERTICAL     纵向
             *      WriteDirectionEnum. HORIZONTAL   横向
             */
            FillConfig fillConfig = FillConfig.builder().direction(WriteDirectionEnum.VERTICAL).build();
            excelWriter.fill(exportDataList, fillConfig, writeSheet);
            System.out.println("导出文件：" + exportExcelPath);
        } finally {
            //关闭流
            if(excelWriter != null){
                excelWriter.finish();
            }
        }
    }

    @Test
    public void test2(){
        //档案表数据
        List<Archive> archiveList = ExampleDataFactory.generateExampleArchiveList();
        List<ArchiveExportInfo> archiveExportInfoList = new ArrayList<>();
        archiveList.forEach(archive -> {
            archiveExportInfoList.add(new ArchiveExportInfo(archive.getId(), archive.getName(), archive.getType().asName()));
        });
        //借阅记录数据
        List<BorrowItem> borrowItemList = ExampleDataFactory.generateBorrowItemList();
        List<BorrowItemExportInfo> borrowItemExportInfoList = new ArrayList<>();
        borrowItemList.forEach(borrowItem -> {
            borrowItemExportInfoList.add(new BorrowItemExportInfo(borrowItem.getBorrowNo(), borrowItem.getArchiveId(), borrowItem.getName(), borrowItem.getType()));
        });
        //导出文件路径
        String exportExcelPath = EXPORT_FOLDER_PATH + File.separator + ARCHIVE_SIMPLE_TEMPLATE_FILE_NAME + LONG_DATE_TIME_FORMATTER.format(LocalDateTime.now()) + ".xlsx";
        ExcelWriter excelWriter = null;
        try {
            /**
             * 构建ExcelWriter对象
             *  write：导出后的文件路径
             *  withTemplate：模版文件，可选文件路径、InputStream、File对象
             *  registerWriteHandler: 注册单元格策略
             */
            excelWriter = EasyExcel.write(exportExcelPath)
                    .registerWriteHandler(ExcelStrategyFactory.getAutoWrapStrategy())
                    .registerWriteHandler(ExcelStrategyFactory.getRowHeightSettingStrategy(35f))
                    .registerWriteHandler(ExcelStrategyFactory.getAnnotationExportHeadCellStyleStrategy())
                    .build();

            WriteSheet archiveSheet = EasyExcel.writerSheet(0, "档案表").head(ArchiveExportInfo.class).build();
            WriteSheet borrowItemSheet = EasyExcel.writerSheet(1, "借阅记录表").head(BorrowItemExportInfo.class).build();

            excelWriter.write(archiveExportInfoList, archiveSheet);
            excelWriter.write(borrowItemExportInfoList, borrowItemSheet);

            System.out.println("导出文件成功【" + exportExcelPath + "】");
            // LzhTODO: 日志美化
        } finally {
            //关闭writer
            if(excelWriter != null){
                excelWriter.finish();
            }
        }
    }
}
