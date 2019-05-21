package embedded.block.vote;

public class AdminVoteItem {
    private int voteNum;
    private String voteName;
    private String voteTime;

    public int getVoteNum(){
        return voteNum;
    }
    public String getVoteName(){
        return voteName;
    }
    public String getVoteTime(){
        return voteTime;
    }

    public AdminVoteItem(int voteNum, String voteName, String voteTime){
        this.voteNum = voteNum;
        this.voteName = voteName;
        this.voteTime = voteTime;
    }
}
