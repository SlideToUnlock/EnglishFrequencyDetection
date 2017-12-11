package com.logic;

import com.csvreader.CsvWriter;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * @Author:langxy
 * @date 创建时间：2017/11/13 20:28
 */
public class WriteFile {
    public String createFile(){
        if (Word.WordUtil.list.size() == 0){
            return "-----请选择文件-----";
        }
        String fileName = "D://output.csv";
        CsvWriter csvWriter = null;
        try {
            csvWriter = new CsvWriter(fileName, ',', Charset.forName("UTF-8"));
            for (String str: Word.WordUtil.list){
                csvWriter.writeRecord(new String[]{str});
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        csvWriter.close();
        return "-----导出CSV成功-----";
    }
}
