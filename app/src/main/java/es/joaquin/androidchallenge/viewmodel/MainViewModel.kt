package es.joaquin.androidchallenge.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import es.joaquin.androidchallenge.model.RepositoriesVo
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    private val repositoriesLiveData by lazy { MutableLiveData<List<RepositoriesVo>>() }

    fun getRepositoriesLiveData() = repositoriesLiveData as LiveData<List<RepositoriesVo>>

    init {
        viewModelScope.launch {
            mock()
        }
    }

    private fun mock() {
        val mockList = (0..50).map {
            RepositoriesVo(it, it.toString(), it.toString(), it.toString(), it % 5 == 0)
        }
        repositoriesLiveData.postValue(mockList)
    }
}
