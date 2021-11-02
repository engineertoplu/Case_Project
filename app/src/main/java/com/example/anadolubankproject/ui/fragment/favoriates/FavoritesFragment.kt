package com.example.anadolubankproject.ui.fragment.favoriates

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.anadolubankproject.R
import com.example.anadolubankproject.databinding.FragmentFavoritesBinding
import com.example.anadolubankproject.ui.base.BaseFragment
import com.example.anadolubankproject.ui.base.viewmodel.ViewModelFactory
import javax.inject.Inject


class FavoritesFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var favoritesViewModel: FavoritesViewModel
    private lateinit var binding: FragmentFavoritesBinding

    @Inject
    lateinit var listAdapter: FavoriteListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        setView()
        return binding.root
    }

    private fun setView() {
        binding.toolbar.onClearClickListener = {
            binding.toolbar.hidekeyboard()
            binding.toolbar.setRightButtonVisibility(true)
        }

        binding.favoriteList.layoutManager = LinearLayoutManager(context)
        binding.favoriteList.adapter = listAdapter
        listAdapter.itemOnClick = {

        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        favoritesViewModel =
            ViewModelProvider(
                requireActivity(),
                viewModelFactory
            ).get(FavoritesViewModel::class.java)

        listAdapter.addItems(favoritesViewModel.getPostList())
    }
}