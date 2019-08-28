package com.woowacourse.edd.application.response;


public class VideoPreviewResponse {

    private final Long id;
    private final String youtubeId;
    private final String title;
    private final String createDate;
    private final VideoResponse.CreatorResponse creator;
    private final int viewCount;

    public VideoPreviewResponse(Long id, String youtubeId, String title, String createDate, VideoResponse.CreatorResponse creator, int viewCount) {
        this.id = id;
        this.youtubeId = youtubeId;
        this.title = title;
        this.createDate = createDate;
        this.creator = creator;
        this.viewCount = viewCount;
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

    public String getCreateDate() {
        return createDate;
    }

    public VideoResponse.CreatorResponse getCreator() {
        return creator;
    }

    public int getViewCount() {
        return viewCount;
    }
}
