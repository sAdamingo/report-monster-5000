package com.monsters.input;

import com.monsters.util.Entry;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


public class FileReader {
    private Path path;
    private List<Entry> entries;

    public List<Entry> parseXLS(String filePath) {

        String user = filePath.substring(filePath.lastIndexOf("/") + 1).replace(".xls", "").replace("_", " ");


        InputStream is = getFileInputStream(filePath);
        List<Entry> entryList = new ArrayList<>();

        HSSFWorkbook wb = null;
        try {
            wb = new HSSFWorkbook(is);

        } catch (IOException e) {
            e.printStackTrace();
        }

        int sheets = wb.getNumberOfSheets();
        for (int i = 0; i < sheets; i++) {

            HSSFSheet sheet = wb.getSheetAt(i);
            Iterator<Row> rowIterator = sheet.rowIterator();

            rowIterator.next(); //nie bież pod uwagę pierwszego wiersza

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                if (row.getCell(0) != null && row.getCell(0).getCellType() != CellType.BLANK) {

                    List<String> rowValues = new ArrayList<>();

                    Date date = row.getCell(0).getDateCellValue();
                    LocalDate dt = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

                    String project = sheet.getSheetName();
                    String taskName = row.getCell(1).getStringCellValue();
                    Double duration = row.getCell(2).getNumericCellValue();
                    String userName = user;

                    Entry entry = new Entry(dt, project, taskName, duration, userName);
                    entryList.add(entry);
                }
            }
        }
        return entryList;
    }

    private InputStream getFileInputStream(String filePath) {
        try {
            InputStream is = new FileInputStream(filePath);
            return is;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


    //    public void parseXLSX() {
    //        TODO
//    }
}
