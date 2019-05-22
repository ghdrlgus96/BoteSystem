package embedded.block.vote

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.alert_list_item.view.*
import org.json.JSONArray

class AlertListViewAdapter(val context: Context): BaseAdapter() {
    companion object{
        var arr_getPage = JSONArray()
    }
    /* 리스트뷰에서 보여줄 아이템(항목) 화면의 인플레이션을 위해 LayoutInflater 참조 */
    private val mInflater: LayoutInflater = LayoutInflater.from(context)


    override fun getCount(): Int {
        Log.d("ktext", "!!"+arr_getPage.length().toString())
        return arr_getPage.length()
    }

    override fun getItem(position: Int) = arr_getPage.getJSONObject(position).getString("voteCandidate")

    override fun getItemId(position: Int) = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {


        val view = mInflater.inflate(R.layout.alert_list_item, parent, false)
        //투표자 이름
        view.findViewById<TextView>(R.id.item_can_name).text =
            getItem(position)

        view.setOnClickListener {
            if(it.radioButton5.isChecked == true)
                it.radioButton5.isChecked = false
            else
                it.radioButton5.isChecked = true
            Toast.makeText(context, it.item_can_name.text.toString(), Toast.LENGTH_LONG).show()
        }

        return view
    }
}