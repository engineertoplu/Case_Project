package com.example.anadolubankproject.ui.fragment.favoriates

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.anadolubankproject.databinding.ItemListBinding
import com.example.anadolubankproject.model.dashboard.PostsResponseItem
import com.example.anadolubankproject.model.room.ItemDBModel
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

/**
 * @author engin.toplu
 */

class FavoriteListAdapter @Inject constructor() :
    RecyclerView.Adapter<FavoriteListAdapter.FavoriteListViewHolder>(), Filterable {

    var dashboardList: ArrayList<ItemDBModel> = arrayListOf()
    var dashboardListFitered: ArrayList<ItemDBModel> = arrayListOf()
    var itemOnClick: ((ItemDBModel) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteListViewHolder {
        val binding = ItemListBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteListViewHolder(binding)
    }

    override fun getItemCount() = dashboardListFitered.size

    override fun onBindViewHolder(holder: FavoriteListViewHolder, position: Int) {
        holder.binding.title.text = dashboardList[position].title
        holder.binding.item.setOnClickListener {
            itemOnClick?.invoke(dashboardList[position])
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addItems(dashboardList_: List<ItemDBModel>) {
        dashboardList.clear()
        dashboardList.addAll(dashboardList_)
        dashboardListFitered.clear()
        dashboardListFitered.addAll(dashboardList_)
        notifyDataSetChanged()
    }

    class FavoriteListViewHolder(val binding: ItemListBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): FilterResults {
                val charString = charSequence.toString()
                if (charString.isEmpty()) {
                    dashboardListFitered = dashboardList
                } else {
                    val filteredList: MutableList<ItemDBModel> = ArrayList()
                    for (row in dashboardList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.title?.toLowerCase(Locale.getDefault())
                                ?.contains(charString.toLowerCase(Locale.getDefault())) == true
                        ) {
                            filteredList.add(row)
                        }
                    }
                    dashboardListFitered = filteredList as ArrayList<ItemDBModel>
                }
                val filterResults = FilterResults()
                filterResults.values = dashboardListFitered
                filterResults.count = dashboardListFitered.size
                return filterResults
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun publishResults(charSequence: CharSequence, filterResults: FilterResults) {
                dashboardListFitered = filterResults.values as ArrayList<ItemDBModel>
                notifyDataSetChanged()
            }
        }
    }

}