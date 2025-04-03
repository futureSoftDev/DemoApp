package uz.company.employeemanagementsystem.repository.custom.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@FieldDefaults(makeFinal = true, level = AccessLevel.PROTECTED)
public class BaseRepositoryImpl {

    @PersistenceContext
    EntityManager entityManager;
}
