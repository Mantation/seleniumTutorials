package Utilities;

import java.util.Arrays;

public class ExcelUtils {

    public String getCellValue(String[] headers, String[] data, String columnName){
        try{
            return  data[Arrays.asList(headers).indexOf(columnName)];
        }catch (Exception outOfBounds){
            System.out.println(columnName + "is not found in the datasheet");
            return "";
        }
    }


}
