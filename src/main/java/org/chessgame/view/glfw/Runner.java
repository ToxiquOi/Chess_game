package org.chessgame.view.glfw;

import org.lwjgl.glfw.GLFWVidMode;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.GL_TRUE;
import static org.lwjgl.system.MemoryUtil.NULL;



public class Runner implements Runnable {

    private int height = 800;
    private int width = 800;

    private boolean isRunning  = false;

    private long window;

    public void start() {
        this.isRunning = true;
        Thread thread = new Thread(this, "Chess");
        thread.start();
    }

    private void init(){
        if (!glfwInit()) {
            // TODO: handle
            return;
        }

        glfwWindowHint(GLFW_RESIZABLE, GL_TRUE);
        this.window = glfwCreateWindow(this.width, this.height, "Chess", NULL, NULL);
        if (this.window == NULL) {
            // TODO: handle

            return;
        }

        GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        if (vidmode != null) {
            glfwSetWindowPos(this.window, (vidmode.width() - this.width) / 2, (vidmode.height() - this.height) / 2);
        }

        glfwMakeContextCurrent(this.window);
        glfwShowWindow(this.window);
    }

    public void run() {
        this.init();

        while (this.isRunning) {
            update();
            render();

            if(glfwWindowShouldClose(this.window)) {
                this.isRunning = false;
            }
        }
    }

    private void update() {
        glfwPollEvents();
    }

    private void render() {
        glfwSwapBuffers(this.window);
    }
}
