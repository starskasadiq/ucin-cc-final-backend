package com.masters.ucin.cc.rest.resources;


import com.masters.ucin.cc.entity.DataObject;
import com.masters.ucin.cc.model.AgeSpendObj;
import com.masters.ucin.cc.model.CommoditySpendObj;
import com.masters.ucin.cc.model.IncomeSpendObj;
import com.masters.ucin.cc.model.YearSpendObj;
import com.masters.ucin.cc.repository.DataPullsRepository;
import com.masters.ucin.cc.rest.resources.operations.CSVReader_DBUpdater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/api/v1")
@RestController
@CrossOrigin
public class ComputingApi {

    @Autowired
    DataPullsRepository dataPullsRepository;

    @Autowired
    CSVReader_DBUpdater csvReader_dbUpdater;

    @Value("${sqlQueries.analysis.incomeSpend}")
    String incomeSpendAnalysis;

    @Value("${sqlQueries.analysis.ageSpend}")
    String ageSpendAnalysis;

    @Value("${sqlQueries.analysis.yearSpend}")
    String yearSpend;

    @Value("${sqlQueries.analysis.growingCommodityByYear}")
    String growingCommodityByYear;

    @Value("${sqlQueries.analysis.shrinkingCommodityByYear}")
    String shrinkingCommodityByYear;

    @Autowired
    DataPullsRepository jdbcDataPullsRepository;

    @GetMapping(value = "/analysis/income-spend")
    public List<IncomeSpendObj> getIncomeSpendAnalysis(){
        System.out.println("Received request for Income Spend");
        return jdbcDataPullsRepository.getIncomeSpendAnalysis(incomeSpendAnalysis);
    }

    @GetMapping(value = "/analysis/age-spend")
    public List<AgeSpendObj> getAgeSpendAnalysis(){
        System.out.println("Received request for Age Spend");
        return jdbcDataPullsRepository.getAgeSpendAnalysis(ageSpendAnalysis);
    }

    @GetMapping(value = "/analysis/year-spend")
    public List<YearSpendObj> getYearSpendAnalysis(){
        System.out.println("Received request for Year Spend");
        return jdbcDataPullsRepository.getYearSpend(yearSpend);
    }

    @GetMapping(value = "/analysis/grow-comm-spend")
    public Map<String, List<Integer>> getGrowingCommSpendAnalysis(){
        System.out.println("Received request for Grow Comm Spend");
        Map<String, List<Integer>> data = new HashMap<>();
        List<CommoditySpendObj> dbResults = jdbcDataPullsRepository.getCommoditySpendAnalysis(growingCommodityByYear);
        for(CommoditySpendObj commoditySpendObj : dbResults){
            if(!data.containsKey(commoditySpendObj.getCommodity().trim())){
                List<Integer> list = new ArrayList<>();
                list.add(commoditySpendObj.getSpend());
                data.put(commoditySpendObj.getCommodity().trim(), list);
            } else {
                data.get(commoditySpendObj.getCommodity().trim()).add(commoditySpendObj.getSpend());
            }
        }
        System.out.println(data);
        return data;
    }

    @GetMapping(value = "/analysis/shrink-comm-spend")
    public Map<String, List<Integer>> getShrinkCommSpendAnalysis(){
        System.out.println("Received request for Shrink Comm Spend");
        Map<String, List<Integer>> data = new HashMap<>();
        List<CommoditySpendObj> dbResults = jdbcDataPullsRepository.getCommoditySpendAnalysis(shrinkingCommodityByYear);
        for(CommoditySpendObj commoditySpendObj : dbResults) {
            if (!data.containsKey(commoditySpendObj.getCommodity().trim())) {
                List<Integer> list = new ArrayList<>();
                list.add(commoditySpendObj.getSpend());
                data.put(commoditySpendObj.getCommodity().trim(), list);
            } else {
                data.get(commoditySpendObj.getCommodity().trim()).add(commoditySpendObj.getSpend());
            }
        }
        System.out.println(data);
        return data;
    }

    @GetMapping(value = "/getHsHdNum/{hshdNum}")
    public List<DataObject> getHostHoldNumData(@PathVariable("hshdNum") String hshdNum){
        return dataPullsRepository.getData(hshdNum);
    }

    @PostMapping(value = "/upload/{fileType}",  consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public int uploadData(@PathVariable("fileType") String fileType, @RequestParam("file") MultipartFile file) throws IOException {
        System.out.println("Request received for data upload on table - " + fileType);
        return csvReader_dbUpdater.readDataFromCSV(fileType, file);
    }

}
