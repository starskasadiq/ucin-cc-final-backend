package com.masters.ucin.cc.repository;

import com.google.common.collect.Lists;
import com.masters.ucin.cc.entity.DataObject;
import com.masters.ucin.cc.entity.HouseHolds;
import com.masters.ucin.cc.entity.Products;
import com.masters.ucin.cc.entity.Transactions;
import com.masters.ucin.cc.model.AgeSpendObj;
import com.masters.ucin.cc.model.CommoditySpendObj;
import com.masters.ucin.cc.model.IncomeSpendObj;
import com.masters.ucin.cc.model.YearSpendObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JdbcDataPullsRepository implements DataPullsRepository {
    final int batchSize = 1000;
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<DataObject> getData(String hshdNum){
        List<DataObject> data = jdbcTemplate.query("select households.hshd_num, transactions.basket_num, transactions.PURCHASE_DATE, products.Product_num, products.Department, products.Commodity,\n" +
                        "transactions.spend, transactions.units, transactions.store_r, transactions.week_num, transactions.year,\n" +
                        "households.l, households.age_range, households.marital, households.income_range, households.homeowner, households.hshd_composition, households.hh_size, households.children\n" +
                        "from \n" +
                        "(select * from 400_households \n" +
                        "where hshd_num = ?) as households inner join 400_transactions as transactions\n" +
                        "\ton households.hshd_num = transactions.hshd_num\n" +
                        "    inner join 400_products as products on transactions.product_num = products.product_num\n" +
                        "order by households.hshd_num, transactions.Basket_num, transactions.PURCHASE_DATE, products.Product_num, products.Department, products.Commodity",
                BeanPropertyRowMapper.newInstance(DataObject.class), Integer.parseInt(hshdNum));
        return data;
    }

    @Override
    public List<AgeSpendObj> getAgeSpendAnalysis(String sql) {
        List<AgeSpendObj> data = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(AgeSpendObj.class));
        return data;
    }

    @Override
    public List<IncomeSpendObj> getIncomeSpendAnalysis(String sql) {
        List<IncomeSpendObj> data = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(IncomeSpendObj.class));
        return data;
    }

    @Override
    public List<YearSpendObj> getYearSpend(String sql) {
        List<YearSpendObj> data = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(YearSpendObj.class));
        return data;
    }

    @Override
    public List<CommoditySpendObj> getCommoditySpendAnalysis(String sql) {
        List<CommoditySpendObj> data = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(CommoditySpendObj.class));
        return data;
    }

    @Override
    public int batchUpdateHouseholds(List<HouseHolds> data, String sql) {
        List<List<HouseHolds>> batchLists = Lists.partition(data, batchSize);
        for(List<HouseHolds> batch : batchLists) {
            jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement ps, int i)
                        throws SQLException {
                    HouseHolds houseHold = data.get(i);
                    ps.setString(1, houseHold.getHshdNum());
                    ps.setString(2, houseHold.getL());
                    ps.setString(3, houseHold.getAgeRange());
                    ps.setString(4, houseHold.getMarital());
                    ps.setString(5, houseHold.getIncomeRange());
                    ps.setString(6, houseHold.getHomeOwner());
                    ps.setString(7, houseHold.getHshdComposition());
                    ps.setString(8, houseHold.getHhSize());
                    ps.setString(9, houseHold.getChildren());
                }
                @Override
                public int getBatchSize() {
                    return batch.size();
                }
            });
        }
        return data.size();
    }

    @Override
    public int batchUpdateProducts(List<Products> data, String sql) {
        List<List<Products>> batchLists = Lists.partition(data, batchSize);
        for(List<Products> batch : batchLists) {
            jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement ps, int i)
                        throws SQLException {
                    Products product = data.get(i);
                    ps.setString(1, product.getProductNum());
                    ps.setString(2, product.getDepartment());
                    ps.setString(3, product.getCommodity());
                    ps.setString(4, product.getBrandTy());
                    ps.setString(5, product.getNaturalOrganicFlag());
                }
                @Override
                public int getBatchSize() {
                    return batch.size();
                }
            });
        }
        return data.size();
    }

    @Override
    public int batchUpdateTransactions(List<Transactions> data, String sql) {
        List<List<Transactions>> batchLists = Lists.partition(data, batchSize);
        for(List<Transactions> batch : batchLists) {
            jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement ps, int i)
                        throws SQLException {
                    Transactions transaction = data.get(i);
                    ps.setString(1, transaction.getBasketNum());
                    ps.setString(2, transaction.getHshdNum());
                    ps.setString(3, transaction.getPurchase());
                    ps.setString(4, transaction.getProductNum());
                    ps.setString(5, transaction.getSpend());
                    ps.setString(6, transaction.getUnits());
                    ps.setString(7, transaction.getStoreR());
                    ps.setString(8, transaction.getWeekNum());
                    ps.setString(9, transaction.getYear());
                }
                @Override
                public int getBatchSize() {
                    return batch.size();
                }
            });
        }
        return data.size();
    }
}
