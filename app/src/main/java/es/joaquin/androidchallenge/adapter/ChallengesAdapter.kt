package es.joaquin.androidchallenge.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import es.joaquin.androidchallenge.R
import es.joaquin.androidchallenge.databinding.RowRepositoriesBinding
import es.joaquin.androidchallenge.model.ChallengesVo

class ChallengesAdapter :
    ListAdapter<ChallengesVo, ChallengesAdapter.ChallengesHolder>(ChallengesDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewtype: Int): ChallengesHolder {
        return ChallengesHolder(
            RowRepositoriesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ChallengesHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ChallengesHolder(private val binding: RowRepositoriesBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ChallengesVo) {
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


    class ChallengesDiffUtil : DiffUtil.ItemCallback<ChallengesVo>() {
        override fun areItemsTheSame(p0: ChallengesVo, p1: ChallengesVo): Boolean {
            return p0.id == p1.id
        }

        override fun areContentsTheSame(p0: ChallengesVo, p1: ChallengesVo): Boolean {
            return p0 == p1
        }

    }
}