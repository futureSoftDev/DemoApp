package uz.company.employeemanagementsystem.repository.custom.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public abstract class BaseRepositoryImpl {

    @PersistenceContext
    protected EntityManager entityManager;
}
