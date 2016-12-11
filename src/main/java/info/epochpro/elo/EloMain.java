package info.epochpro.elo;

import java.util.Random;

/**
 * 测试
 * Created by jin on 2016/12/11.
 */
public class EloMain {
    public static void main(String[] args) {
        Ranker a = new Ranker(2000);
        Ranker b = new Ranker(1800);

        for (int i = 0; i < 10; i++) {
            Elo.Sa sa = random();
            Elo.getInstance((r)->{
                if (r.getRankPoint() > 1000) {
                    return 32;
                }else{
                    return 40;
                }
            }).calElo(a,b,sa);
            System.out.println(sa.str+" ra:"+a.getRankPoint()+" rb:"+b.getRankPoint());
        }
    }

    private static Elo.Sa random() {
        Random random = new Random();
        double[] d = new double[]{1,0.5,0};
        Integer index = random.nextInt(3);
        double f =  d[index];
        return Elo.Sa.value(f);
    }
}
