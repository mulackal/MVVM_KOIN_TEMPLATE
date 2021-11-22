package com.mvvm.mykotlinkoin_template.ui.main.view

import android.os.Bundle
import android.util.Log
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.mvvm.mykotlinkoin_template.BaseActivity
import com.mvvm.mykotlinkoin_template.R
import com.mvvm.mykotlinkoin_template.ui.main.adapter.PagingAdapter
import com.mvvm.mykotlinkoin_template.ui.main.adapter.PlayersLoadingStateAdapter
import com.mvvm.mykotlinkoin_template.ui.main.viewmodel.PagingViewModel
import kotlinx.android.synthetic.main.activity_paging.*
import kotlinx.android.synthetic.main.network_state_item.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class PagingActivity : BaseActivity() {

    private val viewModel: PagingViewModel by viewModel()
    private lateinit var adapter: PagingAdapter

    private var searchJob: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paging)
        Log.d("OnCreate", "OnCreate hit")
        setupUI()
        setupPaging()

    }

    private fun setupPaging() {

       /* searchJob?.cancel()
        searchJob = lifecycleScope.launch {
            viewModel.pagingData.observe(this@PagingActivity, {
                adapter.submitData(this@PagingActivity.lifecycle, it)
            })
        }*/

        /*
        Same thing using flow
        * */

          searchJob?.cancel()
          searchJob = lifecycleScope.launch {
              viewModel.pagingFlow
                  .collectLatest {
                      adapter.submitData(it)
                  }
          }

    }

    private fun setupUI() {
        rv_paging.layoutManager = LinearLayoutManager(this)
        adapter = PagingAdapter()
        rv_paging.addItemDecoration(
            DividerItemDecoration(
                rv_paging.context,
                (rv_paging.layoutManager as LinearLayoutManager).orientation
            )
        )
        rv_paging.adapter = adapter.withLoadStateFooter(
            footer = PlayersLoadingStateAdapter { retry() }
        )

        /*  lifecycleScope.launch {
       adapter.loadStateFlow.collectLatest { loadStates ->
           if (loadStates.refresh is LoadState.Loading) {
               debugLogE("lifecycleScope", "---------------------------")
               showLoadingDialog(this@PagingActivity)
           }
       }
   }*/

        adapter.addLoadStateListener { loadState ->

            //  loadState.mediator?.refresh
            if (loadState.refresh is LoadState.Loading) {
                if (adapter.snapshot().isEmpty()) {
                    showLoadingDialog(this)
                    error_txt.isVisible = true
                }
            } else {

                hideaLoadingDialog()
                error_txt.isVisible = false

                val error = when {
                    loadState.mediator?.prepend is LoadState.Error -> loadState.mediator?.prepend as LoadState.Error
                    loadState.mediator?.append is LoadState.Error -> loadState.mediator?.append as LoadState.Error
                    loadState.mediator?.refresh is LoadState.Error -> loadState.mediator?.refresh as LoadState.Error

                    else -> null
                }
                error?.let {
                    if (adapter.snapshot().isEmpty()) {
                        error_txt.isVisible = true
                        error_txt.text = it.error.localizedMessage
                    }

                }

            }
        }
    }

    private fun retry() {
        adapter.retry()
    }

    override fun onResume() {
        super.onResume()
        Log.d("OnResume", "OnResume hit")
    }

}