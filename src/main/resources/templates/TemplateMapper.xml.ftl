<?xml version="1.0" encoding="UTF-8"?>
<!-- @author <a href="https://github.com/Gliangquan">小梁</a> -->
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${packageName}.mapper.${upperDataKey}Mapper">

    <resultMap id="BaseResultMap" type="${packageName}.model.entity.${upperDataKey}">
        <#list columns as column>
            <#if column.isPrimaryKey == "true">
                <id property="${column.columnName}" column="${column.columnName}" jdbcType="${column.jdbcType}"/>
            <#else>
                <result property="${column.columnName}" column="${column.columnName}" jdbcType="${column.jdbcType}"/>
            </#if>
        </#list>
    </resultMap>

    <sql id="Base_Column_List">
        <#list columns as column>
            ${column.columnName}<#if column_has_next>,</#if>
        </#list>
    </sql>

    <select id="list${upperDataKey}WithDelete" resultType="${packageName}.model.entity.${upperDataKey}">
        select *
        from ${tableName}
        where updateTime >= ${r"#{minUpdateTime}"}
    </select>
</mapper>