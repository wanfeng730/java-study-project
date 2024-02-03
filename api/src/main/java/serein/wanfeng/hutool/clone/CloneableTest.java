package serein.wanfeng.hutool.clone;

import cn.hutool.core.clone.CloneSupport;
import cn.hutool.core.clone.Cloneable;
import cn.hutool.core.util.ObjectUtil;
import org.junit.Test;

import java.io.Serializable;

/**
 * @date: 2024-01-26 15:29
 * @author: luozh
 * @description: hutool克隆工具类测试
 * @since:
 */
public class CloneableTest {

    @Test
    public void test(){
        //深拷贝
        Loopy loopy1 = new Loopy();
        //每一层类都必须实现Serializable接口，否则抛异常
        Loopy loopy2 = ObjectUtil.cloneByStream(loopy1);
        System.out.println();
    }
}

class Doll implements Serializable{
    private String type = "doll";
}
class Loopy implements Serializable {
    private String name = "loopy";
    private Doll doll = new Doll();
}

/**
 * 继承CloneSupport接口，克隆泛型的对象（浅拷贝）
 * CloneSupport有自己的克隆方法
 */
class Dog extends CloneSupport<Dog>{
    private final String name = "mimi";
    private final int age = 1;
}


/**
 * 实现hutool的Cloneable接口（浅拷贝）
 * 与jdk的Cloneable接口相比，定义了clone方法，使得实现类必须实现clone方法
 */
class Cat implements Cloneable<Cat>{
    private final String name = "mimi";
    private final int age = 1;

    @Override
    public Cat clone() {
        try {
            return (Cat) super.clone();
        } catch (CloneNotSupportedException e){
            throw new RuntimeException(e);
        }
    }
}
