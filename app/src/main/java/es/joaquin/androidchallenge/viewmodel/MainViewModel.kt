package es.joaquin.androidchallenge.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import es.joaquin.androidchallenge.model.RepositoriesVo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    private val repositoriesLiveData by lazy { MutableLiveData<List<RepositoriesVo>>() }

    fun getRepositoriesLiveData() = repositoriesLiveData as LiveData<List<RepositoriesVo>>

    var page = 0

    var isLastPage: Boolean = false
    var isLoading: Boolean = false

    init {
        viewModelScope.launch {
            repositoriesLiveData.postValue(mock())
        }
    }

    fun getNetxtPage() {
        viewModelScope.launch(Dispatchers.IO) {
            delay(500)
            page++
            isLastPage = page == 10

            repositoriesLiveData.value?.let {
                repositoriesLiveData.postValue(it + mock())
            }
        }
    }

    private fun mock(): List<RepositoriesVo> {
        val fistValue: Int = (page.toString() + "1").toInt()
        val secondValue: Int = ((page + 1).toString() + "0").toInt()
        return (fistValue..secondValue).map {
            RepositoriesVo(it, it.toString(), it.toString(), it.toString(), it % 5 == 0)
        }
    }
}
