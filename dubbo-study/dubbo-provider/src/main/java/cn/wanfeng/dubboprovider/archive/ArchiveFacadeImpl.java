package cn.wanfeng.dubboprovider.archive;

import cn.wanfeng.dubbofacade.archive.ArchiveFacade;
import serein.wanfeng.entity.Archive;
import serein.wanfeng.valueobject.ArchiveType;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @date: 2024-08-18 05:25
 * @author: luozh
 * @description:
 * @since:
 */
public class ArchiveFacadeImpl implements ArchiveFacade {

    @Override
    public List<Archive> getAllRecordTypeArchive() {
        List<Archive> archiveList = Archive.mockList();
        return archiveList.stream().filter(archive -> ArchiveType.RECORD.equals(archive.getType())).collect(Collectors.toList());
    }
}
