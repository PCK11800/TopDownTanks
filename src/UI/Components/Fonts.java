package UI.Components;

import org.jsfml.graphics.Font;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class Fonts extends Font {
    public static String PIXEL = "Resources/Fonts/Pixel.ttf";
    public static String MONTSERRAT = "Resources/Fonts/Montserrat.otf";

    public Fonts(String fontPath)
    {
        setFontPath(fontPath);
    }

    public void setFontPath(String fontPath)
    {
        Path path = FileSystems.getDefault().getPath(fontPath);
        try
        {
            loadFromFile(path);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
