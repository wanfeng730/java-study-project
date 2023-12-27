package serein.wanfeng.annotation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @date: 2023-12-22 15:23
 * @author: luozh
 * @description:
 * @since:
 */
public class PersonTest {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<Person> personClass = Person.class;
        //遍历方法
        for (Method method : personClass.getMethods()) {
            //是否有对应的注解
            boolean annotationPresent = method.isAnnotationPresent(WanfengInit.class);
            if(annotationPresent){
                method.invoke(personClass.getConstructor(null).newInstance(null));
            }
        }
    }
}
