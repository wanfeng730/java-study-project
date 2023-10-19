package serein.wanfeng.easyexcel.infoclass;


import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.converters.integer.IntegerNumberConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import serein.wanfeng.easyexcel.BorrowStatusConverter;

import java.util.Date;

/**
 * @Date: 2023-10-18 10:54
 * @Author: luozh
 * @Description: 查档登记记录解析对象
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BorrowFormImportInfo {
    /**
     * 单据编号（实际保存的借阅单编号）
     */
    private String borrowingFormNo;
    /**
     * 借阅单编号（用于关联档案清单的借阅档案，实际保存的借阅单编号需另外生成）
     */
    @ExcelProperty(value = "借阅单编号")
    private String importBorrowingFormNo;
    /**
     * 借阅状态（枚举类转换）
     */
    @ExcelProperty(value = "状态")
    private String borrowStatus;
    /**
     * 申请人类型（枚举类转换，01个人、02单位）
     */
    @ExcelProperty(value = "申请人类型")
    private String applicantType;
    /**
     * 申请人
     */
    @ExcelProperty(value = "申请人")
    private String borrowingUserName;
    /**
     * 性别
     */
    @ExcelProperty(value = "性别")
    private String applicantGender;
    /**
     * 联系电话
     */
    @ExcelProperty(value = "联系电话")
    private String applicantTel;
    /**
     * 证件类型(01：居民身份证，02：介绍信)
     */
    @ExcelProperty(value = "证件类型")
    private String applicantCertificateType;
    /**
     * 申请人身份证
     */
    @ExcelProperty(value = "申请人身份证")
    private String applicantCertificateNum;
    /**
     * 所属地区（枚举类转换）
     */
    @ExcelProperty(value = "所属地区")
    private String applicantArea;
    /**
     * 所属单位
     */
    @ExcelProperty(value = "所属单位")
    private String applicantUnit;
    /**
     * 民族
     */
    @ExcelProperty(value = "民族")
    private String applicantNation;
    /**
     * 户籍地址
     */
    @ExcelProperty(value = "户籍地址")
    private String applicantAddress;
    /**
     * 查阅日期（日期格式转换）
     */
    @ExcelProperty(value = "查阅日期")
    private String consulTime;
    /**
     * 查档目的
     */
    @ExcelProperty(value = "查档目的")
    private String applyReason;
    /**
     * 查档内容
     */
    @ExcelProperty(value = "查档内容")
    private String searchArchiveContent;
    /**
     * 代理人姓名
     */
    @ExcelProperty(value = "代理人姓名")
    private String agentName;
    /**
     * 代理人性别
     */
    @ExcelProperty(value = "代理人性别")
    private String agentGender;
    /**
     * 代理人联系电话
     */
    @ExcelProperty(value = "代理人联系电话")
    private String agentTel;
    /**
     * 代理人证件类型（枚举类转换）
     */
    @ExcelProperty(value = "代理人证件类型")
    private String agentCertificateType;
    /**
     * 代理人所属地区（枚举类转换）
     */
    @ExcelProperty(value = "代理人所属地区")
    private String agentArea;
    /**
     * 代理人身份证
     */
    @ExcelProperty(value = "代理人身份证")
    private String agentCertificateNum;
    /**
     * 代理人所属单位
     */
    @ExcelProperty(value = "代理人所属单位")
    private String agentUnit;
    /**
     * 代理人民族
     */
    @ExcelProperty(value = "代理人民族")
    private String agentNation;
    /**
     * 代理人户籍地址
     */
    @ExcelProperty(value = "代理人户籍地址")
    private String agentAddress;
    /**
     * 借阅天数（类型转换）
     */
    @ExcelProperty(value = "借阅天数")
    private String applyDays;
    /**
     * 审核方式（转成boolean值，是否为窗口审批）
     */
    @ExcelProperty(value = "审核方式")
    private String windowApproval;
    /**
     * 借阅方式（枚举类转换）
     */
    @ExcelProperty(value = "借阅方式")
    private String borrowType;
    /**
     * 审批状态（取默认值“审批通过”）
     */
    //private String approvalStatus;
    /**
     * 查档方式（枚举类转换）
     */
    @ExcelProperty(value = "查档方式")
    private String consultWay;

    //************************ 评价单内容 **************************//
    /**
     * 利用件次（类型转换）
     */
    @ExcelProperty(value = "利用件次")
    private String recordUsingTime;
    /**
     * 利用卷次（类型转换）
     */
    @ExcelProperty(value = "利用卷次")
    private String volumeUsingTime;
    /**
     * 打印张数（类型转换）
     */
    @ExcelProperty(value = "打印张数")
    private String printNumber;
    /**
     * 打印份数（类型转换）
     */
    @ExcelProperty(value = "打印份数")
    private String copyNumber;
    /**
     * 利用人次（类型转换）
     */
    @ExcelProperty(value = "利用人次")
    private String userUsing;
    /**
     * 满意度（枚举类转换）
     */
    @ExcelProperty(value = "满意度")
    private String usingEffect;
    /**
     * 评价内容
     */
    @ExcelProperty("评价内容")
    private String remark;

}
