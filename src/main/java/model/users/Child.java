package model.users;

import model.security.User;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="childs")
@NamedQueries({
        @NamedQuery(name = Child.FIND_BY_CLASS, query = "FROM Child c WHERE c.schoolClass.id = :classId"),
        @NamedQuery(name = Child.FIND_ACTIVE, query = "FROM Child c WHERE c.active = true")/*,
        @NamedQuery(name = Child.FIND_BY_PARENT, query = "FROM Child c INNER JOIN c.pa")*/
})
public class Child extends User {

    public static final String FIND_BY_CLASS = "Child.findByClass";
    public static final String FIND_ACTIVE = "Child.findActive";
    public static final String FIND_BY_PARENT = "Child.findByParent";

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "school_class_id", nullable = false)
    private SchoolClass schoolClass;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "relationship",
            joinColumns = {@JoinColumn(name = "child_id")},
            inverseJoinColumns = {@JoinColumn(name = "parent_id")}
            )
    private List<Parent> parents;

    public Child() {
    }

    public List<Parent> getParents() {
        return parents;
    }

    public void setParents(List<Parent> parents) {
        this.parents = parents;
    }

    public SchoolClass getSchoolClass() {
        return schoolClass;
    }

    public void setSchoolClass(SchoolClass schoolClass) {
        this.schoolClass = schoolClass;
    }

    @Override
    public String toString() {
        return "Child{" +
                "id=" + getId() +
                ", firstName=" + firstName +
                ", lastname=" + lastName +
                ", parents=" + parents +
                '}';
    }
}
