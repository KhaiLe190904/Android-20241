package com.example.formstudent

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CalendarView
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var editTextMSSV: EditText
    private lateinit var editTextName: EditText
    private lateinit var editTextEmail: EditText
    private lateinit var editTextPhone: EditText
    private lateinit var radioGroupGender: RadioGroup
    private lateinit var calendarView: CalendarView
    private lateinit var checkBoxSports: CheckBox
    private lateinit var checkBoxMovies: CheckBox
    private lateinit var checkBoxMusic: CheckBox
    private lateinit var checkBoxAgreeTerms: CheckBox

    // Dữ liệu cho spinner
    private val phuongXaList = arrayOf("Phường 1", "Phường 2", "Xã 1", "Xã 2")
    private val quanHuyenList = arrayOf("Quận 1", "Quận 2", "Huyện 1", "Huyện 2")
    private val tinhThanhList = arrayOf("TP.HCM", "Hà Nội", "Đà Nẵng", "Cần Thơ")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextMSSV = findViewById(R.id.editTextMSSV)
        editTextName = findViewById(R.id.editTextName)
        editTextEmail = findViewById(R.id.editTextEmail)
        editTextPhone = findViewById(R.id.editTextPhone)
        radioGroupGender = findViewById(R.id.radioGroupGender)
        calendarView = findViewById(R.id.calendarView)
        checkBoxSports = findViewById(R.id.checkBoxSports)
        checkBoxMovies = findViewById(R.id.checkBoxMovies)
        checkBoxMusic = findViewById(R.id.checkBoxMusic)
        checkBoxAgreeTerms = findViewById(R.id.checkBoxAgreeTerms)

        // Khai báo các spinner
        val spinnerPhuongXa: Spinner = findViewById(R.id.spinnerPhuongXa)
        val spinnerQuanHuyen: Spinner = findViewById(R.id.spinnerQuanHuyen)
        val spinnerTinhThanh: Spinner = findViewById(R.id.spinnerTinhThanh)

        // Gán dữ liệu cho spinner phường/xã
        val adapterPhuongXa = ArrayAdapter(this, android.R.layout.simple_spinner_item, phuongXaList)
        adapterPhuongXa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerPhuongXa.adapter = adapterPhuongXa

        // Gán dữ liệu cho spinner quận/huyện
        val adapterQuanHuyen = ArrayAdapter(this, android.R.layout.simple_spinner_item, quanHuyenList)
        adapterQuanHuyen.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerQuanHuyen.adapter = adapterQuanHuyen

        // Gán dữ liệu cho spinner tỉnh/thành phố
        val adapterTinhThanh = ArrayAdapter(this, android.R.layout.simple_spinner_item, tinhThanhList)
        adapterTinhThanh.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerTinhThanh.adapter = adapterTinhThanh

        val buttonShowCalendar: Button = findViewById(R.id.buttonShowCalendar)
        buttonShowCalendar.setOnClickListener {
            // Hiện/ẩn CalendarView
            if (calendarView.visibility == View.GONE) {
                calendarView.visibility = View.VISIBLE
            } else {
                calendarView.visibility = View.GONE
            }
        }

        val buttonSubmit: Button = findViewById(R.id.buttonSubmit)
        buttonSubmit.setOnClickListener {
            submitForm()
        }
    }

    private fun submitForm() {
        val mssv = editTextMSSV.text.toString()
        val name = editTextName.text.toString()
        val email = editTextEmail.text.toString()
        val phone = editTextPhone.text.toString()
        val selectedGenderId = radioGroupGender.checkedRadioButtonId
        val selectedGender: RadioButton? = findViewById(selectedGenderId)

        // Kiểm tra thông tin đã được điền đầy đủ chưa
        if (mssv.isEmpty() || name.isEmpty() || email.isEmpty() || phone.isEmpty() ||
            selectedGenderId == -1 || !checkBoxAgreeTerms.isChecked) {
            Toast.makeText(this, "Vui lòng điền đầy đủ thông tin.", Toast.LENGTH_SHORT).show()
            return
        }

        // Nếu đã đầy đủ, thực hiện xử lý tiếp theo (ví dụ: gửi dữ liệu)
        Toast.makeText(this, "Thông tin đã được gửi thành công.", Toast.LENGTH_SHORT).show()
    }
}
