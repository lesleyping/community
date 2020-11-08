package com.lxp.community.dao;

import com.lxp.community.entity.DiscussPost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DiscussPostMapper {
    //个人主页 用id查发送过的帖子，因此userId可以为空
    // ordermode = 0 按照type和时间排序，ordermode = 1 按照热度排
    List<DiscussPost> selectDiscussPosts(int userId, int offset, int limit, int orderMode);

    //@Param注解 用于给参数起别名
    //如果只有一个参数，并且在<if>里使用，则必须起别名
    //查询帖子行数
    int selectDiscussPostRows(@Param("userId") int userId);

    int insertDiscussPost(DiscussPost discussPost);

    DiscussPost selectDiscussPostById(int id);

    int updateCommentCount(int id, int commentCount);

    int updateType(int id, int type);

    int updateStatus(int id, int status);

    int updateScore(int id, double score);
}
