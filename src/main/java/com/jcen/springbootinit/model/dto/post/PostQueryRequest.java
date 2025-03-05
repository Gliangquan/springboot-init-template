package com.jcen.springbootinit.model.dto.post;

import com.jcen.springbootinit.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;
import java.io.Serializable;

/**
 * 查询用户评论请求
 *
 * @author <a href="https://github.com/Gliangquan">小梁</a>
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PostQueryRequest extends PageRequest implements Serializable {

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
}

