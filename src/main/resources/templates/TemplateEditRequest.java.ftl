package ${packageName}.model.dto.${dataKey};

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 编辑${dataName}请求
 *
 * @author <a href="https://github.com/Gliangquan">小梁</a>
 */
@Data
public class ${upperDataKey}EditRequest implements Serializable {
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