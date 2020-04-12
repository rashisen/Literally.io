package com.example.w1742120_cw02;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;

@Dao
public interface LanguageDao {

    @Insert
    void insertlang(Language language);

    @Update
    void updatelang(Language language);

    /**
     * Used to update a single Language Object in DB
     * @param code Language Object passed to update relevant field
     */
    @Transaction
    @Query("Update language_table set checkValue =:state where langId=:code")
    void updateState(String code, int state);

    @Query("SELECT * FROM language_table WHERE langId= :id")
    Language getLang(String id);

    @Delete
    void deletelang(Language language);

    @Query("DELETE FROM language_table")
    void deleteAllLanguages();

    @Query("SELECT * FROM language_table")
    LiveData<List<Language>> getAllLanguages();

}
