package br.com.stefanysouza.userservice.database.entity;


import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;

public class UserKey {

    private String userId;

    private String gameId;

    public UserKey() {
    }

    public UserKey(String userId, String gameId) {
        this.userId = userId;
        this.gameId = gameId;
    }

    @DynamoDBHashKey(attributeName = "user_id")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @DynamoDBRangeKey(attributeName = "game_id")
    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }
}
