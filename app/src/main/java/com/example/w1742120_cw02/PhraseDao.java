package com.example.w1742120_cw02;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;

@Dao
public interface PhraseDao {

    @Insert
    void insertPhrase(Phrase phrase);

    @Update
    void updatePhrase(Phrase phrase);

    @Delete
    void deletePhrase(Phrase phrase);

    @Query("DELETE FROM phrase_table")
    void deleteAllPhrases();

    @Query("SELECT * FROM phrase_table")
    LiveData<List<Phrase>> getAllPhrases();

    @Query("SELECT * FROM phrase_table ORDER BY description ASC")
    LiveData<List<Phrase>> getAllSortedPhrases();
}
