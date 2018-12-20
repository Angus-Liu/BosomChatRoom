package com.tickychat.server.common.enums;

import java.util.NoSuchElementException;

/**
 * @author Angus
 * @date 2018/12/18
 */
public enum FriendRequestOperationType {

    /**
     * 忽略请求
     */
    IGNORE(0, "忽略"),

    /**
     * 通过1请求
     */
    PASS(1, "通过");

    public final int type;
    public final String msg;

    FriendRequestOperationType(int type, String msg) {
        this.type = type;
        this.msg = msg;
    }

    public static FriendRequestOperationType of(int type) {
        for (FriendRequestOperationType value : FriendRequestOperationType.values()) {
            if (value.type == type) {
                return value;
            }
        }
        throw new NoSuchElementException("没有该操作类型：state = " + type);
    }
}
