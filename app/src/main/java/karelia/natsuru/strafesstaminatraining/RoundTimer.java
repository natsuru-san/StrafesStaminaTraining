//Author - Natsuru-san (natsuru-san@mail.com)
//Created 16.04.2023

package karelia.natsuru.strafesstaminatraining;

public class RoundTimer extends Thread implements Runnable {

    private final MainActivity main;
    private boolean ran = false;

    public RoundTimer(MainActivity main) {
        this.main = main;
    }

    @SuppressWarnings("BusyWait")
    @Override
    public void run() {
        ran = true;
        while (ran) {
            main.send(-1);
            try {
                sleep(10L);
            } catch (InterruptedException ignored) {}
        }
        main.send(-2);
    }

    @Override
    public void interrupt() {
        ran = false;
        super.interrupt();
    }
}