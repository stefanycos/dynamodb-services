package br.com.stefanysouza.userservice.web.dto;

import br.com.stefanysouza.userservice.database.entity.User;
import br.com.stefanysouza.userservice.database.entity.UserKey;
import br.com.stefanysouza.userservice.database.entity.UserLevel;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UserRequestDTO {

    @NotNull
    @NotEmpty
    @JsonProperty("user_id")
    private String userId;

    @NotNull
    @NotEmpty
    @JsonProperty("game_id")
    private String gameId;

    @NotNull
    @NotEmpty
    private String level;

    @NotNull
    private UserStatus status;

    @NotNull
    private Integer age;


    @NotNull
    @NotEmpty
    private String name;

    public User toUser() {
        User user = new User(new UserKey(this.userId, this.gameId));
        user.setUserLevel(UserLevel.valueOf(this.level));
        user.setAge(this.age);
        user.setName(this.name);
        user.setStatus(status.equals(UserStatus.ATIVO));

        return user;
    }
}
