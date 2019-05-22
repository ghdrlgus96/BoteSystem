package embedded.block.vote

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import embedded.block.vote.R
import kotlinx.android.synthetic.main.fragment_first.*
import kotlinx.android.synthetic.main.fragment_first.view.*
import kotlinx.android.synthetic.main.list_item_shop.*

class FirstFragment: Fragment()
{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_first, container, false)

        return view
    }

    var adapter:VoteListAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){

        adapter = VoteListAdapter(context!!)
        flag_listView1.adapter = adapter

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        flag_listView1.setOnItemClickListener { parent, view, position, id ->

            var intent = Intent(activity, VotepageActivity::class.java)


            startActivity(intent)
        }
    }

}




