import com.monsters.input.FileReader;
import com.monsters.util.Entry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class testFileReader_correctData {

    public final String path = "src/test/resources/test_tes3e.xls";
    public List<Entry> entityList;
    public Entry entry;
    LocalDate from;
    LocalDate to;


    @BeforeEach
    public void setUp(){
        FileReader fr = new FileReader();
        from = LocalDate.of(2000,1,1);
        to = LocalDate.now();
        entityList= fr.parseXLS(path,from,to);
        entry = entityList.get(0);
    }

    @Test
    public void canReadTaskName(){
        assertEquals(entry.getTaskName(),("pierwszy task"));
    }

    @Test
    public void canReadData(){
        assertEquals(LocalDate.of(2012,01,05),(entry.getDate()));
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






}
