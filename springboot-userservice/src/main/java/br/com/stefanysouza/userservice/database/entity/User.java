package br.com.stefanysouza.userservice.database.entity;

import br.com.stefanysouza.userservice.database.converters.LocalDateTimeConverter;
import com.amazonaws.services.dynamodbv2.datamodeling.*;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.time.LocalDateTime;

@DynamoDBTable(tableName = "User")
public class User implements Serializable {

    @Id
    private UserKey userKey;

    @DynamoDBTypeConvertedEnum
    @DynamoDBAttribute(attributeName = "user_level")
    private UserLevel userLevel;

    @DynamoDBAttribute(attributeName = "status")
    private Boolean status;

    @DynamoDBAttribute(attributeName = "name")
    private String name;

    @DynamoDBAttribute(attributeName = "age")
    private Integer age;

    @DynamoDBTypeConverted(converter = LocalDateTimeConverter.class)
    @DynamoDBAttribute(attributeName = "created_at")
    private LocalDateTime createdAt;

    public User() {
    }

    public User(UserKey userKey) {
        this.userKey = userKey;
    }

    @DynamoDBHashKey(attributeName = "user_id")
    public String getUserId() {
        return userKey != null ? userKey.getUserId() : null;
    }

    public void setUserId(String userId) {
        if (userKey == null) {
            userKey = new UserKey();
        }

        userKey.setUserId(userId);
    }

    @DynamoDBRangeKey(attributeName = "game_id")
    public String getGameId() {
        return userKey != null ? userKey.getGameId() : null;
    }

    public void setGameId(String gameId) {
        if (userKey == null) {
            userKey = new UserKey();
        }

        userKey.setGameId(gameId);
    }

    public UserLevel getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(UserLevel userLevel) {
        this.userLevel = userLevel;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
