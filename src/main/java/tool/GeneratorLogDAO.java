package tool;

import model.GeneratorLog;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * @author Lukasz Wojtas
 */
public class GeneratorLogDAO {

    public static void save(GeneratorLog generatorLog) {
        Session session = HibernateUtil.openSession();
        Transaction transaction = HibernateUtil.openTransaction(session);
        session.save(generatorLog);
        HibernateUtil.closeTransaction(transaction);
    }

    public static void saveList(List<GeneratorLog> generatorLogs) {
        Session session = HibernateUtil.openSession();
        Transaction transaction = HibernateUtil.openTransaction(session);
        for (GeneratorLog generatorLog : generatorLogs)
            session.save(generatorLog);
        HibernateUtil.closeTransaction(transaction);
    }

}
