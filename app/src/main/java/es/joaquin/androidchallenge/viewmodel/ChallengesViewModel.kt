package es.joaquin.androidchallenge.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import es.joaquin.androidchallenge.model.ChallengesVo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChallengesViewModel @Inject constructor() : ViewModel() {

    private val ChallengesLiveData by lazy { MutableLiveData<List<ChallengesVo>>() }

    fun getChallengesLiveData() = ChallengesLiveData as LiveData<List<ChallengesVo>>

    var page = 0

    var isLastPage: Boolean = false
    var isLoading: Boolean = false

    init {
        viewModelScope.launch {
            ChallengesLiveData.postValue(mock())
        }
    }

    fun getNetxtPage() {
        viewModelScope.launch(Dispatchers.IO) {
            delay(500)
            page++
            isLastPage = page == 10

            ChallengesLiveData.value?.let {
                ChallengesLiveData.postValue(it + mock())
            }
        }
    }

    private fun mock(): List<ChallengesVo> {
        val fistValue: Int = (page.toString() + "1").toInt()
        val secondValue: Int = ((page + 1).toString() + "0").toInt()
        return (fistValue..secondValue).map {
            ChallengesVo(it, it.toString(), it.toString(), it.toString(), it % 5 == 0)
        }
    }
}
