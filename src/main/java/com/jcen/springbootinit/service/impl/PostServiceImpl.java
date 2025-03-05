package com.jcen.springbootinit.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jcen.springbootinit.common.ErrorCode;
import com.jcen.springbootinit.constant.CommonConstant;
import com.jcen.springbootinit.exception.BusinessException;
import com.jcen.springbootinit.model.dto.post.PostQueryRequest;
import com.jcen.springbootinit.model.entity.Post;
import com.jcen.springbootinit.model.vo.PostVO;
import com.jcen.springbootinit.service.PostService;
import com.jcen.springbootinit.mapper.PostMapper;
import com.jcen.springbootinit.utils.SqlUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class PostServiceImpl extends ServiceImpl<PostMapper, Post> implements PostService {

    @Override
    public PostVO getPostVO(Post post) {
        if (post == null) {
            return null;
        }
        PostVO postVO = new PostVO();
        BeanUtils.copyProperties(post, postVO);
        return postVO;
    }

    @Override
    public Page<PostVO> getPostVOPage(Page<Post> postPage) {

        Stream<PostVO> postVOStream = postPage.getRecords().stream().map(this::getPostVO);

//        PostVO postVO = this.getPostVO(postPage);
//        if (CollUtil.isEmpty(postPage)) {
//            return new ArrayList<>();
//        }
//        return postPage.stream().map(this::get${upperDataKey}VO).collect(Collectors.toList());
        return null;
    }

    @Override
    public Wrapper<Post> getQueryWrapper(PostQueryRequest postQueryRequest) {
        if (postQueryRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "请求参数为空");
        }
        Long id = postQueryRequest.getId();
        String sortField = postQueryRequest.getSortField();
        String sortOrder = postQueryRequest.getSortOrder();
        QueryWrapper<Post> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(id != null, "id", id);
        queryWrapper.orderBy(SqlUtils.validSortField(sortField), sortOrder.equals(CommonConstant.SORT_ORDER_ASC), sortField);
        return queryWrapper;
    }
}