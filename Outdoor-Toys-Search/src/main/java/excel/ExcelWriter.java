 package excel;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelWriter {

    // Writes given data into an Excel (.xlsx) file
    public void excelWriter(List<String[]> data) throws IOException {
        
        // Log size of data received
        System.out.println("excel data " + data.size());

        // Try-with-resources ensures workbook and stream are closed automatically
        try (XSSFWorkbook workbook = new XSSFWorkbook();
             FileOutputStream fwrite = new FileOutputStream("out.xlsx")) {

            // Create a new sheet in the Excel file
            XSSFSheet sheet = workbook.createSheet("Outdoor toys");
            int rowCount = 0;

            // Iterate over each row of data
            for (String[] rowdata : data) {
                XSSFRow row = sheet.createRow(rowCount++);
                int cellCount = 0;

                // Populate each cell in the current row
                for (String value : rowdata) {
                    XSSFCell cell = row.createCell(cellCount++);
                    cell.setCellValue(value);
                }
            }

            // Write data into the Excel file
            workbook.write(fwrite);

        } catch (FileNotFoundException e) {
            // Handles invalid file path or missing permissions
            System.err.println("File not found or path invalid: " + e.getMessage());
            throw e;

        } catch (IOException e) {
            // Handles Excel write failures
            System.err.println("Error writing to excel: " + e.getMessage());
            throw e;
        }
    }
}