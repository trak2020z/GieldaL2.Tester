package tool;

import model.GeneratorLog;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import org.json.JSONObject;

import java.util.Properties;

/**
 * @author Lukasz Wojtas
 */
public class HibernateUtil {

    private static SessionFactory sessionFactory;

    public static void initSessionFactory(JSONObject obj) throws Exception {
        Properties prop = new Properties();
        prop.setProperty(Environment.DIALECT, "");
        prop.setProperty(Environment.DRIVER, "");
        prop.setProperty(Environment.URL, "");
        prop.setProperty(Environment.USER, "");
        prop.setProperty(Environment.PASS, "");
        prop.setProperty(Environment.POOL_SIZE, "20");
        prop.setProperty(Environment.SHOW_SQL, "true");
        prop.setProperty(Environment.AUTO_CLOSE_SESSION, "true");
        prop.setProperty(Environment.FLUSH_BEFORE_COMPLETION, "true");

        for (String key : prop.stringPropertyNames()) {
            if (obj.has(key))
                prop.setProperty(key, obj.getString(key));
            else if (prop.getProperty(key).isEmpty())
                throw new Exception("Missing hibernate configuration: " + key);
        }

        Configuration conf = new Configuration();
        conf.setProperties(prop);
        conf.addAnnotatedClass(GeneratorLog.class);

        ServiceRegistry sr = new StandardServiceRegistryBuilder().applySettings(conf.getProperties()).build();
        sessionFactory = conf.buildSessionFactory(sr);
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static Session openSession() {
        return sessionFactory.openSession();
    }

    public static void closeSession(Session session) {
        session.close();
    }

    public static Transaction openTransaction(Session session) {
        return session.beginTransaction();
    }

    public static void closeTransaction(Transaction transaction) {
        transaction.commit();
    }

    public static JSONObject getConfigTemplate() throws Exception {
        JSONObject obj = new JSONObject();
        obj.put(Environment.DIALECT, "");
        obj.put(Environment.DRIVER, "");
        obj.put(Environment.URL, "");
        obj.put(Environment.USER, "");
        obj.put(Environment.PASS, "");
        obj.put(Environment.POOL_SIZE, "20");
        obj.put(Environment.SHOW_SQL, "true");
        obj.put(Environment.AUTO_CLOSE_SESSION, "true");
        obj.put(Environment.FLUSH_BEFORE_COMPLETION, "true");

        return obj;
    }

}
