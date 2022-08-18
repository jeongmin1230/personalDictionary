package org.techtown.personaldictionary

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
    fun onClickEdit(view: View) {}
}