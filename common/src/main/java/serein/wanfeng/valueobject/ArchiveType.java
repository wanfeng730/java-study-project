package serein.wanfeng.valueobject;

import java.util.Objects;

/**
 * @Date: 2023-08-18 11:50
 * @Author: luozh
 * @Description:
 */

public enum ArchiveType {
    RECORD(1, "案件"),
    VOLUME(2, "案卷");


    private String name;
    private Integer value;

    ArchiveType(Integer value, String name){
        this.value = value;
        this.name = name;
    }

    public Integer asValue(){
        return this.value;
    }

    public String asName(){
        return this.name;
    }

    public ArchiveType getEnumByValue(Integer value){
        for (ArchiveType archiveType : ArchiveType.values()) {
            if(Objects.equals(archiveType.asValue(), value)){
                return archiveType;
            }
        }
        return null;
    }

}
