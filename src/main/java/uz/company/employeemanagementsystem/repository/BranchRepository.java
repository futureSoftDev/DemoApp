package uz.company.employeemanagementsystem.repository;

import org.springframework.stereotype.Repository;
import uz.company.employeemanagementsystem.domain.Branch;
import uz.company.employeemanagementsystem.repository.custom.BranchRepositoryCustom;

@Repository
public interface BranchRepository extends BaseRepository<Branch, Long>, BranchRepositoryCustom {

    boolean existsByCompanyId(Long companyId);

    boolean existsByNameAndIdNot(String name, Long id);

    boolean existsByName(String name);
}
