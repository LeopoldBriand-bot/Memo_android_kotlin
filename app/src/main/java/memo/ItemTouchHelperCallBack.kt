package memo

import androidx.recyclerview.widget.ItemTouchHelper

import androidx.recyclerview.widget.RecyclerView


import memo.MemoAdapter


class ItemTouchHelperCallBack(adapter: MemoAdapter) : ItemTouchHelper.Callback() {
    private val adapter: MemoAdapter = adapter
    override fun isLongPressDragEnabled(): Boolean
    {
        return true
    }
    override fun isItemViewSwipeEnabled(): Boolean
    {
        return true
    }

    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        val dragFlagsUpDown: Int = ItemTouchHelper.UP or ItemTouchHelper.DOWN
        val dragFlagsRight: Int = ItemTouchHelper.RIGHT
        return makeMovementFlags(dragFlagsUpDown, dragFlagsRight)
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        adapter.onItemMove(viewHolder.adapterPosition, target.adapterPosition)
        return true
    }


    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        adapter.onItemDismiss(viewHolder.adapterPosition)
    }

}