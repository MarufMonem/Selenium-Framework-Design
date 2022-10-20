package Selenium.Data;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class DataReader {
    public void getJsonDataToMap() throws IOException {
//        read json to string
       String jsonContent =  FileUtils.readFileToString(new File( System.getProperty("user.dir" + "\\src\\test\\java\\Selenium\\Data\\PurchaseOrder.json")));

//       String to hashmap
        ObjectMapper mapper = new ObjectMapper();

    }
}
