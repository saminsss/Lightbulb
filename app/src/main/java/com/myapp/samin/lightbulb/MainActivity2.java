package com.myapp.samin.lightbulb;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.TransitionDrawable;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity2 extends Activity {

    ImageView image;

    Button button;

    boolean turnedOn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        image = (ImageView) findViewById(R.id.imageView2);
        button = (Button) findViewById(R.id.button2);

        broadcastSender();
    }

    public void onLight(View v){
        Runnable run = new Runnable() {
            @Override
            public void run() {
                if(!turnedOn) {
                    handlerOn.sendEmptyMessage(0);
                    turnedOn = true;
                }
                else{
                    handlerOff.sendEmptyMessage(0);
                    turnedOn = false;
                }
            }
        };
        Thread light = new Thread(run);
        light.start();
    }

    Handler handlerOn = new Handler(){
        @Override
        public void handleMessage(Message msg){
            image.setImageResource(R.drawable.light_transition_on);
            int transitionTime = 300;
            ((TransitionDrawable) image.getDrawable()).startTransition(transitionTime);
            ObjectAnimator backgroundColorAnimator = ObjectAnimator.ofObject(findViewById(R.id.layout2),
                    "backgroundColor",
                    new ArgbEvaluator(),
                    0xFF000000,
                    0xFF140E10);
            backgroundColorAnimator.setDuration(transitionTime);
            backgroundColorAnimator.start();
        }
    };

    Handler handlerOff = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            image.setImageResource(R.drawable.light_transition_off);
            int transitionTime = 800;
            ((TransitionDrawable) image.getDrawable()).startTransition(transitionTime);
            ObjectAnimator backgroundColorAnimator = ObjectAnimator.ofObject(findViewById(R.id.layout2),
                    "backgroundColor",
                    new ArgbEvaluator(),
                    0xFF140E10,
                    0xFF000000);
            backgroundColorAnimator.setDuration(transitionTime);
            backgroundColorAnimator.start();
        }
    };

    public void changeActivity(View v){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    public void broadcastSender(){
        Intent i = new Intent();
        i.setAction("com.myapp.samin.lightbulb");
        i.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        sendBroadcast(i);
    }
}
