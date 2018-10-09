package id.co.asyst.gabriella.louisa.testcase;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import id.co.asyst.gabriella.louisa.testcase.fragment.MatchFragment;
import id.co.asyst.gabriella.louisa.testcase.utility.Constant;

public class MatchActivity extends AppCompatActivity implements MatchFragment.OnButtonNextClickListener {
    String teamAName, teamBName;
    int quarter = 1, scoreTeamA = 0, scoreTeamB = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);


        if (getIntent().getExtras() != null) {
            teamAName = getIntent().getExtras().getString(Constant.KEY_TEAM_A_Name);
            teamBName = getIntent().getExtras().getString(Constant.KEY_TEAM_B_Name);
        }

        loadFragment();
    }

    @Override
    public void onButtonNextClicked(int scoreTeamA, int scoreTeamB) {
        this.scoreTeamA = scoreTeamA;
        this.scoreTeamB = scoreTeamB;
        ++quarter;
        if (quarter <= 4) {
            loadFragment();
        } else {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Confirmation").setCancelable(false).setMessage("Are You Finish ?").setPositiveButton("YES", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    int winnerScore = 0;
                    String winnerName;
                    if (MatchActivity.this.scoreTeamA < MatchActivity.this.scoreTeamB) {
                        winnerScore = MatchActivity.this.scoreTeamB;
                        winnerName = teamBName;
                    } else if (MatchActivity.this.scoreTeamA > MatchActivity.this.scoreTeamB) {
                        winnerScore = MatchActivity.this.scoreTeamA;
                        winnerName = teamAName;
                    } else {
                        winnerName = "DRAW";
                    }
                    Intent intent = new Intent(MatchActivity.this, ResultActivity.class);
                    String resultWinnerName = winnerName;
                    String resultWinnerScore = winnerScore + "";
                    intent.putExtra(Constant.KEY_RESULT_WINNER_SCORE, resultWinnerScore);
                    intent.putExtra(Constant.KEY_RESULT_WINNER_NAME, resultWinnerName);
                    startActivity(intent);
                    finish();
                }
            }).setNegativeButton("No", null).show();
        }
    }

    private void loadFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        MatchFragment matchFragment = MatchFragment.newInstance(teamAName, teamBName, quarter, scoreTeamA, scoreTeamB);
        transaction.replace(R.id.frameLayoutFragmentContainer, matchFragment);
        transaction.commit();
    }
}
