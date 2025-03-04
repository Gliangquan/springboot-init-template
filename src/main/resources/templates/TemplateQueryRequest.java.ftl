package ${packageName}.model.dto.${dataKey};

import ${packageName}.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * 查询${dataName}请求
 *
 * @author <a href="https://github.com/Gliangquan">小梁</a>
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ${upperDataKey}QueryRequest extends PageRequest implements Serializable {
<#list columns as column>

    /**
    * ${column.columnComment}
    */
    private ${getJavaType(column.columnType)} ${column.columnName};
</#list>
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