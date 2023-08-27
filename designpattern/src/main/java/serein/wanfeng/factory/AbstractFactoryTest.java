package serein.wanfeng.factory;

/**
 * @Date: 2023-08-27 18:52
 * @Author: luozh
 * @Description: 抽象工厂模式
 */

public class AbstractFactoryTest {
    public static void main(String[] args) {
        MySQLDatabaseUtils mySQLDatabaseUtils = new MySQLDatabaseUtils();
        SQLConnection connection = mySQLDatabaseUtils.getConnection();
        SQLCommand command = mySQLDatabaseUtils.getCommand();
        connection.connect();
        command.execute();
    }
}

//数据库连接抽象接口
interface SQLConnection{
    void connect();
}

//MySQL数据库连接具体实现
class MySQLConnection implements SQLConnection{
    @Override
    public void connect() {
        System.out.println("MySQL数据库已连接");
    }
}

//数据库命令抽象接口
interface SQLCommand{
    void execute();
}

//MySQL数据库命令具体实现
class MySQLCommand implements SQLCommand{

    @Override
    public void execute() {
        System.out.println("MySQL命令执行已成功");
    }
}

//数据库工具类抽象接口
interface DatabaseUtils{
    SQLConnection getConnection();
    SQLCommand getCommand();
}

//MySQL数据库工具类具体实现
class MySQLDatabaseUtils implements DatabaseUtils{

    @Override
    public SQLConnection getConnection() {
        return new MySQLConnection();
    }

    @Override
    public SQLCommand getCommand() {
        return new MySQLCommand();
    }
}