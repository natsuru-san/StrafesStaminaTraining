//Author - Natsuru-san (natsuru-san@mail.com)
//Created 05.04.2023

package karelia.natsuru.strafesstaminatraining.repository;

import android.content.Context;
import android.database.Cursor;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import karelia.natsuru.strafesstaminatraining.entity.Score;
import karelia.natsuru.strafesstaminatraining.utils.Database;

public class DbDaoImpl implements DbDao {

    private static final int maxScores = 10;
    private final Context context;

    public DbDaoImpl(Context context) {
        this.context = context;
    }

    @Override
    public void insertScore(Score score) {
        List<Score> scores = getAllScores();
        Score minScore = null;
        if (scores.size() >= maxScores) {
            minScore = scores.stream().min(Comparator.comparingInt(Score::getNumSuccessPress)).get();
        }
        try (Database db = new Database(context)) {
            if (minScore != null) {
                long minId = minScore.getId();
                String delete = "DELETE FROM "
                        + Database.scoreName
                        + " WHERE _id="
                        + minId + ";";
                db.getWritableDatabase().execSQL(delete);
                score = new Score(minId, score.getRaceInterval(), score.getNumFailPress(), score.getNumSuccessPress());
            }
            String query = "INSERT INTO "
                    + Database.scoreName
                    + " VALUES(" + score.getId() + ", "
                    + score.getRaceInterval() + ", "
                    + score.getNumFailPress() + ", "
                    + score.getNumSuccessPress() + ", "
                    + score.getDateTime() + ");";
            db.getWritableDatabase().execSQL(query);
        }
    }

    @Override
    public Score getScore(long id) {
        Score score;
        try (Database db = new Database(context)) {
            String query = "SELECT * FROM "
                    + Database.scoreName
                    + " WHERE _id="
                    + id + ";";
            Cursor cursor = db.getReadableDatabase().rawQuery(query, null);
            cursor.moveToFirst();
            score = new Score(cursor.getLong(0), cursor.getLong(1), cursor.getInt(2), cursor.getInt(3));
            score.setDateTime(LocalDateTime.parse(cursor.getString(4)));
            cursor.close();
        }
        return score;
    }

    @Override
    public List<Score> getAllScores() {
        List<Score> scores;
        try (Database db = new Database(context)) {
            String query = "SELECT * FROM "
                    + Database.scoreName + ";";
            Cursor cursor = db.getReadableDatabase().rawQuery(query, null);
            if (cursor.getCount() == 0) return null;
            scores = new ArrayList<>(15);
            while (cursor.moveToNext()) {
                Score score = new Score(cursor.getLong(0), cursor.getLong(1), cursor.getInt(2), cursor.getInt(3));
                score.setDateTime(LocalDateTime.parse(cursor.getString(4)));
                scores.add(score);
            }
            cursor.close();
        }
        return scores;
    }

    @Override
    public long getRaceTime() {
        try (Database db = new Database(context)) {
            String query = "SELECT * FROM "
                    + Database.settingsName
                    + " WHERE _id=1;";
            Cursor cursor = db.getReadableDatabase().rawQuery(query, null);
            cursor.moveToNext();
            long raceTime = cursor.getLong(1);
            cursor.close();
            return raceTime;
        }
    }

    @Override
    public void setRaceTime(long raceTime) {
        try (Database db = new Database(context)) {
            String query = "UPDATE " + Database.settingsName + " SET param=" + raceTime + " WHERE _id=1;";
            db.getWritableDatabase().execSQL(query);
        }
    }
}