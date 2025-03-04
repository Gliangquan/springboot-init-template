package ${packageName}.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ${packageName}.common.ErrorCode;
import ${packageName}.constant.CommonConstant;
import ${packageName}.exception.BusinessException;
import ${packageName}.model.dto.${dataKey}.${upperDataKey}QueryRequest;
import ${packageName}.model.entity.${upperDataKey};
import ${packageName}.model.vo.${upperDataKey}VO;
import ${packageName}.service.${upperDataKey}Service;
import ${packageName}.mapper.${upperDataKey}Mapper;
import ${packageName}.utils.SqlUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ${upperDataKey}ServiceImpl extends ServiceImpl<${upperDataKey}Mapper, ${upperDataKey}> implements ${upperDataKey}Service {

    @Override
    public ${upperDataKey}VO get${upperDataKey}VO(${upperDataKey} ${dataKey}) {
        if (${dataKey} == null) {
            return null;
        }
        ${upperDataKey}VO ${dataKey}VO = new ${upperDataKey}VO();
        BeanUtils.copyProperties(${dataKey}, ${dataKey}VO);
        return ${dataKey}VO;
    }

    @Override
    public List<${upperDataKey}VO> get${upperDataKey}VO(List<${upperDataKey}> ${dataKey}List) {
        if (CollUtil.isEmpty(${dataKey}List)) {
            return new ArrayList<>();
        }
        return ${dataKey}List.stream().map(this::get${upperDataKey}VO).collect(Collectors.toList());
    }

    @Override
    public Wrapper<${upperDataKey}> getQueryWrapper(${upperDataKey}QueryRequest ${dataKey}QueryRequest) {
        if (${dataKey}QueryRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "请求参数为空");
        }
        Long id = ${dataKey}QueryRequest.getId();
        String status = ${dataKey}QueryRequest.getStatus();
        String sortField = ${dataKey}QueryRequest.getSortField();
        String sortOrder = ${dataKey}QueryRequest.getSortOrder();
        QueryWrapper<${upperDataKey}> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(id != null, "id", id);
        queryWrapper.eq(StringUtils.isNotBlank(status), "status", status);
        queryWrapper.orderBy(SqlUtils.validSortField(sortField), sortOrder.equals(CommonConstant.SORT_ORDER_ASC), sortField);
        return queryWrapper;
    }
}