package com.h5190067.saatsaatcloneapp.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.h5190067.saatsaatcloneapp.R
import com.h5190067.saatsaatcloneapp.data.model.Kullanicilar
import com.h5190067.saatsaatcloneapp.data.model.UserResponse
import com.h5190067.saatsaatcloneapp.databinding.ActivityLoginPageBinding
import com.h5190067.saatsaatcloneapp.ui.categories.CategoriesPageActivity
import com.h5190067.saatsaatcloneapp.util.ValidUtil

class LoginPageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginPageBinding
    var userViewModel = UserViewModel()
    var userList: UserResponse? = null
    var userToLoginList: List<Kullanicilar>? = null
    var userToLogin: Kullanicilar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init() {
        binding = ActivityLoginPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViewModel()

        initForm()
    }

    fun getUserToLoginData(emailToLogin: String){
        userList?.let{
            userToLoginList = it.Kullanicilar.filter { it.Email!!.contains(emailToLogin) }
            if (!userToLoginList.isNullOrEmpty())
                userToLogin = userToLoginList!!.first()
        }
    }

    private fun initForm() {
        binding.apply {
            btnLogin.setOnClickListener {
                if (ValidUtil.isValidEmail(editTextEmailAddress.text)) {
                    txtErrorEmail.text = ""
                    if (ValidUtil.isNotEmptyText(editTextPassword.text)) {
                        getUserToLoginData(editTextEmailAddress.text.toString())
                        if (editTextPassword.text.toString() == userToLogin!!.Sifre && userToLogin != null) {
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

    fun initViewModel(){
        userViewModel.apply {
            allUsersLiveData.observe(this@LoginPageActivity, androidx.lifecycle.Observer {
                Log.e("Veri", "observe: " + it.toString())
                userList = it
            })

            error?.observe(this@LoginPageActivity, androidx.lifecycle.Observer {
                Log.e("Veri", "error:")
            })

            loading?.observe(this@LoginPageActivity, androidx.lifecycle.Observer {
                Log.e("Veri", "loading:")

            })
        }
    }
}