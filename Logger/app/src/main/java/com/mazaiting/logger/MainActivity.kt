package com.mazaiting.logger

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.mazaiting.log.L

class MainActivity : AppCompatActivity() {
  
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    
    L.e("mazaitingmazaitingmazaitingmazaitingmazaitingmazaitingmazaitingmazaitingmazaiting" +
            "mazaitingmazaitingmazaitingmazaitingmazaitingmazaitingmazaitingmazaitingmazaiting" +
            "mazaitingmazaitingmazaitingmazaitingmazaitingmazaitingmazaitingmazaitingmazaiting")
  }
}
