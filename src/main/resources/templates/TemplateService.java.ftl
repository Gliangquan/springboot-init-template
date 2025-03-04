package ${packageName}.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import ${packageName}.model.dto.${dataKey}.${upperDataKey}QueryRequest;
import ${packageName}.model.entity.${upperDataKey};
import com.baomidou.mybatisplus.extension.service.IService;
import ${packageName}.model.vo.${upperDataKey}VO;

import java.util.List;

/**
* @author ${author}
* @description 针对表【${tableName}(${dataName})】的数据库操作Service
* @createDate ${createDate?string('yyyy-MM-dd HH:mm:ss')}
*/
public interface ${upperDataKey}Service extends IService<${upperDataKey}> {

    ${upperDataKey}VO get${upperDataKey}VO(${upperDataKey} ${dataKey});

    List<${upperDataKey}VO> get${upperDataKey}VO(List<${upperDataKey}> ${dataKey}List);

    Wrapper<${upperDataKey}> getQueryWrapper(${upperDataKey}QueryRequest ${dataKey}QueryRequest);

}