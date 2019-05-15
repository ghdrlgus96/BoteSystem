package embedded.block.vote;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AdminVoteListAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private ArrayList<AdminVoteItem> data;
    private int layout;

    public AdminVoteListAdapter(Context context, int layout, ArrayList<AdminVoteItem> data){
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.data = data;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return data.size();
    }
    @Override
    public String getItem(int position) {
        return data.get(position).getVoteName();
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = inflater.inflate(layout,parent,false);
        }
        AdminVoteItem adminVoteItem = data.get(position);

        TextView voteNum = (TextView) convertView.findViewById(R.id.voteNum);
        voteNum.setText(Integer.toString(adminVoteItem.getVoteNum()));
        TextView voteName = (TextView) convertView.findViewById(R.id.voteName);
        voteName.setText(adminVoteItem.getVoteName());

        return convertView;
    }

}
