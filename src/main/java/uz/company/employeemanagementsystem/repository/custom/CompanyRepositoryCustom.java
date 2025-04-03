package uz.company.employeemanagementsystem.repository.custom;

import uz.company.employeemanagementsystem.domain.Company;
import uz.company.employeemanagementsystem.dto.ResultList;
import uz.company.employeemanagementsystem.filter.BaseFilter;

public interface CompanyRepositoryCustom {

    ResultList<Company> getResultList(BaseFilter filter);
}
