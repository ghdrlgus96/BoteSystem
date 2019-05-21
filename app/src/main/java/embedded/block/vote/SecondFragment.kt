package embedded.block.vote

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import embedded.block.vote.R
import kotlinx.android.synthetic.main.fragment_first.*
import kotlinx.android.synthetic.main.fragment_first.view.*
import kotlinx.android.synthetic.main.fragment_second.*
import kotlinx.android.synthetic.main.fragment_second.view.*


class SecondFragment: Fragment()
{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_second, container, false)
        return view
    }

    var adapter:VoteResultRecyclerAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){

        adapter = VoteResultRecyclerAdapter(context!!)
        flag_listView2.adapter = adapter
    }

}
