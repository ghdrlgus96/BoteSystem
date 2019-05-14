package embedded.block.vote

import android.content.Context
import android.renderscript.ScriptGroup
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class AdminInputAdapter(val context: Context): BaseAdapter() {
    private val mInflater: LayoutInflater = LayoutInflater.from(context)

    override fun getCount(): Int {
        return InputData.values().size
    }

    override fun getItem(position: Int) = InputData.values()[position]

    override fun getItemId(position: Int) = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val view = convertView ?: mInflater.inflate(R.layout.admin_input_select, parent, false)

        view.findViewById<TextView>(R.id.textView_admin_input_item1).text = getItem(position).Name
        view.findViewById<TextView>(R.id.textView_admin_input_item2).text = getItem(position).birth
        view.findViewById<TextView>(R.id.textView_admin_input_item3).text = getItem(position).Class

        return view
    }
}