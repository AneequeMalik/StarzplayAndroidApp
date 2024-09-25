package com.aneeque.domain.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MediaDao {
    @Query("SELECT * FROM media")
    fun getRecentSearchList(): List<MediaDetail>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSearchedItem(media: MediaDetail)

    @Delete
    fun delete(media: MediaDetail)
}