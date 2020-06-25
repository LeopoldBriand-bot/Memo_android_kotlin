package appDatabase

import androidx.room.Database
import androidx.room.RoomDatabase
import memo.Memo
import memo.MemoDAO


@Database(entities = [Memo::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun memoDAO(): MemoDAO?
}
