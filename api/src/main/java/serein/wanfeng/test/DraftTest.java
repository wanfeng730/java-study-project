package serein.wanfeng.test;

import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;
import serein.wanfeng.entity.Archive;
import serein.wanfeng.valueobject.ArchiveType;

import java.lang.reflect.InvocationTargetException;
import java.security.PublicKey;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Date: 2023-09-06 16:49
 * @Author: luozh
 * @Description:
 */

public class DraftTest {
    @Test
    public void test() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Map describe = BeanUtils.describe(new Archive("1", "2", ArchiveType.RECORD));
        System.out.println();
    }
}
