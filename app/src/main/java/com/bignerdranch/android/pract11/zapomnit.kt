package com.bignerdranch.android.pract11

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.core.content.edit
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.pract11.databinding.ActivityMainBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File

class zapomnit : AppCompatActivity() {

    private val Books: MutableList<Book> = mutableListOf()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val index = intent.getIntExtra("number", -1)
        val toast = Toast.makeText(applicationContext, "Запомнили)", Toast.LENGTH_SHORT)
        val b3 = findViewById<Button>(R.id.button2)
        var editText = findViewById<EditText>(R.id.editTextTextPersonName)
        var editText1 = findViewById<EditText>(R.id.editTextTextPersonName3)
        var editText2 = findViewById<EditText>(R.id.editTextNumber)
        val bb = findViewById<ImageButton>(R.id.imageButton)
        val preferences = getSharedPreferences("pref", MODE_PRIVATE)
        if (preferences.contains("json")) {
            val listBooks: List<Book> = Gson().fromJson<MutableList<Book>>(preferences.getString("json", "qwe").toString(), object : TypeToken<MutableList<Book>>() {}.type)
            Books.addAll(listBooks)
        }
        if(index > -1) {
            b3.setText("Изменить")
            editText.setText(Books[index].name)
            editText1.setText(Books[index].author)
            editText2.setText(Books[index].pages)
        }
        b3.setOnClickListener{

            if (index == -1) {
                val book: Book = Book(editText.text.toString(), editText1.text.toString(), editText2.text.toString())
                Books.add(book)
                preferences.edit {
                    this.putString("json", Gson().toJson(Books))
                }
            }
            else if (index > -1){
                Books[index].name = editText.text.toString()
                Books[index].author = editText1.text.toString()
                Books[index].pages = editText2.text.toString()
                val preferences = getSharedPreferences("pref", MODE_PRIVATE)
                preferences.edit{
                    this.putString("json", Gson().toJson(Books).toString())
                }
            }
            toast.show()
        }

        bb.setOnClickListener{
            super.onBackPressed()
        }
    }
}
