package com.example.mealproject.controller

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.mealproject.ApiProvider
import com.example.mealproject.MealApi
import com.example.mealproject.model.MealDTO
import com.example.mealproject.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var responseValue : MealDTO
    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d("test", "4")

        binding.btSearch.setOnClickListener{
            if(!binding.etDate.text.isNullOrBlank()){
                readMeal(binding.etDate.text.toString())
            }
        }
    }

    private fun readMeal(data: String){ //여기서부터
        val apiProvider = ApiProvider.getInstance().create(MealApi::class.java)
        apiProvider.getMeal(data).enqueue(object : Callback<MealDTO> {
            override fun onResponse(call: Call<MealDTO>, response: Response<MealDTO>) {
                if(response.isSuccessful){
                    System.out.println(response.body())
                    responseValue = response.body()!!
                    setMeal(responseValue)
                }
                Log.d("test", "5")
            }

            override fun onFailure(call: Call<MealDTO>, t: Throwable) {
                println("오류")

            }
        })
    } // 여기까지 다시보기

    private fun setMeal(MealResponse: MealDTO) {

        Log.d("test", "1")
        var mealView_breakfast = "<아침>\n"
        var mealView_lunch = "<점심>\n"
        var mealView_dinner = "<저녁>\n"

        for(i in MealResponse.breakfast){
            Log.d("test", "2")
            mealView_breakfast += "$i\n"
        }
        for(i in MealResponse.lunch){
            mealView_lunch += "$i\n"
        }
        for(i in MealResponse.dinner){
            mealView_dinner += "$i\n"
        }

        binding.tvBreakfast.text = mealView_breakfast
        Log.d("test", "3")
        binding.tvLunch.text = mealView_lunch
        binding.tvDinner.text = mealView_dinner
    }
}