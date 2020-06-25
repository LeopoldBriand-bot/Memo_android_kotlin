package memo


import fragment.DetailsFragment
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import appDatabase.AppDatabaseHelper
import main.DetailsActivity
import main.R
import java.util.*


class MemoAdapter(
    private val listMemos: MutableList<Memo>,
    var activity: AppCompatActivity
) :
    RecyclerView.Adapter<MemoAdapter.MemoViewHolder?>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemoViewHolder {
        val viewCourse: View =
            LayoutInflater.from(parent.context).inflate(R.layout.memo, parent, false)
        return MemoViewHolder(viewCourse)
    }

    override fun onBindViewHolder(holder: MemoViewHolder, position: Int) {
        holder.textViewTextMemo.text = listMemos[position]!!.text
        holder.checkboxViewMemo.isChecked = listMemos[position]!!.status!!
    }

    override fun getItemCount(): Int {
        return listMemos.size
    }

    inner class MemoViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var textViewTextMemo: TextView
        var checkboxViewMemo: CheckBox

        init {
            textViewTextMemo = itemView.findViewById(R.id.text_memo)
            textViewTextMemo.setOnClickListener { view ->
                val memoDTO = listMemos[adapterPosition]
                val orientation = view.resources.configuration.orientation
                val screenSize = view.resources.configuration.screenLayout and
                        Configuration.SCREENLAYOUT_SIZE_MASK
                if (orientation == Configuration.ORIENTATION_PORTRAIT && (screenSize == Configuration.SCREENLAYOUT_SIZE_NORMAL || screenSize == Configuration.SCREENLAYOUT_SIZE_SMALL)) {
                    val intent = Intent(view.context, DetailsActivity::class.java)
                    intent.putExtra("memoName", memoDTO!!.text)
                    intent.putExtra("memoDescription", memoDTO.description)
                    intent.putExtra("memoStatus", memoDTO.status)
                    view.context.startActivity(intent)
                } else {
                    val fragment = DetailsFragment()
                    val bundle = Bundle()
                    fragment.arguments = bundle
                    bundle.putString("memoName", memoDTO!!.text)
                    bundle.putString("memoDescription", memoDTO.description)
                    val fragmentManager =
                        activity.supportFragmentManager
                    val fragmentTransaction =
                        fragmentManager.beginTransaction()
                    fragmentTransaction.replace(R.id.fragmentParent, fragment, "details")
                    fragmentTransaction.commit()
                }
            }
            checkboxViewMemo = itemView.findViewById(R.id.checkbox_memo)
            checkboxViewMemo.setOnCheckedChangeListener  { _, isChecked ->
                val memo = listMemos[adapterPosition]
                memo.status = isChecked
                onItemCheck(memo)
            }
        }
    }

    fun onItemMove(positionDebut: Int, positionFin: Int): Boolean {
        Collections.swap(listMemos, positionDebut, positionFin)
        notifyItemMoved(positionDebut, positionFin)
        return true
    }

    fun onItemDismiss(position: Int) {
        val memo = listMemos[position]
        AppDatabaseHelper.getDatabase(activity).memoDAO()?.delete(memo)
        if (position > -1) {
            listMemos.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    fun onItemCheck(memo: Memo?) {
        AppDatabaseHelper.getDatabase(activity).memoDAO()?.update(memo)
    }

}