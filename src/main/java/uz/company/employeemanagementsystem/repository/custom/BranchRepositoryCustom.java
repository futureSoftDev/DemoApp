package uz.company.employeemanagementsystem.repository.custom;

import uz.company.employeemanagementsystem.domain.Branch;
import uz.company.employeemanagementsystem.dto.ResultList;
import uz.company.employeemanagementsystem.filter.BaseFilter;

public interface BranchRepositoryCustom {

    ResultList<Branch> getResultList(BaseFilter filter);
}
