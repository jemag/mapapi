package ca.ogsl.mapapi.dao;

import ca.ogsl.mapapi.util.GenericsUtil;
import org.hibernate.transform.DistinctResultTransformer;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class GenericDao {
    public <T> T getEntityFromCode(String lang, String code, Class clazz) {
        PersistenceManager.setLanguageContext(lang);
        T entity;
        EntityManager em = MapApiEntityManagerFactory.createEntityManager();
        CriteriaBuilder cb = MapApiEntityManagerFactory.getCriteriaBuilder();
        try {
            CriteriaQuery<T> cq = cb.createQuery(clazz);
            Root<T> root = cq.from(clazz);
            cq.where(cb.equal(root.get("code"), code));
            TypedQuery<T> tq = em.createQuery(cq);
            entity = GenericsUtil.getSingleResultOrNull(tq);
            return entity;
        } finally {
            em.close();
        }
    }

    public <T> List<T> getAllEntities(String lang, Class clazz) {
        PersistenceManager.setLanguageContext(lang);
        EntityManager em = MapApiEntityManagerFactory.createEntityManager();
        CriteriaBuilder cb = MapApiEntityManagerFactory.getCriteriaBuilder();
        List<T> entities;
        try {
            CriteriaQuery<T> cq = cb.createQuery(clazz);
            Root<T> root = cq.from(clazz);
            TypedQuery<T> tq = em.createQuery(cq);
            entities = tq.getResultList();
            entities = DistinctResultTransformer.INSTANCE.transformList(entities);
        } finally {
            em.close();
        }
        return entities;
    }

    public <T> T getEntityFromId(String lang, Integer id, Class clazz) {
        PersistenceManager.setLanguageContext(lang);
        T entity;
        EntityManager em = MapApiEntityManagerFactory.createEntityManager();
        CriteriaBuilder cb = MapApiEntityManagerFactory.getCriteriaBuilder();
        try {
            CriteriaQuery<T> cq = cb.createQuery(clazz);
            Root<T> root = cq.from(clazz);
            cq.where(cb.equal(root.get("id"), id));
            TypedQuery<T> tq = em.createQuery(cq);
            entity = GenericsUtil.getSingleResultOrNull(tq);
        } finally {
            em.close();
        }
        return entity;
    }

    public <T> T mergeEntity(T entity) {
        EntityManager em = MapApiEntityManagerFactory.createEntityManager();
        EntityTransaction et = em.getTransaction();
        T databaseEntity;
        try {
            et.begin();
            databaseEntity = em.merge(entity);
            et.commit();
        } finally {
            em.close();
        }
        return databaseEntity;
    }

    public <T> void deleteEntityFromId(Integer id, Class clazz) {
        EntityManager em = MapApiEntityManagerFactory.createEntityManager();
        EntityTransaction et = em.getTransaction();
        CriteriaBuilder cb = MapApiEntityManagerFactory.getCriteriaBuilder();
        T databaseEntity;
        try {
            et.begin();
            CriteriaQuery<T> cq = cb.createQuery(clazz);
            Root<T> root = cq.from(clazz);
            cq.where(cb.equal(root.get("id"), id));
            TypedQuery<T> tq = em.createQuery(cq);
            databaseEntity = GenericsUtil.getSingleResultOrNull(tq);
            em.remove(databaseEntity);
            et.commit();
        } finally {
            em.close();
        }

    }
}
