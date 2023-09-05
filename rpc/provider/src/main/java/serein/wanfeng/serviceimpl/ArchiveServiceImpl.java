package serein.wanfeng.serviceimpl;

import serein.wanfeng.entity.Archive;
import serein.wanfeng.service.ArchiveService;
import serein.wanfeng.valueobject.ArchiveType;

/**
 * @Date: 2023-09-05 16:37
 * @Author: luozh
 * @Description:
 */

public class ArchiveServiceImpl implements ArchiveService {
    @Override
    public Archive createArchive(String title) {
        System.out.println("ArchiveServiceImpl.createArchive(): 已执行");
        return new Archive("A000", title, ArchiveType.RECORD);
    }
}
