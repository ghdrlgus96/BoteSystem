package embedded.block.vote

import android.R
import android.content.Context
import android.text.Layout
import android.widget.TextView
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.BaseAdapter


class AdminVoteListAdapter(context: Context, val layout: Int, val data: ArrayList<AdminVoteItem>) : BaseAdapter() {
    val inflater: LayoutInflater
    init {
        this.inflater = LayoutInflater.from(context)
    }

    override fun getCount(): Int {
        return data.size
    }

    override fun getItem(position: Int): String {
        return data[position].voteName
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: inflater.inflate(layout, parent, false)

        view.findViewById<TextView>(embedded.block.vote.R.id.voteNum).text = data[position].voteNum.toString()
        view.findViewById<TextView>(embedded.block.vote.R.id.voteName).text = data[position].voteName

        return view
    }

}