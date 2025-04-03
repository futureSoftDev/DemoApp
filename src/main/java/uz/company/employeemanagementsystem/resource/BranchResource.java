package uz.company.employeemanagementsystem.resource;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.company.employeemanagementsystem.dto.*;
import uz.company.employeemanagementsystem.filter.BaseFilter;
import uz.company.employeemanagementsystem.service.BranchService;

@RestController
@RequestMapping("/api/branch")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class BranchResource {

    BranchService branchService;

    @PostMapping
    public ResponseEntity<Long> create(@RequestBody BranchDTO branchDTO) {
        return ResponseEntity.ok(branchService.create(branchDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> update(@PathVariable Long id, @RequestBody BranchDTO branchDTO) {
        return ResponseEntity.ok(branchService.update(id, branchDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BranchDetailDTO> get(@PathVariable Long id) {
        return ResponseEntity.ok(branchService.get(id));
    }

    @GetMapping
    public ResponseEntity<Page<BranchListDTO>> getList(BaseFilter filter) {
        return ResponseEntity.ok(branchService.getList(filter));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        branchService.delete(id);
        return ResponseEntity.ok().build();
    }
}