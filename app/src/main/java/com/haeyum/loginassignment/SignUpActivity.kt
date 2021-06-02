package com.haeyum.loginassignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import com.haeyum.loginassignment.MainApp.Companion.appPref
import com.haeyum.loginassignment.ToastHelper.Companion.showToast
import com.haeyum.loginassignment.statusBarManager.StatusBarManager

class SignUpActivity : AppCompatActivity() {
    lateinit var edtUsername: EditText
    lateinit var edtPassword: EditText
    lateinit var edtConfirmPassword: EditText
    lateinit var btnSignUp: Button


    var isCheckUsername = false
    var isCheckPassword = false
    var isCheckConfirmPassword = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        initUI()
        initListener()
    }


    fun onClick(v: View) {
        when (v.id) {
            R.id.btn_signUp -> {
                    appPref.username=edtUsername.text.toString()
                    appPref.password=edtPassword.text.toString()
                    finish()
                    showToast("회원가입 성공!")
            }
        }
    }
    private fun initUI() {
        initStatusBar()

        edtUsername = findViewById(R.id.edt_signUp_username)
        edtPassword = findViewById(R.id.edt_signUp_password)
        edtConfirmPassword = findViewById(R.id.edt_signUp_confirmPassword)
        btnSignUp = findViewById(R.id.btn_signUp)
    }
    private fun initListener() {
        edtUsername.doAfterTextChanged {
            isCheckUsername = edtUsername.text.toString().length >= 4
            changeSignUp(isCheckUsername && isCheckPassword && isCheckConfirmPassword)
        }

        edtPassword.doAfterTextChanged {
            isCheckPassword = edtPassword.text.toString().length >= 8
            changeSignUp(isCheckUsername && isCheckPassword && isCheckConfirmPassword)
        }
        edtConfirmPassword.doAfterTextChanged {
            isCheckConfirmPassword = edtConfirmPassword.text.toString() == edtPassword.text.toString()
            changeSignUp(isCheckUsername && isCheckPassword && isCheckConfirmPassword)
        }
    }

    private fun changeSignUp(isCheck: Boolean) {
        if(isCheck) {
            btnSignUp.alpha = 1f
            btnSignUp.isEnabled = true
        } else {
            btnSignUp.apply {
                alpha = 0.3f
                isEnabled = false
            }
        }
    }

    private fun initStatusBar() {
        StatusBarManager.changeStatusBarTransparentWithWhiteText(window)
    }
}