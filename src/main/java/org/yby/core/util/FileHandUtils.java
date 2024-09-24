package org.yby.core.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 * 文件处理工具
 */
public final class FileHandUtils {
    private FileHandUtils(){}

    public static List<String> readAllLine(String path){
        List<String> strs = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if(!checkAllNull(line)){ //跳过空行
                    //System.out.println(line);
                    strs.add(line.trim());
                }
            }
        } catch (IOException e) {
            System.out.println("你输入的文件路径不正确！");
            return null;
        }
        return strs;
    }

    /**
     * 将一个文章按句子分开
     * @param path 文件路径
     * @return 所有句子
     */
    public static String readAllSentence(String path){
        List<String> list = readAllLine(path);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Objects.requireNonNull(list).size(); i++) {
            sb.append(list.get(i));
        }
        return sb.toString();
    }

    public static void write(String content,String path){
        try {
            // 创建 FileWriter 对象
            FileWriter fileWriter = new FileWriter(path);

            // 创建 BufferedWriter 对象
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            // 将字符串写入文件
            bufferedWriter.write(content);

            // 关闭 BufferedWriter
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println("文件路径错误！");
        }
    }

    /**
     * 检查一个字符串是不是全部是空格
     */
    public static boolean checkAllNull(String str){
        return Pattern.matches("^\\s*$", str);
    }


}
