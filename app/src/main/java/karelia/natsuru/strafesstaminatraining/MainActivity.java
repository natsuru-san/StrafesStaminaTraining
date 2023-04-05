package karelia.natsuru.strafesstaminatraining;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
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
    private ConstraintLayout keyPad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        setContentView(R.layout.activity_main);
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