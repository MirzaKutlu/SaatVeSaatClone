package com.h5190067.saatsaatcloneapp.ui.detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.h5190067.saatsaatcloneapp.data.model.Product
import com.h5190067.saatsaatcloneapp.databinding.ActivityDetailPageBinding
import com.h5190067.saatsaatcloneapp.ui.product.ProductsPageActivity
import com.h5190067.saatsaatcloneapp.util.Constants
import com.h5190067.saatsaatcloneapp.util.GildeUtil
import com.h5190067.saatsaatcloneapp.util.LayoutTypes
import com.h5190067.saatsaatcloneapp.util.ObjectUtil

class DetailPageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailPageBinding

    var product : Product?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initalize()

    }
    private fun initalize() {
        binding = ActivityDetailPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val detailString = intent.getStringExtra(Constants.MOVED_TITLE)
        product = ObjectUtil.jsonStringToObject(detailString!!)

        binding.apply {
            GildeUtil.glideImage(applicationContext , product!!.UrunResim!!, imgProductDetail)
            txtProductCategory.text = product!!.KategoriAdi
            txtProductCertificate.text = product!!.Sertifika
            txtProductColor.text = product!!.Renk
            txtProductExplain.text = product!!.Aciklama
            txtProductMaterial.text = product!!.MalzemeTipi
            txtProductNameDetail.text= product!!.MarkaAdi
            txtProductPrice.text = product!!.Fiyat
            txtProductSize.text = product!!.KasaBoyutu
            txtProductType.text = product!!.KasaTipi
        }
    }
}