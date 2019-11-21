package com.github.piasy.openglestutorial_android.stu;

import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.Toast;

import com.github.piasy.openglestutorial_android.R;
import com.github.piasy.openglestutorial_android.Utils;

import java.util.Random;

public class StuPlaneActivity extends AppCompatActivity {

    private GLSurfaceView glView; // Use GLSurfaceView
    Random rnd = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(android.R.style.Theme_Translucent_NoTitleBar);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        glView = new GLSurfaceView(this); // Allocate a GLSurfaceView
        glView.setRenderer(new MyGLRenderer(this)); // Use a custom renderer
        glView.getAlpha();
        this.setContentView(glView);
    }

    @Override
    protected void onPause() {
        super.onPause();
        glView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        glView.onResume();

    }
}
