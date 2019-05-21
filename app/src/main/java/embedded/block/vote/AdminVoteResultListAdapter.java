package embedded.block.vote;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AdminVoteResultListAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private ArrayList<AdminVoteResultItem> data;
    private int layout;

    public AdminVoteResultListAdapter(Context context, int layout, ArrayList<AdminVoteResultItem> data){
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
        return data.get(position).getVoteCandidate();
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = inflater.inflate(layout, parent, false);

        }

        ViewGroup.LayoutParams layoutParams = convertView.getLayoutParams();
        layoutParams.height = 100;
        convertView.setLayoutParams(layoutParams);

        AdminVoteResultItem adminVoteResultItem= data.get(position);

        TextView voteCandidate = (TextView) convertView.findViewById(R.id.voteCandidate);
        voteCandidate.setText(adminVoteResultItem.getVoteCandidate());
        TextView voteName = (TextView) convertView.findViewById(R.id.cansScore);
        voteName.setText(adminVoteResultItem.getCansScore()+"표");
        return convertView;
    }

}
