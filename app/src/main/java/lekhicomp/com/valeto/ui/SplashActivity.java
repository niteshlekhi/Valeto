package lekhicomp.com.valeto.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.multidex.MultiDex;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import lekhicomp.com.valeto.R;

public class SplashActivity extends Activity {
TextView txtTitle;
Animation anim;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 101) {
                Intent intent=new Intent(SplashActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        txtTitle=findViewById(R.id.splashTitle);
        anim = AnimationUtils.loadAnimation(this,
                R.anim.fade_in);

        txtTitle.setVisibility(View.VISIBLE);

        // start the animation
        txtTitle.startAnimation(anim);

        handler.sendEmptyMessageDelayed(101, 3000);

    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
