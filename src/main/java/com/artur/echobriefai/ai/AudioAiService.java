package com.artur.echobriefai.ai;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AudioAiService {

    private final ChatClient chatClient;

    public String processText(String userText) {
        return chatClient.prompt()
                .system("""
                        You are EchoBrief, a productivity assistant.
                        Your job is to analyze what the user said and extract tasks from it.
                        For each task found, call the createTask tool.
                        After creating all tasks, reply in the same language the user used with a short summary like:
                        "I created X task(s): [list them]"
                        """)
                .user(userText)
                .tools(taskTools)
                .call()
                .content();
    }

    private final TaskTools taskTools;
}