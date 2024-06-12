package serein.wanfeng.ftp;

import cn.hutool.core.io.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTPClient;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

/**
 * @date: 2024-06-11 13:44
 * @author: luozh
 * @description:
 * @since:
 */
@Slf4j
public class FtpUtilTest {

    private static final String HOST = "192.168.0.114";
    private static final Integer PORT = 21;
    private static final String USERNAME = "luozh";
    private static final String PASSWORD = "Dctm@1234";

    @Test
    public void testFtpDownload() {
        //远程文件相对路径（相对路径，从ftp共享的文件夹路径的下一级开始）
        //例如该文件在ftp服务器中的完整路径为 D:\ADesktop\wanfeng-typora\README.md
        //且ftp服务中配置的共享文件夹为 D:\ADesktop
        String remoteFilePath = "wanfeng-typora\\README.md";
        //下载位置（本机的文件夹路径）
        String downloadFolderPath = "/Users/wanfeng/Desktop";

        FTPClient ftpClient = FtpUtil.getClient(HOST, PORT, USERNAME, PASSWORD);

        String downloadFilePath = FtpUtil.downloadFile(ftpClient, remoteFilePath, downloadFolderPath);

        Assertions.assertTrue(FileUtil.exist(downloadFilePath));
    }

}
