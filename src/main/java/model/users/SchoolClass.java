package model.users;

import model.events.Lesson;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="school_classes")
public class SchoolClass implements Serializable {

    public static final String FIND_ACTIVE = "SchoolClass.findActive";

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", nullable = false, length = 36)
    private String id;

    @Column(name = "name", nullable = false, length = 36)
    private String name;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "schoolClass")
    private List<Child> children;

    @Column(name = "studying_start")
    private LocalDateTime studyingStart;

    @Column(name = "studying_end")
    private LocalDateTime studyingEnd;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "curator_id")
    private Teacher curator;

    @Column(name = "active", nullable = false)
    private boolean active;

    @OneToMany(mappedBy = "schoolClass")
    private List<Lesson> lessons;

    public SchoolClass() {
    }

    public LocalDateTime getStudyingStart() {
        return studyingStart;
    }

    public void setStudyingStart(LocalDateTime studyingStart) {
        this.studyingStart = studyingStart;
    }

    public LocalDateTime getStudyingEnd() {
        return studyingEnd;
    }

    public void setStudyingEnd(LocalDateTime studyingEnd) {
        this.studyingEnd = studyingEnd;
    }

    public Teacher getCurator() {
        return curator;
    }

    public void setCurator(Teacher curator) {
        this.curator = curator;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Child> getChildren() {
        return children;
    }

    public void setChildren(List<Child> children) {
        this.children = children;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }
}
