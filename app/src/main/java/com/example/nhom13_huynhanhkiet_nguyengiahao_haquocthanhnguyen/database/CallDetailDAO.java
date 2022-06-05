package com.example.nhom13_huynhanhkiet_nguyengiahao_haquocthanhnguyen.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
import io.reactivex.Flowable;

@Dao
public interface CallDetailDAO {

    @Query("SELECT * FROM call_detail ORDER BY uid DESC")
    List<CallDetailEntity> getAll();

    @Insert
    void insert(CallDetailEntity callDetailEntity);

    @Update
    void update(CallDetailEntity callDetailEntity);

    @Delete
    void delete(CallDetailEntity callDetailEntity);
}

