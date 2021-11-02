package com.example.anadolubankproject.ui.fragment.dashboard

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.anadolubankproject.R
import com.example.anadolubankproject.databinding.FragmentDashboardBinding
import com.example.anadolubankproject.model.dashboard.PostsResponseItem
import com.example.anadolubankproject.ui.base.BaseFragment
import com.example.anadolubankproject.ui.base.viewmodel.ViewModelFactory
import javax.inject.Inject


class DashboardFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var dashboardViewModel: DashboardViewModel
    private lateinit var binding: FragmentDashboardBinding

    @Inject
    lateinit var listAdapter: DasboardListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentDashboardBinding.inflate(inflater, container, false)
        setView()
        return binding.root
    }

    private fun setView() {
        binding.toolbar.onClearClickListener = {
            binding.toolbar.hidekeyboard()
            binding.toolbar.setRightButtonVisibility(true)
        }


        binding.list.layoutManager = LinearLayoutManager(context)
        binding.list.adapter = listAdapter
        listAdapter.itemOnClick = {item, view->
            val bundle = bundleOf("item" to item)
            //view.findNavController().navigate(R.id.action_dashboard_to_detail, bundle)
            //view.findNavController().navigate(DashboardFragmentDirections.actionDashboardToDetail(item))
            view.findNavController().navigate(R.id.action_dashboard_to_detail, bundle)
        }

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        dashboardViewModel = ViewModelProviders.of(this, viewModelFactory).get(
            DashboardViewModel::class.java
        )
        with(dashboardViewModel) {
            //handleState(this)
            getList()

            data.observe(viewLifecycleOwner, {
                Log.i("dash", it.toString())
                listAdapter.addItems(it)
            })

            loading.observe(viewLifecycleOwner, Observer {
                if (it) {
                    setLoadingIndicator(true, rootView = true)
                } else {
                    setLoadingIndicator(false, rootView = true)
                }
            })

            error.observe(viewLifecycleOwner, Observer {
                //TODO error dialog
            })
        }

        binding.toolbar.onSearchQueryChanged = {
            listAdapter.filter.filter(it)
        }
    }
}