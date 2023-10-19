package serein.wanfeng.easyexcel.infoclass;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @Date: 2023-10-18 16:13
 * @Author: luozh
 * @Description: 借阅档案解析对象
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BorrowItemImportInfo {
    /**
     * 借阅单编号（用于关联档案清单的借阅档案，实际保存的借阅单编号需另外生成）
     */
    @ExcelProperty(value = "借阅单编号")
    private String importBorrowingFormNo;

    /**
     * 题名
     */
    @ExcelProperty(value = "题名")
    private String title;
    /**
     * 档号
     */
    @ExcelProperty(value = "档号")
    private String archivalNo;
    /**
     * 责任者
     */
    @ExcelProperty(value = "责任者")
    private String author;
    /**
     * 年度
     */
    @ExcelProperty(value = "年度")
    private String fileYear;
    /**
     * 归档时间（类型转换）
     */
    @ExcelProperty(value = "归档时间")
    private String filedDate;
    /**
     * 形成日期（类型转换）
     */
    @ExcelProperty(value = "形成日期")
    private String docDate;
    /**
     * 全宗号
     */
    @ExcelProperty(value = "全宗号")
    private String fondsId;
    /**
     * 载体类型（"01" 实体  "02" 电子）
     */
    @ExcelProperty(value = "载体类型")
    private String carrierTypeStatus;
    /**
     * 开放状态
     */
    @ExcelProperty(value = "开放状态")
    private String openClass;
}
