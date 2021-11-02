package com.example.anadolubankproject.ui.fragment.dashboard

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.anadolubankproject.databinding.ItemListBinding
import com.example.anadolubankproject.model.dashboard.PostsResponseItem
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

/**
 * @author engin.toplu
 */

class DasboardListAdapter @Inject constructor() :
    RecyclerView.Adapter<DasboardListAdapter.DashboardListViewHolder>(), Filterable {

    var dashboardList: ArrayList<PostsResponseItem> = arrayListOf()
    var dashboardListFitered: ArrayList<PostsResponseItem> = arrayListOf()
    var itemOnClick: ((PostsResponseItem, View) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardListViewHolder {
        val binding = ItemListBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return DashboardListViewHolder(binding)
    }

    override fun getItemCount() = dashboardListFitered.size

    override fun onBindViewHolder(holder: DashboardListViewHolder, position: Int) {
        holder.binding.title.text = dashboardList[position].title
        holder.binding.item.setOnClickListener {
            itemOnClick?.invoke(dashboardList[position], it)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addItems(dashboardList_: List<PostsResponseItem>) {
        dashboardList.clear()
        dashboardList.addAll(dashboardList_)
        dashboardListFitered.clear()
        dashboardListFitered.addAll(dashboardList_)
        notifyDataSetChanged()
    }

    class DashboardListViewHolder(val binding: ItemListBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): FilterResults {
                val charString = charSequence.toString()
                if (charString.isEmpty()) {
                    dashboardListFitered = dashboardList
                } else {
                    val filteredList: MutableList<PostsResponseItem> = ArrayList()
                    for (row in dashboardList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.title.toLowerCase(Locale.getDefault())
                                .contains(charString.toLowerCase(Locale.getDefault()))
                        ) {
                            filteredList.add(row)
                        }
                    }
                    dashboardListFitered = filteredList as ArrayList<PostsResponseItem>
                }
                val filterResults = FilterResults()
                filterResults.values = dashboardListFitered
                filterResults.count = dashboardListFitered.size
                return filterResults
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun publishResults(charSequence: CharSequence, filterResults: FilterResults) {
                dashboardListFitered = filterResults.values as ArrayList<PostsResponseItem>
                notifyDataSetChanged()
            }
        }
    }

}