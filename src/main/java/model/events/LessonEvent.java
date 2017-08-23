package model.events;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="lesson_events")
public class LessonEvent implements Serializable {

    public static final String SELECT_JOURNAL = "LessonEvent.selectJournal";
    public static final String FIND_BY_CHILD_ID_AND_DATE = "LessonEvent.findByChildAndDate";
    public static final String SELECT_BY_TYPE_AND_TEACHER = "LessonEvent.selectUnfinished";
    public static final String COUNT_UNCOMPLETED_LESSONS = "LessonEvent.countUncompletedLessons";

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", length = 36)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Lesson lesson;

    @Column(name = "event_date")
    private LocalDate eventDate;

    @Column(name = "comment")
    private String comment;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "lessonEvent")
    private List<ChildMark> childMarkList;

    @Column(name = "completed")
    private Boolean completed;

    @Column(name = "completion_date")
    private LocalDate completionDate;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "events")
    private List<FileResource> files;

    public LessonEvent() {
    }

    public LessonEvent(Lesson lesson, LocalDate eventDate,
                       String comment, List<ChildMark> childMarkList, Boolean completed,
                       LocalDate completionDate, List<FileResource> files) {
        this.lesson = lesson;
        this.eventDate = eventDate;
        this.comment = comment;
        this.childMarkList = childMarkList;
        this.completed = completed;
        this.completionDate = completionDate;
        this.files = files;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<ChildMark> getChildMarkList() {
        return childMarkList;
    }

    public void setChildMarkList(List<ChildMark> childMarkList) {
        this.childMarkList = childMarkList;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public LocalDate getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(LocalDate completionDate) {
        this.completionDate = completionDate;
    }

    public List<FileResource> getFiles() {
        return files;
    }

    public void setFiles(List<FileResource> files) {
        this.files = files;
    }
}
