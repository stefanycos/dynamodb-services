package br.com.stefanysouza.userservice.web.dto;

import br.com.stefanysouza.userservice.database.entity.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Setter
@Getter
public class UserResponseDTO {

    @JsonProperty("user_id")
    private String userId;

    @JsonProperty("game_id")
    private String gameId;

    private String level;

    private UserStatus status;

    private Integer age;

    private String name;

    @JsonProperty("created_at")
    private String cratedAt;

    public static UserResponseDTO toUserResponse(final User user) {
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setUserId(user.getUserId());
        userResponseDTO.setName(user.getName());
        userResponseDTO.setAge(user.getAge());
        userResponseDTO.setGameId(user.getGameId());
        userResponseDTO.setLevel(user.getUserLevel().name());
        userResponseDTO.setStatus(user.getStatus() ? UserStatus.ATIVO : UserStatus.INATIVO);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        userResponseDTO.setCratedAt(user.getCreatedAt().format(formatter));

        return userResponseDTO;
    }

    public static List<UserResponseDTO> toUserResponse(final List<User> users) {
        return users.stream()
                .map(UserResponseDTO::toUserResponse)
                .collect(Collectors.toList());
    }
}
