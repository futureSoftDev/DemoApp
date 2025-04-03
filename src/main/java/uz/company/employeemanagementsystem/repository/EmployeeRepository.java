package uz.company.employeemanagementsystem.repository;

import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.company.employeemanagementsystem.domain.Employee;
import uz.company.employeemanagementsystem.repository.custom.EmployeeRepositoryCustom;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>, EmployeeRepositoryCustom {
}
