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
        assertTrue("Projekt1".equals(entry.getProject()));
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

}
