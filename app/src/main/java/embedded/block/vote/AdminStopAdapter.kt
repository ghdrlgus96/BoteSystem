package embedded.block.vote

import android.app.AlertDialog
import android.content.Context
import android.renderscript.ScriptGroup
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.admin_stop_item.view.*

class AdminStopAdapter(val context: Context): BaseAdapter() {
    private val mInflater: LayoutInflater = LayoutInflater.from(context)
    var data = arrayOf("가천대 학생회 선거", "서울 시장 선거")
    override fun getCount(): Int {
        return data.size
    }

    override fun getItem(position: Int) = data[position]

    override fun getItemId(position: Int) = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val view = convertView ?: mInflater.inflate(R.layout.admin_stop_item, parent, false)

        view.findViewById<TextView>(R.id.textView_admin_stopName).text = getItem(position)
        view.button_admin_stopButton.setOnClickListener { v: View? ->
            val alertDialogBuilder = AlertDialog.Builder(context)
            alertDialogBuilder.setTitle("투표 종료")
            alertDialogBuilder.setMessage("정말 투표를 종료하시겠습니까?")
            alertDialogBuilder.setCancelable(false)
            alertDialogBuilder.setPositiveButton("삭제") { dialog, id ->

            }
            alertDialogBuilder.setNegativeButton("취소") { dialog, id ->

            }
            alertDialogBuilder.show()
        }
        return view
    }
}