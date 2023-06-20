package render;

import org.jetbrains.annotations.NotNull;
import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;

import java.nio.*;
import java.util.Objects;

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
        this.resx = resx;
        this.resy = resy;
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
            window = glfwCreateWindow(vidmode.width(), vidmode.height(), "Ripoff Minecraft", glfwGetPrimaryMonitor(), NULL);
        } else { window = glfwCreateWindow(resx, resy, "Ripoff Minecraft", NULL, NULL); }
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
        GLFWImage images[] = new GLFWImage[2];
        images[0] = image;
        System.out.println(imagebuffer.height());
        //glfwSetWindowIcon(window, images);
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
        //LoadGl
        GL.createCapabilities();
        // Enable v-sync
        glfwSwapInterval(1);

        // draw test quad
        //glfwGetFramebufferSize(window, vidmode.width(), vidmode.height());

        setUpProjectionMatrix(vidmode.width(), vidmode.height());
        glDepthFunc(GL_LEQUAL);
        glEnable(GL_DEPTH_TEST);
        //glDrawArrays(GL_TRIANGLE_STRIP, 0, 4);
        // Make the window visible
        glfwShowWindow(window);
        //drawCube();
    }
    private int resx, resy;
    private void loop() {

        //GL11.glViewport(0, 0, resx, resy);

        // This line is critical for LWJGL's interoperation with GLFW's
        // OpenGL context, or any context that is managed externally.
        // LWJGL detects the context that is current in the current thread,
        // creates the GLCapabilities instance and makes the OpenGL
        // bindings available for use.

        //drawCube();
        // Set the clear color
        glClearColor(1.0f, 0.5f, 0.0f, 0.5f);
        // Run the rendering loop until the user has attempted to close
        // the window or has pressed the ESCAPE key.
        //drawCube();
        while (!glfwWindowShouldClose(window)) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer

            drawSpinningCube();
            setUpProjectionMatrix(glfwGetVideoMode(glfwGetPrimaryMonitor()).width(), glfwGetVideoMode(glfwGetPrimaryMonitor()).height());
            glfwSwapBuffers(window); // swap the color buffers
            // Poll for window events. The key callback above will only be
            // invoked during this call.
            glfwPollEvents();
        }
    }
    private void setUpProjectionMatrix(int resolutionx, int resolutiony) {
        glViewport(0, 0, resolutionx, resolutiony);
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        float aspect = (float) resolutionx / (float) resolutiony;
        //System.out.println(aspect + " is aspect");
        //GL11.glFrustum(-aspect, aspect, -1, 1, -1, 100);
        glFrustum(-1, 1, -1, 1, -1, 10);

        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        glLoadIdentity();
        // set up projection matrix

    }
    private void drawTriangle() {
        GL11.glBegin(GL11.GL_TRIANGLES);
        GL11.glColor3f(1.0f, 0.0f, 0.0f);
        GL11.glVertex2f(-0.1f, -0.1f);
        GL11.glColor3f(0.0f, 1.0f, 0.0f);
        GL11.glVertex2f(0.1f, -0.1f);
        GL11.glColor3f(0.0f, 0.0f, 1.0f);
        GL11.glVertex2f(0.0f, 0.1f);
        GL11.glEnd();
    }
    private float rotation = 0.0f;

    private void drawSpinningCube() {
        GL11.glPushMatrix();
        GL11.glRotatef(rotation, 1.0f, 1.0f, 1.0f);
        GL11.glBegin(GL11.GL_QUADS);
        // Front Face
        GL11.glColor3f(1.0f, 0.0f, 0.0f);
        GL11.glVertex3f(-0.5f, -0.5f, 0.5f);
        GL11.glVertex3f(0.5f, -0.5f, 0.5f);
        GL11.glVertex3f(0.5f, 0.5f, 0.5f);
        GL11.glVertex3f(-0.5f, 0.5f, 0.5f);
        // Back Face
        GL11.glColor3f(0.0f, 1.0f, 0.0f);
        GL11.glVertex3f(-0.5f, -0.5f, -0.5f);
        GL11.glVertex3f(0.5f, -0.5f, -0.5f);
        GL11.glVertex3f(0.5f, 0.5f, -0.5f);
        GL11.glVertex3f(-0.5f, 0.5f, -0.5f);
        // Top Face
        GL11.glColor3f(0.0f, 0.0f, 1.0f);
        GL11.glVertex3f(-0.5f, 0.5f, -0.5f);
        GL11.glVertex3f(0.5f, 0.5f, -0.5f);
        GL11.glVertex3f(0.5f, 0.5f, 0.5f);
        GL11.glVertex3f(-0.5f, 0.5f, 0.5f);
        // Bottom Face
        GL11.glColor3f(1.0f, 1.0f, 0.0f);
        GL11.glVertex3f(-0.5f, -0.5f, -0.5f);
        GL11.glVertex3f(0.5f, -0.5f, -0.5f);
        GL11.glVertex3f(0.5f, -0.5f, 0.5f);
        GL11.glVertex3f(-0.5f, -0.5f, 0.5f);
        // Right face
        GL11.glColor3f(1.0f, 0.0f, 1.0f);
        GL11.glVertex3f(0.5f, -0.5f, -0.5f);
        GL11.glVertex3f(0.5f, 0.5f, -0.5f);
        GL11.glVertex3f(0.5f, 0.5f, 0.5f);
        GL11.glVertex3f(0.5f, -0.5f, 0.5f);
        // Left Face
        GL11.glColor3f(0.0f, 1.0f, 1.0f);
        GL11.glVertex3f(-0.5f, -0.5f, -0.5f);
        GL11.glVertex3f(-0.5f, 0.5f, -0.5f);
        GL11.glVertex3f(-0.5f, 0.5f, 0.5f);
        GL11.glVertex3f(-0.5f, -0.5f, 0.5f);
        GL11.glEnd();
        GL11.glPopMatrix();

        rotation += 1.5f;
    }

    public void drawSquare() {
        GL11.glPushMatrix();
        //GL11.glBegin();
        // use glBegin and start a square
        GL11.glBegin(GL11.GL_QUADS);
        GL11.glColor3f(1.0F,0.0F,1.0F);
        //glVertex3f(-0.5)
    }
    public void drawCube(int x, int y, int z)
    {
        GL11.glPushMatrix();
        GL11.glBegin(GL11.GL_QUADS);
        // Front Face
        GL11.glColor3f(1.0f, 0.0f, 0.0f);
        GL11.glVertex3f(-0.5f + x, -0.5f + y, 0.5f + z);
        GL11.glVertex3f(0.5f + x, -0.5f + y, 0.5f + z);
        GL11.glVertex3f(0.5f + x, 0.5f + y, 0.5f + z);
        GL11.glVertex3f(-0.5f + x, 0.5f + y, 0.5f + z);
        // Back Face
        GL11.glColor3f(0.0f, 1.0f, 0.0f);
        GL11.glVertex3f(-0.5f + x, -0.5f + y, -0.5f + z);
        GL11.glVertex3f(0.5f + x, -0.5f + y, -0.5f + z);
        GL11.glVertex3f(0.5f + x, 0.5f + y, -0.5f + z);
        GL11.glVertex3f(-0.5f + x, 0.5f + y, -0.5f + z);
        // Top Face
        GL11.glColor3f(0.0f, 0.0f, 1.0f);
        GL11.glVertex3f(-0.5f + x, 0.5f + y, -0.5f + z);
        GL11.glVertex3f(0.5f + x, 0.5f + y, -0.5f + z);
        GL11.glVertex3f(0.5f + x, 0.5f + y, 0.5f + z);
        GL11.glVertex3f(-0.5f + x, 0.5f + y, 0.5f + z);
        // Bottom Face
        GL11.glColor3f(1.0f, 1.0f, 0.0f);
        GL11.glVertex3f(-0.5f + x, -0.5f + y, -0.5f + z);
        GL11.glVertex3f(0.5f + x, -0.5f + y, -0.5f + z);
        GL11.glVertex3f(0.5f + x, -0.5f + y, 0.5f + z);
        GL11.glVertex3f(-0.5f + x, -0.5f + y, 0.5f + z);
        // Right face
        GL11.glColor3f(1.0f, 0.0f, 1.0f);
        GL11.glVertex3f(0.5f + x, -0.5f + y, -0.5f + z);
        GL11.glVertex3f(0.5f + x, 0.5f + y, -0.5f + z);
        GL11.glVertex3f(0.5f + x, 0.5f + y, 0.5f + z);
        GL11.glVertex3f(0.5f + x, -0.5f + y, 0.5f + z);
        // Left Face
        GL11.glColor3f(0.0f, 1.0f, 1.0f);
        GL11.glVertex3f(-0.5f + x, -0.5f + y, -0.5f + z);
        GL11.glVertex3f(-0.5f + x, 0.5f + y, -0.5f + z);
        GL11.glVertex3f(-0.5f + x, 0.5f + y, 0.5f + z);
        GL11.glVertex3f(-0.5f + x, -0.5f + y, 0.5f + z);
        GL11.glEnd();
        GL11.glPopMatrix();
    }
}

