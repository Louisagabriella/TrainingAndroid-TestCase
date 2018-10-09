package id.co.asyst.gabriella.louisa.testcase;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import id.co.asyst.gabriella.louisa.testcase.utility.Constant;

public class ResultActivity extends AppCompatActivity implements View.OnClickListener {
    TextView winnerTeamName;
    TextView winnerScoreTeame;
    Button bBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        winnerTeamName = findViewById(R.id.textViewNameWinnerTeam);
        winnerScoreTeame = findViewById(R.id.textViewScoreWinnerTeam);
        bBack = findViewById(R.id.buttonBack);
        bBack.setOnClickListener(this);
        if (getIntent().getExtras() != null) {
            Bundle bundle = getIntent().getExtras();
            winnerTeamName.setText(bundle.getString(Constant.KEY_RESULT_WINNER_NAME));
            winnerScoreTeame.setText(bundle.getString(Constant.KEY_RESULT_WINNER_SCORE));
        } else {
            winnerTeamName.setText("TIDAK ADA NAMA TEAM");
            winnerScoreTeame.setText("TIDAK ADA NAMA TEAM");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonBack:
                toHome();
                break;
        }
    }

    public void toHome() {
        Intent intent = new Intent(ResultActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
