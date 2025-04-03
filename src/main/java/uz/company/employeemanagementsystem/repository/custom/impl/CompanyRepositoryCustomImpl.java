package uz.company.employeemanagementsystem.repository.custom.impl;

import jakarta.persistence.Query;
import org.springframework.util.StringUtils;
import uz.company.employeemanagementsystem.domain.Company;
import uz.company.employeemanagementsystem.dto.ResultList;
import uz.company.employeemanagementsystem.filter.BaseFilter;
import uz.company.employeemanagementsystem.repository.custom.CompanyRepositoryCustom;

public class CompanyRepositoryCustomImpl extends BaseRepositoryImpl implements CompanyRepositoryCustom {

    @Override
    public ResultList<Company> getResultList(BaseFilter filter) {
        ResultList<Company> resultList = new ResultList<>();
        StringBuilder sql = new StringBuilder();
        sql.append(" select c ");
        sql.append(" from company c ");
        sql.append(" where 1=1");
        if (StringUtils.hasText(filter.getSearch())) {
            sql.append(" and (c.name like :search ");
            sql.append(" or c.brand like :search ");
            sql.append(" ) ");
        }
        String countSql = sql.toString().replaceFirst(" select c", "select count(c.id) ");
        sql.append(" order by c.").append(filter.getOrderBy());
        Query query = entityManager.createNativeQuery(sql.toString(), Company.class)
                .setFirstResult(filter.getStart())
                .setMaxResults(filter.getSize());
        Query countQuery = entityManager.createNativeQuery(countSql, Long.class);


        if (StringUtils.hasText(filter.getSearch())) {
            query.setParameter("search", filter.getSearch());
            countQuery.setParameter("search", filter.getSearch());
        }
        long count = (long) countQuery.getSingleResult();
        if (count > 0) {
            resultList.setList(query.getResultList());
        }
        return resultList;
    }
}
