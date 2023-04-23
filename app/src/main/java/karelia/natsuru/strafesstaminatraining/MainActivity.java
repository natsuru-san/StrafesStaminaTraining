package karelia.natsuru.strafesstaminatraining;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import java.util.List;
import karelia.natsuru.strafesstaminatraining.entity.Score;
import karelia.natsuru.strafesstaminatraining.repository.DbDao;
import karelia.natsuru.strafesstaminatraining.repository.DbDaoImpl;

public class MainActivity extends AppCompatActivity {

    private TextView timerView;
    private TextView scoreView;
    private ImageButton start;
    private ImageButton score;
    private ImageButton options;
    private Button wBtn;
    private Button aBtn;
    private Button sBtn;
    private Button dBtn;
    private Button startBtn;
    private ConstraintLayout keyPadLayout;
    private ConstraintLayout scoreLayout;
    private ConstraintLayout optionsLayout;
    private Handler handler;
    private Thread timer;
    private Game game;
    private DbDao db;
    private EditText roundValueInput;
    @SuppressWarnings("FieldCanBeLocal")
    private Button apply;
    public long initTime;
    public long roundTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createHandler();
        db = new DbDaoImpl(this);
        roundTime = db.getRaceTime();
        init();
    }

    private void createHandler() {
        handler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(@NonNull Message msg) {
                switch (msg.what) {
                    case -2:
                        stopRound();
                        break;
                    case -1:
                        updateTimer();
                        break;
                    case 1:
                        wBtn.setBackgroundColor(getColor(R.color.white));
                        break;
                    case 2:
                        wBtn.setBackgroundColor(getColor(R.color.tp));
                        break;
                    case 3:
                        aBtn.setBackgroundColor(getColor(R.color.white));
                        break;
                    case 4:
                        aBtn.setBackgroundColor(getColor(R.color.tp));
                        break;
                    case 5:
                        sBtn.setBackgroundColor(getColor(R.color.white));
                        break;
                    case 6:
                        sBtn.setBackgroundColor(getColor(R.color.tp));
                        break;
                    case 7:
                        dBtn.setBackgroundColor(getColor(R.color.white));
                        break;
                    case 8:
                        dBtn.setBackgroundColor(getColor(R.color.tp));
                        break;
                    default:
                        throw new RuntimeException("Invalid sent code: " + msg.what);
                }
            }
        };
    }

    private void openOptions() {
        scoreLayout.setVisibility(View.INVISIBLE);
        keyPadLayout.setVisibility(View.INVISIBLE);
        optionsLayout.setVisibility(View.VISIBLE);
    }

    public long getRoundTime() {
        return roundTime;
    }

    @SuppressLint("SetTextI18n")
    private void updateTimer() {
        long endTime = System.currentTimeMillis();
        long period = endTime - initTime;
        timerView.setText(((double) period / 1000) + "/" + ((double) roundTime / 1000));
        if (period >= roundTime) {
            timer.interrupt();
            stopRound();
        }
    }

    private void init() {
        timerView = findViewById(R.id.Timer);
        start = findViewById(R.id.StartButton);
        score = findViewById(R.id.ScoreButton);
        options = findViewById(R.id.OptionsButton);
        wBtn = findViewById(R.id.WButton);
        aBtn = findViewById(R.id.AButton);
        sBtn = findViewById(R.id.SButton);
        dBtn = findViewById(R.id.DButton);
        scoreView = findViewById(R.id.ScoreView);
        roundValueInput = findViewById(R.id.RoundValueInput);
        apply = findViewById(R.id.ApplyBtn);

        wBtn.setClickable(false);
        aBtn.setClickable(false);
        sBtn.setClickable(false);
        dBtn.setClickable(false);

        startBtn = findViewById(R.id.StartBtn);
        keyPadLayout = findViewById(R.id.KeypadLayout);
        scoreLayout = findViewById(R.id.ScoreLayout);
        optionsLayout = findViewById(R.id.OptionsLayout);

        keyPadLayout.setVisibility(View.VISIBLE);
        start.setOnClickListener(v -> newGame());
        score.setOnClickListener(v -> openScores());
        options.setOnClickListener(v -> openOptions());
        startBtn.setOnClickListener(v -> startTimer());
        apply.setOnClickListener(v -> saveRoundTime());

        timerView.setText(getString(R.string.timer_value));
    }

    private void saveRoundTime() {
        String result = roundValueInput.getEditableText().toString();
        long millis = Long.parseLong(result);
        if (millis > 0) {
            roundTime = millis;
            db.setRaceTime(millis);
        } else {
            throw new RuntimeException("WRONG VALUE!!!");
        }
    }

    @SuppressLint("SetTextI18n")
    private void openScores() {
        scoreLayout.setVisibility(View.VISIBLE);
        keyPadLayout.setVisibility(View.INVISIBLE);
        optionsLayout.setVisibility(View.INVISIBLE);
        List<Score> scores = db.getAllScores();
        StringBuilder allScores = new StringBuilder();
        for (Score s : scores) {
            allScores.append(s.toString()).append("\n");
        }
        scoreView.setText(allScores.toString());
    }

    private void newGame() {
        scoreLayout.setVisibility(View.INVISIBLE);
        keyPadLayout.setVisibility(View.VISIBLE);
        optionsLayout.setVisibility(View.INVISIBLE);
    }

    private void stopTimer() {
        wBtn.setClickable(false);
        aBtn.setClickable(false);
        sBtn.setClickable(false);
        dBtn.setClickable(false);
        timer.interrupt();
        game.stopGame();
        startBtn.setText(getString(R.string.start));
        startBtn.setOnClickListener(v -> startTimer());
        options.setClickable(true);
        score.setClickable(true);
        start.setClickable(true);
        timerView.setText(getString(R.string.timer_value));
        System.gc();
    }

    private void stopRound() {
        stopTimer();
        db.insertScore(game.getScore());
        System.gc();
    }

    private void startTimer() {
        game = new Game(this);
        wBtn.setOnClickListener(v -> game.pressedButton(1));
        aBtn.setOnClickListener(v -> game.pressedButton(3));
        sBtn.setOnClickListener(v -> game.pressedButton(5));
        dBtn.setOnClickListener(v -> game.pressedButton(7));
        wBtn.setClickable(true);
        aBtn.setClickable(true);
        sBtn.setClickable(true);
        dBtn.setClickable(true);
        initTime = System.currentTimeMillis();
        startBtn.setText(getString(R.string.stop));
        startBtn.setOnClickListener(v -> stopTimer());
        options.setClickable(false);
        score.setClickable(false);
        start.setClickable(false);
        timer = new RoundTimer(this);
        timer.start();
    }

    public void send(int code) {
        Message msg = new Message();
        msg.what = code;
        handler.sendMessage(msg);
    }
}