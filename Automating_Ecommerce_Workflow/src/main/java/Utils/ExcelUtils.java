package Utils;


import java.io.FileInputStream;

// Used for handling file paths in a platform-independent way
import java.nio.file.Path;
import java.nio.file.Paths;

// Apache POI classes for handling Excel (.xlsx) files
import org.apache.poi.xssf.usermodel.*;

public class ExcelUtils {

    // Method to get data from Excel using sheet name, row index, and cell index
    public static String getData(String sheetName, int row, int cell) {

        try {
            // Creating the path 
            Path path = Paths.get(
                    System.getProperty("user.dir"),
                    "src", "test", "resources", "testdata.xlsx"
            );

            // Creating FileInputStream 
            FileInputStream fis = new FileInputStream(path.toFile());

            // Creating workbook 
            XSSFWorkbook wb = new XSSFWorkbook(fis);

            // Getting the required sheet by name
            XSSFSheet sheet = wb.getSheet(sheetName);

           
            return sheet.getRow(row).getCell(cell).getStringCellValue();

        } catch (Exception e) {
            
            
            return null;
        }
    }
}
