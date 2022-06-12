package inputTests;

import com.monsters.input.FileReader;
import com.monsters.util.Entry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class testFileReader_wrongData {

    public final String path = "src/test/resources/test_bledy.xls";
    public List<Entry> entryList;
    public Entry entry;
    LocalDate from;
    LocalDate to;

    @BeforeEach
    public void setUp(){
        FileReader fr = new FileReader();
        from = LocalDate.of(2000,1,1);
        to = LocalDate.now();
        entryList = fr.parseXLS(path,from,to);
        entry = entryList.get(0);
    }

    @Test
    public void fileWithErorrs_3Entries(){
        assertEquals(4, entryList.size());
    }

    @Test
    public void canReadSecondEntry_fromFileWithErrors(){
        Entry entry2 = entryList.get(1);
        assertEquals("drugi task", entry2.getTaskName());
    }

}
