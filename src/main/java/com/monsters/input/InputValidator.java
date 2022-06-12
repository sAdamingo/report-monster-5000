package com.monsters.input;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class InputValidator {
    private static final Logger log = Logger.getLogger(InputValidator.class);


    public static boolean isRowEmptyOrHeader(Row row) {
        if (row.getCell(0) != null
                && row.getCell(0).getCellType() != CellType.BLANK
                && !row.getCell(0).toString().equals("Data")) {
            return true;
        } else {
            log.info("Found empty row");
            return false;
        }
    }


    public static boolean isDateBetweenDates(Row row, LocalDate from, LocalDate to) {
        Date tempDate = row.getCell(0).getDateCellValue();
        LocalDate date = tempDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        if(date.isAfter(from) && date.isBefore(to)){
            return true;
        }
        else{
            log.info("data is invalid row: "
                    + row.getRowNum()
                    + " date should be between "
                    + from + " " + to + " was: "
                    + date );
            return false;
        }
    }

    public static boolean isDurationLessThan24h(Row row){
        Double duration = row.getCell(2).getNumericCellValue();
        if(duration < 24){
            return true;
        }
        else{
            return false;
        }


    }

    public static boolean isDurationPositive(Row row) {
        Double duration = row.getCell(2).getNumericCellValue();
        if(duration >= 0){
            return true;
        }
        else{
            return false;
        }
    }
}
