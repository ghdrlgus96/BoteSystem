package embedded.block.vote

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_first.*

class FirstFragment: Fragment()
{
    companion object {
        var adapter:VoteListAdapter? = null
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_first, container, false)

        return view
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?){

        adapter = VoteListAdapter(context!!)
        flag_listView1.adapter = adapter

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        flag_listView1.setOnItemClickListener { parent, view, position, id ->
            var intent = Intent(activity, VotepageActivity::class.java)
            intent.putExtra("votenum",VoteListActivity.voteNumber[position])
            intent.putExtra("quittime", VoteListActivity.voteQuitTime[position])
            startActivity(intent)
        }
    }
}




