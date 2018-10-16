package au.com.codycodes.tpk.tamizhpallikoodam;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;
import android.view.View;
import android.widget.Toast;
import android.widget.RadioGroup;
import android.widget.RadioButton;
import android.media.MediaPlayer;

public class QuestionActivity extends AppCompatActivity {

    private String correct;
    private int Score = 0;

    static int PREFERENCE_MODE_PRIVATE = 0;
    private SharedPreferences topscore;
    private SharedPreferences.Editor topscoreEditior;
    private int TopScore = 0;
    public static final String MyPREFERENCES = "myprefs";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        topscore = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        DatabaseHelper db = new DatabaseHelper(this);
        GetTopScore();
        final Quiz quiz = db.getQuiz();
        Intent myIntent = getIntent();
        Score = myIntent.getIntExtra("score", 0);
        CheckTopScore();

        TextView category = findViewById(R.id.category);
        category.setText(quiz.getCategory());
        TextView score = findViewById(R.id.scoreView);
        score.setText(Integer.toString(Score));
        TextView Highscore = findViewById(R.id.HighScoreView);
        Highscore.setText(Integer.toString(TopScore));
        TextView question = findViewById(R.id.question);
        question.setText(quiz.getTamilTranslation());
        TextView option1 = findViewById(R.id.option1);
        option1.setText(quiz.getOption1());
        TextView option2 = findViewById(R.id.option2);
        option2.setText(quiz.getOption2());
        TextView option3 = findViewById(R.id.option3);
        option3.setText(quiz.getOption3());
        TextView option4 = findViewById(R.id.option4);
        option4.setText(quiz.getOption4());
        Log.d("Answer:", quiz.getAnswer());
        final Button submit = findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                RadioGroup rg = findViewById(R.id.options);
                if(rg.getCheckedRadioButtonId() != -1) {
                    String selectedOption = ((RadioButton)findViewById(rg.getCheckedRadioButtonId())).getText().toString();
                    if (selectedOption.equals(quiz.getAnswer())) {
                        correct = "CORRECT";
                    } else {
                        correct = "INCORRECT";
                    }
                    // Create a new intent to open the {@link LearnActivity}
                    Intent resultIntent = new Intent(QuestionActivity.this, ResultSplashActivity.class);
                    resultIntent.putExtra("correct", correct);
                    resultIntent.putExtra("score", Score);

                    // Start the new activity
                    startActivity(resultIntent);
                }else{
                    Toast.makeText(getApplicationContext(), "Please make a selection", Toast.LENGTH_LONG).show();
                }
            }
        });

        final ImageView play =  findViewById(R.id.play);
        final MediaPlayer mp = MediaPlayer.create(this, quiz.getAudioResourceId());

        play.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mp.start();
            }
        });
    }

    private void CheckTopScore()
    {
        if (Score > TopScore)
        {
            topscoreEditior = topscore.edit();
            TopScore = Score;
            topscoreEditior.putInt("Score", TopScore);
            topscoreEditior.apply();
        }
    }

    private void GetTopScore()
    {
        topscore = getPreferences(PREFERENCE_MODE_PRIVATE);
        TopScore = topscore.getInt("Score", TopScore);
    }
}
