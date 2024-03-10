package com.example.todoapplication

import android.app.Application
import com.example.todoapplication.database.model.MyDataBase

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        MyDataBase.init(this)
    }
}