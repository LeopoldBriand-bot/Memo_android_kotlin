package memo

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update


@Dao
abstract class MemoDAO {
    @Query("SELECT * FROM memos")
    abstract fun getListMemos(): MutableList<Memo>

    @Query("SELECT COUNT(*) FROM memos WHERE text = :text")
    abstract fun countMemosByText(text: String?): Long

    @Insert
    abstract fun insert(vararg courses: Memo?)

    @Update
    abstract fun update(vararg courses: Memo?)

    @Delete
    abstract fun delete(vararg courses: Memo?)

}