package viewModel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import memo.Memo
import repository.MainRepository

class MainViewModel: ViewModel() {
    private var mainRepository : MainRepository? = null
    private var liveDataItem : MutableLiveData<Memo>? = null

    fun initialization(mainRepository: MainRepository) {
        if (this.liveDataItem != null) {
            return
        }

        this.mainRepository = mainRepository
        liveDataItem = MutableLiveData()
    }

    fun loadData(context: Context, listNotes: MutableList<Memo>) {
        mainRepository!!.getData(context, listNotes)
    }
}