package com.bignerdranch.android.pract11.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.bignerdranch.android.pract11.data.models.KnigaTypes
import com.bignerdranch.android.pract11.data.models.KnigaZhanr

@Dao
interface BooksDAO{

    /* Таблица Книг*/
    @Query("SELECT * FROM $BOOKS_TABLE")
    fun getAllBooks(): LiveData<List<KnigaTypes>>

    @Insert
    fun addName(knigaTypes: KnigaTypes)
    fun addAuthor(knigaTypes: KnigaTypes)
    fun addNumPages(knigaTypes: KnigaTypes)

    @Update
    fun saveName(knigaTypes: KnigaTypes)
    fun saveAuthor(knigaTypes: KnigaTypes)
    fun saveNumPages(knigaTypes: KnigaTypes)

    @Delete
    fun killName(knigaTypes: KnigaTypes)
    fun killAuthor(knigaTypes: KnigaTypes)
    fun killNumPages(knigaTypes: KnigaTypes)

    /* Таблица Жанров*/
    @Query("SELECT * FROM $BOOKS_ZHANR_TABLE")
    fun getAllZhanrs(): LiveData<List<KnigaZhanr>>

    @Insert
    fun addZhanr(booksZhanr: KnigaZhanr)

    @Update
    fun saveZhanr(booksZhanr: KnigaZhanr)

    @Delete
    fun killZhanr(booksZhanr: KnigaZhanr)

}