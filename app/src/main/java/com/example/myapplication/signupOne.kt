package com.example.myapplication

import android.content.ContentValues
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import java.time.LocalDate

private var helper: TestOpenHelper? = null
private var db: SQLiteDatabase? = null

class signupOne : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup1)

        //パスワード入力欄
        val textPasswordEdit = findViewById<EditText>(R.id.passwordEdit)
        //誕生年入力欄
        val textYearEdit = findViewById<EditText>(R.id.yearEdit)
        //誕生月入力欄
        val textMonthEdit = findViewById<EditText>(R.id.monthEdit)
        //誕生日入力欄
        val textDayEdit = findViewById<EditText>(R.id.dayEdit)
        //ID欄上書き表示
        val textId = findViewById<TextView>(R.id.id) as TextView
        //IDランダム生成
        fun getRandomString(length: Int): String {
            val allowedChars = ('a'..'z') + ('0'..'9')
            return (1..length)
                .map { allowedChars.random() }
                .joinToString("")
        }
        textId.text = getRandomString(5)

        //今日の日付を取得
        val dateNow: LocalDate = LocalDate.now()
        //今日の日付をyyyymmddに変換
        val dateSum = dateNow.year * 10000 + dateNow.monthValue * 100 + dateNow.dayOfMonth

        //新規登録画面1の次へ
        val nextButton: Button = findViewById(R.id.nextButton)

        //新規登録画面2に遷移
        nextButton.setOnClickListener {
            //DB
            if (helper == null) {
                helper = TestOpenHelper(applicationContext)
            }
            if (db == null) {
                db = helper!!.getWritableDatabase()
            }


//            val intent = Intent(this, signupTwo::class.java)
//            //IDの引き渡し
//            intent.putExtra("idKey", textId.getText().toString())
            //入力制限．パスワードが6文字以上，誕生年が4文字以上，誕生月日が1文字以上
            if (textPasswordEdit.length() > 5 && textYearEdit.length() > 3 && textMonthEdit.length() > 0 && textDayEdit.length() > 0) {
                //生年月日をyyyymmddに変換
                val birthSum = textYearEdit.getText().toString().toInt() * 10000 + textMonthEdit.getText().toString().toInt() * 100 + textDayEdit.getText().toString().toInt() - 1
                //誕生年が1901年以上かつ，
                //（今日の日付）-（誕生年*10000 + 誕生月*100 + 誕生日 + 1）の結果が正
                if (textYearEdit.getText().toString().toInt() > 1900 && (dateSum - birthSum) > 0) {
                    //誕生月によって日にちの制限．うるう年を想定して2月はどの年でも29日を入力可としている
                    when (textMonthEdit.getText().toString().toInt()) {
                        1, 3, 5, 7, 8, 10, 12 -> {
                            if (32 > textDayEdit.getText().toString().toInt()) {

                                // 取得した内容を文字列として認識
                                val userid = textId.getText().toString()
                                val password = textPasswordEdit.getText().toString()
                                val birthyear = textYearEdit.getText().toString()
                                val birthmonth =  textMonthEdit.getText().toString()
                                val birthday = textDayEdit.getText().toString()

                                val birth = birthyear + birthmonth + birthday
                                //入力
                                insertData(db!!, userid,password,birth)

                            }
                        }
                        4, 6, 9, 11-> {
                            if (31 > textDayEdit.getText().toString().toInt()) {
                                //値の引き渡し
                                // 取得した内容を文字列として認識
                                val userid = textId.getText().toString()
                                val password = textPasswordEdit.getText().toString()
                                val birthyear = textYearEdit.getText().toString()
                                val birthmonth =  textMonthEdit.getText().toString()
                                val birthday = textDayEdit.getText().toString()
//
                                val birth = birthyear + birthmonth + birthday
                                //入力
                                insertData(db!!, userid,password,birth)
                            }
                        }
                        2 -> {
                            if (30 > textDayEdit.getText().toString().toInt()) {
                                //値の引き渡し

                                // 取得した内容を文字列として認識
                                val userid = textId.getText().toString()
                                val password = textPasswordEdit.getText().toString()
                                val birthyear = textYearEdit.getText().toString()
                                val birthmonth =  textMonthEdit.getText().toString()
                                val birthday = textDayEdit.getText().toString()


                                val birth = birthyear + birthmonth + birthday
                                //入力
                                insertData(db!!, userid,password,birth)
                            }
                        }
                    }
                }
            }
                val intent = Intent(this,signupTwo::class.java)
                startActivity(intent)

            }


        //キャンセルボタン
        val cancelButton: Button = findViewById(R.id.cancelButton)

        //ログイン画面に遷移
        cancelButton.setOnClickListener {
            val intent = Intent(this, loginScreen::class.java)
            startActivity(intent)
        }

    }
    private fun insertData(db: SQLiteDatabase,userid: String, password: String, birth: String) {
        val values = ContentValues()
        values.put("userid", userid)
        values.put("password", password)
        values.put("birth", birth)
        db.insert("account", null, values)
    }
}