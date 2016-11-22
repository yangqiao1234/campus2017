package com.qunar.homework;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by joe on 2016/11/11.
 * 统计java代码的有效行数，
 * 测试代码，自身
 */
public class EffectiveLines {
    public static void main(String[] args) throws IOException {
        File file = new File("D:\\Program Files\\java\\jdk1.8.0_65\\src\\com\\sun\\corba\\se\\impl\\activation\\CommandHandler.java");
        String s = inputCode(file);
        System.out.println(count(s));
    }

    /**
     * 输入java文件
     * @param f
     * @return  java文件字符String，每行手动添加'\n'
     * @throws IOException
     */
    public static String inputCode(File f) throws IOException {

        StringBuffer code = new StringBuffer();
        String s = code.toString();
        BufferedReader reader = new BufferedReader(new FileReader(f));
        String buffer = null;
        while ((buffer = reader.readLine()) != null) {
            code.append(buffer).append("\n");
        }
        return code.toString();
    }

    /**
     * 统计有效行数
     * 去除文档注释，段注释，单行注释
     * @param s
     * @return 有效行数
     */
    public static int count(String s) {

        s = removeMult(s);
        s = removeSingle(s);
        int effectiveLines = 0;
        String[] strings = s.split("\n");

        for (int i = 0; i < strings.length; i++) {
            if (!"".equals((strings[i].trim()))) {
//                System.out.println(strings[i]);
                effectiveLines++;
            }
        }
        return effectiveLines;
    }

    /**
     * 移除单行注释
     * @param s
     * @return  移除后的代码
     */
    public static String removeSingle(String s) {
        String p1 = "//.*?";
        Pattern pattern = Pattern.compile(p1);
        Matcher matcher = pattern.matcher(s);
        return matcher.replaceAll("");
    }

    /**
     * 移除多行注释
     * 段注释和文档注释
     * @param s
     * @return  移除后注释后的代码
     */
    public static String removeMult(String s) {
        String p2 = "/\\*.*?\\*/";
        Pattern pattern = Pattern.compile(p2, Pattern.DOTALL);
        Matcher matcher = pattern.matcher(s);
        return matcher.replaceAll("");
    }

}
