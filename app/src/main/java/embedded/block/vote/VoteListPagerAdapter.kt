package com.example.testfile

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class VoteListPagerAdapter(fm: FragmentManager): FragmentPagerAdapter(fm)
{
    val PAGE_MAX_CNT = 3

    override fun getCount(): Int {
        return PAGE_MAX_CNT
    }
//각 viewpager 띄울 화면 설정
    override fun getItem(position: Int): Fragment? {
        val fragment = when(position)
        {
            0 -> return FirstFragment()
            1 -> return SecondFragment()
            else -> return ThirdFragment()
        }
        return fragment
    }
//Viewpager의 tablayout제목 설정
    override fun getPageTitle(position: Int): CharSequence? {
        val title = when(position)
        {
            0 -> "투표참여"
            1 -> "투표결과"
            else -> "환경설정"
        }
        return title
    }
}