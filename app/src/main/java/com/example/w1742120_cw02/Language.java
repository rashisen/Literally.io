package com.example.w1742120_cw02;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "language_table")
public class Language {

    @PrimaryKey
    @NonNull
    private String langId;
    private String language;
    private int checkValue;

    public Language(@NonNull String langId,String language) {
        this.langId = langId;
        this.language = language;
        this.checkValue = 0;
    }

    public String getLanguage() {
        return language;
    }

    public int getCheckValue() {
        return checkValue;
    }

    public String getLangId() {
        return langId;
    }

    public void setCheckValue(int checkValue) {
        this.checkValue = checkValue;
    }
}
