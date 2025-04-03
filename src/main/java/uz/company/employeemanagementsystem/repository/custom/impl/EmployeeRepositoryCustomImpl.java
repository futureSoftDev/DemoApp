package uz.company.employeemanagementsystem.repository.custom.impl;

import jakarta.persistence.TypedQuery;
import org.springframework.util.StringUtils;
import uz.company.employeemanagementsystem.domain.Employee;
import uz.company.employeemanagementsystem.dto.ResultList;
import uz.company.employeemanagementsystem.filter.BaseFilter;
import uz.company.employeemanagementsystem.repository.custom.EmployeeRepositoryCustom;

public class EmployeeRepositoryCustomImpl extends BaseRepositoryImpl implements EmployeeRepositoryCustom {

    @Override
    public ResultList<Employee> getResultList(BaseFilter filter) {
        ResultList<Employee> resultList = new ResultList<>();
        StringBuilder sql = new StringBuilder();
        sql.append("select e ");
        sql.append(" from Employee e ");
        sql.append(" join e.branch b");
        sql.append(" where ");
        sql.append(" b.companyId=:companyId ");
        if (StringUtils.hasText(filter.getSearch())) {
            sql.append(" and (e.firstName like :search ");
            sql.append(" or e.lastName like :search ");
            sql.append(" or e.username like :search ");
            sql.append(" ) ");
        }
        String countSql = sql.toString().replaceFirst(" select e", "select count(e.id) ");
        sql.append(" order by e.").append(filter.getOrderBy());
        TypedQuery<Employee> query = entityManager.createQuery(sql.toString(), Employee.class)
                .setFirstResult(filter.getStart())
                .setMaxResults(filter.getSize());
        TypedQuery<Long> countQuery = entityManager.createQuery(countSql, Long.class);

        query.setParameter("companyId", filter.getCompanyId());
        countQuery.setParameter("companyId", filter.getCompanyId());

        if (StringUtils.hasText(filter.getSearch())) {
            query.setParameter("search", filter.getSearch());
            countQuery.setParameter("search", filter.getSearch());
        }
        Long count = countQuery.getSingleResult();
        if (count > 0) {
            resultList.setList(query.getResultList());
        }
        return resultList;
    }
}
