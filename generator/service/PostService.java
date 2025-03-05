package com.jcen.springbootinit.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.jcen.springbootinit.model.dto.post.PostQueryRequest;
import com.jcen.springbootinit.model.entity.Post;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jcen.springbootinit.model.vo.PostVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

/**
* @author liangquan0302
* @description 针对表【post(用户评论)】的数据库操作Service
* @createDate 2025-03-05 09:55:00
*/
public interface PostService extends IService<Post> {

    PostVO getPostVO(Post post);

    Page<PostVO> getPostVOPage(Page<Post> postPage);

    Wrapper<Post> getQueryWrapper(PostQueryRequest postQueryRequest);

}