package sn.chei.gadsleaderboard.submission

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import sn.chei.gadsleaderboard.R

class ProjectSubmission : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_project_submission)
        val iconBack: ImageView = findViewById(R.id.icon_back)

        iconBack.setOnClickListener { onBackPressed() }
    }
}