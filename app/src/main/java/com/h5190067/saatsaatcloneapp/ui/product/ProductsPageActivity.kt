package com.h5190067.saatsaatcloneapp.ui.product

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.h5190067.saatsaatcloneapp.R
import com.h5190067.saatsaatcloneapp.data.model.CategoriesAndProductsResponseItem
import com.h5190067.saatsaatcloneapp.data.model.Product
import com.h5190067.saatsaatcloneapp.databinding.ActivityProductsPageBinding
import com.h5190067.saatsaatcloneapp.ui.categories.CategoriesAdapter
import com.h5190067.saatsaatcloneapp.ui.detail.DetailPageActivity
import com.h5190067.saatsaatcloneapp.util.*

class ProductsPageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductsPageBinding

    companion object{
        var selectCategory: CategoriesAndProductsResponseItem? = null
        var productAdapter: ProductAdapter? = null
        var layoutType: LayoutTypes? = LayoutTypes.GRID_LAYOUT
        var linearLayoutManager: LinearLayoutManager? = null
        var gridLayoutManager: GridLayoutManager? = null
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initalize()

    }

    private fun initalize() {
        binding = ActivityProductsPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ProggresDialogUtil.showDialog(this, getString(R.string.progDialog))

        val clockListString = intent.getStringExtra(Constants.MOVED_TITLE)
        selectCategory = ObjectUtil.jsonStringToObject(clockListString!!)

        binding.apply {
            txtCategory.text = selectCategory!!.KategoriAdi
            btnToggleGrid.setOnClickListener {
                initLayoutManager()
            }
            btnToggleGrid.isEnabled = false
            btnToggleGrid.setBackgroundColor(resources.getColor(R.color.selected))

            btnToggleList.setOnClickListener {
                initLayoutManager()
            }
            btnToggleList.background = getDrawable(R.drawable.ic_gradient_color)

            btnSortCategories.setOnClickListener {
                AlertUtil.giveAlert(this@ProductsPageActivity, "Sırala", "Saat isimlerine göre sralama", Alerts.CATEGORY_SORT_ALERT)
            }
        }

        linearLayoutManager = LinearLayoutManager(applicationContext)
        gridLayoutManager = GridLayoutManager(applicationContext, Constants.GRID_SPAN_COUNT)


        initRecycleView(selectCategory!!.Products!!)
    }

    fun initLayoutManager(){
        binding.apply {
            if (layoutType == LayoutTypes.GRID_LAYOUT){
                btnToggleList.isEnabled = false
                btnToggleList.setBackgroundColor(resources.getColor(R.color.selected))
                rcvClocksProducts.layoutManager = linearLayoutManager
                layoutType = LayoutTypes.LIST_LAYOUT
                btnToggleGrid.isEnabled = true
                btnToggleGrid.background = getDrawable(R.drawable.ic_gradient_color)
            } else {
                btnToggleList.isEnabled = true
                btnToggleList.background = getDrawable(R.drawable.ic_gradient_color)
                rcvClocksProducts.layoutManager = gridLayoutManager
                layoutType = LayoutTypes.GRID_LAYOUT
                btnToggleGrid.isEnabled = false
                btnToggleGrid.setBackgroundColor(resources.getColor(R.color.selected))
            }
        }
    }

    private fun initRecycleView(products: List<Product>) {
        binding.apply {
            productAdapter = ProductAdapter(products, object : OnItemClickListener {
                override fun onItemClick(position: Int) {

                    val intent = Intent(this@ProductsPageActivity, DetailPageActivity::class.java)

                    var selectedProductString = ObjectUtil.objectToJsonString(products.get(position))
                    intent.putExtra(Constants.MOVED_TITLE, selectedProductString)
                    startActivity(intent)
                }
            })

            ProggresDialogUtil.dontShowDialog()

            rcvClocksProducts.adapter = productAdapter
            rcvClocksProducts.layoutManager =
                GridLayoutManager(applicationContext, Constants.GRID_SPAN_COUNT)
        }
    }
}