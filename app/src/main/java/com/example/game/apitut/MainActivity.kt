package com.example.game.apitut

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.Toast
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.example.game.apitut.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getData();
        binding.newMeme.setOnClickListener{
            getData()
        }
    }

    private fun getData() {

        val progressDialog = ProgressDialog(this)
        progressDialog.setIcon(R.drawable.progress_bar)
        progressDialog.show();
        RetrofitInstance.apiInterface.getData().enqueue(object : Callback<ApiModel?> {

            override fun onResponse(call: Call<ApiModel?>, response: Response<ApiModel?>) {

                if (response.isSuccessful) {
                    val apiResponse = response.body()
                    val memes = apiResponse?.data?.memes

                    if (memes != null && memes.isNotEmpty()) {
                        // Access the meme data and do further processing
                        val randomMemeIndex = (memes.indices).random()
                        val memeUrl = memes[randomMemeIndex].url

                        // Load and display each meme image using Glide
                        Glide.with(this@MainActivity).load(memeUrl).into(binding.memeImg)
                        progressDialog.dismiss()

                    }
                } else {
                    // Handle API error
                }
            }

            override fun onFailure(call: Call<ApiModel?>, t: Throwable) {
                progressDialog.dismiss()
                Toast.makeText(this@MainActivity, t.localizedMessage,Toast.LENGTH_SHORT).show();

            }
        })
    }
}

