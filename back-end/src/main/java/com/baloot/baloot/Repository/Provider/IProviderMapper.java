package com.baloot.baloot.Repository.Provider;

import com.baloot.baloot.Repository.IMapper;
import com.baloot.baloot.domain.Baloot.Provider.Provider;
import com.baloot.baloot.domain.Baloot.User.User;

import java.sql.SQLException;
import java.util.List;

public interface IProviderMapper extends IMapper<Provider, Integer> {
    List<Provider> getAll() throws SQLException;
    void updateProviderData(Provider provider) throws SQLException;
}

