package es.joaquin.androidchallenge

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
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
    var isLastPage: Boolean = false
    var isLoading: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        initView()

        observeViewModel()
//        mock()
    }

    private fun initView() {
        val layoutManager = LinearLayoutManager(this)
        binding.recycler.adapter = this.adapter
        binding.recycler.layoutManager = layoutManager
        binding.recycler.addOnScrollListener(object : PaginationScrollListener(layoutManager) {
            override fun isLastPage(): Boolean {
                return isLastPage
            }

            override fun isLoading(): Boolean {
                return isLoading
            }

            override fun loadMoreItems() {
                isLoading = true
                viewModel.getNetxtPage()
            }

        })
    }

    private fun observeViewModel() {
        viewModel.getRepositoriesLiveData().observe(this, {
            isLoading = false
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
