package com.artur.echobriefai.repository;

import com.artur.echobriefai.entity.AudioHistory;
import com.artur.echobriefai.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AudioHistoryRepository extends JpaRepository<AudioHistory, Long> {
    List<AudioHistory> findByUserOrderByCreatedAtDesc(User user);
}