package uz.company.employeemanagementsystem.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.company.employeemanagementsystem.dto.CommonDTO;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "branch")
@Entity
public class Branch extends BaseEntity {

    @Column(name = "name")
    String name;

    @Column(name = "address")
    String address;

    @Column(name = "company_id", nullable = false)
    Long companyId;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "company_id", insertable = false, updatable = false)
    Company company;

    public CommonDTO toCommonDTO() {
        return new CommonDTO(getId(), getName());
    }
}
