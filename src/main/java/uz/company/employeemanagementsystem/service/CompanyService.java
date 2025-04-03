package uz.company.employeemanagementsystem.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import uz.company.employeemanagementsystem.domain.Company;
import uz.company.employeemanagementsystem.dto.CompanyDTO;
import uz.company.employeemanagementsystem.dto.CompanyDetailDTO;
import uz.company.employeemanagementsystem.dto.CompanyListDTO;
import uz.company.employeemanagementsystem.repository.CompanyRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class CompanyService {

    CompanyRepository companyRepository;

    public void validate(CompanyDTO companyDTO) {
        if (!StringUtils.hasText(companyDTO.getName())) {
            throw new RuntimeException("Name is required");
        }
        if (!StringUtils.hasText(companyDTO.getAddress())) {
            throw new RuntimeException("Address is required");
        }

        if (companyDTO.getId() != null) {
            if (companyRepository.existsByNameAndIdNot(companyDTO.getName(), companyDTO.getId())) {
                throw new RuntimeException("Company with this name already exists");
            }
        } else {
            if (companyRepository.existsByName(companyDTO.getName())) {
                throw new RuntimeException("Company with this name already exists");
            }
        }
    }

    public Long create(CompanyDTO companyDTO) {
        this.validate(companyDTO);
        Company company = new Company();
        company.setName(companyDTO.getName());
        company.setAddress(companyDTO.getAddress());
        company.setBrand(companyDTO.getBrand());
        return companyRepository.save(company).getId();
    }

    public Long update(Long id, CompanyDTO companyDTO) {
        companyDTO.setId(id);
        this.validate(companyDTO);
        return companyRepository.findById(id).map(company -> {
            company.setName(companyDTO.getName());
            company.setAddress(companyDTO.getAddress());
            company.setBrand(companyDTO.getBrand());
            return company.getId();
        }).orElseThrow(() -> new RuntimeException("Company with this id not found."));
    }

    @Transactional(readOnly = true)
    public CompanyDetailDTO get(Long id) {
        return companyRepository.findById(id).map(company -> {
            CompanyDetailDTO companyDetailDTO = new CompanyDetailDTO();
            companyDetailDTO.setId(company.getId());
            companyDetailDTO.setName(company.getName());
            companyDetailDTO.setAddress(company.getAddress());
            companyDetailDTO.setBrand(company.getBrand());
            return companyDetailDTO;
        }).orElseThrow(() -> new RuntimeException("Company with this id not found."));
    }

    @Transactional(readOnly = true)
    public List<CompanyListDTO> getList() {
        return companyRepository.findAll().stream().map(company -> {
            CompanyListDTO companyListDTO = new CompanyListDTO();
            companyListDTO.setId(company.getId());
            companyListDTO.setName(company.getName());
            companyListDTO.setAddress(company.getAddress());
            companyListDTO.setBrand(company.getBrand());
            return companyListDTO;
        }).collect(Collectors.toList());
    }


}
