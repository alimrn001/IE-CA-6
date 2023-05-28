package com.baloot.baloot.Repository.User;

import com.baloot.baloot.Repository.IMapper;
import com.baloot.baloot.domain.Baloot.User.User;

import java.sql.SQLException;
import java.util.List;

public interface IUserMapper extends IMapper<User, String> {
    List<User> getAll() throws SQLException;
    void updateUserCredit(User user) throws SQLException;
}