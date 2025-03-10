package com.jcen.springbootinit.model.vo;

import cn.hutool.json.JSONUtil;
import com.jcen.springbootinit.model.entity.Post;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
* 用户评论视图
*
* @author <a href="https://github.com/Gliangquan">小梁</a>
*/
@Data
public class PostVO implements Serializable {

    /**
    * id
    */
    private Long id;

    /**
    * 标题
    */
    private String title;

    /**
    * 内容
    */
    private String content;

    /**
    * 标签列表（json 数组）
    */
    private String tags;

    /**
    * 点赞数
    */
    private Integer thumbNum;

    /**
    * 收藏数
    */
    private Integer favourNum;

    /**
    * 创建用户 id
    */
    private Long userId;

    /**
    * 创建时间
    */
    private Date createTime;

    /**
    * 更新时间
    */
    private Date updateTime;

    /**
    * 是否删除
    */
    private Integer isDelete;

    /**
    * 包装类转对象
    *
    * @param postVO
    * @return
    */
    public static Post voToObj(PostVO postVO) {
        if (postVO == null) {
            return null;
        }
        Post post = new Post();
        BeanUtils.copyProperties(postVO, post);
        // entityVo List 转为 entity String
        // List<String> tagList = postVO.getTagList();
        // post.setTags(JSONUtil.toJsonStr(tagList));
        return post;
    }

    /**
    * 对象转包装类
    *
    * @param post
    * @return
    */
    public static PostVO objToVo(Post post) {
        if (post == null) {
            return null;
        }
        PostVO postVO = new PostVO();
        BeanUtils.copyProperties(post, postVO);
        // entity String 转为 entityVo List
        // postVO.setTagList(JSONUtil.toList(post.getTags(), String.class));
        return postVO;
        }
    }

