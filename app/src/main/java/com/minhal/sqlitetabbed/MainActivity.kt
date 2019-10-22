package com.minhal.sqlitetabbed

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var db=DBHandler(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        btnAdd.setOnClickListener {
            var user=User(etName.text.toString(),etEmail.text.toString())
            var success=db.insert(user)
            if (success){
                Toast.makeText(this,"Added",Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this,"Not Added",Toast.LENGTH_SHORT).show()
            }
        }
        btnShow.setOnClickListener {
            tvInfo.text=db.getAllUsers()
        }
    }
}