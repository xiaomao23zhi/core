package com.github.xiaomao23zhi.core.service;

import lombok.extern.slf4j.Slf4j;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author xiaomao23lzhi
 */
@Component
@Slf4j
public class FFmpegService {

    public String grabFrame(String streamUrl, int interval, int total) {

        try (FFmpegFrameGrabber grabber = FFmpegFrameGrabber.createDefault(streamUrl)) {

            grabber.setOption("hwaccel", "cuda");

            grabber.start();

            int width = grabber.getImageWidth();
            int height = grabber.getImageHeight();
            int frameRate = (int) Math.round(grabber.getFrameRate());

            log.info("video width:{} height:{} ,{} fps ,video codec:{} length:{}", width, height,
                    grabber.getFrameRate(), grabber.getVideoCodec(),
                    grabber.getLengthInFrames());

            Frame frame;

            int i = 0;
            int j = 0;

            while ((frame = grabber.grabFrame(false, true, true, false)) != null) {

                if (i % (frameRate * interval) == 0.0) {

                    log.debug("======== Frame: {}, Total: {} ========", i, j);
                    toImage(frame, "");

                    if (j++ > total) {
                        break;
                    }
                }

                i++;
            }

            log.info("======== {} ========", i);

            grabber.stop();

        } catch (FrameGrabber.Exception e) {
            log.error(e.getMessage());
        }

        return "";
    }

    public boolean toImage(Frame frame, String targetPath) {

        boolean result = true;

        try (Java2DFrameConverter converter = new Java2DFrameConverter()) {

            BufferedImage bufferedImage = converter.convert(frame);
            ImageIO.write(bufferedImage, "png", new File("/tmp/video-frame-" + System.currentTimeMillis() + ".png"));
        } catch (IOException e) {
            log.error(e.getMessage());
            e.printStackTrace();
            result = false;
        }

        return result;
    }
}
