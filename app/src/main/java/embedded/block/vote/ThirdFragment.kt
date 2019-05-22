package embedded.block.vote

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import embedded.block.vote.R
import kotlinx.android.synthetic.main.user_setting.*
import kotlinx.android.synthetic.main.user_setting.view.*


class ThirdFragment: Fragment()
{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        val t = inflater.inflate(R.layout.user_setting, container, false)

        t.findViewById<Button>(R.id.button_user_setting_change).setOnClickListener { v: View? ->
            var intent_change = Intent(context, UpdateActivity::class.java)
            //startActivityForResult(intent_change, 666)
            startActivity(intent_change)
        }

        t.findViewById<Button>(R.id.button_user_setting_elimination).setOnClickListener { v: View? ->
            var intent_elimination = Intent(context, EliminationActivity::class.java)
            //startActivityForResult(intent_elimination, 666)
            startActivity(intent_elimination)
        }
        t.findViewById<Button>(R.id.button_user_setting_logout).setOnClickListener { v: View? ->

            activity?.finish()
        }

        return t
    }
}