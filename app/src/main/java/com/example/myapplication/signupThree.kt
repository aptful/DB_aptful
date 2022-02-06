package com.example.myapplication

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.Button
import android.widget.TextView
import android.widget.ListView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import java.util.ArrayList

//作成者：綾部，井口，久保田

private var textId: TextView? = null
private var textPassword: TextView? = null
private var textBirth: TextView? = null

class signupThree : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup3)

//        //値の受け取り
//        val textPasswordEdit = intent.getStringExtra("passwordKey")
//        val textYearEdit = intent.getStringExtra("yearKey")
//        val textMonthEdit = intent.getStringExtra("monthKey")
//        val textDayEdit = intent.getStringExtra("dayKey")
//        val textId = intent.getStringExtra("idKey")

        textId = findViewById(R.id.ID_edit_signupText)
        textPassword = findViewById(R.id.password_edit_signupText)
        textBirth = findViewById(R.id.birthday_edit_signupText)


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
                    val passText = findViewById<View>(R.id.password_edit_signupText) as TextView
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
            //登録されたキーワードが入ったリスト
            val favKeywordList: kotlin.collections.List<String> =
                ArrayList(intent.getStringArrayListExtra("favKeyList"))

            //キーワード
            // xmlにて実装したListViewの取得
            val favKeywordView = findViewById<ListView>(R.id.favKeywordText)
            // ArrayAdapterの生成
            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, favKeywordList)
            // ListViewに、生成したAdapterを設定
            favKeywordView.adapter = adapter


            //完了ボタン
            val completeButton: Button = findViewById(R.id.okButton)


            //ユーザ登録完了ポップアップ
            completeButton.setOnClickListener {
                AlertDialog.Builder(this)
                    .setTitle("利用規約に同意しますか")

                    .setPositiveButton("同意する") { dialog, which ->
                        AlertDialog.Builder(this)
                            .setTitle("登録完了!")
                            .setMessage("ログインしてみましょう")

                            .setPositiveButton("OK") { dialog, which ->
                                val intent = Intent(this, loginScreen::class.java)
                                startActivity(intent)
                            }
                            .show()

                    }
                    .setNegativeButton("同意しない") { dialog, which ->
                    }

                    .show()

            }


            //キャンセルボタン
            val cancelButton: Button = findViewById(R.id.cancelButton)

            //ログイン画面に遷移
            cancelButton.setOnClickListener {
                val intent = Intent(this, loginScreen::class.java)
                startActivity(intent)
            }

        }


        var checkBoxState = arrayOf(0)

        //チェックボックスが押されたら
        fun onCheckboxClicked(view: View) {
            if (view is CheckBox) {
                var checked: Boolean = view.isChecked
                when (view.id) {
                    R.id.checkbox -> {
                        if (checked) {
                            checkBoxState[0] = 1
                        } else {
                            checkBoxState[0] = 0
                        }
                    }
                }
            }
        }

    }
}