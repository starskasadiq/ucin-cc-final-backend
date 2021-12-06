package com.masters.ucin.cc.repository;

import com.masters.ucin.cc.entity.DataObject;
import com.masters.ucin.cc.entity.HouseHolds;
import com.masters.ucin.cc.entity.Products;
import com.masters.ucin.cc.entity.Transactions;
import com.masters.ucin.cc.model.AgeSpendObj;
import com.masters.ucin.cc.model.CommoditySpendObj;
import com.masters.ucin.cc.model.IncomeSpendObj;
import com.masters.ucin.cc.model.YearSpendObj;

import java.util.List;

public interface DataPullsRepository {
  List<DataObject> getData(String hshdNum);
  List<AgeSpendObj> getAgeSpendAnalysis(String sql);
  List<IncomeSpendObj> getIncomeSpendAnalysis(String sql);
  List<YearSpendObj> getYearSpend(String sql);
  List<CommoditySpendObj> getCommoditySpendAnalysis(String sql);
  int batchUpdateHouseholds(List<HouseHolds> data, String sql);
  int batchUpdateProducts(List<Products> data, String sql);
  int batchUpdateTransactions(List<Transactions> data, String sql);
}