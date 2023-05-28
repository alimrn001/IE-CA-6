package com.baloot.baloot.Repository.Provider;

import com.baloot.baloot.Repository.Mapper;
import com.baloot.baloot.domain.Baloot.Provider.Provider;
import com.baloot.baloot.Repository.ConnectionPool;
import com.baloot.baloot.Utils.StringUtils;
import com.baloot.baloot.domain.Baloot.User.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProviderMapper extends Mapper<Provider, Integer> implements IProviderMapper {

    private static final String COLUMNS = " id, name, registryDate, commoditiesNum, avgCommoditiesRate, image ";
    private static final String TABLE_NAME = "PROVIDERS";

    public ProviderMapper(Boolean doManage) throws SQLException {
        if (doManage) {
            Connection con = ConnectionPool.getConnection();
            Statement st = con.createStatement();
//            st.executeUpdate(String.format("DROP TABLE IF EXISTS %s", TABLE_NAME));
            st.executeUpdate(String.format(
                    "CREATE TABLE IF NOT EXISTS %s (\n" +
                            "    id int primary key,\n" +
                            "    name varchar(255) not null,\n" +
                            "    registryDate DATE not null,\n" +
                            "    commoditiesNum int not null default 0,\n" +
                            "    avgCommoditiesRate double not null default 0,\n" +
                            "    image varchar(255) not null\n" +
                            ");",
                    TABLE_NAME));
            st.execute(String.format("ALTER TABLE %s CHARACTER SET utf8 COLLATE utf8_general_ci;", TABLE_NAME));
            st.close();
            con.close();
        }
    }

    public ProviderMapper() throws SQLException {
    }

    @Override
    protected String getInsertStatement(Provider provider) { // change to INSERT IGNORE ??
        return String.format("INSERT INTO %s ( %s ) values (%d, %s, STR_TO_DATE(%s, '%%Y/%%m/%%d'), %d, %f, %s);", TABLE_NAME, COLUMNS,
                provider.getId(), StringUtils.quoteWrapper(provider.getName()),
                StringUtils.quoteWrapper(provider.getRegistryDate().toString()),
                provider.getCommoditiesNum(),
                provider.getAvgCommoditiesRate(),
                StringUtils.quoteWrapper(provider.getImage()));
    }

    @Override
    protected String getDeleteStatement(Integer id) {
        return String.format("delete from %s where %s.%s = %s", TABLE_NAME, TABLE_NAME, "id", id);
    }

    @Override
    protected String getFindStatement(Integer id) {
        return String.format("select * from %s where %s.%s = %s;", TABLE_NAME, TABLE_NAME, "id", id);
    }

    @Override
    public List<Provider> getAll() throws SQLException {
        List<Provider> result = new ArrayList<Provider>();
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
    protected Provider convertResultSetToObject(ResultSet rs) throws SQLException {
        return new Provider(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getDate("registryDate").toString(),
                rs.getString("image")
        );
    }

    @Override
    public void updateProviderData(Provider provider) throws SQLException {
        String statement = String.format("update %s set %s = %s, %s = %f where %s = %d;",
                TABLE_NAME,
                "commoditiesNum", provider.getCommoditiesNum(),
                "avgCommoditiesRate", provider.getAvgCommoditiesRate(),
                "id", provider.getId());
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
