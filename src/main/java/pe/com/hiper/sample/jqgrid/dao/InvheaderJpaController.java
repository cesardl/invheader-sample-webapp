/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.hiper.sample.jqgrid.dao;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import pe.com.hiper.sample.jqgrid.dao.exceptions.NonexistentEntityException;
import pe.com.hiper.sample.jqgrid.entidad.Invheader;

/**
 *
 * @author s22023
 */
public class InvheaderJpaController implements Serializable {

    public InvheaderJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Invheader invheader) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(invheader);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Invheader invheader) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            invheader = em.merge(invheader);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = invheader.getInvid();
                if (findInvheader(id) == null) {
                    throw new NonexistentEntityException("The invheader with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Invheader invheader;
            try {
                invheader = em.getReference(Invheader.class, id);
                invheader.getInvid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The invheader with id " + id + " no longer exists.", enfe);
            }
            em.remove(invheader);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Invheader> findInvheaderEntities() {
        return findInvheaderEntities(true, -1, -1);
    }

    public List<Invheader> findInvheaderEntities(int maxResults, int firstResult) {
        return findInvheaderEntities(false, maxResults, firstResult);
    }

    private List<Invheader> findInvheaderEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Invheader.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Invheader findInvheader(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Invheader.class, id);
        } finally {
            em.close();
        }
    }

    public int getInvheaderCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Invheader> rt = cq.from(Invheader.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}