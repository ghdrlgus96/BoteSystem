package embedded.block.vote

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import org.json.JSONArray

class AdminInputAdapter(val context: Context): BaseAdapter() {
    companion object {
        var arr_getParticipation = JSONArray()
    }
    private val mInflater: LayoutInflater = LayoutInflater.from(context)
    override fun getCount(): Int {
        Log.d("etest", arr_getParticipation.length().toString())
        return arr_getParticipation.length()
    }

    override fun getItem(position: Int) = arr_getParticipation.getJSONObject(position)

    override fun getItemId(position: Int) = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val view = convertView ?: mInflater.inflate(R.layout.admin_input_select, parent, false)
                    view.findViewById<TextView>(R.id.textView_admin_input_item1).text = getItem(position).getString("userName")
                    view.findViewById<TextView>(R.id.textView_admin_input_item2).text = getItem(position).getString("userPhone")
                    view.findViewById<TextView>(R.id.textView_admin_input_item3).text = getItem(position).getString("userClass")
        return view
    }
}
