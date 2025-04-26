// src/main/java/org/example/arcade/repository/impl/HibernateResultRepository.java
package org.example.arcade.repository.impl;

import org.example.arcade.model.Result;
import org.example.arcade.repository.ResultRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import jakarta.persistence.criteria.CriteriaQuery;
import java.util.List;
import java.util.Optional;

/**
 * Implementación de ResultRepository usando Hibernate puro.
 * Encapsula la obtención de Session y la gestión de transacciones.
 */
public class HibernateResultRepository implements ResultRepository {

    private final SessionFactory sessionFactory;

    public HibernateResultRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Result save(Result result) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.saveOrUpdate(result);
            tx.commit();
            return result;
        } catch (RuntimeException e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }

    @Override
    public Optional<Result> findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Result result = session.get(Result.class, id);
            return Optional.ofNullable(result);
        }
    }

    @Override
    public List<Result> findAll() {
        try (Session session = sessionFactory.openSession()) {
            CriteriaQuery<Result> cq = session.getCriteriaBuilder().createQuery(Result.class);
            cq.from(Result.class);
            return session.createQuery(cq).getResultList();
        }
    }
}
