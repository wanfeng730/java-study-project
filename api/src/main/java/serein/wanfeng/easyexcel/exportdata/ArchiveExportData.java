package serein.wanfeng.easyexcel.exportdata;

import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Date: 2023-10-11 16:10
 * @Author: luozh
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArchiveExportData {
    private String id;
    private String name;
    private String type;
}
