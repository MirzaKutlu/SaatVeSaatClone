package com.h5190067.saatsaatcloneapp.ui.categories

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.h5190067.saatsaatcloneapp.R
import com.h5190067.saatsaatcloneapp.util.AlertUtil
import java.util.*

class CategoriesPageActivity : AppCompatActivity() {


    var recyclerView: RecyclerView? = null
    var progressDialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categories_page)

        initialize()

    }

    override fun onBackPressed() {
        AlertUtil.giveAlert(
            this, getString(R.string.go_back_alert), getString(R.string.go_back_alert_message),
            false
        )
    }

    private fun initialize() {
        getCategories()
    }

    fun getCategories() {

    }

    private fun initRecycleView() {
    }

    private fun changeActivity() {

    }
}