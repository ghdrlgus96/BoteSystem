package embedded.block.vote

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class VoteResultListAdapter(val context: Context): BaseAdapter() {


    private val mInflater: LayoutInflater = LayoutInflater.from(context)

    override fun getCount() = VoteResultData.values().size

    override fun getItem(position: Int) = VoteResultData.values()[position]

    override fun getItemId(position: Int) = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {


        val view = convertView ?: mInflater.inflate(R.layout.voteresult_list_item, parent, false)

        view.findViewById<TextView>(R.id.list_item_text1).text = getItem(position).Name
        view.findViewById<TextView>(R.id.list_item_text2).text = getItem(position).Score.toString()


        return view
    }
}