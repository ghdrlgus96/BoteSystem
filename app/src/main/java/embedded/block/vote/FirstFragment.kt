package com.example.testfile

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import embedded.block.vote.R
import kotlinx.android.synthetic.main.fragment_first.view.*

class FirstFragment: Fragment()
{


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    //리사이클뷰 어댑터, 레이아웃매니저 등록
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootview = inflater.inflate(R.layout.fragment_first, container, false)
        rootview.recyclerView.layoutManager = LinearLayoutManager(activity)
        rootview.recyclerView.adapter = VoteListRecyclerAdapter()

        return rootview
    }
}


