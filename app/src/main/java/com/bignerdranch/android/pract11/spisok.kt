package com.bignerdranch.android.pract11

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class spisok : AppCompatActivity() {

    private val Books: MutableList<Book> = mutableListOf()
    private lateinit var rv: RecyclerView
    var index = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spisok)
        getInfo()
        rv = findViewById(R.id.recycler)
        val bb2 = findViewById<ImageButton>(R.id.i2)
        val adapter = KnigaRVAdapter(this, Books)

        val recListener = object : KnigaRVAdapter.ItemClickListener{
            override fun onItemClick(view: View?, position: Int) {
                index = position
                val intent = Intent(this@spisok, zapomnit::class.java)
                intent.putExtra("number", position)
                startActivity(intent)
            }
        }
        adapter.setClickListener(recListener)
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = adapter

        bb2.setOnClickListener{
            super.onBackPressed()
        }
    }


    override fun onResume() {
        super.onResume()
        if (index != -1){
            Books.clear()
            getInfo()
            rv.adapter?.notifyItemChanged(index)
        }
    }

    private fun getInfo(){
        val preferences = getSharedPreferences("pref", MODE_PRIVATE)
        var json: String= ""
        if (preferences.contains("json")){
            json = preferences.getString("json", "NOT_JSON").toString()
        }else{
            return
        }
        val tempList = Gson().fromJson<List<Book>>(json, object: TypeToken<List<Book>>(){}.type)
        Books.addAll(tempList)
    }

}