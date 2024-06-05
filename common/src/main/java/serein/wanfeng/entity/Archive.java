package serein.wanfeng.entity;

import lombok.Data;
import lombok.ToString;
import serein.wanfeng.valueobject.ArchiveType;

import java.io.Serializable;

/**
 * @Date: 2023-07-12 15:47
 * @Author: luozh
 * @Description:
 */
@Data
@ToString
public class Archive implements Serializable {

    static final long serialVersionUID = 2023090520010730L;

    private String id;
    private String name;
    private ArchiveType type;
    private Integer sortNumber;

    public Archive(String id, String name, ArchiveType type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public Archive(String id, String name, ArchiveType type, Integer sortNumber) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.sortNumber = sortNumber;
    }

    public Archive() {
    }
}
