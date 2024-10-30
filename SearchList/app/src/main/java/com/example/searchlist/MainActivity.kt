package com.example.searchlist

import StudentAdapter
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var studentAdapter: StudentAdapter
    val students = listOf(
        Student("Nguyen Van Anh", "2022015"),
        Student("Tran Thi Binh", "2023018"),
        Student("Le Van Cuong", "2022023"),
        Student("Pham Thi Dung", "2023011"),
        Student("Hoang Van Dat", "2022029"),
        Student("Bui Thi Lan", "2023032"),
        Student("Nguyen Thi Hong", "2022044"),
        Student("Pham Van Khoa", "2023027"),
        Student("Le Thi Thao", "2022022"),
        Student("Nguyen Van Tien", "2023041"),
        Student("Trinh Van Hoa", "2022010"),
        Student("Doan Thi Mai", "2023016"),
        Student("Vu Van Quang", "2022026"),
        Student("Nguyen Thi Tuyet", "2023025"),
        Student("Le Van Hieu", "2022017"),
        Student("Nguyen Van Phuc", "2023039"),
        Student("Tran Thi Nhung", "2022040"),
        Student("Bui Van Tam", "2023013"),
        Student("Hoang Thi Mai", "2023024"),
        Student("Pham Van Long", "2022021"),
        Student("Nguyen Thi Kim", "2023033"),
        Student("Le Van Thanh", "2022019"),
        Student("Tran Van Tai", "2023010"),
        Student("Vu Thi Hoa", "2022042"),
        Student("Nguyen Van Nam", "2023020"),
        Student("Doan Van Minh", "2022031"),
        Student("Trinh Thi Hanh", "2023023"),
        Student("Hoang Van Hanh", "2022043"),
        Student("Bui Van Phuc", "2023014"),
        Student("Le Thi Bich", "2023035"),
        Student("Nguyen Van Vinh", "2022025"),
        Student("Pham Thi Mai", "2023019"),
        Student("Vu Van Duong", "2022037"),
        Student("Nguyen Thi An", "2023012"),
        Student("Tran Van Lieu", "2023036"),
        Student("Doan Thi Ly", "2022047"),
        Student("Hoang Van Hoan", "2023021"),
        Student("Le Van Hoang", "2022046"),
        Student("Trinh Van Son", "2023017"),
        Student("Vu Thi Huong", "2022038"),
        Student("Nguyen Van Kien", "2023028"),
        Student("Pham Van Tai", "2022045"),
        Student("Bui Thi Lan", "2023015"),
        Student("Hoang Van Du", "2022028"),
        Student("Tran Thi Ly", "2023031"),
        Student("Le Van Nhat", "2022048"),
        Student("Doan Van Hai", "2023015"),
        Student("Nguyen Van An", "2022020"),
        Student("Pham Thi Hoai", "2023029"),
        Student("Trinh Van Thanh", "2022049"),
        Student("Vu Van Truong", "2023030"),
        Student("Nguyen Thi Hoan", "2022042"),
        Student("Hoang Van Vinh", "2023018"),
        Student("Bui Thi Ngoc", "2022027"),
        Student("Le Van Phat", "2023016"),
        Student("Tran Van Hung", "2022032"),
        Student("Doan Thi Anh", "2023019"),
        Student("Nguyen Van Duy", "2022040")
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewStudents)
        val searchEditText = findViewById<EditText>(R.id.searchEditText)

        // Khởi tạo adapter và RecyclerView
        studentAdapter = StudentAdapter(students)
        recyclerView.adapter = studentAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Lắng nghe sự thay đổi văn bản trong ô tìm kiếm
        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                filterList(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun filterList(query: String) {
        val filteredList = if (query.length > 2) {
            students.filter {
                it.name.contains(query, ignoreCase = true) || it.studentId.contains(query)
            }
        } else {
            students // Hiện toàn bộ danh sách nếu ít hơn 3 ký tự
        }
        studentAdapter.updateList(filteredList)
    }
}
