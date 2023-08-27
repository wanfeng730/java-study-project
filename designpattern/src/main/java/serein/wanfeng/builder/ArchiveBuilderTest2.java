package serein.wanfeng.builder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import serein.wanfeng.valueobject.ArchiveType;

/**
 * @Date: 2023-08-27 19:57
 * @Author: luozh
 * @Description:
 */

public class ArchiveBuilderTest2 {
    public static void main(String[] args) {
        Archive.Builder builder = new Archive.Builder();
        Archive archive = builder.id("A003")
                .name("2023archive")
                .type(ArchiveType.RECORD)
                .build();
        System.out.println(archive);
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
class Archive {
    private String id;
    private String name;
    private ArchiveType type;

    static class Builder{
        private String id;
        private String name;
        private ArchiveType type;

        public Builder id(String id){
            this.id = id;
            return this;
        }

        public Builder name(String name){
            this.name = name;
            return this;
        }

        public Builder type(ArchiveType type){
            this.type = type;
            return this;
        }

        Archive build(){
            //校验
            //...
            return new Archive(id, name, type);
        }
    }
}

