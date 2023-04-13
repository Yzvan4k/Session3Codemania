package com.example.session3codemania

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.session3codemania.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Response
import java.io.File
import java.io.FileOutputStream

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var tags: List<TagsData>
    lateinit var courses:List<CoursesData>
    lateinit var adapter:rec_courses_adapter
    var isAll = true
    val checked = mutableListOf<Boolean>()

    override fun onCreate(savedInstanceState: Bundle?) {
        val binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        binding.imageView.setOnClickListener { startActivity(Intent(this,CartScreen::class.java)) }

        adapter = rec_courses_adapter(mutableListOf(),tags)

        binding.recCourses.apply {
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, true)
            adapter = this@MainActivity.adapter
        }




        Utils().setUpApi()
        binding.serch.addTextChangedListener {
            val str = binding.serch.text.toString().split(' ')
            for (i in tags.indices) {
                checked[i] = false
            }
            isAll = true
            for (i in str) {
                for (j in tags.indices) {
                    if (i.equals(tags[j].name, ignoreCase = true)) {
                        checked[j] = true
                        isAll = false
                    }
                }
            }
            if (isAll) {
                for (i in tags.indices) {
                    checked[i] = true
                }
            }
            update()
        }
        check()

    }

    fun check() {
        try {
            val path = Environment.getExternalStorageDirectory()
            val outputStream = FileOutputStream(File(path,"sd.jpg"))
            getTags()
        } catch (e:java.lang.Exception){
            startActivityForResult(intent,123)
        }
    }

    fun getTags() {
        api.getTags().enqueue(object : retrofit2.Callback<List<TagsData>> {
            override fun onResponse(
                call: Call<List<TagsData>>,
                response: Response<List<TagsData>>
            ) {
                if (response.isSuccessful) {
                    tags = response.body()!!
                    for (i in tags.indices){
                        checked.add(true)
                    }
                    getCourses()
                }else{
                    Toast.makeText(this@MainActivity, response.message(), Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<TagsData>>, t: Throwable) {
                Toast.makeText(this@MainActivity, t.message!!, Toast.LENGTH_SHORT).show()
            }
        })
    }
    fun getCourses(){
        api.getCourses().enqueue(object :retrofit2.Callback<List<CoursesData>>{


            override fun onResponse(call: Call<List<CoursesData>>, response: Response<List<CoursesData>>) {
                if (response.isSuccessful){
                    courses = response.body()!!
                    binding.recCourses.adapter = rec_courses_adapter(response.body()!!,tags)
                }else{
                    Toast.makeText(this@MainActivity, response.message(), Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<CoursesData>>, t: Throwable) {
                Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
    fun update(){
        val correctData = mutableListOf<CoursesData>()
        for (i in courses){
            for (j in tags.indices){
                if (i.tags.contains(tags[j].id)&& checked[j]){
                    correctData.add(i)
                    break
                }
            }
        }
        binding.recCourses.adapter = rec_courses_adapter(correctData,tags)
    }
}