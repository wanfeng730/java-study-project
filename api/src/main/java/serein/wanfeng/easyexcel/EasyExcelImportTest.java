package serein.wanfeng.easyexcel;

import com.alibaba.excel.EasyExcel;
import org.junit.Test;
import serein.wanfeng.easyexcel.infoclass.BorrowFormImportInfo;
import serein.wanfeng.easyexcel.infoclass.BorrowItemImportInfo;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Date: 2023-10-18 14:08
 * @Author: luozh
 * @Description:
 */

public class EasyExcelImportTest {

    public static final String BORROW_FORM_INFO_EXCEL_FILE_PATH = "src/main/resources/datafile/查档登记记录导入luozh测试.xls";

    @Test
    public void borrowFormInfoImportTest(){
        File excelFile = new File(BORROW_FORM_INFO_EXCEL_FILE_PATH);
        List<BorrowFormImportInfo> borrowFormInfoImportList = EasyExcel.read(excelFile)
                .head(BorrowFormImportInfo.class)
                .sheet(0)
                .headRowNumber(1)
                .doReadSync();

        System.out.println(borrowFormInfoImportList);
    }

    @Test
    public void MapRemoveKeyConcurrentModifyExceptionTest(){
        File excelFile = new File(BORROW_FORM_INFO_EXCEL_FILE_PATH);

        List<BorrowFormImportInfo> borrowFormImportInfoList = null;
        List<BorrowItemImportInfo> borrowItemImportInfoList = null;

        //解析查档登记记录列表（借阅单）
        borrowFormImportInfoList = EasyExcel.read(excelFile).head(BorrowFormImportInfo.class)
                .sheet(0).headRowNumber(1).doReadSync();
        //解析借阅档案记录列表（借阅项）
        borrowItemImportInfoList = EasyExcel.read(excelFile).head(BorrowItemImportInfo.class)
                .sheet(1).headRowNumber(1).doReadSync();

        //根据借阅编号匹配借阅单和借阅档案，生成map
        // 导入借阅单编号 -> 借阅单信息
        Map<String, BorrowFormImportInfo> borrowFormInfoMap = borrowFormImportInfoList.stream().collect(Collectors.toMap(BorrowFormImportInfo::getImportBorrowingFormNo, borrowFormImportInfo -> borrowFormImportInfo));
        // 导入借阅单编号 -> 借阅项信息列表
        Map<String, List<BorrowItemImportInfo>> borrowItemInfoMap = borrowItemImportInfoList.stream().collect(Collectors.groupingBy(BorrowItemImportInfo::getImportBorrowingFormNo));
        //保存（保存完一条借阅单就从map中删除，以便筛选出编号匹配失败的数据）
        Set<String> importBorrowingFormNoSet = borrowFormInfoMap.keySet();
        // LzhTODO: 删除map的key时出现并发修改异常，需要有时间时检查理解
        importBorrowingFormNoSet.forEach(importBorrowingFormNo -> {
            //该编号没有借阅档案
            if(!borrowItemInfoMap.containsKey(importBorrowingFormNo)){
                return;
            }
            BorrowFormImportInfo borrowFormInfo = borrowFormInfoMap.get(importBorrowingFormNo);
            List<BorrowItemImportInfo> borrowItemInfoList = borrowItemInfoMap.get(importBorrowingFormNo);
            //更新其他字段，保存

            //从map中删除这条编号的所有数据
            borrowFormInfoMap.remove(importBorrowingFormNo);
            borrowItemInfoMap.remove(importBorrowingFormNo);
        });
    }
}
