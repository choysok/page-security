package com.page.security.core.validate.code;

import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@RestController
public class ValidateCodeController {

     static final String SESSION_KEY = "SESSION_KEY_IMAGE_CODE";

    private SessionStrategy sessionStrategy  = new HttpSessionSessionStrategy();

    @GetMapping("/code/image")
    public  void createCode(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {

        ImageCode imageCode = createImageCode(httpServletRequest);
        // 将认证码存入SESSION
        sessionStrategy.setAttribute(new ServletWebRequest(httpServletRequest),SESSION_KEY,imageCode);
        // 输出图象到页面
        ImageIO.write(imageCode.getImage(),"JPEG",httpServletResponse.getOutputStream());
    }

    private ImageCode createImageCode(HttpServletRequest httpServletRequest) {
        //实现图片验证码的逻辑,内存中创建图象
        int width =67;
        int height = 23;
      //  Color mywhite = new Color(255,255,255);//Color white
        BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        // 获取图形上下文
        Graphics graphics = image.getGraphics();
        //生成随机类
        Random  random = new Random();
        // 设定背景色
        graphics.setColor(getRandColor(200,250));
        graphics.fillRect(0,0,width,height);
        //设定字体
        graphics.setFont(new Font("Times New Roman",Font.ITALIC,20));
        // 随机产生155条干扰线，使图象中的认证码不易被其它程序探测到
        graphics.setColor(getRandColor(160,200));
        for (int i = 0; i <155 ; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            graphics.drawLine(x,y,x+xl,y+yl);
        }
        // 取随机产生的认证码(4位数字)
        String sRand = "";
        for (int i = 0; i < 4; i++) {
            String rand = String.valueOf(random.nextInt(10));
            sRand += rand;
            // 将认证码显示到图象中
            graphics.setColor(new Color(20+random.nextInt(110),20+random.nextInt(110),20+random.nextInt(110)));
            graphics.drawString(rand,13*i+6,16);
        }
        // 图象生效
        graphics.dispose();

        return new ImageCode(image,sRand,60);
    }

    Color getRandColor(int fc,int bc){//给定范围获得随机颜色
        Random random = new Random();
        if(fc>255) fc=255;
        if(bc>255) bc=255;
        int r=fc+random.nextInt(bc-fc);
        int g=fc+random.nextInt(bc-fc);
        int b=fc+random.nextInt(bc-fc);
        return new Color(r,g,b);
    }
}

