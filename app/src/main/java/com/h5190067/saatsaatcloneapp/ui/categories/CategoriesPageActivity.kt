package com.h5190067.saatsaatcloneapp.ui.categories

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.h5190067.saatsaatcloneapp.R
import com.h5190067.saatsaatcloneapp.data.model.CategoriesAndProductsResponse
import com.h5190067.saatsaatcloneapp.data.model.CategoriesAndProductsResponseItem
import com.h5190067.saatsaatcloneapp.databinding.ActivityCategoriesPageBinding
import com.h5190067.saatsaatcloneapp.util.AlertUtil
import com.h5190067.saatsaatcloneapp.util.Constants
import com.h5190067.saatsaatcloneapp.util.OnItemClickListener
import java.util.*

class CategoriesPageActivity : AppCompatActivity() {

    lateinit var binding: ActivityCategoriesPageBinding
    var categoriesAdapter: CategoriesAdapter? = null
    var categoryArray = CategoriesAndProductsResponse()
    var progressDialog: ProgressDialog? = null
    var viewModel: CategoriesViewModel = CategoriesViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialize()

    }

    override fun onBackPressed() {
        AlertUtil.giveAlert(
            this, getString(R.string.go_back_alert), getString(R.string.go_back_alert_message),
            false
        )
    }

    private fun initialize() {
        binding = ActivityCategoriesPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            searchViewCategory.apply {
                setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextChange(newText: String?): Boolean {

                        return false
                    }

                    override fun onQueryTextSubmit(query: String?): Boolean {

                        categoryFilter(query)
                        return false
                    }
                })

                setOnCloseListener {
                    initRecycleView(categoryArray)
                    false
                }
            }
        }

        initViewModel()
    }

    fun categoryFilter(text: String?) {
        text?.let {
            categoryArray?.let {
                var filteredCategoryList = it.filter { it.KategoriAdi!!.contains(text) }
                initRecycleView(filteredCategoryList)
            }
        }
    }

    private fun initRecycleView(categories: List<CategoriesAndProductsResponseItem>) {
        binding.apply {
            categoriesAdapter = CategoriesAdapter(categories, object : OnItemClickListener {
                override fun onItemClick(position: Int) {
                    Toast.makeText(
                        applicationContext,
                        categories.get(position).KategoriAdi,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })

            rcvClocks.adapter = categoriesAdapter
            rcvClocks.layoutManager =
                GridLayoutManager(applicationContext, Constants.GRID_SPAN_COUNT)
        }
    }

    private fun initViewModel() {
        viewModel.apply {
            allCategoriesLiveData.observe(this@CategoriesPageActivity, androidx.lifecycle.Observer {
                Log.e("Mizaq", "observe: " + it.toString())
                categoryArray = it
                initRecycleView(categoryArray)
            })

            error?.observe(this@CategoriesPageActivity, androidx.lifecycle.Observer {
                Log.e("mirza", "error:")
            })

            loading?.observe(this@CategoriesPageActivity, androidx.lifecycle.Observer {
                Log.e("mirza", "loading:")

            })
        }
    }
}