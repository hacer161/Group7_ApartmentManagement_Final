package vn.edu.spx.group7_apartmentmanagement_final;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

public class LoadingActivity extends AppCompatActivity {
    ProgressBar progressBar;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        progressBar=findViewById(R.id.progress_bar);
        textView=findViewById(R.id.tv_1);
        progressAnimation();
        progressBar.setMax(100);
        progressBar.setScaleY(3f);

    }
    public void progressAnimation(){
        ProgressBarAnimation anim = new ProgressBarAnimation(this,progressBar,textView,0f,100f);
        anim.setDuration(1000);
        progressBar.setAnimation(anim);
    }
}