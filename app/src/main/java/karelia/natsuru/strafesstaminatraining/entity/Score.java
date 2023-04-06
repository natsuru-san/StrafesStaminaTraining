//Author - Natsuru-san (natsuru-san@mail.com)
//Created 05.04.2023

package karelia.natsuru.strafesstaminatraining.entity;

import androidx.annotation.NonNull;
import java.time.LocalDateTime;

public class Score {
    private final long id; //Id of this entity
    private final long raceInterval; //Interval in milliseconds for press training
    private final int numFailPress; //Count of wrong a button's pressing
    private final int numSuccessPress; //Count of right a button's pressing
    private LocalDateTime dateTime; //Current time of this score

    public Score(long id, long raceInterval, int numFailPress, int numSuccessPress) {
        this.id = id;
        this.raceInterval = raceInterval;
        this.numFailPress = numFailPress;
        this.numSuccessPress = numSuccessPress;
        this.dateTime = LocalDateTime.now();
    }

    public long getId() {
        return id;
    }

    public long getRaceInterval() {
        return raceInterval;
    }

    public int getNumFailPress() {
        return numFailPress;
    }

    public int getNumSuccessPress() {
        return numSuccessPress;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @NonNull
    @Override
    public String toString() {
        return numSuccessPress + " | "
                + numFailPress + " | "
                + raceInterval + " | "
                + dateTime;
    }
}