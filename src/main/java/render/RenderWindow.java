package render;

import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;

import java.nio.*;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.stb.STBImage.stbi_image_free;
//import static org.lwjgl.stb.STBImage.stbi_load;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;
import static org.lwjgl.stb.STBImage.stbi_load;
public class RenderWindow {

    // The window handle
    private long window;

    public void run(int resx, int resy, boolean fullscreen) {
        System.out.println("Hello LWJGL " + Version.getVersion() + "!");

        init(resx, resy, fullscreen);
        loop();

        // Free the window callbacks and destroy the window
        glfwFreeCallbacks(window);
        glfwDestroyWindow(window);

        // Terminate GLFW and free the error callback
        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }

    private void init(int resx, int resy, boolean fullscreen) {
        // set up an error callback. The default implementation
        // will print the error message in System.err.
        GLFWErrorCallback.createPrint(System.err).set();

        // Initialize GLFW. Most GLFW functions will not work before doing this.
        if ( !glfwInit() )
            throw new IllegalStateException("Unable to initialize GLFW");

        // Configure GLFW
        glfwDefaultWindowHints(); // optional, the current window hints are already the default
        glfwWindowHint(GLFW_VISIBLE, GLFW_TRUE); // the window will stay visible after creation
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE); // the window will be resizable

        String rootPath = Thread.currentThread().getContextClassLoader().getResource("icon_01.png").getPath();
        //String testPath = Thread.currentThread().getResource("icon_01.png").getPath();
        System.out.println(rootPath);
        //System.out.print(testPath);

        /*GLFWImage images[];
        images = new GLFWImage[1];
        int[] width1 = new int[1];
        int[] height1 = new int[1];
        width1[0] = images[0].width();
        width1[1] = images[1].width();
        height1[0] = images[0].height();
        height1[1] = images[1].height();
        int[] table = new int[0];
        table[0] = 0;
        //images[0] = load_icon("icon_01.png");
        //images[1] = load_icon("icon_01.png");
        //GLFWImage.Buffer buffer = null;
        //buffer.put(image);
        /*GLFWImage.Buffer bbr = stbi_load(rootPath, width1, height1, table, 4); //rgba channels // charsequence, int[], int[], int[] int
        images[0].pixels(bbr);
        glfwSetWindowIcon(window,bbr);
        stbi_image_free(images[0].pixels());*/

        // Get the resolution of the primary monitor
        GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

        // Create the window
        if (fullscreen) {
            window = glfwCreateWindow((int) (vidmode.width() / 1.8), (int) (vidmode.height() / 1.8), "Ripoff Minecraft", glfwGetPrimaryMonitor(), NULL);
        } else { window = glfwCreateWindow((int) (vidmode.width() / 1.8), (int) (vidmode.height() / 1.8), "Ripoff Minecraft", NULL, NULL); }
        if ( window == NULL )
            throw new RuntimeException("Failed to create the window at runtime");

        // Setup a key callback. It will be called every time a key is pressed, repeated or released.
        glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
            if ( key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE )
                glfwSetWindowShouldClose(window, true); // We will detect this in the rendering loop
        });
        image_parser resource_01 = image_parser.load_image(rootPath);
        GLFWImage image = GLFWImage.malloc();
        GLFWImage.Buffer imagebuffer = GLFWImage.malloc(1);
        System.out.println("not null?? w: " + resource_01.get_width() + " h: " + resource_01.get_height());
        image.set(resource_01.get_width(), resource_01.get_height(), resource_01.get_image());

        if (image != null) { System.out.println("not null image"); System.out.println(image.width() + " " + image.height()); }

        imagebuffer.put(image);
        if (imagebuffer == null) {
            System.out.println("image buffer null");
        }
        glfwSetWindowIcon(window, imagebuffer);

        // Get the thread stack and push a new frame
        try ( MemoryStack stack = stackPush() ) {
            IntBuffer pWidth = stack.mallocInt(1); // int*
            IntBuffer pHeight = stack.mallocInt(1); // int*

            // Get the window size passed to glfwCreateWindow
            glfwGetWindowSize(window, pWidth, pHeight);


            // Center the window
            glfwSetWindowPos(
                    window,
                    (vidmode.width() - pWidth.get(0)) / 2,
                    (vidmode.height() - pHeight.get(0)) / 2
            );
        } // the stack frame is popped automatically

        // Make the OpenGL context current
        glfwMakeContextCurrent(window);
        // Enable v-sync
        glfwSwapInterval(1);
        float[] points = new float[]{
                0.0f, 0.5f, 0.0f,
                0.5f, -0.5f, 0.0f,
                -0.5f, -0.5f, 0.0f
        };

        // draw test quad
       // glfwGetFramebufferSize(window, width, height);
        //glViewport(0, 0, width, height);
        //glDrawArrays(GL_TRIANGLE_STRIP, 0, 4);
        // Make the window visible
        glfwShowWindow(window);
        //drawCube();
    }

    private void loop() {
        // This line is critical for LWJGL's interoperation with GLFW's
        // OpenGL context, or any context that is managed externally.
        // LWJGL detects the context that is current in the current thread,
        // creates the GLCapabilities instance and makes the OpenGL
        // bindings available for use.
        GL.createCapabilities();
        //drawCube();
        // Set the clear color
        glClearColor(0.8f, 0.5f, 0.2f, 0.5f);
        // Run the rendering loop until the user has attempted to close
        // the window or has pressed the ESCAPE key.
        //drawCube();
        while (!glfwWindowShouldClose(window)) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer

            glfwSwapBuffers(window); // swap the color buffers
            drawCube();
            // Poll for window events. The key callback above will only be
            // invoked during this call.
            glfwPollEvents();
        }
    }
    public void drawCube() {
        System.out.println("Drawing cube");
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        glLoadIdentity();
        glTranslatef(1.5f,0.0f,-7.0f);              // Move Right And Into The Screen

        glRotatef(1.0f,1.0f,1.0f,1.0f);            // Rotate The Cube On X, Y & Z

        glBegin(GL_QUADS);                  // Start Drawing The Cube

        glColor3f(0.0f,1.0f,0.0f);          // Set The Color To Green
        glVertex3f( 1.0f, 1.0f,-1.0f);          // Top Right Of The Quad (Top)
        glVertex3f(-1.0f, 1.0f,-1.0f);          // Top Left Of The Quad (Top)
        glVertex3f(-1.0f, 1.0f, 1.0f);          // Bottom Left Of The Quad (Top)
        glVertex3f( 1.0f, 1.0f, 1.0f);

        glColor3f(1.0f,0.5f,0.0f);          // Set The Color To Orange
        glVertex3f( 1.0f,-1.0f, 1.0f);          // Top Right Of The Quad (Bottom)
        glVertex3f(-1.0f,-1.0f, 1.0f);          // Top Left Of The Quad (Bottom)
        glVertex3f(-1.0f,-1.0f,-1.0f);          // Bottom Left Of The Quad (Bottom)
        glVertex3f( 1.0f,-1.0f,-1.0f);          // Bottom Right Of The Quad (Bottom)
        glEnd();
    }
}

