package id.co.asyst.gabriella.louisa.testcase.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import id.co.asyst.gabriella.louisa.testcase.R;
import id.co.asyst.gabriella.louisa.testcase.utility.Constant;


public class MatchFragment extends Fragment implements View.OnClickListener {
    TextView tvTeamAName, tvTeamBName, tvQuarter, tvScoreTeamA, tvScoreTeamB;
    Button bPlus1TeamA, bPlus2TeamA, bPlus3TeamA, bPlus1TeamB, bPlus2TeamB, bPlus3TeamB, bNext;
    int scoreTeamA, scoreTeamB;
    private String teamAName, teamBName;
    private int quarter;
    private OnButtonNextClickListener mListener;

    public MatchFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static MatchFragment newInstance(String teamA, String teamB, int quarter, int scoreTeamA, int scoreTeamB) {
        MatchFragment fragment = new MatchFragment();
        Bundle args = new Bundle();
        args.putString(Constant.KEY_TEAM_A_Name, teamA);
        args.putString(Constant.KEY_TEAM_B_Name, teamB);
        args.putInt(Constant.KEY_QUARTER, quarter);
        args.putInt(Constant.KEY_SCORE_TEAM_A, scoreTeamA);
        args.putInt(Constant.KEY_SCORE_TEAM_B, scoreTeamB);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            teamAName = getArguments().getString(Constant.KEY_TEAM_A_Name);
            teamBName = getArguments().getString(Constant.KEY_TEAM_B_Name);
            quarter = getArguments().getInt(Constant.KEY_QUARTER);
            scoreTeamA = getArguments().getInt(Constant.KEY_SCORE_TEAM_A);
            scoreTeamB = getArguments().getInt(Constant.KEY_SCORE_TEAM_B);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_match, container, false);

        tvTeamAName = view.findViewById(R.id.textViewNameTeamA);
        tvTeamBName = view.findViewById(R.id.textViewNameTeamB);
        tvScoreTeamA = view.findViewById(R.id.textViewScoreTeamA);
        tvScoreTeamB = view.findViewById(R.id.textViewScoreTeamB);
        tvQuarter = view.findViewById(R.id.textViewQuarter);

        bPlus1TeamA = view.findViewById(R.id.buttonPlus1TeamA);
        bPlus2TeamA = view.findViewById(R.id.buttonPlus2TeamA);
        bPlus3TeamA = view.findViewById(R.id.buttonPlus3TeamA);
        bPlus1TeamB = view.findViewById(R.id.buttonPlus1TeamB);
        bPlus2TeamB = view.findViewById(R.id.buttonPlus2TeamB);
        bPlus3TeamB = view.findViewById(R.id.buttonPlus3TeamB);
        bNext = view.findViewById(R.id.buttonNext);

        bPlus1TeamA.setOnClickListener(this);
        bPlus2TeamA.setOnClickListener(this);
        bPlus3TeamA.setOnClickListener(this);
        bPlus1TeamB.setOnClickListener(this);
        bPlus2TeamB.setOnClickListener(this);
        bPlus3TeamB.setOnClickListener(this);
        bNext.setOnClickListener(this);
        switch (quarter) {
            case 1:
                tvQuarter.setText("1st");
                break;
            case 2:
                tvQuarter.setText("2nd");
                break;
            case 3:
                tvQuarter.setText("3rd");
                break;
            case 4:
                tvQuarter.setText("4th");
                break;
        }
        tvTeamAName.setText(teamAName);
        tvTeamBName.setText(teamBName);
        tvScoreTeamA.setText(scoreTeamA + "");
        tvScoreTeamB.setText(scoreTeamB + "");
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnButtonNextClickListener) {
            mListener = (OnButtonNextClickListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnButtonNextClickListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonPlus1TeamA:
                scoreTeamA += 1;
                break;
            case R.id.buttonPlus2TeamA:
                scoreTeamA += 2;
                break;
            case R.id.buttonPlus3TeamA:
                scoreTeamA += 3;
                break;
            case R.id.buttonPlus1TeamB:
                scoreTeamB += 1;
                break;
            case R.id.buttonPlus2TeamB:
                scoreTeamB += 2;
                break;
            case R.id.buttonPlus3TeamB:
                scoreTeamB += 3;
                break;
            case R.id.buttonNext:
                mListener.onButtonNextClicked(scoreTeamA, scoreTeamB);
                getActivity().getSupportFragmentManager().popBackStack();
                break;
        }
        tvScoreTeamA.setText(scoreTeamA + "");
        tvScoreTeamB.setText(scoreTeamB + "");
    }


    public interface OnButtonNextClickListener {
        // TODO: Update argument type and name
        void onButtonNextClicked(int scoreTeamA, int scoreTeamB);
    }
}
