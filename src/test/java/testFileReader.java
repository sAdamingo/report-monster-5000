import com.monsters.input.FileReader;
import com.monsters.util.Entry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class testFileReader {

    public final String path = "src/test/resources/test_tes3e.xls";
    public List<Entry> entityList;
    public Entry entry;

    @BeforeEach
    public void setUp(){
        FileReader fr = new FileReader();
        entityList= fr.parseXLS(path);
        entry = entityList.get(0);
    }

    @Test
    public void canReadTaskName(){

        assertTrue(entry.getTaskName().equals("pierwszy task"));
    }

    @Test
    public void canReadData(){
        assertTrue(LocalDate.of(2012,01,05).equals(entry.getDate()));
    }

    @Test
    public void canReadProject(){
        assertEquals("Projekt1",entry.getProject());
    }

    @Test
    public void canReadUser(){
        assertEquals("test tes3e",entry.getUser());
    }

    @Test
    public void canReadDuratio(){
        assertEquals(3,entry.getDuration());
    }

    @Test
    public void shouldBe2Entries(){
        assertEquals(2,entityList.size());
    }

    @Test
    public void fileWithErorrs_3Entries(){
        FileReader fr = new FileReader();
        List<Entry> entries = fr.parseXLS("src/test/resources/test_bledy.xls");
        Entry entry = entries.get(1);
        assertEquals(3, entries.size());

    }

    @Test
    public void canReadSecondEntry_fromFileWithErrors(){
        FileReader fr = new FileReader();
        List<Entry> entries = fr.parseXLS("src/test/resources/test_bledy.xls");
        Entry entry = entries.get(1);
        assertEquals("drugi task", entry.getTaskName());
    }



}
