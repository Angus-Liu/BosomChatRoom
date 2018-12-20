package com.tickychat.server.mapper;

import com.tickychat.server.pojo.User;
import com.tickychat.server.pojo.vo.FriendVO;
import com.tickychat.server.pojo.vo.RequestVO;
import com.tickychat.server.common.utils.mybatis.MyMapper;
import com.tickychat.server.web.netty.bean.ChatMsg;

import java.util.List;

public interface CustomMapper extends MyMapper<User> {
    /**
     * 获取好友请求信息
     *
     * @param acceptUserId
     * @return
     */
    List<RequestVO> queryFriendRequest(String acceptUserId);

    /**
     * 查询好友列表
     *
     * @param userId
     * @return
     */
    List<FriendVO> queryFriend(String userId);

    /**
     * 批量更新消息签收状态
     *
     * @param msgIdList
     */
    void batchUpdateMsgSignFlag(List<String>  msgIdList);

    /**
     * 查询为签收消息
     *
     * @param acceptUseId
     * @return
     */
    List<ChatMsg> queryUnsignedMsg(String acceptUseId);
}