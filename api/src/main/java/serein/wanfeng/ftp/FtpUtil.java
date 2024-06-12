package serein.wanfeng.ftp;

import cn.hutool.core.io.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * @date: 2024-06-11 11:42
 * @author: luozh
 * @description: ftp文件传输工具类
 */
@Slf4j
public class FtpUtil {

    /**
     * 获取一个ftp连接
     *
     * @param host     ip地址
     * @param port     端口
     * @param username 用户名
     * @param password 密码
     * @return 返回ftp连接对象
     */
    public static FTPClient getClient(String host, Integer port, String username, String password) {
        FTPClient ftpClient = new FTPClient();

        try {
            // 连接服务器
            ftpClient.connect(host, port);

            int reply = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                log.error("无法连接至ftp服务器， host:{}, port:{}", host, port);
                ftpClient.disconnect();
                return null;
            }

            // 登入服务器
            boolean login = ftpClient.login(username, password);
            if (!login) {
                log.error("登录失败， 用户名或密码错误");
                ftpClient.logout();
                ftpClient.disconnect();
                return null;
            }

            // 连接并且成功登陆ftp服务器
            log.info("login success ftp server, host:{}, port:{}, user:{}", host, port, username);

            // 设置通道字符集， 要与服务端设置一致
            ftpClient.setControlEncoding("UTF-8");
            // 设置文件传输编码类型， 字节传输：BINARY_FILE_TYPE, 文本传输：ASCII_FILE_TYPE， 建议使用BINARY_FILE_TYPE进行文件传输
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            // 动模式: enterLocalActiveMode(),被动模式: enterLocalPassiveMode(),一般选择被动模式
            ftpClient.enterLocalPassiveMode();
            // 切换目录
            //ftpClient.changeWorkingDirectory("xxxx");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return ftpClient;
    }

    /**
     * 断开ftp连接
     *
     * @param ftpClient ftp连接客户端
     */
    public static void closeClient(FTPClient ftpClient) {
        if (ftpClient == null) {
            return;
        }
        try {
            log.info("断开ftp连接， host:{}, port:{}", ftpClient.getPassiveHost(), ftpClient.getPassivePort());
            ftpClient.logout();
            ftpClient.disconnect();
        } catch (Exception e) {
            log.error("ftp连接断开异常， 请检查");
            throw new RuntimeException(e);
        }

    }

    /**
     * 文件下载
     *
     * @param ftpClient      ftp连接客户端
     * @param remoteFilePath 文件路径
     */
    public static String downloadFile(FTPClient ftpClient, String remoteFilePath, String downloadFolderPath) {
        if (Objects.isNull(ftpClient) || StringUtils.isAnyBlank(remoteFilePath, downloadFolderPath)) {
            log.error("通过ftp下载文件参数缺少，不进行下载！");
            return null;
        }

        // 中文目录处理存在问题， 转化为ftp能够识别中文的字符集
        String remotePath;
        try {
            remotePath = new String(remoteFilePath.getBytes(StandardCharsets.UTF_8), FTP.DEFAULT_CONTROL_ENCODING);
        } catch (UnsupportedEncodingException e) {
            remotePath = remoteFilePath;
        }

        try {
            //从ftp获取流
            InputStream inputStream = ftpClient.retrieveFileStream(remotePath);
            if (inputStream == null) {
                log.error("{}在ftp服务器中不存在，请检查", remoteFilePath);
                return null;
            }
            //新建本地下载文件
            String downloadFilePath = downloadFolderPath + File.separator + FileUtil.getName(remoteFilePath);
            File downloadFile = new File(downloadFilePath);
            if (downloadFile.exists()) {
                log.info("文件{}已存在，将进行覆盖...", downloadFilePath);
                FileUtil.del(downloadFile);
            }
            FileUtil.newFile(downloadFilePath);
            //将流写入本地文件
            FileUtil.writeFromStream(inputStream, downloadFile);
            //关闭流
            inputStream.close();

            // 关闭流之后必须执行，否则下一个文件导致流为空
            boolean complete = ftpClient.completePendingCommand();
            if (complete) {
                log.info("文件{}下载完成", remotePath);
                return downloadFilePath;
            } else {
                log.error("文件{}下载失败", remotePath);
                return null;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
