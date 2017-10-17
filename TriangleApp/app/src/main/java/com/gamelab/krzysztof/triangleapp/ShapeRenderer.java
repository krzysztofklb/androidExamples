package com.gamelab.krzysztof.triangleapp;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import javax.microedition.khronos.opengles.GL11;

import android.opengl.GLSurfaceView;

/**
 * Created by LENOVO on 2017-10-11.
 */

public class ShapeRenderer implements GLSurfaceView.Renderer {

    private Triangle triangle;
    private Square square;

    private float[] mRotationMatrix = new float[16];

    public ShapeRenderer() {
        triangle = new Triangle();
//        square = new Square();
    }

    @Override
    public void onDrawFrame(GL10 gl) {

        gl.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

        gl.glMatrixMode(GL11.GL_MODELVIEW);
        gl.glLoadIdentity();

        gl.glScalef(5.0f, 5.0f, 1.0f);
//        gl.glTranslatef(0.0f, -0.1f, 0.0f);
        // Translates 4 units into the screen.
        gl.glTranslatef(0, 0, -4);
//         gl.glRotatef(90.0f, 0.0f, 0.0f, 1.0f);

//        gl.glLoadIdentity();
//        // Translates 7 units into the screen and 1.5 units up.
//        gl.glTranslatef(0, 1.5f, -7);

        gl.glEnableClientState(GL11.GL_VERTEX_ARRAY);

        triangle.draw(gl);
//        square.draw(gl);
        gl.glDisableClientState(GL11.GL_VERTEX_ARRAY);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {

        gl.glViewport(0, 0, width, height);

        float ratio;
        float zNear = .1f;
        float zFar = 1000f;
        float fieldOfView = (float) Math.toRadians(30);
        float size;

        gl.glEnable(GL11.GL_NORMALIZE);

        ratio = (float) width / (float) height;

        gl.glMatrixMode(GL11.GL_PROJECTION);

        size = zNear * (float) (Math.tan((double) (fieldOfView / 2.0f)));

        gl.glFrustumf(-size, size, -size / ratio, size / ratio, zNear, zFar);

        gl.glMatrixMode(GL11.GL_MODELVIEW);
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {

        gl.glDisable(GL11.GL_DITHER);

        gl.glHint(GL11.GL_PERSPECTIVE_CORRECTION_HINT, GL11.GL_FASTEST);

        gl.glClearColor(0, 0, 0, 0);

        gl.glEnable(GL11.GL_CULL_FACE);
        gl.glFrontFace(GL11.GL_CCW);

        gl.glShadeModel(GL11.GL_SMOOTH);

        gl.glEnable(GL11.GL_DEPTH_TEST);
    }

}