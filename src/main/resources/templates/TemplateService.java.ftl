package ${packageName}.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import ${packageName}.model.dto.${dataKey}.${upperDataKey}QueryRequest;
import ${packageName}.model.entity.${upperDataKey};
import com.baomidou.mybatisplus.extension.service.IService;
import ${packageName}.model.vo.${upperDataKey}VO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

/**
* @author ${author}
* @description 针对表【${tableName}(${dataName})】的数据库操作Service
* @createDate ${createDate?string('yyyy-MM-dd HH:mm:ss')}
*/
public interface ${upperDataKey}Service extends IService<${upperDataKey}> {

    ${upperDataKey}VO get${upperDataKey}VO(${upperDataKey} ${dataKey});

    Page<${upperDataKey}VO> get${upperDataKey}VOPage(Page<${upperDataKey}> ${dataKey}Page);

    Wrapper<${upperDataKey}> getQueryWrapper(${upperDataKey}QueryRequest ${dataKey}QueryRequest);

}