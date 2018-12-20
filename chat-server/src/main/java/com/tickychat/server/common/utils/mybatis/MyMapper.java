package com.tickychat.server.common.utils.mybatis;


import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author Angus
 * @date 2018/12/15
 */
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
