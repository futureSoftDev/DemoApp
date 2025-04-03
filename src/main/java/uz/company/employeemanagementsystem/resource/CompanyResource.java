package uz.company.employeemanagementsystem.resource;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.company.employeemanagementsystem.dto.CompanyDTO;
import uz.company.employeemanagementsystem.dto.CompanyDetailDTO;
import uz.company.employeemanagementsystem.dto.CompanyListDTO;
import uz.company.employeemanagementsystem.filter.BaseFilter;
import uz.company.employeemanagementsystem.service.CompanyService;

@RestController
@RequestMapping("/api/company")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class CompanyResource {

    CompanyService companyService;

    @PostMapping
    public ResponseEntity<Long> create(@RequestBody CompanyDTO companyDTO) {
        return ResponseEntity.ok(companyService.create(companyDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> update(@PathVariable Long id, @RequestBody CompanyDTO companyDTO) {
        return ResponseEntity.ok(companyService.update(id, companyDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyDetailDTO> get(@PathVariable Long id) {
        return ResponseEntity.ok(companyService.get(id));
    }

    @GetMapping
    public ResponseEntity<Page<CompanyListDTO>> getList(BaseFilter filter) {
        return ResponseEntity.ok(companyService.getList(filter));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        companyService.delete(id);
        return ResponseEntity.ok().build();
    }
}