package embedded.block.vote

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import embedded.block.vote.R
import embedded.block.vote.VoteData
import embedded.block.vote.VoteItem
import kotlinx.android.synthetic.main.list_item_shop.view.*


class VoteListRecyclerAdapter: RecyclerView.Adapter<VoteListRecyclerAdapter.ItemViewHolder>(){

    /* [0] 어댑터에서 관리하는 아이템의 개수를 반환(최초에 한번 호출)
       - ShopData.values().size : ShopData에 선언된 모든 상수를 가져와 size를 반환
    */
    override fun getItemCount() = VoteData.values().size

    /* [1] 현재 아이템뷰의 position에 해당하는 뷰타입 반환 */
    override fun getItemViewType(position: Int):Int{
        Log.d("RECYCLE", "position: ${position} - getItemViewType() 호출")
        return VoteData.values()[position].voteType
    }

    /* [2] 뷰홀더(ItemViewHolder)를 생성하여 반환(뷰홀더가 새로 만들어지는 시점에만 호출)
       - 리사이클러뷰에서는 리스트로 출력할 각각의 아이템 뷰객체를 모두 생성하는 것이 아니라
         뷰홀더에 뷰객체를 담아두고 사용자가 스크롤하여 보이지 않게 되는 뷰객체를,
         새로 보일 쪽에 재사용함
       - 따라서, onCreateViewHolder() 메서드는 뷰홀더가 새로 만들어지는 시점에만 호출되며,
         뷰홀더가 재사용되는 시점에는 onBindViewHolder() 메서드를 호출하여,
         기존 뷰홀더(재사용)에 데이터만 바인딩함
    */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        Log.d("RECYCLE", "onCreateViewHolde() 호출")

        /* 뷰타입(BANNER,ITEM)에 따라, 해당 item XML 레이아웃을 인플레이션(ItemViewHolder 생성)
           - ViewGroup 객체의 context를 이용하여 LayoutInflater 참조
           - RecyclerView에 출력할 ItemViewHolder를 생성하여 반환
         */
        val view = when (viewType) {
            ITEM -> LayoutInflater.from(parent.context).inflate(R.layout.list_item_shop, parent, false)
            else -> throw IllegalArgumentException(Error("매칭되는 뷰타입이 없습니다."))
        }
        return ItemViewHolder(view)
    }

    /* [3] 뷰홀더에 데이터를 바인딩
       - 매개변수로 받은 ItemViewHolder와 position을 이용하여 뷰홀더에 데이터를 바인딩
       - 데이터는 enum class로 선언한 ShopData
       - ShopData.values() : ShopData에 선언돤 모든 상수를 가져옴
     */
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        Log.d("RECYCLE", "onBindViewHolder() 호출")
        when (holder.itemViewType) {
            ITEM -> {
                holder.bindItem(VoteData.values()[position].voteItem)
            }
        }
    }

    /* 뷰홀더(ItemViewHolder) 클래스 선언
       - 리사이클러뷰에서는 각각의 아이템을 위한 뷰객체를 뷰홀더에 담아 관리하며,
         필요시 재사용함
       - 뷰홀더에 담아 전달된 뷰객체에 데이터를 바인딩
       - itemView : 리사이클러뷰에 List로 출력될 아이템뷰(뷰객체)
    */
    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        //아이템뷰(list_item_shop 레이아웃)에 ShopItem 데이터 바인딩
        fun bindItem(VoteData: VoteItem) {
            Log.d("RECYCLE", "ItemViewHolder 클래스의 bindItem 호출")
            itemView.list_VoteName.text = VoteData.VoteName
            itemView.list_Date.text = VoteData.Deadline
        }
    }

    //BANNER, ITEM을 전역상수로 사용할 수 있도록 final static으로 선언
    companion object {
        val ITEM = 0
    }
}