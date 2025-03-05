package com.jcen.springbootinit.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
* 用户评论
*
* @author <a href="https://github.com/Gliangquan">小梁</a>
*/
@TableName(value = "post")
@Data
public class Post implements Serializable {

    /**
    * id
    */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
    * 标题
    */
    @TableField(value = "title")
    private String title;

    /**
    * 内容
    */
    @TableField(value = "content")
    private String content;

    /**
    * 标签列表（json 数组）
    */
    @TableField(value = "tags")
    private String tags;

    /**
    * 点赞数
    */
    @TableField(value = "thumbNum")
    private Integer thumbNum;

    /**
    * 收藏数
    */
    @TableField(value = "favourNum")
    private Integer favourNum;

    /**
    * 创建用户 id
    */
    @TableField(value = "userId")
    private Long userId;

    /**
    * 创建时间
    */
    @TableField(value = "createTime")
    private Date createTime;

    /**
    * 更新时间
    */
    @TableField(value = "updateTime")
    private Date updateTime;

    /**
    * 是否删除
    */
    @TableField(value = "isDelete")
    @TableLogic
    private Integer isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}

