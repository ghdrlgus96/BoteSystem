package embedded.block.vote

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import org.json.JSONArray

class AdminResultAdapter(val context: Context) : BaseAdapter() {
    companion object {
        var arr_getList = JSONArray()
        var arr_getResult = JSONArray()
    }

    val mInflater: LayoutInflater = LayoutInflater.from(context)
    override fun getCount(): Int {
        return arr_getResult.length()
    }

    override fun getItem(position: Int) = arr_getResult.getJSONObject(position)

    override fun getItemId(position: Int) = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: mInflater.inflate(R.layout.admin_result_item, parent, false)

        view.findViewById<TextView>(R.id.voteCandidate).text =
            getItem(position).getString("voteCandidate")
        view.findViewById<TextView>(R.id.cansScore).text =
            getItem(position).getString("canScore") + "표"

        return view
    }

}