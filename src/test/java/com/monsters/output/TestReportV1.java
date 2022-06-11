package com.monsters.output;

import com.monsters.util.Entry;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class TestReportV1 {


//   @Test
//    public void sumEmptyList(){
//       ArrayList<Entry> emptyList = new ArrayList<Entry>;
//       assertNull
//   }

   @Test
    public void testSumList(){
       ArrayList<Entry> entryList = new ArrayList<Entry>();
       Entry entry = new Entry(LocalDate.of(2017,2,3), "project1", "spanie", 3.5, "Jan Kowalski");
       entryList.add(entry);
       entry = new Entry(LocalDate.of(2017,2,3), "project1", "jedzenie", 2.5, "Jan Kowalski");
       entryList.add(entry);
       ReportV1 reportV1=new ReportV1(entryList);
       HashMap<String, Double> summedArray = reportV1.sumEntryList(entryList);
       assertEquals(6.0,summedArray.get("Jan Kowalski"));
   }
   @Test
   public void testSumTwoList(){
      ArrayList<Entry> entryList = new ArrayList<Entry>();
      Entry entry = new Entry(LocalDate.of(2017,2,3), "project1", "spanie", 3.5, "Jan Kowalski");
      entryList.add(entry);
      entry = new Entry(LocalDate.of(2017,2,3), "project1", "spanie", 4.5, "Jan Nowak");
      entryList.add(entry);
      entry = new Entry(LocalDate.of(2017,2,3), "project1", "jedzenie", 3.5, "Jan Nowak");
      entryList.add(entry);
      entry = new Entry(LocalDate.of(2017,2,3), "project1", "jedzenie", 2.5, "Jan Kowalski");
      entryList.add(entry);
      ReportV1 reportV1=new ReportV1(entryList);
      HashMap<String, Double> summedArray = reportV1.sumEntryList(entryList);
      assertEquals(6.0,summedArray.get("Jan Kowalski"));
      assertEquals(8.0,summedArray.get("Jan Nowak"));
   }

   @Test
   public void testPrint(){
      ArrayList<Entry> entryList = new ArrayList<Entry>();
      Entry entry = new Entry(LocalDate.of(2017,2,3), "project1", "spanie", 3.5, "Jan Kowalski");
      entryList.add(entry);
      entry = new Entry(LocalDate.of(2017,2,3), "project1", "spanie", 4.5, "Jan Nowak");
      entryList.add(entry);
      entry = new Entry(LocalDate.of(2017,2,3), "project1", "jedzenie", 3.5, "Jan Nowak");
      entryList.add(entry);
      entry = new Entry(LocalDate.of(2017,2,3), "project1", "jedzenie", 2.5, "Jan Kowalski");
      entryList.add(entry);
      ReportV1 reportV1=new ReportV1(entryList);

      reportV1.exportToConsole();
   }
}
