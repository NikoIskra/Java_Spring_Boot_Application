package hr.tvz.iskra.studapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class StudentDto {
    private final String jmbag;
    private final Integer numberOfECTS;
    private final boolean tuitionShouldBePaid;

    private final String firstName;

    private final String lastName;


    @Override
    public String toString() {
        return "StudentDTO{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", jmbag='" + jmbag + '\'' +
                ", numberOfECTS=" + numberOfECTS +
                ", tuitionShouldBePaid=" + tuitionShouldBePaid +
                '}';
    }
}
