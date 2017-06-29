package com.pzj.core.oldimgsrv.utils;

import java.io.IOException;

import org.im4java.core.ConvertCmd;
import org.im4java.core.IM4JavaException;
import org.im4java.core.IMOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ImageUtils extends Thread {

    public static final Logger logger = LoggerFactory.getLogger(ImageUtils.class);

    private String             srcPath;
    private String             newPath;
    private Integer            size;

    public static void zoomImage(String srcPath, String newPath, Integer size) throws IOException, InterruptedException, IM4JavaException {
        //linux直接在系统中加载
        System.setProperty("jmagick.systemclassloader", "no");
        IMOperation op = new IMOperation();

        if (logger.isDebugEnabled()) {
            logger.debug("ImageUtils:生成对应图片，srcpath:" + srcPath + "\nnewPath:" + newPath + "\nsize:" + size);
        }

        op.addImage(new String[] { srcPath });
        op.resize(size);
        op.addImage(new String[] { newPath });
        ConvertCmd convert = new ConvertCmd();
        //windows 手动加载系统中的imageMapgic
        //convert.setSearchPath("D:\\Program Files (x86)\\ImageMagick-6.3.9-Q8");
        convert.run(op, new Object[0]);
    }

    public ImageUtils(String srcPath, String newPath, Integer size) {
        this.srcPath = srcPath;
        this.newPath = newPath;
        this.size = size;
    }

    @Override
    public void run() {
        try {
            zoomImage(this.srcPath, this.newPath, this.size);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IM4JavaException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("finally")
    public static int createThumbnail(String srcPath, String newPath, Integer size) {
        int i = 0;
        try {
            zoomImage(srcPath, newPath, size);
            i = 1;
        } catch (Exception e) {
            i = 0;
            e.printStackTrace();
        } finally {
            return i;
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException, IM4JavaException {
        zoomImage("E:\\apache-tomcat-7.0.55\\webapps\\tms_image\\2015-03-27\\14274642198902681\\14274642198878111.jpg",
            "E:\\apache-tomcat-7.0.55\\webapps\\tms_image\\2015-03-27\\14274642198902681\\60\\14274642198878111.jpg", Integer.valueOf(60));
    }
}