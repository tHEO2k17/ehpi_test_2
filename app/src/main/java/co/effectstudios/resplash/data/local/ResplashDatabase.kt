package co.effectstudios.resplash.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import co.effectstudios.resplash.data.local.entity.AuthorEntry

@Database(
    entities = [AuthorEntry::class],
    version = 1
)
abstract class ResplashDatabase : RoomDatabase() {
    abstract fun authorDao(): AuthorDao
//
//    companion object {
//        @Volatile
//        private var instance: ResplashDatabase? = null
//        private val LOCK = Any()
//
//        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
//            instance ?: buildDatabase(context).also { instance = it }
//        }
//
//        private fun buildDatabase(context: Context) =
//            Room.databaseBuilder(
//                context.applicationContext,
//                ResplashDatabase::class.java,
//                "resplash_db"
//            ).build()
//    }
}