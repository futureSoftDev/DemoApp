package uz.company.employeemanagementsystem.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CompanyDetailDTO {

    Long id;

    String name;

    String address;

    String brand;
}
