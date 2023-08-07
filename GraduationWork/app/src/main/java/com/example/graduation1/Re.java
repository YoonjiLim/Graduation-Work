package com.example.graduation1;

public class Re {
    private String id;
    private String user;
    private String content;
    private String time;
    private String boardID;

    public Re(String id, String user, String content, String time, String boardID) {
        this.id = id;
        this.user = user;
        this.content = content;
        this.time = time;
        this.boardID = boardID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getBoardID() {
        return boardID;
    }

    public void setBoardID(String boardID) {
        this.boardID = boardID;
    }
}
