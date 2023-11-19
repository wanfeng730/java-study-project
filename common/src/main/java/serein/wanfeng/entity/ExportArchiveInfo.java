package serein.wanfeng.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @date: 2023-11-19 19:30
 * @author: luozh
 * @description:
 * @since:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExportArchiveInfo {
    private String id;
    private String name;
    private String type;
}
