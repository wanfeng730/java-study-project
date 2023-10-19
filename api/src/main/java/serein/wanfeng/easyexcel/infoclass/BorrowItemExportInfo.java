package serein.wanfeng.easyexcel.infoclass;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Date: 2023-10-19 15:33
 * @Author: luozh
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BorrowItemExportInfo {

    @ExcelProperty("借阅编号")
    private String borrowNo;

    @ExcelProperty("档号")
    private String archiveId;

    @ExcelProperty("题名")
    private String name;

    @ExcelProperty("档案类型")
    private String archiveType;
}
