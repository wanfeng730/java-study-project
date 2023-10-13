package serein.wanfeng.easyexcel.stratery;

import com.alibaba.excel.metadata.Head;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.style.column.AbstractColumnWidthStyleStrategy;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;

import java.util.List;

/**
 * @Date: 2023-10-13 15:31
 * @Author: luozh
 * @Description: excel行高列宽设置策略（填充模板方式，只对填充的行和列有效）
 */
@NoArgsConstructor
@AllArgsConstructor
public class ExcelRowHeightSettingStrategy extends AbstractColumnWidthStyleStrategy {

    private float rowHeight;

    @Override
    protected void setColumnWidth(WriteSheetHolder writeSheetHolder, List<WriteCellData<?>> cellDataList, Cell cell, Head head, Integer relativeRowIndex, Boolean isHead) {
        cell.getRow().setHeightInPoints(rowHeight);
    }
}
