package embedded.block.vote

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_update.*
import org.json.JSONObject
import java.util.HashMap

class UpdateActivity : AppCompatActivity() {
    var myclass = ArrayList<String>()
    var myAuthor: String? = LoginActivity.userAuthor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)

        changePassText.setText(LoginActivity.userPass)
        changePassCheckText.setText(LoginActivity.userPass)
        changePhoneText.setText(LoginActivity.userPhone)

        if(LoginActivity.userAuthor == "1")
            changeAuthor1.isChecked = true
        else if(LoginActivity.userAuthor == "2")
            changeAuthor2.isChecked = true

        var json = JSONObject()
        json.put("myid", "null!")

        var queue: RequestQueue = Volley.newRequestQueue(this);
        val request = object : JsonObjectRequest(
            Request.Method.GET, LoginActivity.ipAdress+"65001/bote/accountmanager/getclass/?mynum=" + LoginActivity.userNum, json,
            Response.Listener { response ->
                run {
                    var tmp = response.getString("userclass")
                    var tempList = tmp.split(",")

                    for (i in 0..tempList.size-1)
                        myclass.add(tempList.get(i))

                    var tempAdpater = ArrayAdapter(this, android.R.layout.simple_list_item_1, myclass)
                    changeList.adapter = tempAdpater
                }
            }, null
        ) {
            @Throws(AuthFailureError::class)
            override fun getHeaders(): MutableMap<String, String>? {
                val headers = HashMap<String, String>()
                headers.put("Content-Type", "application/json")
                return headers
            }
        }
        queue.add(request)

        changeClassButton.setOnClickListener {
            var flag: Boolean = true
            for(i in 0..myclass.size-1) {
                if(myclass.get(i) == changeClassText.text.toString()) {
                    myclass.remove(changeClassText.text.toString())
                    flag = false
                    break;
                }
            }
            if(flag == true)
                myclass.add(changeClassText.text.toString())

            var tempAdpater = ArrayAdapter(this, android.R.layout.simple_list_item_1, myclass)
            changeList.adapter = tempAdpater
        }

        changeSave.setOnClickListener {
            if(changePassCheckText.text.toString() != changePassText.text.toString())
                Toast.makeText(this, "비밀번호가 일치하지 않습니다", Toast.LENGTH_SHORT).show()
            else if(changePassCheckText.text.toString().length > 19)
                Toast.makeText(this, "비밀번호가 너무 깁니다", Toast.LENGTH_SHORT).show()
            else if(changePhoneText.text.toString().length > 13)
                Toast.makeText(this, "전화번호가 너무 깁니다", Toast.LENGTH_SHORT).show()
            else if(myclass.size == 0)
                Toast.makeText(this, "소속이 없을 수 없습니다", Toast.LENGTH_SHORT).show()
            else
                changeUser()
        }
        changeAuthorGroup.setOnCheckedChangeListener { group, checkedId ->
            when(checkedId) {
                R.id.changeAuthor1 ->
                    myAuthor = "1"
                R.id.changeAuthor2 ->
                    myAuthor = "2"
            }
        }
    }

    fun changeUser() {
        var json = JSONObject()
        json.put("mypass", changePassText.text.toString())
        json.put("mynum", LoginActivity.userNum)
        json.put("myphone", changePhoneText.text.toString())
        json.put("myclass", myclass)
        json.put("myauthor", myAuthor)


        var queue: RequestQueue = Volley.newRequestQueue(this);
        val request = object : JsonObjectRequest(
            Request.Method.POST, LoginActivity.ipAdress+"65001/bote/accountmanager/update", json,
            Response.Listener { response ->
                run {
                    if(response.getString("result") == "fail")
                        Toast.makeText(this, "정보변경 실패 하였습니다", Toast.LENGTH_SHORT).show()
                    else {
                        Toast.makeText(this, "정보변경 성공 하였습니다", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                }
            }, null
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
}
