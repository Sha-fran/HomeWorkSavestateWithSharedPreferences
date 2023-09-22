package com.example.homeworksavestatewithsharedpreferences

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fab: FloatingActionButton = findViewById(R.id.fab)
        textView = findViewById(R.id.text_view)
        val viewModel:MyViewModel = ViewModelProvider(this).get(MyViewModel::class.java)

        viewModel.uiStateLiveData.observe(this) { uiState ->
            when(uiState) {
                is UIState.NoClickedValue -> Unit
                is UIState.ClickedValue -> {
                    textView.text = uiState.numberOfClicks.toString()
                }
            }
        }

        fab.setOnClickListener {
            viewModel.intentLiveData.value = Intent.AddItem
        }

    }
}
