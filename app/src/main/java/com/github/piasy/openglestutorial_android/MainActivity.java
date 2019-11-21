package com.github.piasy.openglestutorial_android;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.github.piasy.openglestutorial_android.stu.ErduoRunderer;
import com.github.piasy.openglestutorial_android.stu.MyGLRenderer;
import com.github.piasy.openglestutorial_android.stu.MyRunder;
import com.github.piasy.openglestutorial_android.stu.StuPlaneActivity;

public class MainActivity extends AppCompatActivity {

    private GLSurfaceView mGLSurfaceView;
    private GLSurfaceView.Renderer mRenderer;
    private static int REQUEST_PERMISSION_CODE = 1;
    private static String[] PERMISSIONS_BE_CHECK = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_SETTINGS};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        permissionCheckIfOk();
        setContentView(R.layout.activity_main);
        initMyAct();
    }

    private void initMyAct() {
        Button button = (Button) findViewById(R.id.run_stu_glv);
        button.setOnClickListener(listener);
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();

            switch (v.getId()) {
                case R.id.run_stu_glv:
                    intent.setClass(MainActivity.this, StuPlaneActivity.class);
                    startActivity(intent);
                    break;
                default:
                    break;
            }
        }
    };

    private boolean permissionCheckIfOk() {
        ActivityCompat.requestPermissions(this, PERMISSIONS_BE_CHECK, REQUEST_PERMISSION_CODE);
        return true;
    }
}
