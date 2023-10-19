package serein.wanfeng.easyexcel;

import com.alibaba.excel.EasyExcel;
import org.junit.Test;
import org.springframework.util.ObjectUtils;
import serein.wanfeng.easyexcel.infoclass.BorrowFormImportInfo;
import serein.wanfeng.easyexcel.infoclass.BorrowItemImportInfo;

import javax.security.auth.kerberos.KerberosTicket;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * @Date: 2023-10-18 14:08
 * @Author: luozh
 * @Description:
 */

public class EasyExcelImportTest {

    public static final String BORROW_FORM_INFO_EXCEL_FILE_PATH = "src/main/resources/datafile/查档登记记录导入测试.xls";

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

        // 并发修改异常
        ////使用hashmap原有的remove，会使modCount自增
        //for (String s : borrowFormInfoMap.keySet()) {
        //    if (!ObjectUtils.isEmpty(borrowItemInfoMap.get(s))){
        //        borrowFormInfoMap.remove(s);
        //        borrowItemInfoMap.remove(s);
        //    }
        //}

        //解决方法有二：
        //使用迭代器的map删除key要调用迭代器内置的删除方法
        for(Iterator<String> iterator = borrowFormInfoMap.keySet().iterator(); iterator.hasNext();){
            String key = iterator.next();
            if(!ObjectUtils.isEmpty(borrowItemInfoMap.get(key))){
                iterator.remove();
                borrowItemInfoMap.remove(key);  //itemMap未使用迭代器，可以直接调用map原有的remove
            }
        }
        //使用ConcurrentMap
        ConcurrentHashMap<String, BorrowFormImportInfo> concurrentHashMap = new ConcurrentHashMap<>(borrowFormInfoMap);
        concurrentHashMap.keySet().forEach(key -> {
            concurrentHashMap.remove(key);
        });


        System.out.println();
    }
}
