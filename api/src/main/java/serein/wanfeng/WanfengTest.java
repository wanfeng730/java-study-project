package serein.wanfeng;

import cn.hutool.core.io.FileUtil;
import com.alibaba.fastjson2.JSON;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @date: 2024-06-13 15:49
 * @author: luozh
 * @description:
 * @since:
 */
public class WanfengTest {

    private static final Logger testLogger = LoggerFactory.getLogger(WanfengTest.class);

    @Test
    public void testSeas() {
        System.out.println(getFilePathByBlobId(103513582L));
        System.out.println(getFilePathByBlobId(103513583L));
    }

    private static String getFilePathByBlobId(long blobId) {
        return (blobId >>> 56 & 0xFF) +
                "/" + (blobId >>> 48 & 0xFF) +
                "/" + (blobId >>> 40 & 0xFF) +
                "/" + (blobId >>> 32 & 0xFF) +
                "/" + (blobId >>> 24 & 0xFF) +
                "/" + (blobId >>> 16 & 0xFF) +
                "/" + (blobId >>> 8 & 0xFF) +
                "/" + (blobId & 0xFF);
    }

    @Test
    public void test0704() {
        try {
            System.out.println("try");
            int c = 1 / 0;
        } catch (Exception e) {
            System.out.println("catch");
            int c = 1 / 0;
            throw new RuntimeException(e);
        } finally {
            System.out.println("finally");
        }
    }

    @Test
    public void test0709() {
        System.out.printf("%03d", 12);
    }

    @Test
    public void test() {
        try {
            File file = new File("/Users/wanfeng/Desktop/new-file");
            File newFile = FileUtil.rename(file, "new-file.txt", true);
            System.out.println();
        } catch (Exception e) {
            testLogger.error("error message", e);
            throw new RuntimeException(e);
        }
    }

    @Test
    public void test0802() {
        String json = "{\"record\":{\"version_no\":\"78\",\"metadata_scheme_name\":\"文书档案(件)\",\"block\":[{\"name\":\"归档信息\",\"block\":[{\"name\":\"资源标识\",\"property\":[{\"name\":\"archival_id\",\"title\":\"档号\",\"content\":\"0813-WS-05\"},{\"name\":\"article_number\",\"title\":\"文号\",\"content\":\"5\"},{\"name\":\"fonds_id\",\"title\":\"全宗号\",\"content\":\"xsqdtz\"},{\"name\":\"item_code\",\"title\":\"件号\",\"content\":\"5\"},{\"name\":\"archive_1st_category\",\"title\":\"一级门类编码\",\"content\":\"WS\"},{\"name\":\"category_code\",\"title\":\"门类编码\",\"content\":\"WS\"},{\"name\":\"classification_number\",\"title\":\"类别号\"},{\"name\":\"da_label_name\",\"title\":\"标签名称\",\"content\":\"\"}]},{\"name\":\"访问控制信息\",\"property\":[{\"name\":\"security_class\",\"title\":\"密级\"},{\"name\":\"open_class\",\"title\":\"开放等级\",\"content\":\"A\"},{\"name\":\"secrecy_period\",\"title\":\"保密期限\"},{\"name\":\"released_network\",\"title\":\"发布网段\"},{\"name\":\"decryption_status\",\"title\":\"解密标识\"}]},{\"name\":\"说明信息\",\"property\":[{\"name\":\"retention_period\",\"title\":\"保管期限\",\"content\":\"C\"},{\"name\":\"fonds_name\",\"title\":\"全宗名称\",\"content\":\"萧山区大同镇\"},{\"name\":\"original_status\",\"title\":\"原件状态\"},{\"name\":\"da_remark\",\"title\":\"备注\"},{\"name\":\"physical_archive_location\",\"title\":\"实体存址号\"},{\"name\":\"job_no\",\"title\":\"工号\"},{\"name\":\"file_year\",\"title\":\"年度\",\"content\":\"2024\"},{\"name\":\"filed_by\",\"title\":\"归档人\"},{\"name\":\"archives_num\",\"title\":\"归档份数\",\"content\":\"1\"},{\"name\":\"filed_date\",\"title\":\"归档日期\",\"content\":\"2024-08-13\"},{\"name\":\"file_department\",\"title\":\"归档部门\"},{\"name\":\"doc_date\",\"title\":\"形成日期\",\"content\":\"20240813\"},{\"name\":\"description\",\"title\":\"描述\"},{\"name\":\"digitized_status\",\"title\":\"数字化状态\"},{\"name\":\"owner\",\"title\":\"文件所有者\"},{\"name\":\"exist_document\",\"title\":\"是否有原文\",\"content\":\"true\"},{\"name\":\"author\",\"title\":\"责任者\"},{\"name\":\"material_num\",\"title\":\"载体数量\"},{\"name\":\"carrier_type\",\"title\":\"载体类型\",\"content\":\"02\"},{\"name\":\"doc_attachments\",\"title\":\"附件名称\"},{\"name\":\"page_num\",\"title\":\"页号\"},{\"name\":\"doc_pages\",\"title\":\"页数\"},{\"name\":\"title\",\"title\":\"题名\",\"content\":\"0813文书05\"},{\"name\":\"flh\",\"title\":\"分类号\"}]},{\"name\":\"管理信息\"}]},{\"name\":\"业务内容\",\"block\":[{\"name\":\"过程信息\"},{\"name\":\"内容信息\",\"property\":[{\"name\":\"BSMTX\",\"title\":\"不扫描图像\"},{\"name\":\"zhuanti\",\"title\":\"专题\"},{\"name\":\"main_receiver_department\",\"title\":\"主送机关\"},{\"name\":\"ZTC\",\"title\":\"主题词\"},{\"name\":\"serial_number_of_copies\",\"title\":\"份号\"},{\"name\":\"QWBS\",\"title\":\"全文标识\"},{\"name\":\"document_identifier\",\"title\":\"公文标识\"},{\"name\":\"GJC\",\"title\":\"关键词\"},{\"name\":\"da_is_related\",\"title\":\"关键词关联\"},{\"name\":\"FLMC\",\"title\":\"分类名称\"},{\"name\":\"s_create_date\",\"title\":\"创建日期\",\"content\":\"2024-08-13\"},{\"name\":\"printing_date\",\"title\":\"印发日期\"},{\"name\":\"printing_and_sending_department\",\"title\":\"印发机关\"},{\"name\":\"YCZH\",\"title\":\"原全宗号\"},{\"name\":\"YWSL\",\"title\":\"原文数量\"},{\"name\":\"release_level\",\"title\":\"发布层次\"},{\"name\":\"issued_number_of_document\",\"title\":\"发文字号\"},{\"name\":\"signature_of_document_issuing_agency\",\"title\":\"发文机关或签发人署名\"},{\"name\":\"identification_of_document_issuer\",\"title\":\"发文机关标志\"},{\"name\":\"color_label\",\"title\":\"四色标签\",\"content\":\"蓝色\"},{\"name\":\"disposal_time\",\"title\":\"处理时间\"},{\"name\":\"disposal_type\",\"title\":\"处理类型\"},{\"name\":\"disposal_result\",\"title\":\"处理结果\"},{\"name\":\"disposal_actor\",\"title\":\"处理者\"},{\"name\":\"disposal_department\",\"title\":\"处理部门\"},{\"name\":\"CFDD\",\"title\":\"存放地点\"},{\"name\":\"XCRQ_date\",\"title\":\"成文日期\"},{\"name\":\"copy_of_department\",\"title\":\"抄送机关\"},{\"name\":\"ZZ\",\"title\":\"指针\"},{\"name\":\"KZF\",\"title\":\"控制符\"},{\"name\":\"doc_tr_id\",\"title\":\"收发文标识\"},{\"name\":\"electronic_file_identifier\",\"title\":\"数字对象标识\"},{\"name\":\"sjlx\",\"title\":\"数据类型\"},{\"name\":\"WJSSRM\",\"title\":\"文件所涉人名\"},{\"name\":\"document_type\",\"title\":\"文种\"},{\"name\":\"da_digitization\",\"title\":\"是否数据化\"},{\"name\":\"QXDM\",\"title\":\"期限代码\"},{\"name\":\"ZZJGHWT\",\"title\":\"机构（问题）\"},{\"name\":\"ZZJGHWTCODE\",\"title\":\"机构（问题）代码\"},{\"name\":\"seq_label\",\"title\":\"条款序号\",\"content\":\"1\"},{\"name\":\"da_tag\",\"title\":\"标签\"},{\"name\":\"AJH\",\"title\":\"案卷号\"},{\"name\":\"environment_information\",\"title\":\"环境信息\"},{\"name\":\"doc_dzwjh\",\"title\":\"电子文件号\"},{\"name\":\"HH\",\"title\":\"盒号\"},{\"name\":\"MLH111\",\"title\":\"目录号\"},{\"name\":\"hardware_environment\",\"title\":\"硬件环境\"},{\"name\":\"GB\",\"title\":\"稿本\"},{\"name\":\"signer\",\"title\":\"签发人\"},{\"name\":\"lbmc\",\"title\":\"类别名称\"},{\"name\":\"emergency_degree\",\"title\":\"紧急程度\"},{\"name\":\"software_environment\",\"title\":\"软件环境\"},{\"name\":\"ZTGG\",\"title\":\"载体规格\"},{\"name\":\"attachment\",\"title\":\"附件说明\"}]}]},{\"name\":\"电子文件\",\"file\":[{\"checksum_type\":\"md5\",\"size\":1722817,\"name\":\"0813-WS-05.pdf\",\"format\":\"pdf\",\"checksum\":\"97a3e08f0f4b9faf25eb0793b31d8f6a\",\"creation_date\":\"2024-08-13T15:47:39.550\",\"isNew\":true,\"modify_date\":\"2024-08-13T15:47:39.550\",\"seq\":1,\"url\":\"/3dab8e47-7629-4ac3-be1a-e71f43010821/0813-WS-05.pdf\"}]}],\"metadata_scheme_code\":\"WS·A-RECORD\",\"create_date\":\"2024-08-13\",\"modify_date\":\"2024-08-13\",\"template_id\":\"61148fe7-cc24-4654-b146-4972b2a67c41\",\"name\":\"文书档案(件)\",\"id\":\"7538bf00-3bd8-40a4-863c-6460c94de726\"}}";
        String jsonPath = "$.record..property[?(@.name=='exist_document')]";
        DocumentContext documentContext = JsonPath.parse(json);
        documentContext.put(jsonPath, "content", "中共杭州市委办公室");


        Object object = new GsonBuilder().create().fromJson("[\"/amberdata/dataimport/dataimport_bf/公房土地登记申请档案卷/J064/001/002/J064-001-002-043.pdf\"]", new TypeToken<List<String>>() {
        }.getType());
        System.out.println();
    }

    @Test
    public void test0826() throws ParseException {
        String docDate = "20201200";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Date date = dateFormat.parse(docDate);

        System.out.println(date);
    }


    @Test
    public void test0908() {
        String text = "         \n\t  ";
        System.out.println(StringUtils.isBlank(text));
    }

    @Test
    public void test0911() {
        Object o = JSON.parseArray("[\"e/\\全文挂接\\0094\\文书一文一件\\0094\\WS·2021\\Y\\0094-WS·2021-D10-0533.pdf\"]", String.class);
        System.out.println(o);
    }

    @Test
    public void test1006() throws InterruptedException {
        long step1 = System.currentTimeMillis();
        Thread.sleep(2098);
    }
}
