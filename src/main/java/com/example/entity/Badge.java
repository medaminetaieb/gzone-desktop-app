package com.example.entity;

public class Badge {
    private Integer id;
    private Integer gameId;
    private String title;

    public Badge(Integer id, Integer gameId, String title) {
        this.id = id;
        this.gameId = gameId;
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGameId() {
        return gameId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Badge{" +
                "id=" + id +
                ", gameId=" + gameId +
                ", title='" + title + '\'' +
                '}';
    }
}
