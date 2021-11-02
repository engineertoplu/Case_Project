package com.example.anadolubankproject.ui.fragment.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.anadolubankproject.databinding.FragmentDetailBinding
import com.example.anadolubankproject.model.dashboard.PostsResponseItem
import com.example.anadolubankproject.model.room.DatabaseManager
import com.example.anadolubankproject.model.room.ItemDBModel
import com.example.anadolubankproject.ui.base.BaseFragment
import com.example.anadolubankproject.ui.base.viewmodel.ViewModelFactory
import javax.inject.Inject


class DetailFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var binding: FragmentDetailBinding
    private lateinit var detailViewModel: DetailViewModel
    private var appDb: DatabaseManager? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentDetailBinding.inflate(inflater, container, false)

        return binding.root
    }

    private fun goBack() {
        findNavController().navigateUp()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        appDb = DatabaseManager.getDatabaseManager(requireContext())
        //binding.toolbar.registerToolbarToActivity(requireActivity() as AppCompatActivity) { goBack() }
        detailViewModel = ViewModelProviders.of(this, viewModelFactory).get(
            DetailViewModel::class.java
        )

        with(detailViewModel) {
            arguments?.let {
                data.postValue(it.getSerializable("item") as PostsResponseItem)
            }

            data.observe(viewLifecycleOwner, Observer { data ->
                binding.title.text = data.title
                binding.content.text = data.body
                val item = appDb?.itemDao()?.findById(data.id.toString())
                item?.let {
                    it.isFav?.let { it1 -> binding.toolbar.setFavIcon(it1) }
                }

                binding.toolbar.onFavIconClickListener = {
                    val item = appDb?.itemDao()?.findById(it.id.toString())
                    if (item != null && item.isFav != false) {
                        appDb?.itemDao()?.updateByPost(false, item.postID.toString())
                        binding.toolbar.setFavIcon(false)
                    } else if (item != null && !item.isFav) {
                        appDb?.itemDao()?.updateByPost(true, item.postID.toString())
                        binding.toolbar.setFavIcon(true)
                    } else if (item == null) {
                        appDb?.itemDao()
                            ?.insertOrUpdate(ItemDBModel(data.id, data.title, data.body, true))
                        binding.toolbar.setFavIcon(true)
                    }
                }
            })
        }

        binding.toolbar.setLeftButtonVisibility(true)
        binding.toolbar.onBackIconClickListener = {
            findNavController().navigateUp()
        }
    }
}