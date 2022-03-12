package es.joaquin.androidchallenge

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import es.joaquin.androidchallenge.adapter.RepositoriesAdapter
import es.joaquin.androidchallenge.databinding.ActivityMainBinding
import es.joaquin.androidchallenge.model.RepositoriesVo
import es.joaquin.androidchallenge.viewmodel.MainViewModel

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val adapter = RepositoriesAdapter()

    private val viewModel: MainViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.recycler.adapter = this.adapter

        observeViewModel()
//        mock()
    }

    private fun observeViewModel() {
        viewModel.getRepositoriesLiveData().observe(this, {
            adapter.submitList(it)
        })
    }

    private fun mock() {
        val mockList = (0..50).map {
            RepositoriesVo(it, it.toString(), it.toString(), it.toString(), it % 5 == 0)
        }
        adapter.submitList(mockList)
    }
}
