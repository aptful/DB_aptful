package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog

//作成者：綾部，久保田

class deleteAccountScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.withdrawalshow)

        //戻るボタン
        val backButton : ImageView = findViewById(R.id.backButton)

        //一つ前の画面に遷移
        backButton.setOnClickListener {
            finish()
        }

        //ホームボタン
        val homeButton : ImageView = findViewById(R.id.homeButton)

        //マイページ画面遷移
        homeButton.setOnClickListener {
            val intent = Intent(this,mypageScreen::class.java)
            startActivity(intent)
        }
        //退会ボタン
        val deleteButton : Button = findViewById(R.id.withdrawalButton)

//        //退会確認ポップアップ
//        deleteButton.setOnClickListener {
//            AlertDialog.Builder(this)
//                .setTitle("退会するとこのアカウントは削除されます")
//                .setMessage("本当によろしいですか?")
//
//                .setPositiveButton("いいえ"){ dialog, which ->
//                    val intent = Intent(this,deleteAccountScreen::class.java)
//                    startActivity(intent)
//                }
//
//                .setNegativeButton("はい"){ dialog, which ->
//                    val intent = Intent(this,loginScreen::class.java)
//                    startActivity(intent)
//                }
//                .show()
//
//        }


        //入力間違いポップアップ
        deleteButton.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("IDまたはパスワードが間違っています")

                .setPositiveButton("OK"){ dialog, which ->
                    val intent = Intent(this,deleteAccountScreen::class.java)
                    startActivity(intent)
                }

                .show()

        }
    }
}