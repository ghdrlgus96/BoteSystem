package embedded.block.vote

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.CheckBox
import android.widget.TextView
import org.json.JSONArray
import org.json.JSONObject

class AdminInputAdapter(val context: Context) : BaseAdapter() {
    companion object {
        var arr_getParticipation = JSONArray()
        var checkValue = ArrayList<Int>()
    }

    var temp = false
    val mInflater: LayoutInflater = LayoutInflater.from(context)
    override fun getCount(): Int {
        Log.d("etest", arr_getParticipation.length().toString())
        return arr_getParticipation.length()
    }

    override fun getItem(position: Int) = arr_getParticipation.getJSONObject(position)

    override fun getItemId(position: Int) = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: mInflater.inflate(R.layout.admin_input_select, parent, false)
        var temp2 = false
        for(i in 0..(checkValue.size-1)){
            if(checkValue[i] == getItem(position).getInt("initNum")) {
                view.findViewById<CheckBox>(R.id.checkBox_select).isChecked = true
                temp2 = true
                break;
            }
        }
        if(temp2 == false)
            view.findViewById<CheckBox>(R.id.checkBox_select).isChecked = false
        if (temp == false) {
            view.findViewById<TextView>(R.id.textView_admin_input_item1).text =
                getItem(position).getString("userName")
            view.findViewById<TextView>(R.id.textView_admin_input_item2).text =
                getItem(position).getString("userPhone")
            view.findViewById<TextView>(R.id.textView_admin_input_item3).text =
                getItem(position).getString("userClass")
        }

        view.findViewById<CheckBox>(R.id.checkBox_select).setOnClickListener {
            if(view.findViewById<CheckBox>(R.id.checkBox_select).isChecked) {
                var temp = false
                for(i in 0..(checkValue.size-1)) {
                    if(checkValue[i] == getItem(position).getInt("initNum"))
                        temp = true
                }
                if(temp == false)
                    checkValue.add(getItem(position).getInt("initNum"))
            }
            else if(!view.findViewById<CheckBox>(R.id.checkBox_select).isChecked) {
                var i = 0;
                while(i <= checkValue.size-1) {
                    if(checkValue[i] == getItem(position).getInt("initNum")) {
                        checkValue.removeAt(i)
                        i = 0
                    }
                    else
                        i++
                }
            }

        }
        return view
    }

}
