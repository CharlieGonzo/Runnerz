package gonzo.learning.runnerz.run;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

public record Run(
        Integer id,
        @NotEmpty
        String title,
        LocalDateTime startedOn,
        LocalDateTime completedOn,

        @Positive
        Integer miles,
        Location location
) {

    public Run{
        if(!completedOn.isAfter(startedOn)){
            System.out.println("Started on:" + startedOn + " Completed on: " + completedOn);
            throw new IllegalArgumentException("Completed on must be after started on");
        }
    }
}
