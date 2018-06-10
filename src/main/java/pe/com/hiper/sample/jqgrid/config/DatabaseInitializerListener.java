package pe.com.hiper.sample.jqgrid.config;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created on 07/06/2018.
 *
 * @author Cesardl
 */
public class DatabaseInitializerListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().log("ServletContextListener started");

        EntityManager em = Persistence.createEntityManagerFactory("jgGridPU").createEntityManager();
        em.getTransaction().begin();

        Query query = em.createNativeQuery("CALL 1");

        Object result = query.getSingleResult();
        sce.getServletContext().log("Opened EntityManager " + result);

        em.getTransaction().commit();
        em.close();


//        if (em.isOpen()) {
//            sce.getServletContext().log("Opened EntityManager");
//        } else {
//            sce.getServletContext().log("Not Opened EntityManager");
//        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        sce.getServletContext().log("ServletContextListener destroyed");
    }
}