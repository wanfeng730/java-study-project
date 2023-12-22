package serein.wanfeng.facade;

import javax.lang.model.element.VariableElement;

/**
 * @date: 2023-12-20 17:32
 * @author: luozh
 * @description: 门面模式
 * @since:
 */
public class FacadeTest {
    public static void main(String[] args) {
        Facade facade = new Facade();
        facade.method();
    }
}


class Facade{
    SubSystem1 subSystem1 = new SubSystem1();
    SubSystem2 subSystem2 = new SubSystem2();

    public void method(){
        System.out.println(subSystem1.method1("wanfeng"));;
        System.out.println(subSystem2.method2("jingyu"));;
    }
}


class SubSystem1{
    public String method1(String value){
        return "sub system 1 receive value : " + value;
    }
}


class SubSystem2{
    public String method2(String value){
        return "sub system 2 receive value : " + value;
    }
}
