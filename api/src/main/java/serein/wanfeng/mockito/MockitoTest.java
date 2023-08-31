package serein.wanfeng.mockito;

import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import serein.wanfeng.entity.Archive;
import serein.wanfeng.entity.BorrowItem;

/**
 * @Date: 2023-08-31 15:12
 * @Author: luozh
 * @Description: Mock框架Mockito的使用
 */

public class MockitoTest {

    @Mock
    private Archive archive;

    @Spy
    private BorrowItem borrowItem;

    @BeforeAll
    public static void beforeAll(){
        System.out.println("测试类开始");
    }

    @BeforeEach // @BeforeEach注解 该方法在每个测试方法执行前执行
    public void init(){
        System.out.println("测试方法开始");
        //启用Mock注解
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void test1() {
        //mock一个虚拟对象
        Archive archive = Mockito.mock(Archive.class);
        System.out.println(archive.getId());

        //验证方法是否被调用
        Mockito.verify(archive).getId();
    }

    @Test
    public void test2(){
        archive.setId("A001");
        System.out.println(archive.getId());
        //断言
        Assertions.assertEquals(null, archive.getId());

        //指定虚拟对象的返回值（打桩）
        Mockito.when(archive.getId()).thenReturn("A111");

        System.out.println(archive.getId());
        //断言
        Assertions.assertEquals("A111", archive.getId());
    }

    @Test
    public void test3(){
        borrowItem.setArchiveId("A001");
        System.out.println(borrowItem.getArchiveId());
        Assertions.assertEquals("A001", borrowItem.getArchiveId());
    }

    @AfterEach
    public void afterEach(){
        System.out.println("测试方法结束");
    }

    @AfterAll
    public static void afterAll(){
        System.out.println("测试类结束");
    }
}
