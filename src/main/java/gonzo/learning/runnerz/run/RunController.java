package gonzo.learning.runnerz.run;


import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/runs")
public class RunController {

    //private List<Run> runs = new ArrayList<>();

    private final JdbcRunRepository runRepository;

    public RunController(JdbcRunRepository runRepository) {
        this.runRepository = runRepository;
    }

    @GetMapping("")
    List<Run> findAll(){
       return runRepository.findAll();
   }


   @GetMapping("/{id}")
   Run findById(@PathVariable Integer id){

       Optional<Run> run = runRepository.findById(id);

       if(run.isEmpty()){
           throw new RunNotFoundException();
       }
       return run.get();
   }


   //post
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    void create(@Valid @RequestBody Run run){
        runRepository.create(run);
    }

    //put
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    void update(@Valid @RequestBody Run run, @PathVariable Integer id){
        runRepository.update(run,id);
    }



    @ResponseStatus(HttpStatus.NO_CONTENT)
    //delete
    @DeleteMapping("/{id}")
    void delete(Integer id){
        runRepository.delete(id);
    }

    @GetMapping("/location/{location}")
    List<Run> findByLocation(@PathVariable String location){
        return runRepository.findByLocation(location);
    }


}
