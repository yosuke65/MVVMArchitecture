package com.example.mvvmarchitecture.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

import com.example.mvvmarchitecture.models.RepositoryItem
import com.example.mvvmarchitecture.databinding.RowRepoAdapterBinding

class AdapterRepoList(private var mContext:Context): RecyclerView.Adapter<AdapterRepoList.MyViewHolder>() {

    private var mList:ArrayList<RepositoryItem> = ArrayList()

    inner class MyViewHolder(private val binding: RowRepoAdapterBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(item: RepositoryItem){
            binding.item = item
            binding.adapter = this@AdapterRepoList
            //Adapt to fast scrolling
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = RowRepoAdapterBinding.inflate(LayoutInflater.from(parent.context))
        val layoutParams = RecyclerView.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.WRAP_CONTENT)
        binding.root.layoutParams = layoutParams
        return MyViewHolder(binding)
    }

    override fun getItemCount() = mList.size


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(mList[position])
    }

    fun setData(list: List<RepositoryItem>) {
        mList = ArrayList(list)
        notifyDataSetChanged()
    }

    fun onItemClicked(item:RepositoryItem){
        Toast.makeText(mContext, "${item.name}", Toast.LENGTH_SHORT).show()
    }
}