package io.lance.gradle.common.core.bean;

import java.util.Date;

/**
 * @desc: 日志
 * @author: lance
 * @time: 2017-10-23 15:08
 */
public class LogRecord extends BaseBean {

    /**
     * 操作人
     */
    private String operator;

    /**
     * 操作人编码
     */
    private Integer operatorId;

    /**
     * 操作内容
     */
    private String content;

    /**
     * 操作记录
     */
    private Integer objId;

    /**
     * 操作表
     */
    private String tableName;

    /**
     * 操作时间
     */
    private Date createTime = new Date(System.currentTimeMillis());

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getObjId() {
        return objId;
    }

    public void setObjId(Integer objId) {
        this.objId = objId;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "LogRecord{" +
                "operator='" + operator + '\'' +
                ", operatorId=" + operatorId +
                ", content='" + content + '\'' +
                ", objId=" + objId +
                ", tableName='" + tableName + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
