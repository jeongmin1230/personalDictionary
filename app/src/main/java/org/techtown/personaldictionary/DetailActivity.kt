package org.techtown.personaldictionary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class DetailActivity : AppCompatActivity() {

    private val TAG : String = "jeongmin"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
    }

/* onClick 함수 */
    // 단어 뜻을 수정하고 MainActivity 로 이동하는 클릭이벤트
    fun onClickEdit(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}