package uz.company.employeemanagementsystem.repository.custom;

import uz.company.employeemanagementsystem.domain.Employee;
import uz.company.employeemanagementsystem.dto.ResultList;
import uz.company.employeemanagementsystem.filter.BaseFilter;

public interface EmployeeRepositoryCustom {

    ResultList<Employee> getResultList(BaseFilter filter);
}
