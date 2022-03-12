package es.joaquin.androidchallenge

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import es.joaquin.androidchallenge.adapter.ChallengesAdapter
import es.joaquin.androidchallenge.databinding.ActivityMainBinding
import es.joaquin.androidchallenge.scrollUtils.PaginationScrollListener
import es.joaquin.androidchallenge.viewmodel.ChallengesViewModel

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val adapter = ChallengesAdapter()

    private val viewModel: ChallengesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        initView()

        observeViewModel()
    }

    private fun initView() {
        val layoutManager = LinearLayoutManager(this)
        binding.recycler.adapter = this.adapter
        binding.recycler.layoutManager = layoutManager
        binding.recycler.addOnScrollListener(object : PaginationScrollListener(layoutManager) {
            override fun isLastPage(): Boolean {
                return viewModel.isLastPage
            }

            override fun isLoading(): Boolean {
                return viewModel.isLoading
            }

            override fun loadMoreItems() {
                viewModel.isLoading = true
                viewModel.getNetxtPage()
            }

        })
    }

    private fun observeViewModel() {
        viewModel.getChallengesLiveData().observe(this, {
            viewModel.isLoading = false
            adapter.submitList(it)
        })
    }

}
