package com.me.eva.helloworldapplication.activity;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.me.eva.helloworldapplication.R;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ArgbEvaluator;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.animation.ValueAnimator;

import java.util.Random;


public class AnimationActivity extends ActionBarActivity implements View.OnClickListener{

    private Button btnAnim;
    private Button btnMenu, btnSubMenu1, btnSubMenu2, btnSubMenu3, btnSubMenu4, btnSubMenu5, btnSubMenu6;
    private ImageView iv;
    private final String TAG = "AnimationActivity";
    private boolean isShown = false;
    private boolean animatFinish = true;

    private final long showAnimaSpan = 700;
    private final long showAnimaDelaySpan = 70;
    private final float radius = 500f;
    private final int rotateCircle = 3;
    private final int wholeCircle = 360;
    private final int totalNum = 6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);

        init();
    }

    private void init() {
        btnAnim = (Button) findViewById(R.id.btnAnim);
        btnAnim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator valueAnimator = ObjectAnimator.ofInt(v, "backgroundColor", /*Red*/0xFFFF0000, /*Green*/0xFF00FF00,/*Blue*/0xFF0000FF);

//                valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator(AnimationActivity.this, ));
                valueAnimator.setEvaluator(new ArgbEvaluator());
                valueAnimator.setDuration(3000);
                valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
                valueAnimator.setRepeatMode(ValueAnimator.REVERSE);

                valueAnimator.start();

                ObjectAnimator scaleXAnim = ObjectAnimator.ofFloat(v, "scaleX", 1f, 1.5f, 1f);
                ObjectAnimator scaleYAnim = ObjectAnimator.ofFloat(v, "scaleY", 1f, 1.5f, 1f);

                scaleXAnim.setInterpolator(new AccelerateDecelerateInterpolator());
                scaleYAnim.setInterpolator(new AccelerateDecelerateInterpolator());

                scaleXAnim.setRepeatCount(Animation.INFINITE);
                scaleXAnim.setRepeatMode(Animation.RESTART);

                scaleYAnim.setRepeatCount(Animation.INFINITE);
                scaleYAnim.setRepeatMode(Animation.RESTART);

                scaleXAnim.setDuration(1000).start();
                scaleYAnim.setDuration(1000).start();

            }
        });

        btnMenu = (Button) findViewById(R.id.btnMenu);
        btnSubMenu1 = (Button) findViewById(R.id.btn1);
        btnSubMenu2 = (Button) findViewById(R.id.btn2);
        btnSubMenu3 = (Button) findViewById(R.id.btn3);
        btnSubMenu4 = (Button) findViewById(R.id.btn4);
        btnSubMenu5 = (Button) findViewById(R.id.btn5);
        btnSubMenu6 = (Button) findViewById(R.id.btn6);

        btnMenu.setOnClickListener(this);
        btnSubMenu1.setOnClickListener(this);
        btnSubMenu2.setOnClickListener(this);
        btnSubMenu3.setOnClickListener(this);
        btnSubMenu4.setOnClickListener(this);
        btnSubMenu5.setOnClickListener(this);
        btnSubMenu6.setOnClickListener(this);

        iv = (ImageView) findViewById(R.id.imageView);
    }

    @Override
    public void onClick(View v) {
        if (v.equals(btnMenu)) {
            if(!isShown && animatFinish) {
                doShowAniamtion(btnSubMenu1, 0, totalNum, 0, 0);
                doShowAniamtion(btnSubMenu2, 1, totalNum, 0, 0);
                doShowAniamtion(btnSubMenu3, 2, totalNum, 0, 0);
                doShowAniamtion(btnSubMenu4, 3, totalNum, 0, 0);
                doShowAniamtion(btnSubMenu5, 4, totalNum, 0, 0);
                doShowAniamtion(btnSubMenu6, 5, totalNum, 0, 0);
                isShown = true;
                log("Start Shown : "+isShown +animatFinish);
            }else if (isShown && animatFinish){
                doCloseAniamtion(btnSubMenu6, 5, totalNum, 0, 0);//????????????? ???????start???????delay?????
                doCloseAniamtion(btnSubMenu5, 4, totalNum, 0, 0);//????????????? ???????start???????delay?????
                doCloseAniamtion(btnSubMenu4, 3, totalNum, 0, 0);
                doCloseAniamtion(btnSubMenu3, 2, totalNum, 0, 0);
                doCloseAniamtion(btnSubMenu2, 1, totalNum, 0, 0);
                doCloseAniamtion(btnSubMenu1, 0, totalNum, 0, 0);
                isShown = false;
                log("Start Close : "+isShown +animatFinish);
            }

        }else {
            log("You are click button : " + v);
            Toast.makeText(AnimationActivity.this,"You are click button : "+v,Toast.LENGTH_SHORT).show();

            //randomly jump to TransitionTestActivity
            Random random = new Random();
            int randNum = random.nextInt(totalNum);
            if (3 == randNum) {
                Intent jmpAnimActToTransiAct = new Intent(AnimationActivity.this,TransitionTestActivity.class);
                startActivity(jmpAnimActToTransiAct);
                return;
            }


            do3DRotationAttamp(iv, 5000, 4);
        }
    }

    //????????????????????ninecoldandroid
    private void doShowAniamtion(View v, final int curId, final int total, final float startX, final float startY) {
        if(v.getVisibility() == View.GONE) {
            v.setVisibility(View.VISIBLE);
            log("doShowAniamtion | show view : "+curId);
        }

        double degree = Math.PI / 2 * ( (float)curId / (float)(total-1) );

        float endX = (float) (startX + Math.cos(degree)*radius);
        float endY = (float) (startY + Math.sin(degree)*radius);

        log(String.format("degree: %f | endX: %f | endY: %f",degree,endX,endY));

        ObjectAnimator transX = ObjectAnimator.ofFloat(v,"translationX",startX, endX);
        transX.setInterpolator(new AccelerateDecelerateInterpolator());

        ObjectAnimator transY = ObjectAnimator.ofFloat(v,"translationY",startY, endY);
        transY.setInterpolator(new AccelerateDecelerateInterpolator());

        ObjectAnimator rotate = ObjectAnimator.ofFloat(v,"rotation",0,(float)(wholeCircle*rotateCircle));
        rotate.setInterpolator(new AccelerateDecelerateInterpolator());

        ObjectAnimator alpha = ObjectAnimator.ofFloat(v, "alpha", 0, 1f);

        ObjectAnimator scaleX = ObjectAnimator.ofFloat(v, "scaleX", 0, 1f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(v, "scaleY", 0, 1f);


        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(
                transX, transY,
                rotate,
                alpha,
                scaleX, scaleY);

        animatorSet.setDuration(showAnimaSpan);
        animatorSet.setStartDelay(curId * showAnimaDelaySpan);
        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                animatFinish = false;
                log("doShowAniamtion : onAnimationStart : "+animatFinish + "on VIEW : "+curId);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if(curId == total-1) {
                    animatFinish = true;
                    log("doShowAniamtion : onAnimationEnd : "+animatFinish + "on VIEW : "+curId);
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animatorSet.start();
    }

    private void doCloseAniamtion(final View v, final int curId, final int total, final float endX, final float endY) {
        if(v.getVisibility() == View.GONE) {
            v.setVisibility(View.VISIBLE);
        }

        double degree = Math.PI / 2 * ( (float)curId / (float)(total-1) );
        float startX = (float) (endX + Math.cos(degree)*radius);
        float startY = (float) (endY + Math.sin(degree)*radius);
        ObjectAnimator transX = ObjectAnimator.ofFloat(v,"translationX",startX, endX);
        transX.setInterpolator(new AccelerateDecelerateInterpolator());

        ObjectAnimator transY = ObjectAnimator.ofFloat(v,"translationY",startY, endY);
        transY.setInterpolator(new AccelerateDecelerateInterpolator());

        ObjectAnimator rotate = ObjectAnimator.ofFloat(v,"rotation",0,-(float)(wholeCircle*rotateCircle));
        rotate.setInterpolator(new AccelerateDecelerateInterpolator());

        ObjectAnimator alpha = ObjectAnimator.ofFloat(v, "alpha", 1f, 0);

        ObjectAnimator scaleX = ObjectAnimator.ofFloat(v, "scaleX", 1f, 0);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(v, "scaleY", 1f, 0);


        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(
                transX, transY,
                rotate,
                alpha,
                scaleX, scaleY);

        animatorSet.setDuration(showAnimaSpan);
        animatorSet.setStartDelay((total - 1 - curId) * showAnimaDelaySpan);
        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                animatFinish = false;
                log("doCloseAniamtion : onAnimationStart : "+animatFinish + "on VIEW : "+curId);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (curId == 0) {
                    animatFinish = true;
                    log("doCloseAniamtion : onAnimationEnd : "+animatFinish + "on VIEW : "+curId);
                }
                v.setVisibility(View.GONE);
                log("doCloseAniamtion : onAnimationEnd : "+ " View Gone " + "on VIEW : "+curId);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animatorSet.start();

    }


    private void do3DRotationAttamp(View v, long duration, int turnsNum) {
        duration = duration<=0?1:duration;
        turnsNum = turnsNum<=0?1:turnsNum;

        AnimatorSet aniSet = new AnimatorSet();
        aniSet.playSequentially(
                ObjectAnimator.ofFloat(v, "rotationX", 0f, 360f*turnsNum),
                ObjectAnimator.ofFloat(v, "rotationY", 0f, 360f*turnsNum),
                ObjectAnimator.ofFloat(v, "rotation", 0f, 360f*turnsNum)
                );
        aniSet.setInterpolator(new AccelerateDecelerateInterpolator());
        aniSet.setDuration(duration).start();

    }


    private String log(String strLogContent) {
        Log.w(TAG,strLogContent);
//        Toast.makeText(AnimationActivity.this, strLogContent, Toast.LENGTH_SHORT).show();
        return strLogContent;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_aniamation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
