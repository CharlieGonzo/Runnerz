package gonzo.learning.runnerz.run;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.asm.TypeReference;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.InputStream;

@Component
public class RunJsonDataLoader {

    private static final Logger log = LoggerFactory.getLogger(RunJsonDataLoader.class);
    private final ObjectMapper objectMapper;

    private final JdbcRunRepository runRepository;

    public RunJsonDataLoader(JdbcRunRepository runRepository, ObjectMapper objectMapper){
        this.runRepository = runRepository;
        this.objectMapper = objectMapper;
    }

//    @Override
//    public void run(String... args) throws Exception {
//        if(runRepository.count() == 0){
//            try(InputStream inputStream = TypeReference.class.getResourceAsStream("/data/runs.json")){
//                Runs allRuns = objectMapper.readValue(inputStream,Runs.class);
//                log.info("Reading json data for saving in-memory collection");
//                runRepository.saveAll(allRuns.runs());
//            }
//        }else{
//            log.info("Not loading Runs from JSON because collection already exist");
//        }
//    }
}
