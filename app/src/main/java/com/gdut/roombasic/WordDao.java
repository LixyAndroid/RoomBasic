package com.gdut.roombasic;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

/**
 * @author Li Xuyang
 * date  2019/9/7 20:14
 */
@Dao  // Database access object
public interface WordDao {

    @Insert
    void insertWords(Word... words);

    @Update
    void updataWords(Word... words);

    @Delete
    void deleteWords(Word...words);

    @Query("DELETE FROM Word")
    void deleteAllWords();

    @Query("SELECT * FROM WORD ORDER BY ID DESC")
    //List<Word> getAllWrods();
    LiveData<List<Word>>getAllWordsLive();



}
