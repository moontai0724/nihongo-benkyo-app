package tw.edu.pu.nihongo_benkyo.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.CoroutineScope

@Database(
    entities = [
        Type::class,
        Tag::class,
        QuestionType::class,
        QuestionTag::class,
        Question::class,
        HistoryTag::class,
        HistoryDetail::class,
        History::class],
    version = 1
)
abstract class MyDatabase : RoomDatabase() {
    abstract fun getDao(): SqlDao

    companion object {
        @Volatile
        private var INSTANCE: MyDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): MyDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance =
                    Room.databaseBuilder(
                        context.applicationContext,
                        MyDatabase::class.java,
                        "MyDatabase"
                    ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}