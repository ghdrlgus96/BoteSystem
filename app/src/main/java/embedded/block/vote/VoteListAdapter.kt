package embedded.block.vote

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import embedded.block.vote.R
import kotlinx.android.synthetic.main.list_item_shop.view.*
import org.json.JSONArray


class VoteListAdapter(val context: Context): BaseAdapter() {
    /* 리스트뷰에서 보여줄 아이템(항목) 화면의 인플레이션을 위해 LayoutInflater 참조 */
    private val mInflater: LayoutInflater = LayoutInflater.from(context)


    override fun getCount() = voteData.values().size

    override fun getItem(position: Int) = voteData.values()[position]

    override fun getItemId(position: Int) = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {

        if (convertView != null) {
            Log.i("CONVERT-VIEW", "convertView is not null, position: ${position.toString()}")
        } else {
            Log.i("CONVERT-VIEW", "convertView is null, position: ${position.toString()}")
        }

        val view = convertView ?: mInflater.inflate(R.layout.list_item_shop, parent, false)

        view.findViewById<TextView>(R.id.list_VoteName).text = getItem(position).voteName
        view.findViewById<TextView>(R.id.list_Date).text = getItem(position).voteDate


        return view
    }
}