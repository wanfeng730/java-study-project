package serein.wanfeng.builder;

import serein.wanfeng.entity.Archive;
import serein.wanfeng.valueobject.ArchiveType;

/**
 * @Date: 2023-08-27 19:35
 * @Author: luozh
 * @Description: 建造者模式
 */

public class ArchiveBuilderTest {
    public static void main(String[] args) {
        //传统构造对象
        Archive archive = new Archive("A001", "2023迎亚运", ArchiveType.RECORD);
        //建造者构造对象
        PhysicalArchiveBuilder builder = new PhysicalArchiveBuilder();
        PhysicalArchiveDirector director = new PhysicalArchiveDirector(builder);
        Archive archive1 = director.createArchive("A002", "2023迎亚运文件", ArchiveType.VOLUME);
        System.out.println(archive1);

    }
}

//建造类抽象接口，规范建造步骤方法
interface ArchiveBuilder{
    void id(String id);

    void name(String name);

    void type(ArchiveType archiveType);

    Archive build();
}

//建造类，具体实现建造对象中的各个步骤方法
class PhysicalArchiveBuilder implements ArchiveBuilder{
    private String id;
    private String name;
    private ArchiveType type;


    @Override
    public void id(String id) {
        this.id = id;
    }

    @Override
    public void name(String name) {
        this.name = name;
    }

    @Override
    public void type(ArchiveType archiveType) {
        this.type = archiveType;
    }

    @Override
    public Archive build() {
        return new Archive(this.id, this.name, this.type);
    }

}

//具体指导类，指定对象创建时的具体顺序
class PhysicalArchiveDirector{
    private PhysicalArchiveBuilder builder;

    public PhysicalArchiveDirector(PhysicalArchiveBuilder builder) {
        this.builder = builder;
    }

    public Archive createArchive(String id, String name, ArchiveType type){
        builder.id(id);
        builder.name(name);
        builder.type(type);

        return builder.build();
    }
}
