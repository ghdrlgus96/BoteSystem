package embedded.block.vote;

public class AdminVoteItem {
    private int voteNum;
    private String voteName;

    public int getVoteNum(){
        return voteNum;
    }
    public String getVoteName(){
        return voteName;
    }

    public AdminVoteItem(int voteNum, String voteName){
        this.voteNum = voteNum;
        this.voteName = voteName;
    }
}
