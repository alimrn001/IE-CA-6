package com.baloot.baloot.Repository.User;

import com.baloot.baloot.Repository.ConnectionPool;
import com.baloot.baloot.Repository.Mapper;
import com.baloot.baloot.Utils.StringUtils;
import com.baloot.baloot.domain.Baloot.User.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserMapper extends Mapper<User, String> implements IUserMapper {

    private static final String COLUMNS = " username, password, birthDate, email, address, credit ";
    private static final String TABLE_NAME = "USERS";

    public UserMapper(Boolean doManage) throws SQLException {
        if (doManage) {
            Connection con = ConnectionPool.getConnection();
            Statement st = con.createStatement();
            //st.executeUpdate(String.format("DROP TABLE IF EXISTS %s", TABLE_NAME));
            st.executeUpdate(String.format(
                    "CREATE TABLE IF NOT EXISTS %s (\n" +
                            "    username varchar(255) primary key,\n" +
                            "    password varchar(255) not null,\n" +
                            "    birthDate DATE not null,\n" +
                            "    email varchar(255) not null,\n" +
                            "    address varchar(255) not null,\n" +
                            "    credit int not null default 0,\n" +
                            ");",
                    TABLE_NAME));
            st.execute(String.format("ALTER TABLE %s CHARACTER SET utf8 COLLATE utf8_general_ci;", TABLE_NAME));
            st.close();
            con.close();
        }
    }

    public UserMapper() throws SQLException {
    }

    @Override
    protected String getFindStatement(String username) {
        return String.format("select * from %s where %s.%s = %s;", TABLE_NAME, TABLE_NAME, "username", StringUtils.quoteWrapper(username));
    }

    @Override
    protected String getInsertStatement(User user) {
        return String.format("INSERT INTO %s ( %s ) values (%s, %s, STR_TO_DATE(%s, '%%Y/%%m/%%d'), %s, %s, %d);",
                TABLE_NAME, COLUMNS, StringUtils.quoteWrapper(user.getUsername()),
                StringUtils.quoteWrapper(user.getPassword()), StringUtils.quoteWrapper(user.getBirthDate().toString()),
                StringUtils.quoteWrapper(user.getEmail()), StringUtils.quoteWrapper(user.getAddress()), user.getCredit());
    }

    @Override
    protected String getDeleteStatement(String username) {
        return String.format("delete from %s where %s.%s = %s;", TABLE_NAME, TABLE_NAME, "username", StringUtils.quoteWrapper(username));
    }

    @Override
    protected User convertResultSetToObject(ResultSet rs) throws SQLException {
        return new User(
                rs.getString("username"),
                rs.getString("password"),
                rs.getDate("birthDate").toString(),
                rs.getString("email"),
                rs.getString("address"),
                rs.getInt("credit")
        );
    }

    @Override
    public List<User> getAll() throws SQLException {
        List<User> result = new ArrayList<User>();
        String statement = "SELECT * FROM " + TABLE_NAME;
        try (Connection con = ConnectionPool.getConnection();
             PreparedStatement st = con.prepareStatement(statement);
        ) {
            ResultSet resultSet;
            try {
                resultSet = st.executeQuery();
                while (resultSet.next())
                    result.add(convertResultSetToObject(resultSet));
                return result;
            } catch (SQLException ex) {
                System.out.println("error in Mapper.findAll query.");
                throw ex;
            }
        }
    }

    @Override
    public void updateUserCredit(User user) throws SQLException {
        String statement = String.format("update %s set %s = %s where %s = %s;", TABLE_NAME, "credit",
                user.getCredit(), "username", StringUtils.quoteWrapper(user.getUsername()));
        try (Connection con = ConnectionPool.getConnection();
             PreparedStatement st = con.prepareStatement(statement);
        ) {
            try {
                st.executeUpdate();
            } catch (SQLException ex) {
                System.out.println("error in Mapper.updateCartItem query.");
                throw ex;
            }
        }
    }

}