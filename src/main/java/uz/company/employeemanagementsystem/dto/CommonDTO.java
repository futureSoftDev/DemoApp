package uz.company.employeemanagementsystem.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommonDTO {

    Long id;

    String name;

    public CommonDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
