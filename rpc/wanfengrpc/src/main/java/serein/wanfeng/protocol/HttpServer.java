package serein.wanfeng.protocol;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.Server;
import org.apache.catalina.Service;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.core.StandardEngine;
import org.apache.catalina.core.StandardHost;
import org.apache.catalina.startup.Tomcat;

/**
 * @Date: 2023-09-05 16:43
 * @Author: luozh
 * @Description: http网络服务启动类
 */

public class HttpServer {

    public void start(String hostname, Integer port){
        //读取用户配置 application.yml
        Tomcat tomcat = new Tomcat();

        Server server = tomcat.getServer();
        Service service = server.findService("Tomcat");

        //设置端口，存于Connector对象中
        Connector connector = new Connector();
        connector.setPort(port);

        //设置主机名（域名）
        StandardEngine standardEngine = new StandardEngine();
        standardEngine.setDefaultHost(hostname);

        StandardHost standardHost = new StandardHost();
        standardHost.setName(hostname);

        String contextPath = "";
        StandardContext standardContext = new StandardContext();
        standardContext.setPath(contextPath);
        standardContext.addLifecycleListener(new Tomcat.FixContextListener());

        standardHost.addChild(standardContext);
        standardEngine.addChild(standardHost);

        service.setContainer(standardEngine);
        service.addConnector(connector);

        //添加网络请求处理的Servlet容器
        tomcat.addServlet(contextPath, "archive_dispatcher", new ArchiveDispatcherServlet());
        standardContext.addServletMappingDecoded("/*", "archive_dispatcher");

        //启动tomcat网络服务
        try {
            tomcat.start();
            tomcat.getServer().await();
        } catch (LifecycleException e) {
            e.printStackTrace();
        }

    }
}
