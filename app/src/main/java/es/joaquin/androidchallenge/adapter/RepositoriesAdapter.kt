package es.joaquin.androidchallenge.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import es.joaquin.androidchallenge.R
import es.joaquin.androidchallenge.databinding.RowRepositoriesBinding
import es.joaquin.androidchallenge.model.RepositoriesVo

class RepositoriesAdapter :
    ListAdapter<RepositoriesVo, RepositoriesAdapter.RepositoriesHolder>(RepositoriesDiffUtil()) {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RepositoriesHolder {
        return RepositoriesHolder(
            RowRepositoriesBinding.inflate(
                LayoutInflater.from(p0.context),
                p0,
                false
            )
        )
    }

    override fun onBindViewHolder(p0: RepositoriesHolder, p1: Int) {
        p0.bind(getItem(p1))
    }

    class RepositoriesHolder(private val binding: RowRepositoriesBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: RepositoriesVo) {
            with(binding) {
                nameValue.text = item.name
                descriptionValue.text = item.description
                loginValue.text = item.ownerLogin

                root.background = if (item.isFork) {
                    ContextCompat.getDrawable(root.context, R.color.green)
                } else {
                    ContextCompat.getDrawable(root.context, R.color.white)
                }
            }
        }
    }


    class RepositoriesDiffUtil : DiffUtil.ItemCallback<RepositoriesVo>() {
        override fun areItemsTheSame(p0: RepositoriesVo, p1: RepositoriesVo): Boolean {
            return p0.id == p1.id
        }

        override fun areContentsTheSame(p0: RepositoriesVo, p1: RepositoriesVo): Boolean {
            return p0 == p1
        }

    }
}