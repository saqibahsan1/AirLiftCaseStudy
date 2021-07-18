package com.saqib.casestudy.activities

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.saqib.casestudy.R
import com.saqib.casestudy.util.GenericViewModelFactory
import com.saqib.casestudy.util.pureUtils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var activityViewModelBind: mActivityViewModel
    private lateinit var recyclerViewAdapter: ImageRecyclerViewAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        activityViewModelBind =
            ViewModelProvider(this, GenericViewModelFactory(mActivityViewModel())).get(
                mActivityViewModel::class.java
            )
        if (pureUtils.isNetworkAvailable(this))
            activityViewModelBind.callApi()
        else
            Toast.makeText(
                this,
                "Please make sure your internet is working fine",
                Toast.LENGTH_SHORT
            ).show()

        activityViewModelBind.showProgress.observe(this, {
            if (it) {
                progress_bar.visibility = View.VISIBLE
            } else {
                progress_bar.visibility = View.GONE
            }
        })

        activityViewModelBind.showErrorMessage.observe(this,{
            Toast.makeText(
                this,
                it,
                Toast.LENGTH_SHORT
            ).show()
            swipeRefreshLayout.isRefreshing = false
        })
        recyclerViewAdapter = ImageRecyclerViewAdapter(this)
        recycler_view.adapter = recyclerViewAdapter
        activityViewModelBind.pixaBayResponseValue.observe(this, {
            if (it.total > 0) {
                swipeRefreshLayout.isRefreshing = false
                recyclerViewAdapter.clear()
                recyclerViewAdapter.addAll(it.hits)
            }else
                Toast.makeText(
                    this,
                    "Error while fetching images from server",
                    Toast.LENGTH_SHORT
                ).show()

        })

        swipeRefreshLayout.setOnRefreshListener { activityViewModelBind.callApi() }
    }
}
