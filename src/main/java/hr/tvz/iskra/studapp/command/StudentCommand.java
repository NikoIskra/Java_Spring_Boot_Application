package hr.tvz.iskra.studapp.command;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentCommand {

    private Long ID;

    @NotNull
    @Pattern(message = "JMBAG must have 10 digits", regexp="[\\d]{10}")
    private String JMBAG;

    @NotBlank(message = "First name must not be empty")
    private String firstName;

    @NotBlank(message = "Last name must not be empty")
    private String lastName;

    @JsonFormat(pattern = "dd.MM.yyyy.")
    @NotNull(message = "Date of birth must be entered")
    @Past(message = "Date of birth must be in the past")
    private LocalDate dateOfBirth;

    @NotNull(message = "Number of ECTS points must be entered")
    @PositiveOrZero(message = "Number of ECTS must be entered as a positive integer")
    @Max(message = "Number of ECTS can not be higher than 480", value = 480)
    private Integer numberOfECTS;

    public StudentCommand(String JMBAG, String firstName, String lastName, LocalDate dateOfBirth, Integer numberOfECTS) {
        this.JMBAG = JMBAG;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.numberOfECTS = numberOfECTS;
    }
}
