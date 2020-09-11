package sn.chei.gadsleaderboard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import sn.chei.gadsleaderboard.submission.ProjectSubmission


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewPager = findViewById<ViewPager>(R.id.pager)
        val tabLayout = findViewById<TabLayout>(R.id.tab_layout)
        val viewPagerAdapter = ViewPagerAdapter(supportFragmentManager)
        val submitButton: Button = findViewById(R.id.app_bar_submit_button)

        viewPagerAdapter.addFragment(LearningLeadersFragment(), getString(R.string.label_learning_leaders))
        viewPagerAdapter.addFragment(SkillIqLeadersFragment(), getString(R.string.label_skill_iq_leaders))

        viewPager.adapter = viewPagerAdapter
        tabLayout.setupWithViewPager(viewPager)

        submitButton.setOnClickListener {
            startActivity(Intent(this, ProjectSubmission::class.java))
        }
    }
}