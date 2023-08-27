package serein.wanfeng.singleton.lazy;

/**
 * @Date: 2023-06-16 0016 10:06
 * @Author: luozh
 * @Description: 单例模式
 */
public class SingletonLazyTest {
    public static void main(String[] args) {
//        Wanfeng w1 = Wanfeng.getInstance();
//        Wanfeng w2 = Wanfeng.getInstance();
//        System.out.println(w1 == w2);


        for(int i=0; i<5; i++){
            new Thread(() ->{
                Wanfeng wanfeng = null;
                try {
                    wanfeng = Wanfeng.getInstance();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(wanfeng);
            }).start();
        }

    }
}

class Wanfeng{
    private volatile static Wanfeng instance;
    private Wanfeng(){

    }
    public synchronized static Wanfeng getInstance() throws InterruptedException {
        if(instance == null){
            //加锁防多线程情况下创建多个实例
            synchronized (Wanfeng.class){
                //double check：可能存在多个线程同时等锁的情况，则进行二次检查，防止第二个线程拿到锁后再次实例化
                if(instance == null){
                    instance = new Wanfeng();
                }
            }
        }
        return instance;
    }
}
