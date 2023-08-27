package serein.wanfeng.singleton.enums;

/**
 * @Date: 2023-06-16 16:13
 * @Author: luozh
 * @Description:
 * @Version：
 */
public class SingletonEnumTest {
    public static void main(String[] args) {
        Wanfeng w1 = Wanfeng.INSTANCE;
        Wanfeng w2 = Wanfeng.INSTANCE;

        System.out.println(w1 == w2);
    }
}

enum Wanfeng{
    INSTANCE;

    public void print(){
        System.out.println(this.hashCode());
    }
}