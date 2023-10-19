package serein.wanfeng.easyexcel.infoclass;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import serein.wanfeng.valueobject.ArchiveType;

/**
 * @Date: 2023-10-19 09:08
 * @Author: luozh
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArchiveExportInfo {

    @ExcelProperty("档号")
    private String id;

    @ExcelProperty("题名")
    private String name;

    @ExcelProperty("档案类型")
    private String type;
}
