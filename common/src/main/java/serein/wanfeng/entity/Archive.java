package serein.wanfeng.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import serein.wanfeng.valueobject.ArchiveType;

import java.io.Serializable;

/**
 * @Date: 2023-07-12 15:47
 * @Author: luozh
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Archive implements Serializable {

    static final long serialVersionUID = 2023090520010730L;

    private String id;
    private String name;
    private ArchiveType type;
}
