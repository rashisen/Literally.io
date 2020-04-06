package com.example.w1742120_cw02;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "phrase_table")
public class Phrase {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String description;

    public Phrase(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setId(int id) {
        this.id = id;
    }
}
