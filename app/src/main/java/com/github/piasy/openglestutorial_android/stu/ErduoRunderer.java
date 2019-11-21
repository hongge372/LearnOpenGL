package com.github.piasy.openglestutorial_android.stu;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLES10;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.GLUtils;
import android.widget.Button;

import com.github.piasy.openglestutorial_android.R;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL;
import javax.microedition.khronos.opengles.GL10;

public class ErduoRunderer implements GLSurfaceView.Renderer {
    private final String TAG = getClass().getName();
    private Bitmap bitmap;
    private Context aContext;
    int[] myTextureId = new int[1];

    private static final float[] VERTEX_COORDINATES = new float[]{
            -1f,  1f, 0f,
            1f,   1f, 0f,
            -1f, -1f, 0f,
            1f,  -1f, 0f,
    };

    private static final float[] TEXTURE_COORDINATES = new float[]{
            0f, 0f,
            1f, 0f,
            0f, 1f,
            1f, 1f,
    };

    private static final Buffer TEXCOORD_BUFFER = ByteBuffer.allocateDirect(TEXTURE_COORDINATES.length*4)
            .order(ByteOrder.nativeOrder()).asFloatBuffer().put(TEXTURE_COORDINATES).rewind();
    private static final Buffer VERTEX_BUFFER = ByteBuffer.allocateDirect(VERTEX_COORDINATES.length*4)
            .order(ByteOrder.nativeOrder()).asFloatBuffer().put(VERTEX_COORDINATES).rewind();
    private int width;
    private int height;

    public ErduoRunderer(Context context){
        //super();
        aContext = context;

    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        bitmap = BitmapFactory.decodeResource(aContext.getResources(), R.drawable.erduo_000);
        OpenGLUtils.glGenTextures(myTextureId);
        GLES10.glBindTexture(GLES20.GL_TEXTURE_2D, myTextureId[0]);
        GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, bitmap, 0);
        GLES10.glBindTexture(GLES20.GL_TEXTURE_2D, 0);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        this.width = width;
        this.height = height;
        gl.glViewport(0, 0, width, height);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        //gl.glViewport(0,0, runderWidth, runderHeight);
        gl.glActiveTexture(GL10.GL_TEXTURE0);
        gl.glBindTexture(GLES10.GL_TEXTURE0, myTextureId[0]);
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, VERTEX_BUFFER);
        gl.glTexCoordPointer(2, GLES10.GL_FLOAT, 0, TEXCOORD_BUFFER);
        gl.glDrawArrays(GLES10.GL_TRIANGLE_STRIP, 0, 4);
        //super.onDrawFrame(gl);
        //onDrawEerduo();
    }

    private void onDrawEerduo(){
        {
            GLES20.glViewport(0,0, width, height);
            GLES20.glActiveTexture(GLES20.GL_TEXTURE_2D);
            GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, myTextureId[0]);
            GLES10.glVertexPointer(3, GL10.GL_FLOAT, 0, VERTEX_BUFFER);
            GLES10.glTexCoordPointer(2, GLES10.GL_FLOAT, 0, TEXCOORD_BUFFER);
            GLES20.glDrawArrays(GLES10.GL_TRIANGLE_STRIP, 0, 4);
        }
    }
}
