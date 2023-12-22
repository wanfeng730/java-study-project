package serein.wanfeng.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @date: 2023-12-20 21:18
 * @author: luozh
 * @description:
 * @since:
 */
public class ObserverTest {
    public static void main(String[] args) {
        Subject subject = new Subject();

        Person person1 = new Person();
        Person person2 = person1.clone();
        Cat cat = new Cat();

        subject.addObserver(person1);
        subject.addObserver(cat);
        subject.addObserver(person2);
        subject.notifyObserver("元旦放假啦～");
        System.out.println();

        subject.removeObserver(person2);
        subject.notifyObserver("需求做完啦～");
    }
}


class Subject{
    List<Observer> obsList = new ArrayList<>();

    public void addObserver(Observer observer){
        this.obsList.add(observer);
    }

    public void removeObserver(Observer observer){
        this.obsList.remove(observer);
    }

    public void notifyObserver(String message){
        obsList.forEach(observer -> observer.update(message));
    }

}

interface Observer{
    void update(String message);
}

class Person implements Observer, Cloneable{

    @Override
    public void update(String message) {
        System.out.println("我收到一条消息：" + message);
    }

    @Override
    public Person clone() {
        try {
            return (Person) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}


class Cat implements Observer{

    @Override
    public void update(String message) {
        System.out.println("喵喵喵～");
    }
}