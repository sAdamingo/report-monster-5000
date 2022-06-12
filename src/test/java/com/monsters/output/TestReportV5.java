package com.monsters.output;

import com.monsters.util.Entry;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

public class TestReportV5 {
    @Test
    public void testPrint(){
        ArrayList<Entry> entryList = new ArrayList<Entry>();
        Entry entry = new Entry(LocalDate.of(2017,2,3), "project1", "spanie", 3.5, "Jan Kowalski");
        entryList.add(entry);
        entry = new Entry(LocalDate.of(2017,2,3), "project1", "spanie", 4.5, "Jan Nowak");
        entryList.add(entry);
        entry = new Entry(LocalDate.of(2017,2,3), "project2", "jedzenie", 3.5, "Jan Nowak");
        entryList.add(entry);
        entry = new Entry(LocalDate.of(2017,2,3), "project1", "jedzenie", 2.5, "Jan Kowalski");
        entryList.add(entry);
        ReportV5 reportV5=new ReportV5(entryList);

        reportV5.exportToConsole();
    }
}
