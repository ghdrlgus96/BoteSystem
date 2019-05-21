package embedded.block.vote

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_findpass.*
import kotlinx.android.synthetic.main.activity_register.*
import org.json.JSONObject
import java.util.HashMap

class RegisterActivity : AppCompatActivity() {

    var passQuestionNumList = ArrayList<String>()
    var passQuestionList = ArrayList<String>()
    var myAnswer: String? = null
    var myAuthor: String? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        regAuthor1.isChecked = true
        myAuthor = "1"

        regNeedButton.setOnClickListener {
            if(regNameText.text.toString() == "" || regIDText.text.toString() == "" || regPassText.text.toString() == "" || reganswerText.text.toString() == "" || regPhoneText.text.toString() == "" || regClassText.text.toString() == "")
                Toast.makeText(this, "정보를 모두 입력해주세요", Toast.LENGTH_SHORT).show()
            else if(regPassText.text.toString() != regPassCheckText.text.toString())
                Toast.makeText(this, "비밀번호가 일치하지 않습니다", Toast.LENGTH_SHORT).show()
            else if(regIDText.text.toString().length > 19)
                Toast.makeText(this, "아이디가 너무 깁니다", Toast.LENGTH_SHORT).show()
            else if(regPassText.text.toString().length > 19)
                Toast.makeText(this, "비밀번호가 너무 깁니다", Toast.LENGTH_SHORT).show()
            else if(regPhoneText.text.toString().length > 13)
                Toast.makeText(this, "전화번호가 너무 깁니다", Toast.LENGTH_SHORT).show()
            else
                register()
        }

        regAuthorGroup.setOnCheckedChangeListener { group, checkedId ->
            when(checkedId) {
                R.id.regAuthor1 ->
                    myAuthor = "1"
                R.id.regAuthor2 ->
                    myAuthor = "2"
            }
        }

        var json = JSONObject()
        json.put("temp", "temp")

        var queue: RequestQueue = Volley.newRequestQueue(this);
        val request = object : JsonObjectRequest(
            Request.Method.GET, "http://203.249.127.32:65001/bote/register/getquestion", json,
            Response.Listener { response ->
                run {
                    var temp1 = response.getString("passquestionnum").split(",")
                    var temp2 = response.getString("passquestion").split(",")

                    for(i in 0..temp1.size - 1) {
                        passQuestionNumList.add(temp1.get(i))
                        passQuestionList.add(temp2.get(i))
                    }

                    answerList.adapter = ArrayAdapter<String>(
                        this,
                        android.R.layout.simple_list_item_1,
                        passQuestionList
                    )

                    answerList.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                        override fun onNothingSelected(parent: AdapterView<*>?) { }
                        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                            myAnswer = passQuestionNumList[position]
                        }
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

    fun register() {
        var json = JSONObject()
        json.put("myname", regNameText.text.toString())
        json.put("myid", regIDText.text.toString())
        json.put("mypass", regPassText.text.toString())
        json.put("myanswernum", myAnswer)
        json.put("myanswer", reganswerText.text.toString())
        json.put("myphone", regPhoneText.text.toString())
        json.put("myclass", regClassText.text.toString())
        json.put("myauthor", myAuthor)


        var queue: RequestQueue = Volley.newRequestQueue(this);
        val request = object : JsonObjectRequest(
            Request.Method.POST, "http://203.249.127.32:65001/bote/register/index", json,
            Response.Listener { response ->
                run {
                    if(response.getString("result") == "fail")
                        Toast.makeText(this, "회원가입에 실패 하였습니다", Toast.LENGTH_SHORT).show()
                    else {
                        Toast.makeText(this, "회원가입에 성공 하였습니다", Toast.LENGTH_SHORT).show()
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
