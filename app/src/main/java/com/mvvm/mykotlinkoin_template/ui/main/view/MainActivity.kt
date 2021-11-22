package com.mvvm.mykotlinkoin_template

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.eduvy.demo.utils.extentions.showToast
import com.mvvm.mykotlinkoin_template.data.model.User
import com.mvvm.mykotlinkoin_template.data.preferences.SharedPreferenceValue
import com.mvvm.mykotlinkoin_template.ui.main.adapter.MainAdapter
import com.mvvm.mykotlinkoin_template.ui.main.viewmodel.MainViewModel
import com.mvvm.mykotlinkoin_template.utils.Status
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity() {

    private val mainViewModel: MainViewModel by viewModel()
    private lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("OnCreate", "OnCreate hit")
        setContentView(R.layout.activity_main)
        setupUI()
        setupObserver()

        preferences.setValue(SharedPreferenceValue.IS_LOGGED, true)
        debugLogE("Username:", preferences.getStringValue(SharedPreferenceValue.USER_NAME, "")!!)


    }

    private fun setupUI() {

        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MainAdapter(arrayListOf())
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        recyclerView.adapter = adapter

        /*  recyclerView.apply {   }*/
    }

    private fun setupObserver() {
        mainViewModel.users.observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    hideaLoadingDialog()
                    nodata.visibility = View.GONE
                    it.data?.let { users -> renderList(users) }
                    recyclerView.visibility = View.VISIBLE
                    showToast("Data fetched successfully.!")
                }
                Status.LOADING -> {
                    showLoadingDialog(this)
                    nodata.visibility = View.VISIBLE
                    recyclerView.visibility = View.GONE
                }
                Status.ERROR -> {
                    hideaLoadingDialog()
                    //Handle Error
                    nodata.visibility = View.GONE
                    showToast(R.string.apierror)
                }
            }
        })
    }

    private fun renderList(users: List<User>) {
        /*  adapter.addData(users)
          adapter.notifyDataSetChanged()*/
        adapter.setItems(users)
    }

    override fun onResume() {
        super.onResume()
        Log.d("OnResume", "OnResume hit")
    }
}
