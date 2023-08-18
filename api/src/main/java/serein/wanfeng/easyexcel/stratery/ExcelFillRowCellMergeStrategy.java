package serein.wanfeng.easyexcel.stratery;

import com.alibaba.excel.metadata.Head;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.write.handler.CellWriteHandler;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteTableHolder;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;

import java.util.List;

/**
 * @Date: 2023-08-18 11:33
 * @Author: luozh
 * @Description: excel行单元格填充合并策略（同一行的相同数据合并）
 */

public class ExcelFillRowCellMergeStrategy implements CellWriteHandler {

    /**
     * 合并的行索引
     * 在哪几行进行列合并
     */
    public int[] mergeRow;
    /**
     * 合并的列索引（从哪一列开始合并）
     * 例：若需要将3、4、5列合并为一个单元格，8、9列合并为一个单元格，则将第3列视为合并的列
     */
    public int mergeColumn;

    public ExcelFillRowCellMergeStrategy() {

    }

    public ExcelFillRowCellMergeStrategy(int[] mergeRowIndex, int mergeColumn) {
        this.mergeRow = mergeRowIndex;
        this.mergeColumn = mergeColumn;
    }

    @Override
    public void afterCellDispose(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, List<WriteCellData<?>> cellDataList, Cell cell, Head head, Integer relativeRowIndex, Boolean isHead) {
        //当前列
        int curColumn = cell.getColumnIndex();
        //当前行
        int curRow = cell.getRowIndex();

        //若当前列大于合并列，在指定行进行列的合并
        if (curColumn <= mergeColumn) {
            return;
        }
        for (int row : mergeRow) {
            if (curRow == row) {
                mergeWithPrevColumn(writeSheetHolder, cell, curRow, curColumn);
            }
        }

    }

    private void mergeWithPrevColumn(WriteSheetHolder writeSheetHolder, Cell cell, int curRow, int curColumn) {
        //当前行当前列数据
        String curData = cell.getStringCellValue();
        //当前行上一列数据
        String preData = cell.getSheet().getRow(curRow).getCell(curColumn - 1).getStringCellValue();

        //若相等则合并
        if (curData.equals(preData)) {
            Sheet sheet = writeSheetHolder.getSheet();
            List<CellRangeAddress> mergedRegions = sheet.getMergedRegions();
            boolean isMerged = false;
            //遍历已合并的行列数据
            for (int index = 0; index < mergedRegions.size() && !isMerged; index++) {
                CellRangeAddress cellRangeAddress = mergedRegions.get(index);
                // 若当前行上一列的单元格已经被合并，则先移除原有的合并单元格，再重新添加合并单元格
                if (cellRangeAddress.isInRange(curRow, curColumn - 1)) {
                    sheet.removeMergedRegion(index);
                    cellRangeAddress.setLastColumn(curColumn);
                    sheet.addMergedRegion(cellRangeAddress);
                    isMerged = true;
                }
            }
            //若当前行上一列单元格未被合并，则新增合并单元格
            if (!isMerged) {
                CellRangeAddress cellRangeAddress = new CellRangeAddress(curRow, curRow, curColumn - 1, curColumn);
                sheet.addMergedRegion(cellRangeAddress);
            }
        }
    }
}
