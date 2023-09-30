package shecode.sgip5.DTO;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UniversityDTO {
    private Integer id;

    private String name;

    private String avatarUrl;

    private String description;

    private Double voteAve;

}
