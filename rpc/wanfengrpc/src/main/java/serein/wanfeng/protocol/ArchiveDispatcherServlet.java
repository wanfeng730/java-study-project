package serein.wanfeng.protocol;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Date: 2023-09-05 16:57
 * @Author: luozh
 * @Description: 网络请求过滤处理层
 */

public class ArchiveDispatcherServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //处理网络请求
        HttpServerHandler httpServerHandler = new HttpServerHandler();
        httpServerHandler.handle(req, resp);
    }
}
