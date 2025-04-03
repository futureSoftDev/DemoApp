package uz.company.employeemanagementsystem.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import uz.company.employeemanagementsystem.domain.Employee;
import uz.company.employeemanagementsystem.dto.EmployeeDTO;
import uz.company.employeemanagementsystem.dto.EmployeeDetailDTO;
import uz.company.employeemanagementsystem.dto.EmployeeListDTO;
import uz.company.employeemanagementsystem.dto.ResultList;
import uz.company.employeemanagementsystem.filter.BaseFilter;
import uz.company.employeemanagementsystem.repository.BranchRepository;
import uz.company.employeemanagementsystem.repository.EmployeeRepository;

import java.util.List;

@Service
@Transactional
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class EmployeeService {

    BranchRepository branchRepository;
    EmployeeRepository employeeRepository;

    public void validate(EmployeeDTO employeeDTO) {
        if (employeeDTO.getCompanyId() == null) {
            throw new RuntimeException("Company id is null");
        }
        if (employeeDTO.getBranchId() == null) {
            throw new RuntimeException("Branch id is null");
        }
        if (!StringUtils.hasText(employeeDTO.getUsername())) {
            throw new RuntimeException("Username is required");
        }
        if (!StringUtils.hasText(employeeDTO.getFirstName())) {
            throw new RuntimeException("FirstName is required");
        }
        if (!StringUtils.hasText(employeeDTO.getLastName())) {
            throw new RuntimeException("LastName is required");
        }

        if (employeeDTO.getId() != null) {
            if (employeeRepository.existsByUsernameAndIdNot(employeeDTO.getUsername(), employeeDTO.getId())) {
                throw new RuntimeException("Employee with this username already exists");
            }
        } else {
            if (employeeRepository.existsByUsername(employeeDTO.getUsername())) {
                throw new RuntimeException("Employee with this username already exists");
            }
        }
    }

    public Long create(EmployeeDTO employeeDTO) {
        this.validate(employeeDTO);
        Employee employee = new Employee();
        employee.setFirstName(employeeDTO.getFirstName());
        employee.setLastName(employeeDTO.getLastName());
        employee.setUsername(employeeDTO.getUsername());
        employee.setBranchId(employeeDTO.getBranchId());
        employee.setCompanyId(employeeDTO.getCompanyId());
        return employeeRepository.save(employee).getId();
    }

    public Long update(Long id, EmployeeDTO employeeDTO) {
        employeeDTO.setId(id);
        this.validate(employeeDTO);
        return employeeRepository.findById(id).map(employee -> {
            employee.setFirstName(employeeDTO.getFirstName());
            employee.setLastName(employeeDTO.getLastName());
            employee.setUsername(employeeDTO.getUsername());
            employee.setBranchId(employeeDTO.getBranchId());
            employeeRepository.save(employee);
            return employee.getId();
        }).orElseThrow(() -> new RuntimeException("Company with this id not found."));
    }

    @Transactional(readOnly = true)
    public EmployeeDetailDTO get(Long id) {
        return employeeRepository.findById(id).map(employee -> {
            EmployeeDetailDTO employeeDetailDTO = new EmployeeDetailDTO();
            employeeDetailDTO.setFirstName(employee.getFirstName());
            employeeDetailDTO.setLastName(employee.getLastName());
            employeeDetailDTO.setUsername(employee.getUsername());
            if (employee.getBranchId() != null) {
                employeeDetailDTO.setBranch(employee.getBranch().toCommonDTO());
            }
            employeeDetailDTO.setCompany(employee.getCompany().toCommonDTO());
            return employeeDetailDTO;
        }).orElseThrow(() -> new RuntimeException("Company with this id not found."));
    }

    @Transactional(readOnly = true)
    public Page<EmployeeListDTO> getList(BaseFilter filter) {
        ResultList<Employee> resultList = employeeRepository.getResultList(filter);
        List<EmployeeListDTO> result =
                resultList.getList().stream().map(employee -> {
                    EmployeeListDTO employeeListDTO = new EmployeeListDTO();
                    employeeListDTO.setFirstName(employee.getFirstName());
                    employeeListDTO.setLastName(employee.getLastName());
                    employeeListDTO.setUsername(employee.getUsername());
                    if (employee.getBranchId() != null) {
                        employeeListDTO.setBranch(employee.getBranch().toCommonDTO());
                    }
                    employeeListDTO.setCompany(employee.getCompany().toCommonDTO());
                    return employeeListDTO;
                }).toList();
        return new PageImpl<>(result, filter.getPageable(), resultList.getTotal());
    }

    public void delete(Long id) {
        employeeRepository.deleteById(id);
    }
}
