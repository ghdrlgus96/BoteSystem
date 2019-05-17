package embedded.block.vote

import android.content.DialogInterface
import android.content.Intent
import android.icu.lang.UCharacter.GraphemeClusterBreak.V
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v4.widget.DrawerLayout
import android.support.design.widget.NavigationView
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.util.Log.d
import android.view.*
import android.widget.ArrayAdapter
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import embedded.block.vote.AdminInputAdapter.Companion.arr_getParticipation
import kotlinx.android.synthetic.main.admin_input.*
import kotlinx.android.synthetic.main.admin_stop.*
import kotlinx.android.synthetic.main.admin_stop_item.*
import kotlinx.android.synthetic.main.admin_stop_item.view.*
import kotlinx.android.synthetic.main.content_admin.*
import org.json.JSONArray
import org.json.JSONObject

//제허짱
//성빈이 왔다감
class AdminActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    /*
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.admin, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
*/
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_home -> {
                admin_content.removeAllViewsInLayout()
                admin_content.addView(View.inflate(this, R.layout.admin_input, null))

                button_admin_input.setOnClickListener { v: View? ->
                    val intent = Intent(this, AdminInputActivity::class.java)
                    startActivityForResult(intent, 0)
                }
            }
            R.id.nav_start -> {
                admin_content.removeAllViewsInLayout()
                admin_content.addView(View.inflate(this, R.layout.admin_start, null))
                val intent = Intent(this, AdminVoteStart::class.java)
                startActivityForResult(intent, 0)

            }
            R.id.nav_slideshow -> {
                admin_content.removeAllViewsInLayout()
                admin_content.addView(View.inflate(this, R.layout.admin_stop, null))
                var adapter = AdminStopAdapter(this)
                listView_admin_stoplist.adapter = adapter
            }
            R.id.nav_tools -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            0 -> {
                when (resultCode) {
                    0 -> {
                        var arr = data?.getIntegerArrayListExtra("key")

                        var temp = AdminInputAdapter.arr_getParticipation
                        var temp2 = ArrayList<JSONObject>()
                        var temp3 = ArrayList<String>()
                        for (i in 0..(arr!!.size - 1)) {
                            temp2.add(temp.getJSONObject(arr[i]))
                            temp3.add(temp.getJSONObject(arr[i]).getString("userName"))
                        }
                        var adpater = ArrayAdapter(this, android.R.layout.simple_list_item_1, temp3)
                        listView_admin_input_selected.adapter = adpater
                        adpater.notifyDataSetChanged()
                    }
                    666 -> {
                        Log.d("finish", data?.getStringExtra("finish"))
                    }
                }
            }

        }
    }

}
