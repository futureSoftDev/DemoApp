package uz.company.employeemanagementsystem.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Table
@Entity(name = "company")
public class Company extends BaseEntity {

    @Column(name = "name")
    String name;

    @Column(name = "brand")
    String brand;

    @Column(name = "address")
    String address;
}
