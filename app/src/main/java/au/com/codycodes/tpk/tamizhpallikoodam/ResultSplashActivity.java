package au.com.codycodes.tpk.tamizhpallikoodam;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class ResultSplashActivity extends AppCompatActivity {

    private final int SPLASH_DISPLAY_LENGTH = 3000;
    int Score = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent myIntent = getIntent();
        Score = myIntent.getIntExtra("score", 0);
        String correct = myIntent.getStringExtra("correct");
        if(correct.matches("CORRECT"))
        {
            Score += 1;
            Log.d("Answer", "Correct");
            Log.d("Score", Integer.toString(Score));
        }else
        {
            Log.d("Answer", "incorect");
            Log.d("Score", Integer.toString(Score));
        }


        Toast.makeText(getApplicationContext(), "Answer: " + correct, Toast.LENGTH_LONG).show();
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(ResultSplashActivity.this, QuestionActivity.class);
                mainIntent.putExtra("score", Score);
                ResultSplashActivity.this.startActivity(mainIntent);
                ResultSplashActivity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
