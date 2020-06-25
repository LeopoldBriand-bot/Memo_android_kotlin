package memo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "memos")
class Memo {
    @PrimaryKey(autoGenerate = true)
    var memoId: Long? = null
    var text: String? = null
    var status: Boolean? = null
    var description: String? = null


    constructor(text: String?, status: Boolean?, description: String?) {
        this.text = text
        this.status = status
        this.description = description
    }
}
