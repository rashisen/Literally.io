package com.example.w1742120_cw02;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface LanguageDao {

    @Insert
    void insertlang(Language language);

    @Update
    void updatelang(Language language);

    @Query("SELECT * FROM language_table WHERE langId= :id")
    Language getLang(String id);

    @Delete
    void deletelang(Language language);

    @Query("DELETE FROM language_table")
    void deleteAllLanguages();

    @Query("SELECT * FROM language_table")
    LiveData<List<Language>> getAllLanguages();

}
