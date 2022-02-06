package com.example.myapplication

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

//作成者：綾部
private var textId: TextView? = null
private var textPassword: TextView? = null
private var textBirth: TextView? = null
class checkAccountScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.checkaccount)


        textId = findViewById(R.id.text_id)
        textPassword = findViewById(R.id.passwordText)
        textBirth = findViewById(R.id.birthText)

        val database = baseContext.openOrCreateDatabase("test13.db", Context.MODE_PRIVATE, null)

        val query = database.rawQuery("select * from account", null)
        query.use {
            while (it.moveToNext()) { //カーソルが次のレコードに移動する。何もないならfalseを返す。
                //全てのレコードを循環する
                with(it) {
                    val userid = getString(1)
                    val password = getString(2)
                    val birth = getString(3)
//                    val result = "ID: $id, Name = $name, phone = $phone, email = $email"
                    textId!!.text = userid
                    textPassword!!.text = password
                    textBirth!!.text = birth

                    //パスワード
                    val passText = findViewById<View>(R.id.passwordText) as TextView
                    //6~8文字しか想定してないです
                    if (textPassword != null) {
                        when (textPassword!!.length()) {
                            6 -> {
                                passText.text = "  ●●●●●●"
                            }
                            7 -> {
                                passText.text = "  ●●●●●●●"
                            }
                            8 -> {
                                passText.text = "  ●●●●●●●●"
                            }
                            else -> {
                                passText.text = "範囲外です！"
                            }
                        }
                    }
                }
            }
            //ホームボタン
            val homeButton: ImageView = findViewById(R.id.homeButton)

            //マイページ画面遷移
            homeButton.setOnClickListener {
                val intent = Intent(this, mypageScreen::class.java)
                startActivity(intent)
            }

            //戻るボタン
            val backButton: ImageView = findViewById(R.id.backButton)

            //一つ前の画面に遷移
            backButton.setOnClickListener {
                finish()
            }


        }
    }
}