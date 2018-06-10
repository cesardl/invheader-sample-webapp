package pe.com.hiper.sample.jqgrid.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.persistence.config.SessionCustomizer;
import org.eclipse.persistence.sessions.Session;
import org.eclipse.persistence.sessions.SessionEvent;
import org.eclipse.persistence.sessions.SessionEventAdapter;
import org.eclipse.persistence.sessions.UnitOfWork;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * Created on 09/06/2018.
 *
 * @author Cesardl
 */
public class ImportSQL implements SessionCustomizer {

    private static Log log = LogFactory.getLog(ImportSQL.class);

    private void importSql(UnitOfWork unitOfWork, String fileName) {
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            stream.filter(line -> !line.isEmpty()).forEach(unitOfWork::executeNonSelectingSQL);

        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }

    @Override
    public void customize(Session session) {
        session.getEventManager().addListener(new SessionEventAdapter() {
            @Override
            public void postLogin(SessionEvent event) {
                String fileName = event.getSession().getProperty("import.sql.file").toString();
                UnitOfWork unitOfWork = event.getSession().acquireUnitOfWork();
                importSql(unitOfWork, fileName);
                unitOfWork.commit();
            }
        });
    }
}
