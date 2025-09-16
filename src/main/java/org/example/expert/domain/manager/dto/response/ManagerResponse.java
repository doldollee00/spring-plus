package org.example.expert.domain.manager.dto.response;

import lombok.Getter;
import org.example.expert.domain.user.dto.response.UserResponse;

@Getter
public class ManagerResponse {

    private final Long id;
    private final UserResponse user;
    private final int count;

    public ManagerResponse(Long id, UserResponse user) {
        this.id = id;
        this.user = user;
        this.count = 0;
    }

    public ManagerResponse(int count) {
        this.id = null;
        this.user = null;
        this.count = count;
    }
}
