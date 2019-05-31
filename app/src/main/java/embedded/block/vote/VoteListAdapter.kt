package embedded.block.vote

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import org.json.JSONArray


class VoteListAdapter(val context: Context): BaseAdapter() {
    companion object {
        var arr_getList = JSONArray()
    }

    private val mInflater: LayoutInflater = LayoutInflater.from(context)


    override fun getCount(): Int {
        return arr_getList.length()
    }

    override fun getItem(position: Int) = arr_getList.getJSONObject(position)

    override fun getItemId(position: Int) = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        val view = convertView ?: mInflater.inflate(R.layout.list_item_shop, parent, false)

        view.findViewById<TextView>(R.id.list_VoteName).text =
            getItem(position).getString("voteName")
        view.findViewById<TextView>(R.id.list_Date).text =
            getItem(position).getString("quitTime")

        return view
    }
}