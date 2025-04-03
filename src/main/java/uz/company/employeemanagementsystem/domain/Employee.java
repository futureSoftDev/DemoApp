package uz.company.employeemanagementsystem.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import uz.company.employeemanagementsystem.dto.CommonDTO;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "employee")
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Employee extends BaseEntity {

    @Column(name = "username")
    String username;

    @Column(name = "first_name")
    String firstName;

    @Column(name = "last_name")
    String lastName;

    @Column(name = "branch_id")
    Long branchId;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "branch_id", insertable = false, updatable = false)
    Branch branch;

    @Column(name = "company_id")
    Long companyId;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "company_id", insertable = false, updatable = false)
    Company company;

    public CommonDTO toCommonDTO() {
        return new CommonDTO(getId(), getName());
    }

    public String getName() {
        return firstName + " " + lastName;
    }
}
