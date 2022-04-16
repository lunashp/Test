package com.example.test

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import com.example.test.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RegisterActivity : AppCompatActivity() {
    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    val api = RegisterService.create();
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.postbutton.setOnClickListener {
            val data = PostModel(binding.nameedt.text.toString(),binding.emailedt.text.toString(),binding.pwdedt.text.toString())
            api.post_users(data).enqueue(object : Callback<PostResult> {
                override fun onResponse(call: Call<PostResult>, response: Response<PostResult>) {
                    Log.d("log",response.toString())
                    Log.d("log", response.body().toString())
                    if(response.isSuccessful) {
                        val result = 1
                        Log.d("result", "result : ${result}")
                        binding.text.setText(response.body().toString());
                    }else{
                        val result = 0
                        Log.d("result", "result : ${result}")
                    }
                }

                override fun onFailure(call: Call<PostResult>, t: Throwable) {
                    // 실패
                    Log.d("log",t.message.toString())
                    Log.d("log","fail")
                }
            })
        }

    }
}



