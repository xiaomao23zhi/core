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

    public String grabFrame(String streamUrl, int frames) {

        try (FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(streamUrl)) {

            grabber.setOption("hwaccel", "cuda");

            grabber.start();

            try (Java2DFrameConverter java2DFrameConverter = new Java2DFrameConverter()) {
                for (int i = 0; i < frames; i++) {
                    Frame frame = grabber.grabFrame();
                    BufferedImage bufferedImage = java2DFrameConverter.convert(frame);

                    ImageIO.write(bufferedImage, "png", new File("frame-dump/video-frame-" + System.currentTimeMillis() + ".png"));
                }
            } catch (IOException e) {
                log.error(e.getMessage());
            }

        } catch (FrameGrabber.Exception e) {
            log.error(e.getMessage());
        }

        return "";
    }

}
