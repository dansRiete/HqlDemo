package model.events;

import model.users.Child;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * This class is used to describe child mark entity.
 */
@Entity
@Table(name="child_marks")
public class ChildMark implements Serializable {
    public static final String SELECT_BY_LESSON_EVENT_ID = "ChildMark.selectByLessonEventId";
    public static final String GET_BY_CHILD_ID = "ChildMark.getMarksByChildId";
    public static final String GET_BY_CHILD_ID_AND_DATE_RANGE = "ChildMark.getByChildIdAndDateRange";

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", length = 36)
    private String id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "lesson_event_id")
    private LessonEvent lessonEvent;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "child_id")
    private Child child;

    @ManyToOne(fetch = FetchType.LAZY)
    private LessonEventType lessonEventType;

    @Column(name = "mark")
    private Integer mark;

    @Column(name = "absent")
    private Boolean absent;

    private String remark;

    public ChildMark(LessonEvent lessonEvent, Child child, Integer mark, boolean absent) {
        this.lessonEvent = lessonEvent;
        this.child = child;
        this.mark = mark;
        this.absent = absent;
    }

    public ChildMark() {
    }

    public ChildMark(Integer mark, Boolean absent) {
        this.mark = mark;
        this.absent = absent;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LessonEvent getLessonEvent() {
        return lessonEvent;
    }

    public void setLessonEvent(LessonEvent lessonEvent) {
        this.lessonEvent = lessonEvent;
    }

    public Child getChild() {
        return child;
    }

    public void setChild(Child child) {
        this.child = child;
    }

    public Integer getMark() {
        return mark;
    }

    public String getStringMark() {
        if (mark == null)
            return "";
        return String.valueOf(mark);
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    public Boolean getAbsent() {
        return absent;
    }

    public void setAbsent(Boolean absent) {
        this.absent = absent;
    }
}

