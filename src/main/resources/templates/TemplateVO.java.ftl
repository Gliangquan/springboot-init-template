package ${packageName}.model.vo;

import cn.hutool.json.JSONUtil;
import ${packageName}.model.entity.${upperDataKey};
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
* ${dataName}视图
*
* @author <a href="https://github.com/Gliangquan">小梁</a>
*/
@Data
public class ${upperDataKey}VO implements Serializable {
<#list columns as column>

    /**
    * ${column.columnComment}
    */
    private ${getJavaType(column.columnType)} ${column.columnName};
</#list>

    /**
    * 包装类转对象
    *
    * @param ${dataKey}VO
    * @return
    */
    public static ${upperDataKey} voToObj(${upperDataKey}VO ${dataKey}VO) {
        if (${dataKey}VO == null) {
            return null;
        }
        ${upperDataKey} ${dataKey} = new ${upperDataKey}();
        BeanUtils.copyProperties(${dataKey}VO, ${dataKey});
        List<String> tagList = ${dataKey}VO.getTagList();
        ${dataKey}.setTags(JSONUtil.toJsonStr(tagList));
        return ${dataKey};
    }

    /**
    * 对象转包装类
    *
    * @param ${dataKey}
    * @return
    */
    public static ${upperDataKey}VO objToVo(${upperDataKey} ${dataKey}) {
        if (${dataKey} == null) {
            return null;
        }
        ${upperDataKey}VO ${dataKey}VO = new ${upperDataKey}VO();
        BeanUtils.copyProperties(${dataKey}, ${dataKey}VO);
        ${dataKey}VO.setTagList(JSONUtil.toList(${dataKey}.getTags(), String.class));
        return ${dataKey}VO;
        }
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