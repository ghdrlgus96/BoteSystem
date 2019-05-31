package embedded.block.vote

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_second.*


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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        flag_listView2.setOnItemClickListener { parent, view, position, id ->

            var intent = Intent(activity, VoteResultActivity::class.java)
            intent.putExtra("votenum",VoteListActivity.resultVoteNumber[position])
            startActivity(intent)
        }
    }

}
