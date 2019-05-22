package embedded.block.vote

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class AlertListViewAdapter(val context: Context): BaseAdapter() {
    /* 리스트뷰에서 보여줄 아이템(항목) 화면의 인플레이션을 위해 LayoutInflater 참조 */
    private val mInflater: LayoutInflater = LayoutInflater.from(context)


    override fun getCount() = voteData.values().size

    override fun getItem(position: Int) = voteData.values()[position]

    override fun getItemId(position: Int) = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {


        val view = convertView ?: mInflater.inflate(R.layout.alert_list_item, parent, false)
        //투표자 이름
        view.findViewById<TextView>(R.id.item_can_name).text = getItem(position).voteName

        return view
    }
}