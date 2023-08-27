package serein.wanfeng.singleton.hungry;

/**
 * @Date: 2023-06-16 0016 10:48
 * @Author: luozh
 * @Description:
 */
public class SingletonHungryTest {
    public static void main(String[] args) {
        Wanfeng w1 = Wanfeng.getInstance();
        Wanfeng w2 = Wanfeng.getInstance();
        System.out.println(w1 == w2);
    }
}

class Wanfeng{
    private static Wanfeng instance = new Wanfeng();
    private Wanfeng(){

    }
    public static Wanfeng getInstance(){
        return instance;
    }
}
