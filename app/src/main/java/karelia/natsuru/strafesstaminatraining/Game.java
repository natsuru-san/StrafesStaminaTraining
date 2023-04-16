//Author - Natsuru-san (natsuru-san@mail.com)
//Created 16.04.2023

package karelia.natsuru.strafesstaminatraining;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import karelia.natsuru.strafesstaminatraining.entity.Score;

public class Game {

    private final Random random;
    private final Timer timer;
    private static final long period = 1500L;
    private static final long delay = 1500L;
    private int value;
    private int numFailPress = 0;
    private int numSuccessPress = 0;

    public Game(MainActivity main) {
        this.random = new Random();
        this.timer = new Timer();
        this.timer.schedule(new TimerTask() {
            @Override
            public void run() {
                value = random.nextInt(9);
                while ((value % 2) == 0) {
                    value = random.nextInt(9);
                }
                main.send(2);
                main.send(4);
                main.send(6);
                main.send(8);
                main.send(value);
            }
        }, delay, period);
    }

    public void pressedButton(int valid) {
        if (valid == value) {
            numSuccessPress++;
        } else {
            numFailPress++;
        }
    }

    public void stopGame() {
        timer.cancel();
        timer.purge();
    }

    public Score getScore() {
        return new Score(-1L, MainActivity.roundTime, numFailPress, numSuccessPress);
    }
}