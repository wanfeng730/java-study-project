package serein.wanfeng.templatemethod;

/**
 * @date: 2023-12-20 21:02
 * @author: luozh
 * @description:
 * @since:
 */
public class TemplateMethodTest {
    public static void main(String[] args) {
        MySQLConnector mySQLConnector = new MySQLConnector();
        mySQLConnector.connect();
    }
}


abstract class SQLConnector{
    public void connect(){
        configure();

        System.out.println("发起连接");

        System.out.println("关闭连接");
    }

    abstract void configure();
}

class MySQLConnector extends SQLConnector{

    @Override
    void configure() {
        System.out.println("配置mysql的ip、端口、用户名、密码");
    }
}
