package server.dao;

import common.messages.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * PersonDao的具体实现类
 *
 * @author dyleaf
 */
public class UserDaoImpl implements UserDao {

    private UserDaoImpl() {
        super();
    }

    private static UserDaoImpl userDao = new UserDaoImpl();

    public static UserDaoImpl getInstance() {
        return userDao;
    }

    /**
     * 实现添加方法
     */
    @Override
    public void add(User user) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "insert into user (id,nickname,account,password) values (?,?,?,?)";
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, user.getId());
            ps.setString(2, user.getNickname());
            ps.setString(3, user.getAccount());
            ps.setString(4, user.getPassword());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("添加数据失败");
        } finally {
            DBUtils.close(null, ps, conn);
        }
    }

    /**
     * 更新方法
     */
    @Override
    public void update(User user) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "update user set nickname=?, password=? where id = ?";

        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, user.getNickname());
            ps.setString(2, user.getPassword());
            ps.setInt(3, user.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("更新数据失败");
        } finally {
            DBUtils.close(null, ps, conn);
        }
    }

    /**
     * 删除方法
     */
    @Override
    public void deleteById(int id) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "delete from user where id=?";
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("删除数据失败");
        } finally {
            DBUtils.close(null, ps, conn);
        }
    }

    @Override
    public void deleteByAccount(String account) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "delete from user where account=?";
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, account);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("删除数据失败");
        } finally {
            DBUtils.close(null, ps, conn);
        }
    }

    @Override
    public User findByAccount(String account) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        User user = null;
        String sql = "select id, nickname, password from user where account=?";
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, account);
            rs = ps.executeQuery();
            if (rs.next()) {
                user = new User(rs.getInt(1), rs.getString(2),account, rs.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("查询所有数据失败");
        } finally {
            DBUtils.close(rs, ps, conn);
        }
        return user;
    }

    @Override
    public List<User> findByNickname(String username) throws SQLException {
        return null;
    }

    @Override
    public List<User> findAll() throws SQLException {
        return null;
    }


//    @Override
//    public User findByName(String username) throws SQLException {
//        Connection conn = null;
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        ServerUser p = null;
//        String sql = "select id,password from ChatUser where name=?";
//        try {
//            conn = DBUtils.getConnection();
//            ps = conn.prepareStatement(sql);
//            ps.setString(1, username);
//            rs = ps.executeQuery();
//            if (rs.next()) {
//                p = new ServerUser(rs.getInt(1), username, rs.getString(2));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            throw new SQLException("根据ID查询数据失败");
//        } finally {
//            DBUtils.close(rs, ps, conn);
//        }
//        return p;
//    }
//
//    /**
//     * 查询所有数据
//     */
//    @Override
//    public List<ServerUser> findAll() throws SQLException {
//        Connection conn = null;
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        ServerUser p = null;
//        List<ServerUser> persons = new ArrayList<ServerUser>();
//        String sql = "select id,name,password from ChatUser";
//        try {
//            conn = DBUtils.getConnection();
//            ps = conn.prepareStatement(sql);
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                p = new ServerUser(rs.getInt(1), rs.getString(2), rs.getString(3));
//                persons.add(p);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            throw new SQLException("查询所有数据失败");
//        } finally {
//            DBUtils.close(rs, ps, conn);
//        }
//        return persons;
//    }

}
