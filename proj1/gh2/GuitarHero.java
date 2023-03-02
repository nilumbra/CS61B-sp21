package gh2;
import edu.princeton.cs.algs4.StdAudio;
import edu.princeton.cs.algs4.StdDraw;

public class GuitarHero {
    public static final GuitarString[] SCALE37 = new GuitarString[37];
    public static void main(String[] args) {
        /* create two guitar strings, for concert A and C */
        String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
        for (int i = 0; i < 37; i++) {
            SCALE37[i] = new GuitarString(440 * Math.pow(2, (i - 24) / (double) 12));
        }


        GuitarString vibratingString;
        int mainKey = -1;
        int[] hm = new int[2]; // two harmonics of mainKey
        int currHm = 0;
        while (true) {

            /* check if the user has typed a key; if so, process it */

            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                mainKey = keyboard.indexOf(key);
                if (mainKey > -1 && mainKey < 37) { // initialized a string
                    SCALE37[mainKey].pluck();
                    currHm = 0;
                    int m = 1;
                    while ((mainKey + m * 12) < 37 && m <= 2) {
                        int hrmK = mainKey + m * 12;
                        hm[m-1] = hrmK;
                        SCALE37[hrmK].pluck();
                        currHm++;
                        m++;
                    }
                }
            }

            /* compute the superposition of samples */
            if (mainKey > -1 && mainKey < 37) {
                double sample = SCALE37[mainKey].sample();
                for (int m = 0; m < currHm; m++) {
                    int hrmK = mainKey + m * 12;
                    sample += SCALE37[hrmK].sample();
                }
                StdAudio.play(sample);

                SCALE37[mainKey].tic();
                for (int m = 0; m < currHm; m++) {
                    int hrmK = mainKey + m * 12;
                    SCALE37[hrmK].tic();
                }
            }

            /* play the sample on standard audio */


            /* advance the simulation of each guitar string by one step */
//            stringA.tic();
//            stringC.tic();
        }
    }
}
