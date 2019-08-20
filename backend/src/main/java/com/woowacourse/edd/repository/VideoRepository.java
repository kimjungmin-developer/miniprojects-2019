package com.woowacourse.edd.repository;

import com.woowacourse.edd.domain.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface VideoRepository extends JpaRepository<Video, Long> {
}
