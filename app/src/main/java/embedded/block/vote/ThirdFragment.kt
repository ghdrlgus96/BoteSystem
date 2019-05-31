package embedded.block.vote

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button


class ThirdFragment: Fragment()
{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode) {
            666 -> {
                activity?.finish()
            }
        }
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        val t = inflater.inflate(R.layout.user_setting, container, false)

        t.findViewById<Button>(R.id.button_user_setting_change).setOnClickListener { v: View? ->
            var intent_change = Intent(context, UpdateActivity::class.java)
            startActivityForResult(intent_change, 666)
        }

        t.findViewById<Button>(R.id.button_user_setting_elimination).setOnClickListener { v: View? ->
            var intent_elimination = Intent(context, EliminationActivity::class.java)
            startActivityForResult(intent_elimination, 666)
        }
        t.findViewById<Button>(R.id.button_user_setting_logout).setOnClickListener { v: View? ->
            activity?.finish()
        }
        return t
    }
}