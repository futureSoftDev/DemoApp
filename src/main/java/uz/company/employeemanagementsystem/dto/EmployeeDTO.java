package uz.company.employeemanagementsystem.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeDTO {

    Long id;

    String firstName;

    String lastName;

    String username;

    Long branchId;

    Long companyId;
}
