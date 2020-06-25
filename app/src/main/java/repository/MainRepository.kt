package repository

import android.content.Context
import appDatabase.AppDatabaseHelper
import main.DIApplication
import memo.Memo
import memo.MemoDAO
import javax.inject.Inject

class MainRepository {

    @Inject
    lateinit var  memoDao: MemoDAO

    @Inject
    lateinit var applicationContext: Context

    init {
        DIApplication.getAppComponent()?.inject(this)
    }

    fun getData(context: Context, listMemo: MutableList<Memo>?) {
            listMemo!!.addAll(AppDatabaseHelper.getDatabase(context).memoDAO()?.getListMemos()!!)
    }
}