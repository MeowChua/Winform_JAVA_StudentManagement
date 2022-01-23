/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Technical;

import Model.admin;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author WIN 10
 */
public class ReadEcalAdmin {
      public static final int COLUMN_INDEX_ID_GV = 0;
    public static final int COLUMN_INDEX_FULLNAME = 1;
    public static final int COLUMN_INDEX_DOB = 2;
    public static final int COLUMN_INDEX_ADDRESS = 3;
    public static final int COLUMN_INDEX_EMAIL = 4;
    public static final int COLUMN_INDEX_SDT = 5;

    public static List<admin> readExcel(String excelFilePath) throws IOException {
        List<admin> listBooks = new ArrayList<>();
 
        // Get file
        InputStream inputStream = new FileInputStream(new File(excelFilePath));
 
        // Get workbook
        Workbook workbook = getWorkbook(inputStream, excelFilePath);
 
        // Get sheet
        Sheet sheet = workbook.getSheetAt(0);
 
        // Get all rows
        Iterator<Row> iterator = sheet.iterator();
        while (iterator.hasNext()) {
            Row nextRow = iterator.next();
            if (nextRow.getRowNum() == 0) {
                // Ignore header
                continue;
            }
 
            // Get all cells
            Iterator<Cell> cellIterator = nextRow.cellIterator();
 
            // Read cells and set value for book object
            admin book = new admin();
            while (cellIterator.hasNext()) {
                //Read cell
                Cell cell = cellIterator.next();
                Object cellValue = getCellValue(cell);
                if (cellValue == null || cellValue.toString().isEmpty()) {
                    continue;
                }
                // Set value for book object
                int columnIndex = cell.getColumnIndex();
                switch (columnIndex) {
                case COLUMN_INDEX_ID_GV:
                    book.setID((String)getCellValue(cell));
                    break;
                case COLUMN_INDEX_FULLNAME:
                    book.setTenAD((String) getCellValue(cell));
                    break;
                case COLUMN_INDEX_DOB:
                    book.setDOB((String) getCellValue(cell));
                    break;
                case COLUMN_INDEX_ADDRESS:
                    book.setADDRESS((String) getCellValue(cell));
                    break;
                case COLUMN_INDEX_SDT:
                    book.setSDT((String) getCellValue(cell));
                    break;
                case COLUMN_INDEX_EMAIL:
                    book.setEMAIL((String) getCellValue(cell));
                    break;    
                default:
                    break;
                }
 
            }
            listBooks.add(book);
        }
 
        workbook.close();
        inputStream.close();
 
        return listBooks;
    }
 
    // Get Workbook
    private static Workbook getWorkbook(InputStream inputStream, String excelFilePath) throws IOException {
        Workbook workbook = null;
        if (excelFilePath.endsWith("xlsx")) {
            workbook = new XSSFWorkbook(inputStream);
        } else if (excelFilePath.endsWith("xls")) {
            workbook = new HSSFWorkbook(inputStream);
        } else {
            throw new IllegalArgumentException("The specified file is not Excel file");
        }
 
        return workbook;
    }
 
    // Get cell value
    private static Object getCellValue(Cell cell) {
        CellType cellType = cell.getCellTypeEnum();
        Object cellValue = null;
        switch (cellType) {
        case BOOLEAN:
            cellValue = cell.getBooleanCellValue();
            break;
        case FORMULA:
            Workbook workbook = cell.getSheet().getWorkbook();
            FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
            cellValue = evaluator.evaluate(cell).getNumberValue();
            break;
        case NUMERIC:
            cellValue = cell.getNumericCellValue();
            break;
        case STRING:
            cellValue = cell.getStringCellValue();
            break;
        case _NONE:
        case BLANK:
        case ERROR:
            break;
        default:
            break;
        }
 
        return cellValue;
    }
    
    public static void main(String[] args) throws IOException {
        final String excelFilePath = "C:\\Users\\WIN 10\\Documents\\SV.xlsx";
        final List<admin> books = readExcel(excelFilePath);
        for (admin book : books) {
            System.out.println(book);
        }
    }
}
