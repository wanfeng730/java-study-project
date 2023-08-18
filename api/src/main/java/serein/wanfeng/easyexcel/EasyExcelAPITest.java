package serein.wanfeng.easyexcel;

import com.alibaba.excel.EasyExcel;
import org.apache.commons.compress.utils.Lists;
import org.junit.Test;
import serein.wanfeng.constant.PathConstant;
import serein.wanfeng.easyexcel.stratery.ExcelFillRowCellMergeStrategy;
import serein.wanfeng.entity.Archive;
import serein.wanfeng.factory.ArchiveFactory;
import serein.wanfeng.factory.NumberFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Date: 2023-08-18 11:30
 * @Author: luozh
 * @Description: EasyExcel API测试类
 */

public class EasyExcelAPITest {

    public final static String[] ARCHIVE_FIELD = {
            "ID", "题名", "类型"
    };


    @Test
    public void excelFillCellMergeTest() {
        List<List<String>> data = new ArrayList<>();

        //第一行（全合并）：“档案信息”
        data.add(Lists.newArrayList());
        for (String s : ARCHIVE_FIELD) {
            data.get(0).add("档案信息");
        }
        //第二行：字段名
        data.add(Arrays.asList(ARCHIVE_FIELD));
        //第三行及以后：档案数据
        List<Archive> archiveList = ArchiveFactory.generateExampleArchiveList();
        archiveList.forEach(archive -> {
            data.add(Arrays.asList(
                    archive.getId(),
                    archive.getName(),
                    archive.getType().asName()
            ));
        });

        //excel导出路径
        String excelPath = PathConstant.OUTPUT_FOLDER_PATH + File.separator + "easyexcel" + NumberFactory.getRandomNumber(8) + ".xls";
        //合并单元格位置
        int[] mergeRow = {0};
        int mergeColumn = 0;
        EasyExcel.write(excelPath)
                .autoCloseStream(Boolean.TRUE)
                .registerWriteHandler(new ExcelFillRowCellMergeStrategy(mergeRow, mergeColumn))
                .sheet("test-sheet").doWrite(data);

        System.out.println("导出成功！导出路径：" + excelPath);
    }
}
