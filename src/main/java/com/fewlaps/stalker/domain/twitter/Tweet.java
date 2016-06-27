package com.fewlaps.stalker.domain.twitter;

import java.util.Date;

public class Tweet {

    private final long id;
    private final String user;
    private final String text;
    private final Date date;

    public Tweet(long id, String user, String text, Date date) {
        this.id = id;
        this.user = user;
        this.text = text;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public String getText() {
        return text;
    }
}
