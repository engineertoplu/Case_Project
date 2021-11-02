package com.example.anadolubankproject.ui.base

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatDialog
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.anadolubankproject.model.BaseThrowable
import com.example.anadolubankproject.ui.base.interfaces.IBaseView
import com.example.anadolubankproject.ui.base.interfaces.ILoadingIndicator
import com.example.anadolubankproject.ui.fragment.dashboard.DashboardViewModel
import com.example.anadolubankproject.ui.ktx.showInfoDialog
import com.example.anadolubankproject.ui.ktx.showRetryDialog
import com.example.anadolubankproject.ui.loader.LoadingDialog
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

open class BaseFragment : Fragment(), IBaseView, ILoadingIndicator, HasSupportFragmentInjector {
    @Inject
    lateinit var childFragmentInjector: DispatchingAndroidInjector<Fragment>
    protected lateinit var loadingDialog: AppCompatDialog
    val isActive = isAdded

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadingDialog = LoadingDialog(requireActivity())
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = childFragmentInjector

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onError(t: BaseThrowable) {
        if (!isAdded) return

        if (t.stopLoading) {
            setLoadingIndicator(false, true)
        }

        if (t.finishScreen) {
            setLoadingIndicator(false, false)
            activity.showInfoDialog(t.uiMessage.toString(), positiveListener = { dialog, _ ->
                dialog.dismiss()
                activity?.onBackPressed()
            })
            return
        }

        if (t.retryListener != null) {
            setLoadingIndicator(false, false)
            activity.showRetryDialog(t.uiMessage.toString(), positiveListener = t.retryListener,
                    negativeListener = { dialog, _ ->
                        dialog.dismiss()
                        activity?.onBackPressed()
                    })
            return
        }

        if (t.showMessage) {
            activity.showInfoDialog(t.uiMessage.toString())
        }
    }

    override fun setLoadingIndicator(loader: Boolean, rootView: Boolean) {
        if (!isAdded) return
        loadingDialog.apply {
            if (loader) {
                if (!isShowing) show()
            } else
                if (isShowing) dismiss()
        }
        view?.isVisible = rootView
    }

    override fun setLoadingIndicator(pair: Pair<Boolean, Boolean>) {
        setLoadingIndicator(pair.first, pair.second)
    }

    fun handleState(viewModel: BaseViewModel){
        viewModel.state.observe(viewLifecycleOwner, Observer {
            Log.d("BaseFragment", it.status.toString())
            when(it.status){
                Status.ERROR-> onError(it.error ?: BaseThrowable())
                Status.LOADING -> setLoadingIndicator(it.loadingIndicator ?: Pair(true,
                    second = true
                ))
                Status.SUCCESS -> setLoadingIndicator(it.loadingIndicator ?: Pair(
                    first = false,
                    second = true
                ))
            }
        })
    }
}