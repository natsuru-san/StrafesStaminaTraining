//Author - Natsuru-san (natsuru-san@mail.com)
//Created 05.04.2023

package karelia.natsuru.strafesstaminatraining.repository;

import java.util.List;
import karelia.natsuru.strafesstaminatraining.entity.Score;

public interface DbDao {
    void insertScore(Score score);
    List<Score> getAllScores();
    long getRaceTime();
    void setRaceTime(long raceTime);
}