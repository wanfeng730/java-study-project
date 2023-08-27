package serein.wanfeng.factory;

/**
 * @Date: 2023-08-27 17:58
 * @Author: luozh
 * @Description: 工厂模式
 */

public class WanfengFactoryTest {
    public static void main(String[] args) {
        WanfengFactory wanfengFactory = new WanfengFactory();
        Wanfeng wanfeng = wanfengFactory.getWanfeng();
        wanfeng.printId();
    }
}



class Wanfeng {
    public void printId(){
        System.out.println("STU123456");
    }
}


class WanfengFactory {
    private Wanfeng createObject(){

        return new Wanfeng();
    }

    public Wanfeng getWanfeng(){
        Wanfeng wanfeng = createObject();
        return wanfeng;
    }
}