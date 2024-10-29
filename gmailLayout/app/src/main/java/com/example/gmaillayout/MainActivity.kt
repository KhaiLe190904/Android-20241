package com.example.gmaillayout

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.example.gmaillayout.adapter.GmailAdapter
import com.example.gmaillayout.modle.Gmail

class MainActivity : AppCompatActivity() {
    private lateinit var rcvGmail: RecyclerView
    private lateinit var myGmailList: ArrayList<Gmail>
    private lateinit var gmailAdapter: GmailAdapter
    private lateinit var senderName: Array<String>
    private lateinit var time: Array<String>
    private lateinit var description: Array<String>
    private lateinit var avatar: Array<Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        rcvGmail = findViewById(R.id.rsv_gmail)
        rcvGmail.layoutManager = LinearLayoutManager(this)

        senderName = arrayOf(
            "Alice", "Bob", "Charlie", "David", "Eve", "Frank", "Grace", "Hank", "Ivy"
        )

        time = arrayOf(
            "10:30 AM", "11:15 AM", "12:00 PM", "12:45 PM", "1:30 PM", "2:15 PM", "3:00 PM", "3:45 PM", "4:30 PM"
        )

        description = arrayOf(
            "Meeting with team members", "Project deadline reminder", "Client follow-up", "Lunch break",
            "Team presentation prep", "System update scheduled", "Report submission",
            "Performance review discussion", "End-of-day summary"
        )

        avatar = arrayOf(
            R.drawable.avt1,
            R.drawable.avt2,
            R.drawable.avt3,
            R.drawable.avt4,
            R.drawable.avt5,
            R.drawable.avt6,
            R.drawable.avt7,
            R.drawable.avt8,
            R.drawable.avt9
        )

        myGmailList = arrayListOf<Gmail>()
        for(i in avatar.indices){
            var emails = Gmail(senderName[i], time[i], description[i], avatar[i])
            myGmailList.add(emails)
        }
        rcvGmail.adapter = GmailAdapter(myGmailList)
    }
}