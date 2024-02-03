package serein.wanfeng.log;

import org.junit.Test;
import serein.wanfeng.util.SoutUtil;

import java.io.IOException;
import java.util.logging.*;

/**
 * @date: 2024-02-03 21:50
 * @author: luozh
 * @description: JUL（Java原生日志框架）
 * @since:
 */
public class JULTest {
    @Test
    public void test_quick_use(){
        //通过Logger的静态方法获取日志记录器对象，参数作为日志记录器的唯一标识
        Logger logger = Logger.getLogger(JULTest.class.getName());
        //打印日志输出
        logger.info("wanfeng log info print~");
        //通用方法指定日志级别
        logger.log(Level.WARNING, "warning message~");
        //使用占位符（用大括号包裹的数字，从0开始）
        String msg = "WANFENG_AXY";
        logger.log(Level.INFO, "put {0} into info log", msg);
    }

    @Test
    public void test_level() throws IOException {
        Logger logger = Logger.getLogger(JULTest.class.getName());

        // JUL默认的日志级别为INFO，级别大于等于INFO的日志输出

        //控制台日志处理器，添加到logger中后将日志输出在控制台中
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setFormatter(new SimpleFormatter());
        consoleHandler.setLevel(Level.FINE);
        logger.addHandler(consoleHandler);

        //文件日志处理器，看到加logger中后将日志输出到指定文件中
        FileHandler fileHandler = new FileHandler("src/main/resources/log/JULTest.log");
        fileHandler.setFormatter(new SimpleFormatter());
        fileHandler.setLevel(Level.ALL);
        logger.addHandler(fileHandler);

        //关闭系统默认级别配置
        logger.setUseParentHandlers(false);
        //自定义设置日志级别
        logger.setLevel(Level.INFO);

        /**
         * 日志等级是否输出的校验顺序：Logger的level -> Handler的level
         * 按顺序校验，级别全部校验通过的日志才会在处理器的对应位置输出（按如上配置，日志文件中只会输出info及以上的级别）
         */
        logger.severe("severe log");
        logger.warning("warning log");
        logger.info("info log");
        logger.config("config info");
        logger.fine("fine");
        logger.finer("finer log");
        logger.finest("finest log");
    }


    @Test
    public void test_parent(){
        //通过包名建立logger的父子关系
        Logger logger0 = Logger.getLogger("cn");
        Logger logger1 = Logger.getLogger("cn.wanfeng");

        //获取logger1的父logger
        System.out.println(logger1.getParent() == logger0);
        //所有日志记录器的顶级父对象，name为空字符串（即所有包名的顶级名称）
        System.out.println(logger0.getParent() + "   name: " + logger0.getParent().getName());
        SoutUtil.splitLine();

        //设置logger1级别
        logger1.setUseParentHandlers(false);    //若为true，则logger的级别继承自父logger
        ConsoleHandler consoleHandler1 = new ConsoleHandler();
        consoleHandler1.setFormatter(new SimpleFormatter());
        consoleHandler1.setLevel(Level.ALL);
        logger1.addHandler(consoleHandler1);
        logger1.setLevel(Level.ALL);

        logger0.severe("severe log");
        logger0.warning("warning log");
        logger0.info("info log");
        logger0.config("config info");
        logger0.fine("fine");
        logger0.finer("finer log");
        logger0.finest("finest log");

        SoutUtil.splitLine();

        logger1.severe("severe log");
        logger1.warning("warning log");
        logger1.info("info log");
        logger1.config("config info");
        logger1.fine("fine");
        logger1.finer("finer log");
        logger1.finest("finest log");
    }
}
