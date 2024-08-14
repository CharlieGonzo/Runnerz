package gonzo.learning.runnerz.run;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryRunRepositoryTest {

    InMemoryRunRepository repository;

    @BeforeEach
    void setUp() {
        repository = new InMemoryRunRepository();
        repository.create(new Run(1,"morning run", LocalDateTime.now(),LocalDateTime.now().plusHours(1),3,Location.INDOOR));
        repository.create(new Run(2,"afternoon run", LocalDateTime.now(),LocalDateTime.now().plusHours(1),4,Location.OUTDOOR));
    }

    @Test
    void shouldFinAllRuns(){
        List<Run> runs = repository.findAll();
        assertEquals(2,runs.size(),"should have returned two runs");

    }

    @Test
    void ShouldFindRunWithValidId(){
        var run = repository.findById(1).get();
        assertEquals("morning run",run.title());
        assertEquals(3,run.miles(),"failed to retrieve run corectly");
    }

    @Test
    void shouldFindRunWithInvalidId(){
        RunNotFoundException notFoundException = assertThrows(
                RunNotFoundException.class,
                () -> repository.findById(3)
        );
    }

    @Test
    void shouldCreateNewRun(){
        repository.create(new Run(3,"night run", LocalDateTime.now(),LocalDateTime.now().plusHours(1),4,Location.OUTDOOR));
        List<Run> runs = repository.findAll();
        assertEquals(3,runs.size());
    }

    @Test
    void shouldUpdateRun(){
        repository.update(new Run(1,"not morning run", LocalDateTime.now(),LocalDateTime.now().plusHours(1),5,Location.OUTDOOR),1);
        var run = repository.findById(1).get();
        assertEquals("not morning run",run.title());
        assertEquals(5,run.miles());
        assertEquals("OUTDOOR",run.location().toString());
    }

    void shouldDeleteRun(){
        repository.delete(1);
        List<Run> runs = repository.findAll();
        assertEquals(
                1,
                runs.size()
        );
    }



}