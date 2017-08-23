import model.events.*;
import model.security.Authority;
import model.security.User;
import model.users.Child;
import model.users.Parent;
import model.users.SchoolClass;
import model.users.Teacher;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.beans.PropertyVetoException;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Aleks on 23.08.2017.
 */
public class DataSource {

    private final static String DRIVER = "org.postgresql.Driver";
    private static String activeRemoteHost = "jdbc:postgresql://localhost:5432/schooljournal053";
    private static String activeUser = "postgres";
    private static String activePassword = "postgres";
    private static SessionFactory hibernateSessionFactory = buildHibernateSessionFactory();

    private static SessionFactory buildHibernateSessionFactory() {

        try {
            Configuration configuration = new Configuration();
            configuration.addAnnotatedClass(ChildMark.class);
            configuration.addAnnotatedClass(FileResource.class);
            configuration.addAnnotatedClass(Lesson.class);
            configuration.addAnnotatedClass(LessonEvent.class);
            configuration.addAnnotatedClass(LessonEventType.class);
            configuration.addAnnotatedClass(Subject.class);
            configuration.addAnnotatedClass(Authority.class);
            configuration.addAnnotatedClass(User.class);
            configuration.addAnnotatedClass(Child.class);
            configuration.addAnnotatedClass(Parent.class);
            configuration.addAnnotatedClass(SchoolClass.class);
            configuration.addAnnotatedClass(Teacher.class);
            configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
            configuration.setProperty("hibernate.connection.driver_class", DRIVER);
            configuration.setProperty("hibernate.connection.username", activeUser);
            configuration.setProperty("hibernate.connection.password", activePassword);
            configuration.setProperty("hibernate.connection.url", activeRemoteHost);
//            aleks.kuzko.configuration.setProperty(Environment.SHOW_SQL, "true");
            configuration.setProperty("hibernate.id.new_generator_mappings", "false");
            configuration.configure("hibernate.cfg.xml");
            System.out.println("1 " + activeUser + " " + activePassword + " " + activeRemoteHost);

            hibernateSessionFactory = configuration.buildSessionFactory(new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build());

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("There was an error during Hibernate buildHibernateSessionFactory()" + e.getLocalizedMessage());
        }

        return hibernateSessionFactory;
    }

    public static SessionFactory getHibernateSessionFactory() {
        return hibernateSessionFactory;
    }
}
