package com.masters.ucin.cc.rest.resources.operations;

import com.masters.ucin.cc.entity.HouseHolds;
import com.masters.ucin.cc.entity.Products;
import com.masters.ucin.cc.entity.Transactions;
import com.masters.ucin.cc.repository.DataPullsRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Configuration
@Component
public class CSVReader_DBUpdater {

    @Value("${sqlQueries.batchUpdate.products}")
    String productsQuery;
    @Value("${sqlQueries.batchUpdate.transactions}")
    String transactionsQuery;
    @Value("${sqlQueries.batchUpdate.households}")
    String householdsQuery;

    @Autowired
    DataPullsRepository dataPullsRepository;

    public int readDataFromCSV(String fileType, MultipartFile file) throws IOException {
        InputStream inputStream = file.getInputStream();
        BufferedReader fileReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        CSVParser csvParser = new CSVParser(fileReader,
                CSVFormat.INFORMIX_UNLOAD_CSV.withFirstRecordAsHeader().withTrim());
        Iterable<CSVRecord> csvRecords = csvParser.getRecords();
        if(fileType.equalsIgnoreCase("households"))
            return constructHouseHoldsSlqQuery(csvRecords).size();
        else if(fileType.equalsIgnoreCase("products")){
            return constructProductsSlqQuery(csvRecords).size();
        } else if (fileType.equalsIgnoreCase("transactions")) {
            return constructTransactionsSlqQuery(csvRecords).size();
        } else {
            return 0;
        }
    }

    private List<HouseHolds> constructHouseHoldsSlqQuery(Iterable<CSVRecord> csvRecords) {
        List<HouseHolds> houseHolds = new ArrayList<>();
        for (CSVRecord csvRecord : csvRecords) {
            HouseHolds houseHold = new HouseHolds (
                    csvRecord.get(0),
                    csvRecord.get(1),
                    csvRecord.get(2),
                    csvRecord.get(3),
                    csvRecord.get(4),
                    csvRecord.get(5),
                    csvRecord.get(6),
                    csvRecord.get(7),
                    csvRecord.get(8)
            );
            houseHolds.add(houseHold);
        }
        dataPullsRepository.batchUpdateHouseholds(houseHolds, householdsQuery);
        return houseHolds;
    }

    private List<Products> constructProductsSlqQuery(Iterable<CSVRecord> csvRecords){
        List<Products> products = new ArrayList<>();
        for (CSVRecord csvRecord : csvRecords) {
            Products product = new Products(
                    csvRecord.get(0),
                    csvRecord.get(1),
                    csvRecord.get(2),
                    csvRecord.get(3),
                    csvRecord.get(4)
            );
            products.add(product);
        }
        dataPullsRepository.batchUpdateProducts(products, productsQuery);
        return products;
    }

    private List<Transactions> constructTransactionsSlqQuery(Iterable<CSVRecord> csvRecords){
        List<Transactions> transactions = new ArrayList<>();
        for (CSVRecord csvRecord : csvRecords) {
            Transactions transaction = new Transactions(
                    csvRecord.get(0),
                    csvRecord.get(1),
                    csvRecord.get(2),
                    csvRecord.get(3),
                    csvRecord.get(4),
                    csvRecord.get(5),
                    csvRecord.get(6),
                    csvRecord.get(7),
                    csvRecord.get(8)
            );
            transactions.add(transaction);
        }
        dataPullsRepository.batchUpdateTransactions(transactions, transactionsQuery);
        return transactions;
    }
}