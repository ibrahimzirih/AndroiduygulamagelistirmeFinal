package com.example.kotlin_final

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    var eposta_input:EditText?=null
    var sifre_input:EditText?=null
    var helper:IboHelper?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        eposta_input=findViewById(R.id.Eposta)
        sifre_input=findViewById(R.id.Sifre)
        helper= IboHelper(this,"ibo_db",1)
    }
    fun sign_up(view:View){
        val name= eposta_input?.text.toString()
        val pass= sifre_input?.text.toString()
        if(!name.isNullOrEmpty()&&!pass.isNullOrEmpty()){
            val exists= helper?.uyeExists(name,pass)
            if(!exists!!){
                helper?.insertUye(name,pass)
            }
            else{
                Toast.makeText(this, "uye zaten var", Toast.LENGTH_SHORT).show()
            }
        }
        else{
            Toast.makeText(this, "gecersiz giris", Toast.LENGTH_SHORT).show()
        }
        clearInputs()
    }
    fun sign_in(view:View){
        val name= eposta_input?.text.toString()
        val pass= sifre_input?.text.toString()
        if(!name.isNullOrEmpty()&&!pass.isNullOrEmpty()){
            val exists= helper?.uyeExists(name,pass)
            if(exists!!){
                Toast.makeText(this, "giris basarili", Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this, "giris basarisiz", Toast.LENGTH_SHORT).show()
            }
        }
        else{
            Toast.makeText(this, "gecersiz giris", Toast.LENGTH_SHORT).show()
        }
        clearInputs()
    }
    fun clearInputs(){
        eposta_input?.setText("")
        sifre_input?.setText("")
    }
}