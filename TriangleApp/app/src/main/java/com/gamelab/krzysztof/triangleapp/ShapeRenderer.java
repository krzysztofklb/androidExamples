package com.gamelab.krzysztof.triangleapp;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import javax.microedition.khronos.opengles.GL11;

import android.opengl.GLSurfaceView;
import android.opengl.GLU;

/**
 * Created by LENOVO on 2017-10-11.
 */

public class ShapeRenderer implements GLSurfaceView.Renderer {

    private Cube cube;

    private static float angleCube = 0;    // Rotational angle in degree for cube (NEW)
    private static float speedCube = -1.5f;   // Rotational speed for cube (NEW)


    public ShapeRenderer() {
//        triangle = new Triangle();
//        square = new Square();
        cube = new Cube();         // (NEW)
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        // Clear color and depth buffers
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

        gl.glMatrixMode(GL11.GL_MODELVIEW);
//        gl.glLoadIdentity();
//
        gl.glEnableClientState(GL11.GL_VERTEX_ARRAY);


        // ----- Render the Color Cube -----
        gl.glLoadIdentity();                // Reset the model-view matrix
        gl.glTranslatef(-0.2f, -1.0f, -6.0f); // Translate right and into the screen
        gl.glScalef(0.8f, 0.8f, 0.8f);      // Scale down (NEW)
        gl.glRotatef(angleCube, 1.0f, 1.0f, 1.0f); // rotate about the axis (1,1,1) (NEW)
        // Update the rotational angle after each refresh (NEW)
        angleCube += speedCube;         // (NEW)

        gl.glDisableClientState(GL11.GL_VERTEX_ARRAY);
        cube.draw(gl);

    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        if (height == 0) height = 1;   // To prevent divide by zero
        float aspect = (float) width / height;
//
//        // Set the viewport (display area) to cover the entire window
        gl.glViewport(0, 0, width, height);

// select the projection matrix to setup the projection
        gl.glMatrixMode(GL10.GL_PROJECTION); // Select projection matrix
        gl.glLoadIdentity();                 // Reset projection matrix
// Use perspective projection with the projection volume defined by
//   fovy, aspect-ration, z-near and z-far
        GLU.gluPerspective(gl, 45, aspect, 0.1f, 100.f);

//        float ratio;
//        float zNear = .1f;
//        float zFar = 1000f;
//        float fieldOfView = (float) Math.toRadians(30);
//        float size;
//
//        gl.glEnable(GL11.GL_NORMALIZE);
//
//        ratio = (float) width / (float) height;
//
//        gl.glMatrixMode(GL11.GL_PROJECTION);
//
//        size = zNear * (float) (Math.tan((double) (fieldOfView / 2.0f)));
//
//        gl.glFrustumf(-size, size, -size / ratio, size / ratio, zNear, zFar);
//
//        gl.glMatrixMode(GL11.GL_MODELVIEW);
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