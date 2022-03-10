package es.joaquin.androidchallenge

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import es.joaquin.androidchallenge.adapter.RepositoriesAdapter
import es.joaquin.androidchallenge.databinding.ActivityMainBinding
import es.joaquin.androidchallenge.model.RepositoriesVo

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val adapter = RepositoriesAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.recycler.adapter = this.adapter

        mock()
    }

    private fun mock() {
        val mockList = (0..50).map {
            RepositoriesVo(it, it.toString(), it.toString(), it.toString(), it % 5 == 0)
        }
        adapter.submitList(mockList)
    }
}
