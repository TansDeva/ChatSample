package com.tanshul.chat.ui.activity

import android.os.Bundle
import android.os.Handler
import com.tanshul.chat.base.BaseActivity
import com.tanshul.chat.helper.AppConstants.SplashDelay
import com.tanshul.chat.ui.R

class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
    }

    override fun onStart() {
        super.onStart()
        Handler().postDelayed({
            ChatActivity.newInstance(this@SplashActivity)
            finish()
        }, SplashDelay)
    }

    override fun onBackPressed() {
        //Do nothing
    }
}