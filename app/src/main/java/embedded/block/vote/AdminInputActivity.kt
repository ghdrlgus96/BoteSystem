package embedded.block.vote

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.admin_input.*
import kotlinx.android.synthetic.main.admin_input_item.*
import kotlinx.android.synthetic.main.admin_input_item.view.*
import kotlinx.android.synthetic.main.admin_input_select.view.*

class AdminInputActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.admin_input_item)
        supportActionBar?.title = "참여자 선택"
        var adapter = AdminInputAdapter(this)
        listview_admin_input_select.adapter = adapter
        button_admin_input_select.setOnClickListener { v: View? ->
            var select  = ""
            for (i in 0..(adapter.count-1)) {

                val view = listview_admin_input_select.getChildAt(i)
                if (view.checkBox.isChecked == true)
                    select = select + view.textView_admin_input_item1.text + " "
            }
            Toast.makeText(this,select + "선택됨",Toast.LENGTH_SHORT).show()
            finish()
            //findViewById<TextView>(R.id.textView_admin_input_selectedText).text = "가나다"
        }
    }
}