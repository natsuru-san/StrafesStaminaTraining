package karelia.natsuru.strafesstaminatraining;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView timer;
    private ImageButton start;
    private ImageButton score;
    private ImageButton options;
    private Button wBtn;
    private Button aBtn;
    private Button sBtn;
    private Button dBtn;
    private ConstraintLayout keyPadLayout;
    private ConstraintLayout scoreLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
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
        keyPadLayout = findViewById(R.id.KeypadLayout);
        scoreLayout = findViewById(R.id.ScoreLayout);

        keyPadLayout.setVisibility(View.VISIBLE);
        start.setOnClickListener(v -> newGame());
        score.setOnClickListener(v -> openScores());
    }

    private void openScores() {
        scoreLayout.setVisibility(View.VISIBLE);
        keyPadLayout.setVisibility(View.INVISIBLE);
    }

    private void newGame() {
        scoreLayout.setVisibility(View.INVISIBLE);
        keyPadLayout.setVisibility(View.VISIBLE);
    }
}