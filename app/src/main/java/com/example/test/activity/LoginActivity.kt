package com.example.test.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.test.LoginPostModel
import com.example.test.LoginPostResult
import com.example.test.service.LoginService
import com.example.test.databinding.ActivityLoginBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginActivity : AppCompatActivity() {
    val binding by lazy { ActivityLoginBinding.inflate(layoutInflater)}
    val api = LoginService.create();
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            val intent = Intent(this@LoginActivity, LoginResultActivity::class.java)

            val data = LoginPostModel(binding.editText.text.toString(),binding.editText2.text.toString())
            api.post_users(data).enqueue(object : Callback<LoginPostResult> {
                override fun onResponse(call: Call<LoginPostResult>, response: Response<LoginPostResult>) {

                    Log.d("log",response.toString())
                    Log.d("log", response.body().toString())

                   if(response.isSuccessful) {
                       startActivity(intent)
                   }
                }

                override fun onFailure(call: Call<LoginPostResult>, t: Throwable) {
                    // 실패
                    Log.d("log",t.message.toString())
                    Log.d("log","fail")
                }

            })

        }

    }
}



