package com.example.odc.myapplication

import android.content.Intent
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.content.ContextCompat.startActivity
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_tutorial.*


class TutorialActivity : AppCompatActivity() {

    private lateinit var pagerAdapter: TutorialPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tutorial)
        val saveSettings= SaveSettings(this)
        saveSettings.saveSettings()
        val (titles, images) = initializeData()
        initializeIndicators()
        initializeViewPager(titles, images)
        skipButton.setOnClickListener{
            startActivity(Intent(this, LoginActivity::class.java))
            //finish()
        }

    }

    private fun initializeClickListeners() {
        skipButton.setOnClickListener{ _ ->
            startActivity(Intent(this, LoginActivity::class.java))
            //finish()
        }
    }

    private fun initializeIndicators() {
        val circles = arrayOf(circleOne, circleTwo, circleThree, circleFour)
        for (i in 0 until circles.size) {
            if (i <= 0) {
                circles[i].setImageDrawable(ContextCompat.getDrawable(this, R.drawable.circle_while))
                circles[i].setPadding(0,0,0, 0)
            } else {
                circles[i].setImageDrawable(ContextCompat.getDrawable(this, R.drawable.circle_gray))
                circles[i].setPadding(resources.getDimensionPixelSize(R.dimen.padding_2),0,resources.getDimensionPixelSize(R.dimen.padding_2), 0)
            }
        }
    }

    private fun initializeViewPager(titles: Array<String>, images: Array<Int>) {
        pagerAdapter = TutorialPagerAdapter(supportFragmentManager, titles, images)
        viewPager.adapter = pagerAdapter

        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            override fun onPageScrollStateChanged(state: Int) {}

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

            override fun onPageSelected(position: Int) {
                val circles = arrayOf(circleOne, circleTwo, circleThree, circleFour)
                for (i in 0 until circles.size) {
                    if (i <= position) {
                        circles[i].setImageDrawable(ContextCompat.getDrawable(this@TutorialActivity, R.drawable.circle_while))
                        circles[i].setPadding(0,0,0, 0)
                    } else {
                        circles[i].setImageDrawable(ContextCompat.getDrawable(this@TutorialActivity, R.drawable.circle_gray))
                        circles[i].setPadding(resources.getDimensionPixelSize(R.dimen.padding_2),0,resources.getDimensionPixelSize(R.dimen.padding_2), 0)
                    }
                }
            }

        })
    }

    private fun initializeData(): Pair<Array<String>, Array<Int>> {
        val titles = arrayOf("Best Workout.", "Hot Motivation.", "Fully personalised.", "Clean Diet.")
        val images = arrayOf(R.drawable.background_login, R.drawable.bg1, R.drawable.background_login, R.drawable.bg1)
        return Pair(titles, images)
    }
}
