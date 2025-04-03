package uz.company.employeemanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.company.employeemanagementsystem.domain.Company;
import uz.company.employeemanagementsystem.repository.custom.CompanyRepositoryCustom;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long>, CompanyRepositoryCustom {

    boolean existsByName(String name);

    boolean existsByNameAndIdNot(String name, Long id);
}
