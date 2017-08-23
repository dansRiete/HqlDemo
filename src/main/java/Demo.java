import model.users.Child;
import model.users.Parent;

import javax.persistence.EntityManager;
import javax.persistence.Parameter;
import java.util.List;

/**
 * Created by Aleks on 23.08.2017.
 */
public class Demo {
    public static void main(String[] args) {
        EntityManager entityManager = DataSource.getHibernateSessionFactory().createEntityManager();
//        String hql1 = "SELECT p FROM Parent p INNER JOIN p.children c WHERE c.id = '204'";
        String hql2 = "SELECT DISTINCT c FROM Child c INNER JOIN c.parents p WHERE p.id = '202'";
        List<Child> parents = entityManager.createQuery(hql2).getResultList();
        parents.forEach(child -> System.out.println(child.toString() + '\n'));
        entityManager.close();
    }
}
