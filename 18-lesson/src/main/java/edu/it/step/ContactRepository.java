package edu.it.step;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class ContactRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void save(Contact contact) {
        entityManager.persist(contact);
    }

    public List<Contact> getAll() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Contact> criteriaQuery = builder.createQuery(Contact.class);
        Root<Contact> root = criteriaQuery.from(Contact.class);
        criteriaQuery.select(root);

        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}
