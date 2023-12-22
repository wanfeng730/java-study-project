package serein.wanfeng.decorator;

/**
 * @date: 2023-12-20 20:03
 * @author: luozh
 * @description:
 * @since:
 */
public class DecoratorTest {
    public static void main(String[] args) {
        ColorfulDecorator colorfulDecorator = new ColorfulDecorator(new WatermarkDecorator(new PrintComponent()));
        colorfulDecorator.print();
    }
}


interface Component{
    void print();
}

class PrintComponent implements Component{

    @Override
    public void print() {
        System.out.println("打印功能...");
    }
}

abstract class Decorator implements Component{
    Component component;

    public Decorator(Component component){
        this.component = component;
    }
}

class WatermarkDecorator extends Decorator{

    public WatermarkDecorator(Component component) {
        super(component);
    }

    @Override
    public void print() {
        component.print();
        System.out.println("增加水印...");
    }
}

class ColorfulDecorator extends Decorator{

    public ColorfulDecorator(Component component) {
        super(component);
    }

    @Override
    public void print() {
        component.print();
        System.out.println("彩色打印...");
    }
}