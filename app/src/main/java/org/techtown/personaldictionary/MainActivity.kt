package org.techtown.personaldictionary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val TAG : String = "jeongmin"

    private val database = Firebase.database
    private val myRef = database.getReference("dic")

    private val db = FirebaseFirestore.getInstance()    // Firestore 인스턴스 선언
    private val itemList = arrayListOf<ListLayout>()    // 리스트 아이템 배열
    private val adapter = ListAdapter(itemList)         // 리사이클러 뷰 어댑터

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvList.adapter = adapter

        infLoad()
    }
/* 일반 함수 */
    private fun infLoad() {
        Log.d(TAG, "MainActivity 안 nameLoad 안 받은 이름 : ${intent.getStringExtra("name")}, 메일 : ${intent.getStringExtra("email")}, 번호 : ${intent.getStringExtra("phone")}")
        mainTvInf.text = "${intent.getStringExtra("name")}님의 사전"

        onLoadDatabase()
    }

    // realtime database 값 읽어오기
    private fun onLoadDatabase() {
        myRef.addValueEventListener(object : ValueEventListener {
            // 이메일 @뒤에 자르는 기능
            var getEmail = intent.getStringExtra("email").toString() // 골뱅이 뒤에를 잘라서 child 로 만들어야 함
            var splitEmail = getEmail.split("@")
            var split0thEmailString = splitEmail[0].toString()
            override fun onDataChange(snapshot: DataSnapshot) {
                itemList.clear()
                val test = snapshot.child("$split0thEmailString")
                for(ds in test.children) {
                    Log.d(TAG, "저장된 값 : ${ds.toString()}")
                    val item = ListLayout(ds.key.toString(), ds.value.toString())
                    itemList.add(item)
                }
                adapter.notifyDataSetChanged() // 리사이클러 뷰 갱신
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d(TAG, "읽어오지 못함")
            }
        })
    }
}