package com.h5190067.saatsaatcloneapp.util

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.provider.Settings
import android.widget.Toast
import com.h5190067.saatsaatcloneapp.R
import com.h5190067.saatsaatcloneapp.data.model.Product
import com.h5190067.saatsaatcloneapp.ui.product.ProductsPageActivity.Companion.productAdapter
import com.h5190067.saatsaatcloneapp.ui.product.ProductsPageActivity.Companion.selectCategory


object AlertUtil {

    private var list: ArrayList<Product>? = null
    private val adapter = productAdapter!!

    fun giveAlert(activity: Activity, title: String, message: String, alertShape: Alerts ){
        val builder = AlertDialog.Builder(activity)
        builder.setTitle(title)
        if (alertShape == Alerts.NO_CONN_ALERT) {
            builder.setMessage(message)
            builder.setNegativeButton(
                R.string.ExitApp,
                DialogInterface.OnClickListener { dialog, which ->
                    dialog.dismiss()
                    activity.finish()
                })
            builder.setPositiveButton(R.string.GoSettings,
                DialogInterface.OnClickListener { dialog, which ->
                    dialog.dismiss()
                    activity.startActivity(Intent(Settings.ACTION_WIFI_SETTINGS))
                    activity.finish()
                })
        } else if(alertShape == Alerts.EXIT_ALERT) {
            builder.setMessage(message)
            builder.setNegativeButton(R.string.ExitNo,
                DialogInterface.OnClickListener { dialog, which -> dialog.dismiss() })
            builder.setPositiveButton(R.string.ExitYes,
                DialogInterface.OnClickListener { dialog, which ->
                    dialog.dismiss()
                    activity.finish()
                })
        } else if (alertShape == Alerts.CATEGORY_SORT_ALERT){

            list = selectCategory!!.Products as ArrayList<Product>

            val options = arrayOf("Artan Sıralama", "Azalan Sıralama")

            list?.run {
                builder.setItems(options) { dialog, pozisyon ->
                    when (pozisyon) {
                        0 -> {
                            this.sortBy { it.MarkaAdi }
                            adapter.notifyDataSetChanged()
                        }
                        1 -> {
                            this.sortByDescending { it.MarkaAdi }
                            adapter.notifyDataSetChanged()
                        }
                    }
                }
            }
            builder.setNegativeButton("İptal", DialogInterface.OnClickListener { dialog, which -> dialog.dismiss() })

        }
        builder.show()
    }
}
