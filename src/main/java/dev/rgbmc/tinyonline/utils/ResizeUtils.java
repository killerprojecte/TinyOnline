package dev.rgbmc.tinyonline.utils;

import org.imgscalr.Scalr;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;

public class ResizeUtils {
    public static BufferedImage resize(byte[] image, int targetWidth, int targetHeight) throws Exception {
        BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(image));
        return Scalr
                .resize(
                        bufferedImage,
                        Scalr.Method.AUTOMATIC,
                        Scalr.Mode.AUTOMATIC,
                        targetWidth,
                        targetHeight,
                        Scalr.OP_ANTIALIAS
                );
    }
}
