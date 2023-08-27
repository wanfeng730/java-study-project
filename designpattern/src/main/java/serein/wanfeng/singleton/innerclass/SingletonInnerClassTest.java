package serein.wanfeng.singleton.innerclass;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @Date: 2023-06-16 0016 10:59
 * @Author: luozh
 * @Description: 静态内部类实现
 */
public class SingletonInnerClassTest {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        //反射攻击，破坏单例模式
        Constructor<Wanfeng> constructor = Wanfeng.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        Wanfeng w1 = constructor.newInstance();

        Wanfeng w2 = Wanfeng.getInstance();
        System.out.println(w1 == w2);


    }
}

class Wanfeng{
    private static class InnerClass{
        private static Wanfeng instance = new Wanfeng();
    }
    private Wanfeng(){
        //防止被反射攻击，即修改构造函数的访问权限以创建多个实例
        if(InnerClass.instance != null){
            throw new RuntimeException("单例不允许多个实例");
        }
    }
    public static Wanfeng getInstance(){
        return InnerClass.instance;
    }
}