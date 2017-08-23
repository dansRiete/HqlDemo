package model.users;

import model.security.User;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="parents")
@NamedQueries({
        @NamedQuery(name = Parent.FIND_ACTIVE,
                query = "FROM Parent p WHERE p.active = true"),
        @NamedQuery(name = Parent.FIND_AVAILABLE,
                query = "SELECT p FROM Parent p WHERE NOT p.id IN (SELECT p FROM Parent p INNER JOIN p.children c WHERE c.id = :childId)")
})
public class Parent extends User {

    public static final String FIND_ACTIVE = "Parent.findActive";
    public static final String FIND_AVAILABLE = "Parent.findAvailable";

    @Column(name = "job", length = 64)
    private String job;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "relationship",
            joinColumns = {@JoinColumn(name = "parent_id")},
            inverseJoinColumns = {@JoinColumn(name = "child_id")})
    private List<Child> children;

    public Parent() {
    }

    public Parent(String job) {
        this.job = job;
    }

    public List<Child> getChildren() {
        return children;
    }

    public void setChildren(List<Child> children) {
        this.children = children;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    @Override
    public String toString() {
        return "[Parent: id=" + getId() + " " + firstName + " " + lastName + ']';
    }
}
