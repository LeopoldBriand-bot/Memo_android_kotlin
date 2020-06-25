package main

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import appDatabase.AppDatabaseHelper
import main.R
import memo.ItemTouchHelperCallBack
import memo.Memo
import memo.MemoAdapter
import repository.MainRepository
import viewModel.MainViewModel


class MainActivity : AppCompatActivity() {
    var listMemos: MutableList<Memo> = mutableListOf()
    var newMemoText: EditText? = null
    var createMemoButton: Button? = null
    private var mainViewModel: MainViewModel? = null
    private var memosAdapter: MemoAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView = findViewById<RecyclerView>(R.id.list_memo)
        recyclerView.setHasFixedSize(true)
        newMemoText = findViewById<View>(R.id.editText) as EditText
        createMemoButton = findViewById<View>(R.id.button) as Button
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        // TODO: Use ViewModel to get memoDao
        // listMemos = AppDatabaseHelper.getDatabase(this).memoDAO()?.getListMemos()!!

        val context = applicationContext
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        mainViewModel!!.initialization(MainRepository())
        mainViewModel?.loadData(context, listMemos)

        memosAdapter = MemoAdapter(listMemos, this)
        recyclerView.adapter = memosAdapter
        val itemTouchHelper = ItemTouchHelper(
            ItemTouchHelperCallBack(memosAdapter!!)
        )
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }


    fun createMemo(view: View?) {
        val memoDTO = Memo(newMemoText!!.text.toString(), false, "Default description")
        listMemos!!.add(memoDTO)
        memosAdapter!!.notifyItemInserted(listMemos!!.size)
        AppDatabaseHelper.getDatabase(this).memoDAO()?.insert(memoDTO)
    }
}
