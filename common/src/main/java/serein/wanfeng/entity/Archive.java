package serein.wanfeng.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import serein.wanfeng.valueobject.ArchiveType;

/**
 * @Date: 2023-07-12 15:47
 * @Author: luozh
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Archive {
    private String id;
    private String name;
    private ArchiveType type;
}
