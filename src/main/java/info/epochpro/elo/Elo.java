package info.epochpro.elo;

import java.util.function.Function;

/**
 * ELO 算法的简单实现
 * <code>
 * Ra：A玩家当前的Rating
 * Rb：B玩家当前的Rating
 * Sa：实际胜负值，胜=1，平=0.5，负=0
 * Ea：预期A选手的胜负值，Ea=1/(1+10^[(Rb-Ra)/400])
 * Eb：预期B选手的胜负值，Eb=1/(1+10^[(Ra-Rb)/400])
 * R’a=Ra+K（Sa-Ea)
 * </code>
 * Created by jin on 2016/12/11.
 */
public class Elo {

    public enum Sa {
        WIN(1d, "胜"),
        DRAW(0.5d, "平"),
        LOSS(0d, "负"),;
        public Double value;
        public String str;

        Sa(Double value, String str) {
            this.value = value;
            this.str = str;
        }

        public static Sa value(double i) {
            for (Sa sa : Sa.values()) {
                if (sa.value == i) {
                    return sa;
                }
            }
            return null;
        }
    }

    private Function<Ranker,Integer> function;

    private Elo(Function<Ranker,Integer> function) {
        this.function = function;
    }

    private Elo(){
        this.function = (r)->32;
    }

    public static Elo getInstance(Function<Ranker,Integer> function) {
        return new Elo(function);
    }

    public static Elo getInstance() {
        return new Elo();
    }

    public void calElo(Ranker ranka,Ranker rankb,Sa sa) {

        Integer ra = ranka.getRankPoint();
        Integer rb = rankb.getRankPoint();
        Integer k = getK(ra, rb);

        Double a = (rb - ra) / 400d;
        Double ea = 1 / (1 + Math.pow(10, a));
        Double r_a = ra + k * (sa.value - ea);

        ranka.setRankPoint(r_a.intValue());


        Double eb = 1 - ea;
        Double r_b = rb + k * ((1 - sa.value) - eb);

        rankb.setRankPoint(r_b.intValue());
    }

    private Integer getK(Integer rankA,Integer rankB) {
        Integer averageRank = (rankA + rankB) / 2;
        Ranker ranker = new Ranker(averageRank);
        return function.apply(ranker);
    }


}
