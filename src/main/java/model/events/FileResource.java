package model.events;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="file_resources")
public class FileResource implements Serializable {

    public static final String FIND_BY_EVENT = "FileResource.findFilesByEventId";

    private String id;
    private String name;
    private String relativePath;
    private List<LessonEvent> events;

    public FileResource() {}

    public FileResource(String name, String relativePath) {
        this.name = name;
        this.relativePath = relativePath;
    }

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "file_event",
            joinColumns = @JoinColumn(name = "file_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id")
    )

    public List<LessonEvent> getEvents() {
        return events;
    }
    public void setEvents(List<LessonEvent> events) {
        this.events = events;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "relative_path")
    public String getRelativePath() {
        return relativePath;
    }
    public void setRelativePath(String relativePath) {
        this.relativePath = relativePath;
    }

}