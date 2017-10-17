package com.gamelab.krzysztof.triangleapp;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;
import javax.microedition.khronos.opengles.GL11;

/**
 * Created by LENOVO on 2017-10-11.
 */

public class Triangle {
    // Our vertices.
    float vertices[] = {
            -0.5f, -0.29f, -10f,
            0.5f, -0.29f, -10f,
            0f, 0.58f, -10f
    };

    // The colors mapped to the vertices.
    private float[] colors = {
            1f, 0f, 0f, 1f, // vertex 0 red
            0f, 1f, 0f, 1f, // vertex 1 green
            0f, 0f, 1f, 1f, // vertex 2 blue
    };

    // The order we like to connect them.
    private short[] indices = { 0, 1, 2};

    // Our vertex buffer.
    private FloatBuffer vertexBuffer;

    // Our color buffer.
    private FloatBuffer colorBuffer;

    // Our index buffer.
    private ShortBuffer indexBuffer;

    public Triangle() {
        // a float is 4 bytes, therefore we multiply the number if
        // vertices with 4.
        ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length * 4);
        vbb.order(ByteOrder.nativeOrder());
        vertexBuffer = vbb.asFloatBuffer();
        vertexBuffer.put(vertices);
        vertexBuffer.position(0);

        // short is 2 bytes, therefore we multiply the number if
        // vertices with 2.
        ByteBuffer ibb = ByteBuffer.allocateDirect(indices.length * 2);
        ibb.order(ByteOrder.nativeOrder());
        indexBuffer = ibb.asShortBuffer();
        indexBuffer.put(indices);
        indexBuffer.position(0);

        // float has 4 bytes, colors (RGBA) * 4 bytes
        ByteBuffer cbb = ByteBuffer.allocateDirect(colors.length * 4);
        cbb.order(ByteOrder.nativeOrder());
        colorBuffer = cbb.asFloatBuffer();
        colorBuffer.put(colors);
        colorBuffer.position(0);
    }

    /**
     * This function draws our square on screen.
     * @param gl
     */
    public void draw(GL10 gl) {
        // Counter-clockwise winding.
        gl.glFrontFace(GL10.GL_CCW);
        // Enable face culling.
        gl.glEnable(GL10.GL_CULL_FACE);
        // What faces to remove with the face culling.
        gl.glCullFace(GL10.GL_BACK);

        // Enabled the vertices buffer for writing and to be used during
        // rendering.
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        // Specifies the location and data format of an array of vertex
        // coordinates to use when rendering.
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0,
                vertexBuffer);

        // Enable the color array buffer to be used during rendering.
        gl.glEnableClientState(GL10.GL_COLOR_ARRAY); // NEW LINE ADDED.
        // Point out the where the color buffer is.
        gl.glColorPointer(4, GL10.GL_FLOAT, 0, colorBuffer); // NEW LINE ADDED.

        gl.glDrawElements(GL10.GL_TRIANGLES, indices.length,
                GL10.GL_UNSIGNED_SHORT, indexBuffer);

        // Disable the vertices buffer.
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        // Disable face culling.
        gl.glDisable(GL10.GL_CULL_FACE);

        // Disable the color buffer.
        gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
    }

}