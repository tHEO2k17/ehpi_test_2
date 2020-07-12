package co.effectstudios.resplash.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import co.effectstudios.resplash.data.local.entity.AuthorEntry

@Dao
interface AuthorDao {

    @Transaction()
    fun addAllAuthors(authors: List<AuthorEntry>){
        deleteAllAuthors()
        addAuthor(authors)
    }


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAuthor(author: List<AuthorEntry>)

    @Query("DELETE FROM authors_table")
    fun deleteAllAuthors()

    @Query("SELECT * FROM authors_table")
    fun getAllAuthors(): LiveData<List<AuthorEntry>>
}