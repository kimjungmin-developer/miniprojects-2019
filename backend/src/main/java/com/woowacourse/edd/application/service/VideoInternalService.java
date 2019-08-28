package com.woowacourse.edd.application.service;

import com.woowacourse.edd.application.dto.VideoUpdateRequestDto;
import com.woowacourse.edd.domain.Video;
import com.woowacourse.edd.exceptions.VideoNotFoundException;
import com.woowacourse.edd.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
class VideoInternalService {

    private final VideoRepository videoRepository;

    @Autowired
    public VideoInternalService(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    public Video save(Video video) {
        return videoRepository.save(video);
    }

    @Transactional(readOnly = true)
    public Page<Video> findAll(Pageable pageable) {
        return videoRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Video findById(long id) {
        return videoRepository.findById(id)
            .orElseThrow(VideoNotFoundException::new);
    }

    public void updateViewCount(Long videoId) {
        findById(videoId).increaseViewCount();
    }

    public Video update(Long id, VideoUpdateRequestDto requestDto) {
        Video video = findById(id);
        video.update(requestDto.getYoutubeId(), requestDto.getTitle(), requestDto.getContents());
        return video;
    }

    public void delete(Long id) {
        findById(id);
        videoRepository.deleteById(id);
    }
}
