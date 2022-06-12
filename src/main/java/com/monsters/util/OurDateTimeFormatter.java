package com.monsters.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class OurDateTimeFormatter {

    LocalDateTime now;

    public OurDateTimeFormatter() {
        this.now = LocalDateTime.now();;
    }

    public String getFormattedDateTime(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        return now.format(formatter);
    }
}
