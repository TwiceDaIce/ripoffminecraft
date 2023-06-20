package render;

import org.jetbrains.annotations.NotNull;
import org.lwjgl.stb.STBImage;
import org.lwjgl.system.MemoryStack;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.stb.STBImage.stbi_load;

public class image_parser {
    public ByteBuffer get_image() {
        return image;
    }

    public int get_width() {
        return width;
    }

    public int get_height() {
        return height;
    }

    private ByteBuffer image;
    private int width, height;

    image_parser(int width, int height, ByteBuffer image) {
        this.image = image;
        this.height = height;
        this.width = width;
    }
    @NotNull
    public static image_parser load_image(String path) {
        String path1 = "C:/Users/twice/Desktop/ripoffminecraft/build/classes/java/main/icon_01.png";
        System.out.println(path + " in load_image");
        ByteBuffer image;
        int width = 0, height = 0;
        System.out.println(width + " " + height);
        try (MemoryStack stack = MemoryStack.stackPush()) {
            IntBuffer comp = stack.mallocInt(1);
            IntBuffer w = stack.mallocInt(1);
            IntBuffer h = stack.mallocInt(1);

            image = stbi_load(path1, w, h, comp, 4);
            if (image == null)
            {
                System.out.println(path + " " + w + " " + h + " " + comp);
                System.out.println("image is null");
                throw new IOException(STBImage.stbi_failure_reason());
            } else
            {
                width = w.get();
                height = h.get();
                System.out.println("not null?? w: " + width + " h: " + height);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new image_parser(width, height, image);
    }
}
