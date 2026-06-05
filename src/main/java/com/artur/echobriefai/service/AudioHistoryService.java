package com.artur.echobriefai.service;

import com.artur.echobriefai.dto.AudioHistoryResponse;
import com.artur.echobriefai.entity.AudioHistory;
import com.artur.echobriefai.entity.User;
import com.artur.echobriefai.repository.AudioHistoryRepository;
import com.artur.echobriefai.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AudioHistoryService {

    private final AudioHistoryRepository audioHistoryRepository;
    private final UserRepository userRepository;

    public void save(String transcription, String reply) {
        User user = getCurrentUser();
        AudioHistory history = AudioHistory.builder()
                .user(user)
                .transcription(transcription)
                .reply(reply)
                .build();
        audioHistoryRepository.save(history);
    }

    public List<AudioHistoryResponse> findAll() {
        User user = getCurrentUser();
        return audioHistoryRepository.findByUserOrderByCreatedAtDesc(user)
                .stream()
                .map(AudioHistoryResponse::from)
                .toList();
    }

    private User getCurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(email).orElseThrow();
    }
}