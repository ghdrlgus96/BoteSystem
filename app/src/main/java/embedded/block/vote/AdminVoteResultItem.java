package embedded.block.vote;

public class AdminVoteResultItem {
    private int cansScore;
    private String voteCandidate;

    public int getCansScore(){
        return cansScore;
    }
    public String getVoteCandidate(){
        return voteCandidate;
    }

    public AdminVoteResultItem(int cansScore, String voteCandidate){
        this.cansScore = cansScore;
        this.voteCandidate = voteCandidate;
    }
}
