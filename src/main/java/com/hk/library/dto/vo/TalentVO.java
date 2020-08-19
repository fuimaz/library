package com.hk.library.dto.vo;

import lombok.Data;
import org.apache.commons.lang.StringUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author 
 * @since 2020-04-08
 */
@Data
public class TalentVO implements Serializable {

    private static final long serialVersionUID=1L;

    private String tid;

    private String name;

    private String degree;

    private String type;

    private String sex;

    private String remarks;

    /**
     * 生日
     */
    private String birth;

    /**
     * 毕业院校
     */
    private String school;


    /**
     * 地址
     */
    private String address;

    /**
     * 工作单位
     */
    private String workUnit;

    /**
     * 手机号
     */
    private String phoneNo;

    /**
     * 座机号
     */
    private String mobileNo;

    /**
     * 邮箱
     */
    private String email;

    /**
     * qq号
     */
    private String qq;

    /**
     * 爱好特长
     */
    private String interest;

    /**
     * 工艺经历
     */
    private String experience;


    /**
     * 审核状态
     */
    private String auditing;

    /**
     * 顺序
     */
    private Integer order;

    /**
     * 人才照片
     */
    private String img;

    public static void main(String arg[]) throws Exception {
        List<String> fileNames = folderMethod1("/Users/jiman/workspace/culture-mini-program/src/main/java/com/hk/culture/mini/program/entity");

        String comment = "";
        String type = "";
        String name = "";
        for (String file : fileNames) {
            System.out.println(file);
            List<String> stringBuffer = readFileByBytes(file);
            for (String line : stringBuffer) {
                if (line.contains("/*") || line.contains("*/")) {
                    continue;
                }

                if (line.contains("*")) {
                    comment = line.replace("*", "");

                }

                if (line.contains("private") && !line.contains("static")) {
                    String[] strings = StringUtils.strip(line).split(" ");
                    type = strings[1];
                    name = strings[2].replace(";", "");
                }

                if (StringUtils.isNotEmpty(type)) {
                    System.out.println(name + " | " + type + " | " + StringUtils.strip(comment));
                    comment = "";
                    type = "";
                    name = "";
                }
            }

        }

    }

    public static List<String> readFileByBytes(String fileName) throws Exception {
        File file = new File(fileName);
        BufferedReader reader = null;
        List<String> sb = new ArrayList<>();
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                line++;
                if (!tempString.contains("*") && !tempString.contains("private")) {
                    continue;
                }
                sb.add(tempString);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }

        return sb;
    }

    public static List<String> folderMethod1(String path) {
        int fileNum = 0, folderNum = 0;
        File file = new File(path);
        LinkedList<File> list = new LinkedList<>();
        List<String> fileNames = new ArrayList<>();

        if (file.exists()) {
            if (null == file.listFiles()) {
                return null;
            }
            list.addAll(Arrays.asList(file.listFiles()));
            while (!list.isEmpty()) {
                File files = list.removeFirst();
                if (null == files) {
                    continue;
                }
//                System.out.println("文件:" + files.getAbsolutePath());
                fileNum++;
                fileNames.add(files.getAbsolutePath());
            }
        } else {
            System.out.println("文件不存在!");
        }
//        System.out.println("文件夹数量:" + folderNum + ",文件数量:" + fileNum);

        return fileNames;
    }

}
