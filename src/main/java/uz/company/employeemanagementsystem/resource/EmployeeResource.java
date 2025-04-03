package uz.company.employeemanagementsystem.resource;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.company.employeemanagementsystem.dto.EmployeeDTO;
import uz.company.employeemanagementsystem.dto.EmployeeDetailDTO;
import uz.company.employeemanagementsystem.dto.EmployeeListDTO;
import uz.company.employeemanagementsystem.filter.BaseFilter;
import uz.company.employeemanagementsystem.service.EmployeeService;

@RestController
@RequestMapping("/api/employee")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class EmployeeResource {

    EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<Long> create(@RequestBody EmployeeDTO employeeDTO) {
        return ResponseEntity.ok(employeeService.create(employeeDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> update(@PathVariable Long id, @RequestBody EmployeeDTO employeeDTO) {
        return ResponseEntity.ok(employeeService.update(id, employeeDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDetailDTO> get(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.get(id));
    }

    @GetMapping
    public ResponseEntity<Page<EmployeeListDTO>> getList(BaseFilter filter) {
        return ResponseEntity.ok(employeeService.getList(filter));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        employeeService.delete(id);
        return ResponseEntity.ok().build();
    }
}