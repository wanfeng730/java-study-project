package serein.wanfeng.optional;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;
import serein.wanfeng.entity.Archive;
import serein.wanfeng.entity.BorrowItem;
import serein.wanfeng.exception.WanfengException;
import serein.wanfeng.valueobject.ArchiveType;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @Date: 2023-07-11 16:16
 * @Author: luozh
 * @Description:
 */

public class OptionalAPITest {
    /**
     * Optional对象创建
     */
    @Test
    public void createOptionalObject(){
        Archive wenshuArchive = new Archive("A001", "文书档案-1", ArchiveType.RECORD);
        Archive nullArchive = null;

        // Optional.of(T t) 创建一个Optional对象，t必须非空
        Optional<Archive> wenshuOptional = Optional.of(wenshuArchive);

        // Optional.empty() 创建一个空的Optional对象
        Optional<Object> emptyOptional = Optional.empty();

        // Optional.ofNullable(T t) 创建一个Optional对象，t可以是空
        Optional<Archive> nullOptional = Optional.ofNullable(nullArchive);
    }

    /**
     * 判断Optional容器是否包含对象
     * 若Optional容器有对象，对对象执行操作
     */
    @Test
    public void judgeOptionalExistObject(){
        Archive wenshuArchive = new Archive("A001", "文书档案-1", ArchiveType.RECORD);
        Optional<Archive> wenshuOptional = Optional.ofNullable(wenshuArchive);
        Archive nullArchive = null;
        Optional<Archive> nullOptional = Optional.ofNullable(nullArchive);

        //判断Optional容器是否包含对象
        boolean wenshuOptionalPresent = wenshuOptional.isPresent();
        boolean nullOptionalPresent = nullOptional.isPresent();

        //如果对象存在，传入对象执行方法体中的操作
        wenshuOptional.ifPresent(archive -> {
            archive.setName("文书档案-1（存在Option容器中）");
        });
        nullOptional.ifPresent(archive -> {
            archive.setName("文书档案-1（存在Option容器中）");
        });
    }

    /**
     * 获取Optional容器中的对象，若为空则抛NoSuchElementException异常
     * 获取Optional容器中的对象，若为空则返回指定对象
     * 获取Optional容器中的对象，若为空则返回接口实现方法体提供的对象
     * 获取Optional容器中的对象，若为空则抛出自定义异常
     */
    @Test
    public void getOptionalObject(){
        Archive wenshuArchive = new Archive("A001", "文书档案-1", ArchiveType.RECORD);
        Optional<Archive> wenshuOptional = Optional.ofNullable(wenshuArchive);
        Archive nullArchive = null;
        Optional<Archive> nullOptional = Optional.ofNullable(nullArchive);

        //获取容器中的对象，若为空则抛异常NoSuchElementException    optional.get()
        try {
            Archive wenshuGetArchive = wenshuOptional.get();
            Archive nullGetArchive = nullOptional.get();
        } catch (Exception e) {
            System.out.println("容器内对象为空");
        }

        //获取容器中的对象，若为空则返回other对象    optional.orElse(T other)
        Archive wenshuElseArchive = wenshuOptional.orElse(new Archive("A002", "婚姻档案-1", ArchiveType.VOLUME));
        Archive nullElseArchive = nullOptional.orElse(new Archive("A002", "婚姻档案-1", ArchiveType.VOLUME));

        //获取容器中的对象，若为空则返回接口实现提供的对象  optional.orElseGet(Supplier<? extends T> other)
        Archive nullElseGetArchive = nullOptional.orElseGet(() -> {
            String id = UUID.randomUUID().toString() + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            return new Archive(id, "新增档案-2", ArchiveType.VOLUME);
        });

        //获取容器中的对象，若为空则抛出Supplier接口实现提供的异常  optional.orElseThrow(Supplier<? extends X> exceptionSupplier)
        try {
            nullOptional.orElseThrow(() -> new WanfengException("容器内对象为空！！！"));
        } catch (WanfengException e) {
            e.printStackTrace();
        }

    }


    /**
     * 过滤Optional容器中的对象，
     */
    @Test
    public void filterOptional(){
        Archive wenshuArchive = new Archive("A001", "文书档案-1", ArchiveType.RECORD);
        Optional<Archive> wenshuOptional = Optional.ofNullable(wenshuArchive);

        //过滤type为"volume"的对象，若不匹配则optional中的对象被删除，optional为空
        Optional<Archive> wenshuFilterOptional = wenshuOptional.filter(archive -> "volume".equals(archive.getType()));
        boolean wenshuFilterOptionalPresent = wenshuFilterOptional.isPresent();
    }

    /**
     * 转换Optional中对象的类型（映射）
     */
    @Test
    public void mapOptional(){
        Archive wenshuArchive = new Archive("A001", "文书档案-1", ArchiveType.RECORD);
        Optional<Archive> wenshuOptional = Optional.ofNullable(wenshuArchive);

        Optional<BorrowItem> borrowItemOptional = wenshuOptional.map(archive -> new BorrowItem(archive.getId(), archive.getName(), archive.getType().asName()));

        BorrowItem borrowItem = borrowItemOptional.get();
    }

    @Test
    public void useInDevelop(){
        //对象判空
        Archive wenshuArchive = new Archive("A001", "文书档案-1", ArchiveType.RECORD);
        Optional.ofNullable(wenshuArchive).ifPresent(archive -> {
            archive.setName("文书档案（判空测试）");
        });

        Archive nullArchive = null;
        Optional.ofNullable(nullArchive).ifPresent(archive -> {
            archive.setName("文书档案（判空测试）");
        });

    }

}
