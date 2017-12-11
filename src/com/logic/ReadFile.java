package com.logic;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * @Author:langxy
 * @date 创建时间：2017/11/13 16:01
 */
public class ReadFile {
    public String read(String fileName){

        StringBuilder stringBuilder = new StringBuilder();

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            String s;
            while ((s = bufferedReader.readLine()) != null){
                stringBuilder.append(s);
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
}
