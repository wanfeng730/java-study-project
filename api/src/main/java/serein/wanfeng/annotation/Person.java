package serein.wanfeng.annotation;

import java.lang.annotation.Target;

/**
 * @date: 2023-12-22 15:21
 * @author: luozh
 * @description:
 * @since:
 */

public class Person {

    @WanfengInit
    public void walk(){
        System.out.println("person walk");
    }

}
