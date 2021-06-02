package com.haeyum.loginassignment

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import com.haeyum.loginassignment.MainApp.Companion.appPref
import com.haeyum.loginassignment.ToastHelper.Companion.showToast
import com.haeyum.loginassignment.statusBarManager.StatusBarManager

class SignInActivity : AppCompatActivity() {
    lateinit var edtUsername: EditText
    lateinit var edtPassword: EditText
    lateinit var btnSignIn: Button

    var isCheckUsername = false
    var isCheckPassword = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        initUI()
        initListener()
    }

    fun onClick(v: View) {
        when(v.id) {
            R.id.btn_signIn -> {
                if(appPref.username == null || appPref.password == null)
                    showToast("회원가입을 먼저해주세요")
                else if(appPref.username == edtUsername.text.toString())
                    if(appPref.password == edtPassword.text.toString())
                        showToast("로그인 성공!")
                    else
                        showToast("잘못된 비밀번호입니다.")
                else
                    showToast("존재하지 않는 사용자 이름입니다.")
            }

            R.id.ll_signIn_signUp -> {

            }
        }
    }

    private fun initUI() {
        initStatusBar()

        edtUsername = findViewById(R.id.edt_signIn_username)
        edtPassword = findViewById(R.id.edt_signIn_password)
        btnSignIn = findViewById(R.id.btn_signIn)
    }

    private fun initListener() {
        edtUsername.doAfterTextChanged {
                isCheckUsername = edtUsername.text.toString().length >= 4
                changeSignIn(isCheckUsername && isCheckPassword)
        }

        edtPassword.doAfterTextChanged {
            isCheckPassword = edtPassword.text.toString().length >= 8
            changeSignIn(isCheckUsername && isCheckPassword)
        }
    }

    private fun changeSignIn(isCheck: Boolean) {
        if(isCheck) {
            btnSignIn.alpha = 1f
            btnSignIn.isEnabled = true
        } else {
            btnSignIn.apply {
                alpha = 0.3f
                isEnabled = false
            }
        }
    }

    private fun initStatusBar() {
        StatusBarManager.changeStatusBarTransparentWithWhiteText(window)
    }
}