package uz.company.employeemanagementsystem.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import uz.company.employeemanagementsystem.domain.Branch;
import uz.company.employeemanagementsystem.dto.BranchDTO;
import uz.company.employeemanagementsystem.dto.BranchDetailDTO;
import uz.company.employeemanagementsystem.dto.BranchListDTO;
import uz.company.employeemanagementsystem.dto.ResultList;
import uz.company.employeemanagementsystem.filter.BaseFilter;
import uz.company.employeemanagementsystem.repository.BranchRepository;
import uz.company.employeemanagementsystem.repository.EmployeeRepository;

import java.util.List;

@Service
@Transactional
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class BranchService {

    BranchRepository branchRepository;
    EmployeeRepository employeeRepository;

    public void validate(BranchDTO branchDTO) {
        if (!StringUtils.hasText(branchDTO.getName())) {
            throw new RuntimeException("Name is required");
        }
        if (!StringUtils.hasText(branchDTO.getAddress())) {
            throw new RuntimeException("Address is required");
        }

        if (branchDTO.getId() != null) {
            if (branchRepository.existsByNameAndIdNot(branchDTO.getName(), branchDTO.getId())) {
                throw new RuntimeException("Company with this name already exists");
            }
        } else {
            if (branchRepository.existsByName(branchDTO.getName())) {
                throw new RuntimeException("Company with this name already exists");
            }
        }
    }

    public Long create(BranchDTO branchDTO) {
        this.validate(branchDTO);
        Branch branch = new Branch();
        branch.setName(branchDTO.getName());
        branch.setAddress(branchDTO.getAddress());
        branch.setCompanyId(branchDTO.getCompanyId());
        return branchRepository.save(branch).getId();
    }

    public Long update(Long id, BranchDTO branchDTO) {
        branchDTO.setId(id);
        this.validate(branchDTO);
        return branchRepository.findById(id).map(branch -> {
            branch.setName(branchDTO.getName());
            branch.setAddress(branchDTO.getAddress());
            branchRepository.save(branch);
            return branch.getId();
        }).orElseThrow(() -> new RuntimeException("Company with this id not found."));
    }

    @Transactional(readOnly = true)
    public BranchDetailDTO get(Long id) {
        return branchRepository.findById(id).map(branch -> {
            BranchDetailDTO branchDetailDTO = new BranchDetailDTO();
            branchDetailDTO.setId(branch.getId());
            branchDetailDTO.setName(branch.getName());
            branchDetailDTO.setAddress(branch.getAddress());
            branchDetailDTO.setCompany(branch.getCompany().toCommonDTO());
            return branchDetailDTO;
        }).orElseThrow(() -> new RuntimeException("Company with this id not found."));
    }

    @Transactional(readOnly = true)
    public Page<BranchListDTO> getList(BaseFilter filter) {
        ResultList<Branch> resultList = branchRepository.getResultList(filter);
        List<BranchListDTO> result =
                resultList.getList().stream().map(branch -> {
                    BranchListDTO branchListDTO = new BranchListDTO();
                    branchListDTO.setId(branch.getId());
                    branchListDTO.setName(branch.getName());
                    branchListDTO.setAddress(branch.getAddress());
                    branchListDTO.setCompany(branch.getCompany().toCommonDTO());
                    return branchListDTO;
                }).toList();
        return new PageImpl<>(result, filter.getPageable(), resultList.getTotal());
    }

    public void delete(Long id) {
        if (employeeRepository.existsByBranchId(id)) {
            throw new RuntimeException("Employee exists with this branchId");
        }
        branchRepository.deleteById(id);
    }
}
