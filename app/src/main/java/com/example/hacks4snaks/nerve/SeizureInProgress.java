package com.example.hacks4snaks.nerve;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.hardware.Camera;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.hacks4snaks.nerve.utils.ResizeAnimation;
import com.example.hacks4snaks.nerve.utils.Utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class SeizureInProgress extends AppCompatActivity implements SurfaceHolder.Callback {
    private SurfaceHolder surfaceHolder;
    private SurfaceView surfaceView;
    public MediaRecorder mrec = new MediaRecorder();

    ViewFlipper mViewFlipper;
    File video;
    private Camera mCamera = null;
    private ImageButton startRecording = null;

    boolean call_animation = false;
    boolean calling = false;

    LinearLayout column1, column2;

    private int time = 0;
    private int second = 0;
    private int minute = 0;
    long startTime;
    long endTime ;
    private boolean doubletap = false;
    Handler handlerDoubleClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seizure_in_progress);
        handlerDoubleClick = new Handler();

        mViewFlipper = (ViewFlipper) this.findViewById(R.id.profile);

        Button callButton1 = (Button) SeizureInProgress.this.findViewById(R.id.call_button1);
        final ImageView phone1 =  SeizureInProgress.this.findViewById(R.id.phone1);
        Button callButton2 = (Button) SeizureInProgress.this.findViewById(R.id.call_button2);
        final ImageView phone2 =  SeizureInProgress.this.findViewById(R.id.phone2);

        LinearLayout seizure_detected = (LinearLayout) SeizureInProgress.this.findViewById(R.id.seizure_detected);

        View tvBlink = (View) findViewById(R.id.seizure_in_progress);
        LinearLayout tvBlink2 = (LinearLayout) findViewById(R.id.profile_image);
        Animation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(500);
        anim.setStartOffset(10);
        anim.setRepeatMode(Animation.REVERSE);
        anim.setRepeatCount(Animation.INFINITE);
        tvBlink.startAnimation(anim);
        tvBlink2.startAnimation(anim);

        View.OnTouchListener callOnTouchListener = new View.OnTouchListener() {
//            private final float TOUCH_SCALE_FACTOR = 180.0f / 320;
//            private float mPreviousX;
//            private float mPreviousY;
            @Override
            public boolean onTouch(final View view, MotionEvent event) {
                Resources r = getResources();
                final int original_size = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 60, r.getDisplayMetrics());
                final int target_size = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 120, r.getDisplayMetrics());
                Rect rect;
                if (event.getAction() == android.view.MotionEvent.ACTION_DOWN && !call_animation) {
                    phone1.setImageResource(R.drawable.icon_phone_green2);
                    phone2.setImageResource(R.drawable.icon_phone_green2);
                    call_animation = true;
                    ResizeAnimation resizeAnimation = new ResizeAnimation(
                            view,
                            target_size,
                            original_size,
                            target_size,
                            original_size
                    );
                    resizeAnimation.setDuration(500);

                    AnimationSet animSet = new AnimationSet(true);
                    animSet.setFillEnabled(true);
                    animSet.addAnimation(resizeAnimation);

                    view.startAnimation(animSet);
                } else if (event.getAction() == android.view.MotionEvent.ACTION_UP && call_animation) {
                    phone1.setImageResource(R.drawable.icon_phone_green1);
                    phone2.setImageResource(R.drawable.icon_phone_green1);
                    call_animation = false;
                    ResizeAnimation resizeAnimation = new ResizeAnimation(
                            view,
                            original_size,
                            target_size,
                            original_size,
                            target_size
                    );
                    resizeAnimation.setDuration(500);

                    AnimationSet animSet = new AnimationSet(true);
                    animSet.setFillEnabled(true);
                    animSet.addAnimation(resizeAnimation);

                    view.startAnimation(animSet);
//                    view.postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            view.getLayoutParams().height = original_size;
//                            view.getLayoutParams().width = original_size;
//                        }
//                    }, 500);
                } if(event.getAction() == MotionEvent.ACTION_MOVE){
                    rect = new Rect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
                    if(!rect.contains(view.getLeft() + (int) event.getX(), view.getTop() + (int) event.getY()) && call_animation){
                        view.getLayoutParams().height = original_size;
                        view.getLayoutParams().width = original_size;
                        ImageButton profileButton = (ImageButton) SeizureInProgress.this.findViewById(R.id.imageRecordSeizure);
                        LinearLayout cameraButton = (LinearLayout) SeizureInProgress.this.findViewById(R.id.camera_button);
                        if (recordingStarted) {
                            recordingStarted = false;
                            profileButton.setImageResource(R.drawable.icon_movie_grey);
                            mrec.stop();
                            mrec.release();
                            mrec = null;
                            surfaceView.setVisibility(View.INVISIBLE);
                            cameraButton.setVisibility(View.INVISIBLE);
                        }
                        view.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Log.i("Dzwonie:", "782779646");
                                Intent callIntent = new Intent(Intent.ACTION_CALL);
                                if(!calling) {
                                    calling = true;
                                    callIntent.setData(Uri.parse("tel:782779646"));
                                    startActivity(callIntent);
                                }
                            }
                        }, 100);
                    }
                }
                return false;
            }
        };
//        seizure_detected.setOnTouchListener(callOnTouchListener);
        callButton1.setOnTouchListener(callOnTouchListener);
        callButton2.setOnTouchListener(callOnTouchListener);

        mCamera = null;
        mrec = null;
        startRecording = (ImageButton)findViewById(R.id.imageRecordSeizure);
        mCamera = Camera.open();
        mCamera.setDisplayOrientation(90);
        surfaceView = (SurfaceView) findViewById(R.id.surface_camera);
        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback(this);
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

        SeizureDecetedFun();
    }
    @Override
    public void onBackPressed()
    {
//        Intent intentMain = new Intent(SeizureInProgress.this ,
//                HomeActivity.class);
//        SeizureInProgress.this.startActivity(intentMain);
    }
    public  void ProgressonClickToCancel(View v)
    {
        Log.e("jaki stan double click",String.valueOf(doubletap));
        handlerDoubleClick = new Handler();
        handlerDoubleClick.postDelayed(new Runnable() {
            @Override
            public void run() {
                doubletap=false;
            }
        }, 2000);
        if (doubletap==true) {
            handlerDoubleClick.removeMessages(0);

            stopRecording();
            releaseMediaRecorder();
            releaseCamera();

            endTime=System.currentTimeMillis();
            endTime=endTime-startTime;
            addToCalendarSeizureInProgress(endTime);

            Intent intentMain = new Intent(SeizureInProgress.this ,
                    HomeActivity.class);
            SeizureInProgress.this.startActivity(intentMain);
        } else {
            doubletap=true;
        }
    }

    private void SeizureDecetedFun() {
        Handler handler2= new Handler();
        handler2.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!doubletap) {
                    TextView myAwesomeTextView = (TextView)findViewById(R.id.timer);
//                    Log.e("Time",String.valueOf(time));
                    second++;
                    String seconds = String.valueOf(second);
                    if(second % 60 == 0) {
                        minute++;
                        second = 0;
                    }
                    if (second < 10) {
                        seconds = "0" + seconds;
                    }
                    String minutes = String.valueOf(minute);
                    if (minute < 10) {
                        minutes = "0" + minutes;
                    }
                    myAwesomeTextView.setText(String.valueOf(minutes + ":" + seconds));
                    if(!doubletap) {
                        SeizureDecetedFun();
                    }
                }
            }
        }, 1000);
    }

    private void addToCalendarSeizureInProgress(long timeOfSeizure) {
        Calendar calendar  = Calendar.getInstance();
        String seizureDate  = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH).format(Calendar.getInstance().getTime());
        String time = calendar.get(Calendar.HOUR_OF_DAY)+":"+calendar.get(Calendar.MINUTE);
        Log.e("dzien",seizureDate);
        Log.e("czas",time);
        timeOfSeizure=timeOfSeizure/1000;
        Log.e("czas ataku", String.valueOf(timeOfSeizure));

        Utils seizureAdder = new Utils();
        seizureAdder.addToArray("Seizure_" + seizureDate, String.valueOf(time), this);
        seizureAdder.addToArray("Seizures", seizureDate, this);
    }

    Button buttonBlink;
    Animation animRecord;
    boolean recordingStarted = false;
    public void onClickStartRecording(View view) {
        ImageButton profileButton = (ImageButton) SeizureInProgress.this.findViewById(R.id.imageRecordSeizure);
        LinearLayout cameraButton = (LinearLayout) SeizureInProgress.this.findViewById(R.id.camera_button);
        if (!recordingStarted) {
            try {
                surfaceView.setVisibility(View.VISIBLE);
                cameraButton.setVisibility(View.VISIBLE);
                startRecording();

                buttonBlink = (Button) findViewById(R.id.record_button);
                animRecord = new AlphaAnimation(0.0f, 1.0f);
                animRecord.setDuration(500);
                animRecord.setStartOffset(10);
                animRecord.setRepeatMode(Animation.REVERSE);
                animRecord.setRepeatCount(Animation.INFINITE);
                buttonBlink.startAnimation(animRecord);

                profileButton.setImageResource(R.drawable.icon_movie_blue);
                recordingStarted = true;
            } catch (Exception e) {
                surfaceView.setVisibility(View.INVISIBLE);
                cameraButton.setVisibility(View.INVISIBLE);
                profileButton.setImageResource(R.drawable.icon_movie_grey);
                recordingStarted = false;
                String message = e.getMessage();
                Log.i(null, "Problem Start" + message);
//                mrec.release();
                releaseMediaRecorder();
                releaseCamera();
            }
        } else {
            recordingStarted = false;
            animRecord.cancel();
            profileButton.setImageResource(R.drawable.icon_movie_grey);
//            stopRecording();
            mrec.stop();
            mrec.release();
            mrec = null;
            surfaceView.setVisibility(View.INVISIBLE);
            cameraButton.setVisibility(View.INVISIBLE);
        }
    }

    protected void startRecording() throws IOException
    {
        mrec = new MediaRecorder();  // Works well
        mCamera.unlock();

        mrec.setCamera(mCamera);

        mrec.setPreviewDisplay(surfaceHolder.getSurface());
        mrec.setVideoSource(MediaRecorder.VideoSource.CAMERA);
        mrec.setAudioSource(MediaRecorder.AudioSource.MIC);

        mrec.setProfile(CamcorderProfile.get(CamcorderProfile.QUALITY_HIGH));
        mrec.setPreviewDisplay(surfaceHolder.getSurface());

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        mrec.setOutputFile(Environment.getExternalStorageDirectory() + "/com.hacks4snacks.nerve/" + timeStamp + ".3gp");

        mrec.prepare();
        mrec.start();
    }

    protected void stopRecording() {
        if (mrec != null) {
            mrec.stop();
            mrec.release();
            if (mCamera != null) {
                mCamera.release();
            }
        }
    }

    private void releaseMediaRecorder(){
        if (mrec != null) {
            mrec.reset();   // clear recorder configuration
            mrec.release(); // release the recorder object
            mrec = null;
            if(mCamera != null) {
                mCamera.lock();           // lock camera for later use
            }
        }
    }

    private void releaseCamera(){
        if (mCamera != null){
            mCamera.release();        // release the camera for other applications
            mCamera = null;
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if (mCamera != null){
            Camera.Parameters params = mCamera.getParameters();
            mCamera.setParameters(params);
        }
        else {
            Toast.makeText(getApplicationContext(), "Camera not available!", Toast.LENGTH_LONG).show();
            finish();
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        if (mCamera != null) {
            mCamera.stopPreview();
        }
        releaseMediaRecorder();
        releaseCamera();
    }


    int out = 0;
    public void onClickProfileFlipper(View view) {
        if(out == 0) {
            mViewFlipper.setOutAnimation(outToRightAnimation(500));
            mViewFlipper.setInAnimation(inFromLeftAnimation(500));
            mViewFlipper.showNext();
            out = 1;
        } else if(out == 1) {
            mViewFlipper.setOutAnimation(outToLeftAnimation(500));
            mViewFlipper.setInAnimation(inFromRightAnimation(500));
            mViewFlipper.showNext();
            out = 0;
        }
    }

    public static Animation inFromRightAnimation(long durationMillis) {
        Animation inFromRight = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT,  +1.0f, Animation.RELATIVE_TO_PARENT,  0.0f,
                Animation.RELATIVE_TO_PARENT,  0.0f, Animation.RELATIVE_TO_PARENT,   0.0f
        );
        inFromRight.setDuration(durationMillis);
        return inFromRight;
    }
    public static Animation outToLeftAnimation(long durationMillis) {
        Animation outtoLeft = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT,  0.0f, Animation.RELATIVE_TO_PARENT,  -1.0f,
                Animation.RELATIVE_TO_PARENT,  0.0f, Animation.RELATIVE_TO_PARENT,   0.0f
        );
        outtoLeft.setDuration(durationMillis);
        return outtoLeft;
    }
    public static Animation inFromLeftAnimation(long durationMillis) {
        Animation inFromLeft = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT,  -1.0f, Animation.RELATIVE_TO_PARENT,  0.0f,
                Animation.RELATIVE_TO_PARENT,  0.0f, Animation.RELATIVE_TO_PARENT,   0.0f
        );
        inFromLeft.setDuration(durationMillis);
        return inFromLeft;
    }

    public static Animation outToRightAnimation(long durationMillis) {
        Animation outtoRight = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT,  0.0f, Animation.RELATIVE_TO_PARENT,  +1.0f,
                Animation.RELATIVE_TO_PARENT,  0.0f, Animation.RELATIVE_TO_PARENT,   0.0f
        );
        outtoRight.setDuration(durationMillis);
        return outtoRight;
    }
}
