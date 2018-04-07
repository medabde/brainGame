package com.example.medabde.braingame;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class MainActivity extends AppCompatActivity {
    TextView equation;
    TextView score;
    TextView counter;
    TextView textView1;
    TextView textView2;
    TextView textView3;
    TextView textView4;
    GridLayout textView;
    Button button;
    TextView[] answers;
    int random,random2;
    boolean isPlaying = true;
    int scr=0;
    int playing=0;
    CountDownTimer countDownTimer = new CountDownTimer(30*1000+100,1000) {
        @Override
        public void onTick(long l) {
            if(l/1000<10){
                counter.setText("0"+l/1000+"s");
            }else {
                counter.setText(l/1000+"s");
            }
        }

        @Override
        public void onFinish() {
            counter.setText("00s");
            countDownTimer.cancel();
            textView.setClickable(false);
            isPlaying = true;
            button.setText("start");
            equation.setText("00 + 00");
            score.setText("0/0");
            answers[0].setText("00");
            answers[1].setText("00");
            answers[2].setText("00");
            answers[3].setText("00");
            scr=0;
            playing=0;
            for(int i = 0;i<=3;i++) {
                answers[i].setClickable(false);
            }

        }
    };

    public void check(View view){
        TextView ans = (TextView) view;

        int answer = Integer.parseInt(ans.getText().toString());
        if(answer == random+random2){
            scr++;
            playing++;
            score.setText(scr+"/"+playing);
            Toast.makeText(getApplicationContext(), "true", Toast.LENGTH_SHORT).show();
            setEquation();

        }else {
            playing++;
            score.setText(scr+"/"+playing);
            Toast.makeText(getApplicationContext(), "false", Toast.LENGTH_SHORT).show();
            setEquation();
        }

    }
    public void setEquation(){

        random = ThreadLocalRandom.current().nextInt(0, 500);
        random2 = ThreadLocalRandom.current().nextInt(0, 500);
        int plc =ThreadLocalRandom.current().nextInt(0, 4);
        int sum = random+random2;
        equation.setText(random+" + "+random2);
        answers[0].setText(""+ThreadLocalRandom.current().nextInt(0, 1000));
        answers[1].setText(""+ThreadLocalRandom.current().nextInt(0, 1000));
        answers[2].setText(""+ThreadLocalRandom.current().nextInt(0, 1000));
        answers[3].setText(""+ThreadLocalRandom.current().nextInt(0, 1000));
        answers[plc].setText(sum+"");
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        equation = findViewById(R.id.equation);
        score = findViewById(R.id.score);
        counter = findViewById(R.id.count);
        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);
        textView4 = findViewById(R.id.textView4);
        textView = findViewById(R.id.gridLayout);
        button = findViewById(R.id.button);
        answers = new TextView[]{textView1, textView2, textView3, textView4};

        for(int i = 0;i<=3;i++){
            answers[i].setClickable(false);
        }


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isPlaying){
                    textView.setClickable(true);
                    countDownTimer.start();
                    setEquation();
                    isPlaying = false;
                    button.setText("stop");
                    for(int i = 0;i<=3;i++){
                        answers[i].setClickable(true);
                    }
                }else {
                    countDownTimer.cancel();
                    textView.setClickable(false);
                    isPlaying = true;
                    button.setText("start");
                    equation.setText("00 + 00");
                    answers[0].setText("00");
                    answers[1].setText("00");
                    answers[2].setText("00");
                    answers[3].setText("00");
                    scr=0;
                    playing=0;
                    score.setText("0/0");
                    counter.setText("00s" +
                            "");
                    for(int i = 0;i<=3;i++){
                        answers[i].setClickable(false);
                    }
                }

            }
        });


    }
}
