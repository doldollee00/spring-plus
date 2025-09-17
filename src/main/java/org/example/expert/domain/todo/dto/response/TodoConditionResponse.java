package org.example.expert.domain.todo.dto.response;

import lombok.Getter;
import org.example.expert.domain.manager.dto.response.ManagerResponse;

@Getter
public class TodoConditionResponse {

    private final String title;
    private final int managerCount;
    private final int commentCount;

    public TodoConditionResponse(String title, int managerCount, int commentCount) {
        this.title = title;
        this.managerCount = managerCount;
        this.commentCount = commentCount;
    }
}
