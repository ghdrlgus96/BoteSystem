package com.example.testfile

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import embedded.block.vote.R

//(제헌)투표목록 화면 Mainactivity
class VoteListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.votelist_main)

        val pagerAdapter = VoteListPagerAdapter(supportFragmentManager)
        val pager = findViewById<ViewPager>(R.id.Viewpage)
        pager.adapter = pagerAdapter

        val tab = findViewById<TabLayout>(R.id.tablayout_main)
        tab.setupWithViewPager(pager)
    }
}
