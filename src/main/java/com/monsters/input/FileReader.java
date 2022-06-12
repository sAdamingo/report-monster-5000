package com.monsters.input;

import com.monsters.Main;
import com.monsters.util.Entry;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

import java.io.*;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


public class FileReader {

    private static final Logger log = Logger.getLogger(FileReader.class);

    public List<Entry> parseXLS(String filePath) {

        String user = getUserName(filePath);
        InputStream inputStream = getFileInputStream(filePath);
        List<Entry> entryList = new ArrayList<>();
        HSSFWorkbook workbook = null;

        try {
            workbook = new HSSFWorkbook(inputStream);

        } catch (IOException e) {
            e.printStackTrace();
        }

        int numberOfSheets = workbook.getNumberOfSheets();
        for (int i = 0; i < numberOfSheets; i++) {

            HSSFSheet currentSheet = workbook.getSheetAt(i);
            Iterator<Row> rowIterator = currentSheet.rowIterator();

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                if (validateEntryData(row)) {
                    Entry entry = getEntry(user, currentSheet, row);
                    entryList.add(entry);
                }
            }
        }
        return entryList;
    }

    private boolean validateEntryData(Row row) {
        return row.getCell(0) != null && row.getCell(0).getCellType() != CellType.BLANK && !row.getCell(0).toString().equals("Data");
    }

    private Entry getEntry(String user, HSSFSheet currentSheet, Row row) {
        Date tempDate = row.getCell(0).getDateCellValue();
        LocalDate date = tempDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        String project = currentSheet.getSheetName();
        String taskName = validateTaskName(row);
        Double duration = row.getCell(2).getNumericCellValue();

        Entry entry = new Entry(date, project, taskName, duration, user);
        log.info("Created entry: " + entry.toString());
        return entry;
    }

    private String validateTaskName(Row row) {
        if(row.getCell(1) == null || row.getCell(1).getCellType() == CellType.BLANK){
            return "NO DATA";
        }
        return row.getCell(1).getStringCellValue();
    }

    private String getUserName(String filePath) {
        String user = filePath.substring(filePath.lastIndexOf("/") + 1)
                .replace(".xls", "")
                .replace("_", " ");
        return user;
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
