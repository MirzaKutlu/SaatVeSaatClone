package com.h5190067.saatsaatcloneapp.ui.login

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doOnTextChanged
import com.h5190067.saatsaatcloneapp.R
import com.h5190067.saatsaatcloneapp.data.model.Kullanicilar
import com.h5190067.saatsaatcloneapp.data.model.UserResponse
import com.h5190067.saatsaatcloneapp.databinding.ActivityLoginPageBinding
import com.h5190067.saatsaatcloneapp.ui.categories.CategoriesPageActivity

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

    private fun initForm() {

        var isEmailValid: Boolean = false
        var isPasswordValid: Boolean = false

        binding.apply {
            editTextEmailAddress.addTextChangedListener(object: TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int){}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    if (!editTextEmailAddress.text.isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(editTextEmailAddress.text).matches()){
                        txtErrorEmail.text = ""
                        isEmailValid = true
                    }
                    else{
                        txtErrorEmail.text = getString(R.string.txtErrorEmail)
                        isEmailValid = false
                    }
                }

                override fun afterTextChanged(s: Editable?) {
                    btnLogin.isEnabled = isEmailValid
                }
            })

            editTextPassword.addTextChangedListener(object: TextWatcher{
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    if (!editTextPassword.text.isNullOrEmpty()){
                        txtErrorPassword.text = ""
                        isPasswordValid = true
                    }
                    else{
                        txtErrorPassword.text = getString(R.string.txtErrorPassword)
                        isPasswordValid = false
                    }
                }

                override fun afterTextChanged(s: Editable?) {
                    btnLogin.isEnabled = isEmailValid
                }
            })

            btnLogin.setOnClickListener {
                if (isEmailValid && isPasswordValid){
                    getUserToLoginData(editTextEmailAddress.text.toString())
                    if (editTextPassword.text.toString() == userToLogin!!.Sifre && userToLogin != null) {
                        startActivity(Intent(this@LoginPageActivity, CategoriesPageActivity::class.java))
                        finish()
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


    fun getUserToLoginData(emailToLogin: String){
        userList?.let{
            userToLoginList = it.Kullanicilar.filter { it.Email!!.contains(emailToLogin) }
            if (!userToLoginList.isNullOrEmpty())
                userToLogin = userToLoginList!!.first()
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