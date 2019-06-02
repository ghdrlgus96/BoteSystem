package embedded.block.vote

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.AnimatedImageDrawable
import android.graphics.drawable.AnimationDrawable
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.app.AppCompatActivity
import android.support.v7.app.AppCompatDialog
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.android.volley.*
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONObject
import java.util.*
import com.android.volley.DefaultRetryPolicy
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget


class LoginActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        ipAdress = "http://192.9.44.53:"


        regBtn.setOnClickListener {
            var intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        loginBtn.setOnClickListener {
            progressON(this, "로그인중")
            login(myID.text.toString(), myPass.text.toString())
        }

        findID.setOnClickListener {
            var intent = Intent(this, FindidActivity::class.java)
            startActivity(intent)
        }

        findPass.setOnClickListener {
            var intent = Intent(this, FindpassActivity::class.java)
            startActivity(intent)
        }
    }

    fun login(myid: String, mypass: String) {
        var json = JSONObject()
        json.put("myid", "null!")

        var queue: RequestQueue = Volley.newRequestQueue(this);
        val request = object : JsonObjectRequest(Request.Method.GET, ipAdress + "65001/bote/login/?myid=" + myid + "&mypass=" + mypass, json,
            Response.Listener { response ->
                run {
                    progressOFF()
                    retryCount = 0
                    userClass.clear()
                    if(response.getString("userauthor") != "undefind") {
                        var tmp = response.getString("userclassnum")
                        var tempList = tmp.split(",")


                        for (i in 0..tempList.size - 1)
                            userClass.add(tempList.get(i))

                        userId = myid
                        userPass = mypass
                        userName = response.getString("username")
                        userNum = response.getString("usernum")
                        userPhone = response.getString("userphone")
                        userAuthor = response.getString("userauthor")

                        if(userAuthor == "1") {
                            var intent = Intent(this, AdminActivity::class.java)
                            startActivity(intent)
                        }
                        else if(userAuthor == "2") {
                            var intent = Intent(this, VoteListActivity::class.java)
                            startActivity(intent)
                        }
                        else if(userAuthor == "3") {
                            Toast.makeText(this, "회원 탈퇴한 정보입니다", Toast.LENGTH_SHORT).show()
                        }
                    }
                    else {
                        Toast.makeText(this, "아이디 혹은 비밀번호가 틀렸습니다", Toast.LENGTH_SHORT).show()
                    }
                }
            }, Response.ErrorListener {
                retryCount++
                if(retryCount <= 2) {
                    if(ipAdress == "http://203.249.127.32:")
                        ipAdress = "http://192.9.44.53:"
                    else
                        ipAdress = "http://203.249.127.32:"
                    login(myid, mypass)
                }

                else {
                    progressOFF()
                    Toast.makeText(this, "서버가 작동중이 아닙니다.", Toast.LENGTH_SHORT).show()
                }
            }
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

    fun progressON(activity: Activity?, message: String) {

        if (activity == null || activity.isFinishing) {
            return
        }


        if (progressDialog != null && progressDialog!!.isShowing()) {
            progressSET(message)
        } else {

            progressDialog = AppCompatDialog(activity)
            progressDialog?.setCancelable(false)
            progressDialog?.getWindow()!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            progressDialog?.setContentView(R.layout.progress_loading)
            progressDialog?.show()

        }

        val img_loading_frame = progressDialog?.findViewById<ImageView>(R.id.iv_frame_loading) as ImageView
        var gifImg = GlideDrawableImageViewTarget(img_loading_frame)
        Glide.with(this).load(R.drawable.loading).into(gifImg)

        val tv_progress_message = progressDialog?.findViewById<TextView>(R.id.tv_progress_message) as TextView
        if (!TextUtils.isEmpty(message)) {
            tv_progress_message.text = message
        }


    }


    fun progressOFF() {
        if (progressDialog != null && progressDialog!!.isShowing) {
            progressDialog!!.dismiss()
        }
    }
    fun progressSET(message: String) {

        if (progressDialog == null || !progressDialog!!.isShowing) {
            return
        }

        val tv_progress_message = progressDialog?.findViewById<View>(R.id.tv_progress_message) as TextView?
        if (!TextUtils.isEmpty(message)) {
            tv_progress_message!!.text = message
        }

    }
    //user 정보 저장합니다
    companion object {
        var userId = ""
        var userPass = ""
        var userName = ""
        var userNum = ""
        var userClass = ArrayList<String>()
        var userPhone = ""
        var userAuthor = ""
        var ipAdress = ""
        var retryCount = 0
        var progressDialog : AppCompatDialog? = null
    }
}