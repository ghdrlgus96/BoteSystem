package com.example.testfile

//껍데기 구현 데이터베이스 가져오는법 알면 수정할 것

//메인이미지, 날짜, 투표이름, 투표참여 이미지
data class VoteItem(
    val VoteName: String,
    val Deadline: String
)

enum class VoteData(val voteType: Int, val voteItem: VoteItem) {
    VOTE_ITEM1(VoteListRecyclerAdapter.ITEM, VoteItem("서울시장선거","2019-05-04")),
    VOTE_ITEM2(VoteListRecyclerAdapter.ITEM, VoteItem("가천대학생회장선거","2019-05-15"))
}