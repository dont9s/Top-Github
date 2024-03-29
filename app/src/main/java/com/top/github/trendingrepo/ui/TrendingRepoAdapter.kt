package com.top.github.trendingrepo.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.top.github.repodetail.ui.RepoDetailFragment
import com.top.github.R
import com.top.github.databinding.ListItemRepoBinding
import com.top.github.trendingrepo.data.User

class TrendingRepoAdapter(val activity: FragmentActivity) : ListAdapter<User, TrendingRepoAdapter.ViewHolder>(DiffCallback()) {


    class ViewHolder(
            val binding: ListItemRepoBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(listener: View.OnClickListener, item: User) {
            binding.apply {
                clickListener = listener
                user = item
                executePendingBindings()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ListItemRepoBinding.inflate(
                LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = getItem(position)
        holder.apply {
            bind(createOnClickListener(user, holder.binding), user)
            itemView.tag = user
        }
    }

    private fun createOnClickListener(user: User,
                                      binding: ListItemRepoBinding): View.OnClickListener {

        return View.OnClickListener {

            activity.supportFragmentManager.beginTransaction().replace(R.id.fl_fragment,
                    RepoDetailFragment.getInstance(user))
                    .addToBackStack(null)
                    .commit()

        }
    }
}


private class DiffCallback : DiffUtil.ItemCallback<User>() {

    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.username == newItem.username
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }
}