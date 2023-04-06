package karelia.natsuru.strafesstaminatraining;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import karelia.natsuru.strafesstaminatraining.entity.Score;
import karelia.natsuru.strafesstaminatraining.repository.DbDao;
import karelia.natsuru.strafesstaminatraining.repository.DbDaoImpl;

public class MainActivity extends AppCompatActivity {

    private TextView timer;
    private ImageButton start;
    private ImageButton score;
    private ImageButton options;
    private Button wBtn;
    private Button aBtn;
    private Button sBtn;
    private Button dBtn;
    private ConstraintLayout keyPad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        test();
        init();
        setContentView(R.layout.activity_main);
    }

    private void test() {
        DbDao db = new DbDaoImpl(this);
        db.insertScore(new Score(-1, 666, 5, 2));
        db.insertScore(new Score(-1, 777, 1, 22));
        db.insertScore(new Score(-1, 66, 2, 21));
        db.insertScore(new Score(-1, 245, 3, 25));
        db.insertScore(new Score(-1, 74382, 4, 211));
        db.insertScore(new Score(-1, 23664, 6, 200));
        db.insertScore(new Score(-1, 883, 7, 20));
        db.insertScore(new Score(-1, 1223, 8, 12));
        db.insertScore(new Score(-1, 987, 9, 72));
        db.insertScore(new Score(-1, 111, 10, 85));
        db.insertScore(new Score(-1, 888, 11, 99));
        db.insertScore(new Score(-1, 888, 11, 1234));

        System.out.println(db.getScore(4));
        System.out.println("\n");
        System.out.println(db.getAllScores());

        db.setRaceTime(10000);
        System.out.println(db.getRaceTime());
    }

    private void init() {
        timer = findViewById(R.id.Timer);
        start = findViewById(R.id.StartButton);
        score = findViewById(R.id.ScoreButton);
        options = findViewById(R.id.OptionsButton);
        wBtn = findViewById(R.id.WButton);
        aBtn = findViewById(R.id.AButton);
        sBtn = findViewById(R.id.SButton);
        dBtn = findViewById(R.id.DButton);
        keyPad = findViewById(R.id.KeypadLayout);
    }
}