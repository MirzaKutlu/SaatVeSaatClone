package com.h5190067.saatsaatcloneapp.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.h5190067.saatsaatcloneapp.R
import com.h5190067.saatsaatcloneapp.databinding.ActivityLoginPageBinding
import com.h5190067.saatsaatcloneapp.ui.categories.CategoriesPageActivity
import com.h5190067.saatsaatcloneapp.util.ValidUtil

class LoginPageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init() {
        binding = ActivityLoginPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            btnLogin.setOnClickListener {
                if (ValidUtil.isValidEmail(editTextEmailAddress.text)) {
                    txtErrorEmail.text = ""
                    if (ValidUtil.isNotEmptyText(editTextPassword.text)) {
                        if (editTextPassword.text.toString() == "123456789") {
                            startActivity(Intent(this@LoginPageActivity, CategoriesPageActivity::class.java))
                            finish()
                        } else {
                            txtErrorPassword.text = getString(R.string.txtErrorPassword)
                        }
                    } else {
                        txtErrorPassword.text = getString(R.string.txtErrorPassword)
                    }
                } else {
                    txtErrorEmail.text = getString(R.string.txtErrorEmail)
                    txtErrorPassword.text = getString(R.string.txtErrorPassword)
                }
            }
        }
    }
}