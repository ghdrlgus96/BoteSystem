package embedded.block.vote

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import org.json.JSONArray

class VoteResultRecyclerAdapter(val context: Context): BaseAdapter() {
    private val mInflater: LayoutInflater = LayoutInflater.from(context)

    companion object {
        var arr_getResultList = JSONArray()
    }

    override fun getCount(): Int {
        return arr_getResultList.length()
    }

    override fun getItem(position: Int) = arr_getResultList.getJSONObject(position)

    override fun getItemId(position: Int) = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {


        val view = convertView ?: mInflater.inflate(R.layout.votelist_result, parent, false)

        view.findViewById<TextView>(R.id.result_VoteName).text = getItem(position).getString("voteName")

        return view
    }
}