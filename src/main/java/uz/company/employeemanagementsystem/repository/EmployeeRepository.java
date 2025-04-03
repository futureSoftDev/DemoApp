package uz.company.employeemanagementsystem.repository;

import org.springframework.stereotype.Repository;
import uz.company.employeemanagementsystem.domain.Employee;
import uz.company.employeemanagementsystem.repository.custom.EmployeeRepositoryCustom;

@Repository
public interface EmployeeRepository extends BaseRepository<Employee, Long>, EmployeeRepositoryCustom {

    boolean existsByCompanyId(Long companyId);

    boolean existsByBranchId(Long companyId);

    boolean existsByUsername(String username);

    boolean existsByUsernameAndIdNot(String username, Long id);
}
