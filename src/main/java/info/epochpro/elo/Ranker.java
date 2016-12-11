package info.epochpro.elo;

/**
 * 排位人
 * Created by jin on 2016/12/11.
 */
public class Ranker {

    private Integer rankPoint;

    public Ranker(Integer rankPoint) {
        this.rankPoint = rankPoint;
    }

    public Integer getRankPoint() {
        return rankPoint;
    }

    public void setRankPoint(Integer rankPoint) {
        this.rankPoint = rankPoint;
    }
}
