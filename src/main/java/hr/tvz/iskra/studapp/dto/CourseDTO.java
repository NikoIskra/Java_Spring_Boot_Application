package hr.tvz.iskra.studapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CourseDTO {
    private final String name;
    private final Integer numberOfECTS;


    public String getName() {
        return name;
    }

    public Integer getNumberOfECTS() {
        return numberOfECTS;
    }
}
