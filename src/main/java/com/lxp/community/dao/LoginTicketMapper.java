package com.lxp.community.dao;

import com.lxp.community.entity.LoginTicket;
import org.apache.ibatis.annotations.*;

@Mapper
public interface LoginTicketMapper {
    @Insert({
            "insert into login_ticket(user_id,ticket, status, expired) ",
            "values(#{userId},#{ticket},#{status},#{expired})"
    })
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertLoginTicket(LoginTicket loginTicket);



    @Select({
            "select id,user_id,ticket,status,expired ",
            "from login_ticket where ticket=#{ticket}"
    })
    //ticket发送个客户端，其余字段都要保存在数据库，查询主要以ticket为条件
    LoginTicket selectByTicket(String ticket);



    @Update({
            "<script>",
            "update login_ticket set status=#{status} where ticket=#{ticket} ",
            "<if test=\"ticket!=null\">",
            "and 1=1 ",
            "</if>",
            "</script>"
    })
    //使ticket失效
    int updateStatus(String ticket, int status);
}
