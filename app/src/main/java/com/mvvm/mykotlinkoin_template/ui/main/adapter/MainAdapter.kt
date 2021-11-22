package com.mvvm.mykotlinkoin_template.ui.main.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.eduvy.demo.data.model.DiffUtilModelData
import com.mvvm.mykotlinkoin_template.R
import com.mvvm.mykotlinkoin_template.data.model.User
import kotlinx.android.synthetic.main.item_layout.view.*

class MainAdapter(
    private val users: ArrayList<User>,
) : RecyclerView.Adapter<MainAdapter.DataViewHolder>() {


    fun setItems(mData: List<User>) {
        val diff= DiffUtilModelData(this.users,mData)
        val res= DiffUtil.calculateDiff(diff)
        this.users.clear()
        this.users.addAll(mData)
        res.dispatchUpdatesTo(this)
    }


    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(user: User) {
            itemView.textViewUserName.text = user.name
            itemView.textViewUserEmail.text = user.email

            Log.e("imageViewAvatar","------ ${user.avatar}")

            Glide.with(itemView.imageViewAvatar.context)
                .load("https://www.pngkey.com/png/detail/114-1149878_setting-user-avatar-in-specific-size-without-breaking.png")
                .into(itemView.imageViewAvatar)

            itemView.setOnClickListener {
                Toast.makeText(itemView.imageViewAvatar.context,"Name: ${user.name}",Toast.LENGTH_SHORT).show()
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_layout, parent,
                false
            )
        )

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(users[position])

    fun addData(list: List<User>) {
        users.addAll(list)
    }
}