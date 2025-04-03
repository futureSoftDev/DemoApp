package uz.company.employeemanagementsystem.repository.custom.impl;

import jakarta.persistence.criteria.*;
import org.springframework.util.StringUtils;
import uz.company.employeemanagementsystem.domain.Branch;
import uz.company.employeemanagementsystem.domain.Company;
import uz.company.employeemanagementsystem.dto.ResultList;
import uz.company.employeemanagementsystem.filter.BaseFilter;
import uz.company.employeemanagementsystem.repository.custom.BranchRepositoryCustom;

import java.util.ArrayList;
import java.util.List;

public class BranchRepositoryCustomImpl extends BaseRepositoryImpl implements BranchRepositoryCustom {

    @Override
    public ResultList<Branch> getResultList(BaseFilter filter) {
        ResultList<Branch> resultList = new ResultList<>();
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Branch> query = builder.createQuery(Branch.class);

        Root<Branch> branch = query.from(Branch.class);
        Join<Branch, Company> company = branch.join("company", JoinType.INNER);

        List<Predicate> predicates = new ArrayList<>();

        predicates.add(builder.equal(company.get("id"), filter.getCompanyId()));

        if (StringUtils.hasText(filter.getSearch())) {
            Predicate searchPredicate = builder.or(
                    builder.like(branch.get("name"), filter.getSearch())
            );
            predicates.add(searchPredicate);
        }

        CriteriaQuery<Long> countQuery = builder.createQuery(Long.class);
        Root<Branch> branchRoot = countQuery.from(Branch.class);
        countQuery.select(builder.count(branchRoot)).where(predicates.toArray(new Predicate[0]));

        Long count = entityManager.createQuery(countQuery).getSingleResult();
        if (count > 0) {
            resultList.setList(entityManager.createQuery(query).getResultList());
            resultList.setTotal(count);
        }
        return resultList;
    }
}
