package ${packageName}.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
* ${dataName}
*
* @author <a href="https://github.com/Gliangquan">小梁</a>
*/
@TableName(value = "${dataKey}")
@Data
public class ${upperDataKey} implements Serializable {
<#list columns as column>

    /**
    * ${column.columnComment}
    */
    <#if column.columnName == "id">
    @TableId(type = IdType.ASSIGN_ID)
    <#else>
    @TableField(value = "${column.columnName}")
    </#if>
    <#if column.columnName == "isDelete">
    @TableLogic
    </#if>
    <#if column.columnName == "serialVersionUID">
    @TableField(exist = false)
    </#if>
    private ${getJavaType(column.columnType)} ${column.columnName};
</#list>

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}

<#function getJavaType dbType>
    <#if dbType == "VARCHAR" || dbType == "CHAR" || dbType == "TEXT">
        <#return "String">
    <#elseif dbType == "INT" || dbType == "TINYINT" || dbType == "SMALLINT">
        <#return "Integer">
    <#elseif dbType == "BIGINT">
        <#return "Long">
    <#elseif dbType == "FLOAT">
        <#return "Float">
    <#elseif dbType == "DOUBLE" || dbType == "DECIMAL">
        <#return "Double">
    <#elseif dbType == "DATE" || dbType == "DATETIME" || dbType == "TIMESTAMP">
        <#return "Date">
    <#elseif dbType == "BOOLEAN">
        <#return "Boolean">
    <#else>
        <#return "Object">
    </#if>
</#function>