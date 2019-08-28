package com.woowacourse.edd.domain;

import com.woowacourse.edd.exceptions.InvalidContentsException;
import com.woowacourse.edd.exceptions.InvalidTitleException;
import com.woowacourse.edd.exceptions.InvalidYoutubeIdException;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String youtubeId;

    @Column(nullable = false, length = 100)
    private String title;

    @Lob
    @Column(nullable = false)
    private String contents;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime createDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creator_id", nullable = false)
    private User creator;

    @Column(nullable = false)
    private int viewCount;

    public Video() {
    }

    public Video(String youtubeId, String title, String contents, User creator) {
        checkYoutubeId(youtubeId);
        checkTitle(title);
        checkContents(contents);
        this.youtubeId = youtubeId.trim();
        this.title = title.trim();
        this.contents = contents.trim();
        this.creator = creator;
        this.viewCount = 0;
    }

    private void checkContents(String contents) {
        if (Objects.isNull(contents) || contents.trim().isEmpty()) {
            throw new InvalidContentsException();
        }
    }

    private void checkTitle(String title) {
        if (Objects.isNull(title) || title.trim().isEmpty()) {
            throw new InvalidTitleException();
        }
    }

    private void checkYoutubeId(String youtubeId) {
        if (Objects.isNull(youtubeId) || youtubeId.trim().isEmpty()) {
            throw new InvalidYoutubeIdException();
        }
    }

    public void update(String youtubeId, String title, String contents) {
        checkYoutubeId(youtubeId);
        checkTitle(title);
        checkContents(contents);
        this.youtubeId = youtubeId;
        this.title = title;
        this.contents = contents;
    }

    public void increaseViewCount() {
        viewCount++;
    }

    public boolean isNotMatch(Long videoId) {
        return id != videoId;
    }

    public Long getId() {
        return id;
    }

    public String getYoutubeId() {
        return youtubeId;
    }

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public User getCreator() {
        return creator;
    }

    public int getViewCount() {
        return viewCount;
    }

    @Override
    public String toString() {
        return "Video{" +
            "id=" + id +
            ", youtubeId='" + youtubeId + '\'' +
            ", title='" + title + '\'' +
            ", contents='" + contents + '\'' +
            ", createDate=" + createDate +
            ", creator=" + creator +
            '}';
    }
}
