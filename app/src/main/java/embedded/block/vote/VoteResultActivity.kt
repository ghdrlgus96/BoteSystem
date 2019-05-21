package embedded.block.vote

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.voteresult_list.*

class VoteResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.voteresult_list)

        //액션바 타이틀 설정
        supportActionBar?.title = "투표 결과 열람"

        //어댑터 생성 및 리스트뷰에 어댑터 설정
        var adapter = VoteResultListAdapter(this)
        voteresult_listview.adapter = adapter

    }
}
