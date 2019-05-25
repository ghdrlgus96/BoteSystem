package embedded.block.vote

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Toast
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import kotlinx.android.synthetic.main.admin_input.*
import kotlinx.android.synthetic.main.admin_start.*
import kotlinx.android.synthetic.main.admin_stop.*
import kotlinx.android.synthetic.main.content_admin.*
import kotlinx.android.synthetic.main.user_setting.*
import org.json.JSONArray
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

//제허짱
//성빈이 왔다감
class AdminActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    var arr_voter = ArrayList<Int>()    //투표 참여자 선언
    lateinit var input_view: View   //투표 참여 화면 늦은 초기화
    lateinit var stop_view: View
    lateinit var user_setting_view: View
    lateinit var result_view: View

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
        //늦은 초기화 한 애들 인플레이션
        input_view = View.inflate(this, R.layout.admin_input, null)
        stop_view = View.inflate(this, R.layout.admin_stop, null)
        user_setting_view = View.inflate(this, R.layout.user_setting, null)
        result_view = View.inflate(this, R.layout.admin_start, null)

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
            R.id.nav_home -> {      //투표 등록 메뉴
                admin_content.removeAllViews()
                admin_content.addView(input_view)
                var arr_can = ArrayList<String?>()
                var adapter_canList = ArrayAdapter(this, android.R.layout.simple_list_item_1, arr_can)
                listViw_admin_input_can.adapter = adapter_canList


                //투표 참여자 선택 인텐트 불러옴
                button_admin_input_callSelect.setOnClickListener { v: View? ->
                    val intent = Intent(this, AdminInputActivity::class.java)
                    startActivityForResult(intent, 0)
                }
                //투표 후보자 입력 다이얼로그 불러옴
                button_admin_input_can.setOnClickListener { v: View? ->
                    Log.d("etest", "다이얼로그 테스트")
                    val alertDialogBuilder = android.app.AlertDialog.Builder(this)
                    alertDialogBuilder.setTitle("후보자 입력")
                    alertDialogBuilder.setMessage("후보자 이름을 입력하세요")
                    val editText = EditText(this)
                    alertDialogBuilder.setView(editText)
                    alertDialogBuilder.setCancelable(false)
                    alertDialogBuilder.setPositiveButton("입력") { dialog, id ->
                        arr_can.add(editText.text.toString())
                        adapter_canList.notifyDataSetChanged()
                        dialog.dismiss()
                    }
                    alertDialogBuilder.setNegativeButton("취소") { dialog, id ->
                        dialog.dismiss()
                    }
                    alertDialogBuilder.show()
                }
                //서버에 투표 등록
                button_admin_inputGo.setOnClickListener { v: View? ->
                    if(arr_can.size == 0 || arr_voter.size == 0 ||
                            editText_admin_input_name.text == null || editText_admin_input_name.text.length == 0)
                        Toast.makeText(this, "입력되지 않은 데이터가 있습니다.", Toast.LENGTH_SHORT).show()
                    else {
                        //서버로 보낼 JSON 구성
                        var arr_userNum = ArrayList<Int>()
                        for (i in 0..(arr_voter.size-1)) {
                            arr_userNum.add(AdminInputAdapter.arr_getParticipation.getJSONObject(arr_voter[i]).getInt("userNum"))
                        }
                        var voteName = editText_admin_input_name.text.toString()
                        var json_toServer = JSONObject()

                        json_toServer.put("voteName", voteName)
                        json_toServer.put("voteVoter", Gson().toJson(arr_userNum))
                        json_toServer.put("voteCandidate", Gson().toJson(arr_can))
                        json_toServer.put("userNum", LoginActivity.userNum.toInt())

                        //서버에 보내는 부분
                        var queue: RequestQueue = Volley.newRequestQueue(this);
                        val request = object : JsonObjectRequest(
                            Request.Method.POST,
                            "http://203.249.127.32:65001/bote/vote/votemaker",
                            json_toServer,
                            Response.Listener { response ->
                                run {
                                    Log.d("etest", response.toString())
                                }
                            },
                            null
                        ) {
                            @Throws(AuthFailureError::class)
                            override fun getHeaders(): MutableMap<String, String>? {
                                val headers = HashMap<String, String>()
                                headers.put("Content-Type", "application/json")
                                return headers
                            }
                        }
                        queue.add(request)
                        admin_content.removeAllViews()
                        Toast.makeText(this, "투표 등록 완료!", Toast.LENGTH_SHORT).show()
                    }
                }
            } //투표 등록 눌렀을때
            R.id.nav_start -> {
                admin_content.addView(View.inflate(this, R.layout.admin_start, null))
                val intent = Intent(this, AdminVoteStart::class.java)
                startActivity(intent)

            }
            R.id.nav_slideshow -> {
                admin_content.removeAllViews()
                admin_content.addView(stop_view)

                var queue: RequestQueue = Volley.newRequestQueue(this);
                val request = object : StringRequest(
                    Request.Method.GET,
                    "http://203.249.127.32:65001/bote/vote/voteupdater/getlist/?userNum=" + LoginActivity.userNum,
                    Response.Listener { response ->
                        run {
                            var arr_getlist = JSONArray(response.toString())
                            val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                            var nowTime = format.format(System.currentTimeMillis())
                            var i = 0
                            while(i <= (arr_getlist.length()-1)) {
                                Log.d("etest", "dsfs"+arr_getlist.getJSONObject(i).getString("quitTime"))
                                if (arr_getlist.getJSONObject(i).getString("quitTime") == "null" ||
                                    arr_getlist.getJSONObject(i).getString("quitTime") < nowTime) {
                                    Log.d("etest", arr_getlist.getJSONObject(i).toString())
                                    arr_getlist.remove(i)
                                    i = 0
                                }
                                else
                                    i++
                            }
                            AdminStopAdapter.arr_getlistforStop = arr_getlist
                            Log.d("etest", "rrrrr" + AdminStopAdapter.arr_getlistforStop.toString())
                            var adapter = AdminStopAdapter(this)
                            listView_admin_stoplist.adapter = adapter
                        }
                    },
                    null
                ) {
                    @Throws(AuthFailureError::class)
                    override fun getHeaders(): MutableMap<String, String>? {
                        val headers = HashMap<String, String>()
                        headers.put("Content-Type", "application/json")
                        return headers
                    }
                }
                queue.add(request)
            }  //투표 중단 눌렀을때
            R.id.nav_result -> {
                admin_content.removeAllViews()
                admin_content.addView(result_view)
                var queue: RequestQueue = Volley.newRequestQueue(this);
                var arr_getName = ArrayList<String>()
                val request = object : StringRequest(
                    Request.Method.GET,
                    "http://203.249.127.32:65001/bote/vote/voteresulter/admingetlist/?userNum=" + LoginActivity.userNum,
                    Response.Listener { response ->
                        run {
                            var arr_getlist = JSONArray(response.toString())
                            val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                            var nowTime = format.format(System.currentTimeMillis())
                            var i = 0

                            while(i <= (arr_getlist.length()-1)) {
                                if (arr_getlist.getJSONObject(i).getString("quitTime") == "null" ||
                                    arr_getlist.getJSONObject(i).getString("quitTime") > nowTime) {
                                    arr_getlist.remove(i)
                                    i = 0
                                }
                                else
                                    i++
                            }

                            AdminResultAdapter.arr_getList = arr_getlist
                            for(i in 0..(AdminResultAdapter.arr_getList.length()-1))
                                arr_getName.add(AdminResultAdapter.arr_getList.getJSONObject(i).getString("voteName"))

                            var madapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, arr_getName)
                            admin_start.setOnItemClickListener { parent, view, position, id ->
                                var intent = Intent(this, AdminResultActivity::class.java)
                                Log.d("etest", "포지션" + position)
                                intent.putExtra("position", position)
                                Log.d("etest", "열림")
                                startActivity(intent)

                            }
                            admin_start.adapter = madapter

                        }
                    },
                    null
                ) {
                    @Throws(AuthFailureError::class)
                    override fun getHeaders(): MutableMap<String, String>? {
                        val headers = HashMap<String, String>()
                        headers.put("Content-Type", "application/json")
                        return headers
                    }
                }
                queue.add(request)

            }
            R.id.user_settings -> {
                Log.d("etest", "왜안나오냐")
                admin_content.removeAllViews()
                admin_content.addView(user_setting_view)

                button_user_setting_change.setOnClickListener { v: View? ->
                    var intent_change = Intent(this, UpdateActivity::class.java)
                    startActivityForResult(intent_change, 666)
                }
                button_user_setting_elimination.setOnClickListener { v: View? ->
                    var intent_elimination = Intent(this, EliminationActivity::class.java)
                    startActivityForResult(intent_elimination, 666)
                }
                button_user_setting_logout.setOnClickListener { v: View? ->
                    super.recreate()
                    finish()

                }

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
                        var arr = data?.getIntegerArrayListExtra("userNum")

                        var temp = AdminInputAdapter.arr_getParticipation
                        var temp3 = ArrayList<String>()
                        arr_voter.removeAll(arr_voter)
                        for (i in 0..(arr!!.size - 1)) {
                            temp3.add(temp.getJSONObject(arr[i]).getString("userName"))
                            arr_voter.add(arr[i])
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
            666 -> {
                finish()
                super.recreate()
            }
        }
    }
}
