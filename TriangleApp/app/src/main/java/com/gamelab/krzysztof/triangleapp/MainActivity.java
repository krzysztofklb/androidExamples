package com.gamelab.krzysztof.triangleapp;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.WindowManager;

/**
 * Created by LENOVO on 2017-10-11.
 */


public class MainActivity extends Activity {

    GLSurfaceView view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        view = new GLSurfaceView(this);
        view.setRenderer(new ShapeRenderer());
        // Render the view only when there is a change in the drawing data.
        // To allow the triangle to rotate automatically, this line is commented out:
//        view.setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);// WYPIERDOLIIIC!!!!
        setContentView(view);
    }

    @Override
    protected void onPause() {
        super.onPause();
        view.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        view.onResume();
    }

}