package embedded.block.vote

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import org.json.JSONArray

class VoteResultListAdapter(val context: Context): BaseAdapter() {

    companion object {
        var arr_ResultList = JSONArray()
    }

    private val mInflater: LayoutInflater = LayoutInflater.from(context)

    override fun getCount() = arr_ResultList.length()

    override fun getItem(position: Int) = arr_ResultList.getJSONObject(position)

    override fun getItemId(position: Int) = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {

        val view = convertView ?: mInflater.inflate(R.layout.voteresult_list_item, parent, false)

        view.findViewById<TextView>(R.id.list_item_text1).text = getItem(position).getString("voteCandidate")
        view.findViewById<TextView>(R.id.list_item_text2).text = getItem(position).getString("canScore")


        return view
    }
}