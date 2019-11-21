package com.github.piasy.openglestutorial_android.stu;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLES20;
import android.opengl.GLUtils;

import com.github.piasy.openglestutorial_android.R;

import javax.microedition.khronos.opengles.GL10;

public class ErduoRunderer extends MyRunder {
    private final String TAG = getClass().getName();
    private Bitmap bitmap;
    private Context aContext;
    int[] myTextureId = new int[1];

    public ErduoRunderer(Context context){
        super();
        aContext = context;
        bitmap = BitmapFactory.decodeResource(aContext.getResources(), R.drawable.erduo_000);
        OpenGLUtils.glGenTextures(myTextureId);
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, myTextureId[0]);
        GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, bitmap, 0);
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, 0);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        //super.onDrawFrame(gl);
        onDrawEerduo();
    }

    private void onDrawEerduo(){
        {
            GLES20.glViewport(0,0, runderWidth, runderHeight);

        }
    }
}
