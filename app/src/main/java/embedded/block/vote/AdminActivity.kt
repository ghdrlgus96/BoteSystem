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
import android.util.Log.d
import android.view.*
import kotlinx.android.synthetic.main.admin_input.*
import kotlinx.android.synthetic.main.admin_stop.*
import kotlinx.android.synthetic.main.admin_stop_item.*
import kotlinx.android.synthetic.main.admin_stop_item.view.*
import kotlinx.android.synthetic.main.content_admin.*

//제허짱
//성빈이 왔다감
class AdminActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
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
                    //startActivity(Intent(this, AdminInputActivity::class.java))
                    val intent = Intent(this, AdminInputActivity::class.java)
                    startActivityForResult(intent, 0)
                }
            }
            R.id.nav_start -> {
                admin_content.addView(View.inflate(this, R.layout.test, null))
                button_admin_input.setOnClickListener { v: View? ->
                    val intent = Intent(this, AdminVoteStart::class.java)
                    startActivity(intent)
                }
            }
            R.id.nav_slideshow -> {
                admin_content.removeAllViewsInLayout()
                admin_content.addView(View.inflate(this, R.layout.admin_stop, null))
                var adapter = AdminStopAdapter(this)
                listView_admin_stoplist.adapter = adapter
                /*
                for(i in 0..(adapter.count-1)) {
                    val view = listView_admin_stoplist.getChildAt(i)
                    view.button_admin_stopButton.setOnClickListener { v: View? ->
                        /*
                        val alertDialogBuilder = AlertDialog.Builder(this)
                        alertDialogBuilder.setTitle("투표 종료")
                        alertDialogBuilder.setMessage("정말 투표를 종료하시겠습니까?")
                        alertDialogBuilder.setCancelable(false)
                        alertDialogBuilder.setPositiveButton("삭제") { dialog, id ->

                        }
                        alertDialogBuilder.setNegativeButton("취소") { dialog, id ->

                        }
                        */
                    }
                }
                */

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
        when(requestCode) {
            0 -> {textView_admin_input_selectedText.text = data?.getStringExtra("key")}
        }
    }
}
