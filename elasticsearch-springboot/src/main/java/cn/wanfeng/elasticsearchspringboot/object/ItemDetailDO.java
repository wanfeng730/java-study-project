package cn.wanfeng.elasticsearchspringboot.object;

import com.alibaba.excel.annotation.ExcelProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @date: 2024-07-25 17:10
 * @author: luozh
 * @description:
 * @since:
 */
@Data
@Document(indexName = "swap_platform_sys_item_detail", shards = 1, replicas = 1)
public class ItemDetailDO {
    /**
     * 电子文件号
     */
    @Field(name = "document_number", type = FieldType.Keyword)
    private String documentNumber;

    /**
     * 办件名称
     */
    @Field(name = "project_name", type = FieldType.Keyword)
    private String projectName;

    /**
     * 办件编码
     */
    @Field(name = "project_id", type = FieldType.Keyword)
    private String projectId;

    /**
     * 办件系统
     */
    @Field(name = "item_system", type = FieldType.Keyword)
    private String itemSystem;

    /**
     * 发送方系统交换方式
     * 交换方式 按区域交换、指定接收方接收
     */
    @Field(name = "swap_method", type = FieldType.Keyword)
    private String swapMethod;

    /**
     * 事项名称
     */
    @Field(name = "task_name", type = FieldType.Keyword)
    private String taskName;

    /**
     * 区域
     */
    @Field(name = "area", type = FieldType.Keyword)
    private String area;

    /**
     * 部门
     * 实施主体名称
     */
    @Field(name = "department_name", type = FieldType.Keyword)
    private String departmentName;

    /**
     * 年度
     */
    @Field(name = "year")
    private Integer year;

    /**
     * 形成时间
     */
    @Field(name = "doc_date", type = FieldType.Date,
            format = DateFormat.custom,
            pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING,
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8")
    private Date docDate;

    /**
     * 状态
     */
    @ExcelProperty(value = "状态", index = 8)
    @Field("status")
    private Integer status;

    /**
     * 生命周期时间
     */
    @Field(name = "lifecycle", type = FieldType.Keyword)
    private String lifecycle;

    /**
     * 发送单位
     */
    @Field(name = "unit_code", type = FieldType.Keyword)
    private String unitCode;

    /**
     * 接收单位
     */
    @Field(name = "receive_unit_code", type = FieldType.Keyword)
    private String receiveUnitCode;

    /**
     * 最终接收单位
     */
    @Field(name = "final_receive_unit_code", type = FieldType.Keyword)
    private String finalReceiveUnitCode;
    /**
     * 门类名(顶层门类)
     */
    @Field(name = "category_name", type = FieldType.Keyword)
    private String categoryName;

    /**
     * 门类编码(顶层门类)
     */
    @Field(name = "category_code", type = FieldType.Keyword)
    private String categoryCode;

    @Field(name = "type", type = FieldType.Keyword)
    private String type;

    /**
     * 事项类型代码
     */
    @Field(name = "item_type_code", type = FieldType.Keyword)
    private String itemTypeCode;

    /**
     * 主项代码
     */
    @Field(name = "QL_MAINITEM_ID", type = FieldType.Keyword)
    private String qlMainItemId;

    /**
     * 子项代码
     */
    @Field(name = "QL_SUBITEM_ID", type = FieldType.Keyword)
    private String qlSubitemId;

    /**
     * 业务代码项
     */
    @Field(name = "business_code_item", type = FieldType.Keyword)
    private String businessCodeItem;

    /**
     * 业务办理项编码
     */
    @Field(name = "business_code", type = FieldType.Keyword)
    private String businessCode;

    /**
     * 保管期限
     */
    @Field(name = "storage_period", type = FieldType.Keyword)
    private String storagePeriod;

    /**
     * 流水号
     */
    @Field(name = "serial_number")
    private Integer serialNumber;

    /**
     * 扩展项
     */
    @Field(name = "extensions", type = FieldType.Keyword)
    private String extensions;

    /**
     * 信息系统描述
     */
    @Field(name = "information_system_description", type = FieldType.Keyword)
    private String informationSystemDescription;


    /**
     * 事项类型
     */
    @Field(name = "task_type", type = FieldType.Keyword)
    private String taskType;

    /**
     * 事项版本
     */
    @Field(name = "task_version", type = FieldType.Keyword)
    private String taskVersion;

    /**
     * 业务办理项扩展码
     */
    @Field(name = "business_extended_code", type = FieldType.Keyword)
    private String businessExtendedCode;

    /**
     * 事项实施编码
     */
    @Field(name = "task_code", type = FieldType.Keyword)
    private String taskCode;

    /**
     * 办理结果
     */
    @Field(name = "result", type = FieldType.Keyword)
    private String result;

    /**
     * 办件类型
     */
    @Field(name = "service_type", type = FieldType.Keyword)
    private String serviceType;

    /**
     * 办结时间
     */
    @Field(name = "transaction_time", type = FieldType.Date,
            format = DateFormat.custom,
            pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING,
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8")
    private Date transactionTime;

    @Field(name = "department_code", type = FieldType.Keyword)
    private String departmentCode;

    @Field(name = "area_code", type = FieldType.Keyword)
    private String areaCode;

    /**
     * 归档时间
     */
    @Field(name = "archive_time", type = FieldType.Date,
            format = DateFormat.custom,
            pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING,
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8")
    private Date archiveTime;

    @Field(name = "secrecy_period", type = FieldType.Keyword)
    private String secrecyPeriod;

    @Field(name = "security_classification", type = FieldType.Keyword)
    private String securityClassification;

    @Field(name = "retention_period", type = FieldType.Keyword)
    private String retentionPeriod;

    /**
     * 上链结果 0:待上链,1:上链成功,2:上链失败
     */
    @Field("chain_status")
    private Integer chainStatus;

    /**
     * 上链时间
     */
    @Field(name = "chain_date", type = FieldType.Date,
            format = DateFormat.custom,
            pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING,
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8")
    private Date chainDate;

    /**
     * 区块链交易号
     */
    @Field(name = "transaction_id", type = FieldType.Keyword)
    private String transactionId;

    /**
     * sm3摘要
     */
    @Field(name = "sm3", type = FieldType.Keyword)
    private String sm3;

    /**
     * oss的id
     */
    @Field(name = "oss_info_id", type = FieldType.Keyword)
    private String ossInfoId;

    /**
     * 存储的相对路径（不含桶名）
     */
    @Field(name = "storage_path", type = FieldType.Keyword)
    private String storagePath;

    @Field(name = "create_time", type = FieldType.Date, format = DateFormat.custom, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;

    @Field(name = "modify_time", type = FieldType.Date, format = DateFormat.custom, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime modifyTime;

    /**
     * 发送方账本对账状态
     */
    @Field(name = "sender_reconciliation_status", type = FieldType.Keyword)
    private String senderReconciliationStatus;

    /**
     * 接收方账本对账状态
     */
    @Field(name = "receiver_reconciliation_status", type = FieldType.Keyword)
    private String receiverReconciliationStatus;
}
