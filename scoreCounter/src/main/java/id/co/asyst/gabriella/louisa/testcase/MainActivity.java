package id.co.asyst.gabriella.louisa.testcase;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import id.co.asyst.gabriella.louisa.testcase.utility.Constant;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etTeamA, etTeamB;
    Button bStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTeamA = findViewById(R.id.editTextTeamA);
        etTeamB = findViewById(R.id.editTextTeamB);
        bStart = findViewById(R.id.buttonStart);

        bStart.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonStart:
                if (validate()) {
                    Intent intent = new Intent(this, MatchActivity.class);
                    intent.putExtra(Constant.KEY_TEAM_A_Name, etTeamA.getText().toString());
                    intent.putExtra(Constant.KEY_TEAM_B_Name, etTeamB.getText().toString());
                    startActivity(intent);
                }
                break;
        }
    }

    public boolean validate() {
        if (TextUtils.isEmpty(etTeamA.getText().toString()) || TextUtils.isEmpty(etTeamB.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Nama Team Tidak Boleh Kosong", Toast.LENGTH_LONG).show();
            return false;
        }
        if (etTeamA.getText().toString().equalsIgnoreCase(etTeamB.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Nama Team Harus Berbeda", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
