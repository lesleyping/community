package com.lxp.community;

import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;

public class WkTests {

    public static void main(String[] args) {
        String wkImageCommand = "/usr/local/bin/wkhtmltoimage";
        String htmlUrl = "www.baidu.com";
        String wkImageStorage = "/Users/lixiping/WorkSpace/nowcoder/data/wkimage";
        String fileName = "2";
        String suffix = ".png";

        String cmd = wkImageCommand + " --quality 75 " + htmlUrl + " " + wkImageStorage + "/" + fileName + suffix;
        //String cmd = "rm -rf /Users/lixiping/WorkSpace/nowcoder/data/wk-image";
        //System.out.println(cmd);
        try {
            Runtime.getRuntime().exec(cmd);
            Thread.sleep(1000);
            System.out.println("ok.");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
