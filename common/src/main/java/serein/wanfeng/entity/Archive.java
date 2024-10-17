package serein.wanfeng.entity;

import lombok.Data;
import lombok.ToString;
import serein.wanfeng.valueobject.ArchiveType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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

    public static List<Archive> mockList() {
        List<Archive> archiveList = new ArrayList<>();
        archiveList.add(new Archive("1", "32", ArchiveType.RECORD));
        archiveList.add(new Archive("2", "42", ArchiveType.VOLUME));
        return archiveList;
    }
}
