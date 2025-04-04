package uz.company.employeemanagementsystem.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.company.employeemanagementsystem.dto.CommonDTO;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "company")
@Entity
public class Company extends BaseEntity {

    @Column(name = "name")
    String name;

    @Column(name = "brand")
    String brand;

    @Column(name = "address")
    String address;


    public CommonDTO toCommonDTO() {
        return new CommonDTO(getId(), getName());
    }
}
