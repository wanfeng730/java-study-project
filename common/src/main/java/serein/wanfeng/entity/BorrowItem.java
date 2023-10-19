package serein.wanfeng.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

/**
 * @Date: 2023-07-12 15:36
 * @Author: luozh
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BorrowItem {
    private String borrowNo;
    private String archiveId;
    private String name;
    private String type;

    public BorrowItem(String archiveId, String name, String type){
        this.archiveId = archiveId;
        this.name = name;
        this.type = type;
    }
}
