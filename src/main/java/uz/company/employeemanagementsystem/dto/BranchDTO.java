package uz.company.employeemanagementsystem.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BranchDTO {

    Long id;

    String name;

    String address;

    Long companyId;
}
