package com.github.piasy.openglestutorial_android.stu;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class MyRunder implements GLSurfaceView.Renderer {
    private final String TAG = getClass().getName();
    int runderWidth, runderHeight;
    private FloatBuffer mVertexBuffer;

    private String vertexShaderCode =
            "precision mediump float; \n" +
                    "attribute vec4 a_Position; \n" +
                    "void main() {\n" +
                    "   gl_Position = a_Position; \n" +
                    "}";
    private String fragmentShaderCode =
            "precision mediump float;\n" +
                    "void main() {\n" +
                    "   gl_FragColor = vec4(0.0, 0.0, 1.0, 1.0); \n" +
                    "}";
    private int mProgam;

    private static final float[] VERTEX = {0f, 0.5f, -0.5f, -0.5f, 0.5f, -0.5f};
    private int localAPosition;

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        int vertexShader = GLES20.glCreateShader(GLES20.GL_VERTEX_SHADER);
        int fragmentShader = GLES20.glCreateShader(GLES20.GL_FRAGMENT_SHADER);
        GLES20.glShaderSource(vertexShader, vertexShaderCode);
        GLES20.glShaderSource(fragmentShader, fragmentShaderCode);
        GLES20.glCompileShader(vertexShader);
        GLES20.glCompileShader(fragmentShader);
        mProgam = GLES20.glCreateProgram();
        GLES20.glAttachShader(mProgam, vertexShader);
        GLES20.glAttachShader(mProgam, fragmentShader);
        GLES20.glLinkProgram(mProgam);
        localAPosition = GLES20.glGetAttribLocation(mProgam, "a_Position");
        mVertexBuffer = ByteBuffer.allocateDirect(VERTEX.length * 4)
                .order(ByteOrder.nativeOrder())
                .asFloatBuffer()
                .put(VERTEX);
        mVertexBuffer.position(0);
        GLES20.glUseProgram(mProgam);
        GLES20.glEnableVertexAttribArray(localAPosition);
        GLES20.glVertexAttribPointer(localAPosition, 2, GLES20.GL_FLOAT, false, 0, mVertexBuffer);
        int tmp = GLES20.glGetError();
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        runderWidth = width;
        runderHeight = height;
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        GLES20.glClearColor(1f, 0f, 0f, 1f);
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
        GLES20.glViewport(0,0, runderWidth, runderHeight);
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, 3);
    }
}
