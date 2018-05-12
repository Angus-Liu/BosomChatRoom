package server.dao;

import common.messages.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {
    //添加方法
    public void add(User user) throws SQLException;

    //更新方法
    public void update(User user) throws SQLException;

    //删除方法
    public void deleteById(int id) throws SQLException;

    //删除方法
    public void deleteByAccount(String account) throws SQLException;

    //查找方法
    public User findByAccount(String account) throws SQLException;

    public List<User> findByNickname(String username) throws SQLException;

    //查找所有
    public List<User> findAll() throws SQLException;
}