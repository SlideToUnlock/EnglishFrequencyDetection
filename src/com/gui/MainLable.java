package com.gui;

import com.logic.ReadFile;
import com.logic.Word;
import com.logic.WriteFile;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

/**
 * @Author:langxy
 * @date 创建时间：2017/11/14 10:52
 */
public class MainLable extends JFrame{

    private JPanel panel;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JTextField textField1;
    private JTextField textField2;
    private JList list1;

    public String path = null;

    public static void main(String[] args) {
        JFrame frame = new JFrame("langxy");
        frame.setContentPane(new MainLable().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(200, 200);
        frame.setSize(400, 600);
        frame.setVisible(true);
    }
    //按钮点击监听事件
    public MainLable() {
        textField1.setEditable(false);
        textField2.setEditable(false);
        DefaultListModel dlm = new DefaultListModel();
        list1.setModel(dlm);
        //选择文件夹的监听事件
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result;
                Component chatFrame = null;
                JFileChooser fileChooser = new JFileChooser();
                FileSystemView fsv = FileSystemView.getFileSystemView();
                fileChooser.setCurrentDirectory(fsv.getHomeDirectory());
                fileChooser.setDialogTitle("请选择文件路径");
                fileChooser.setApproveButtonText("确定");
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                fileChooser.setFileFilter(new FileNameExtensionFilter("TXT", "txt"));
                result = fileChooser.showOpenDialog(chatFrame);
                if (JFileChooser.APPROVE_OPTION == result) {
                    path = fileChooser.getSelectedFile().getPath();
                }
                textField1.setText(path);
            }
        });
        //识别处理的监听事件
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ReadFile readFile = new ReadFile();
                String path1 = path.replace("\\", "/");
                String context = readFile.read(path);
                int count = 0;
                Scanner scanner = new Scanner(
                        context.replace(",", " ")
                                .replace("，", " ")
                                .replace(".", " ")
                                .replace("。", " ")
                                .replace("?", " ")
                                .replace("？", " ")
                                .replace("!", " ")
                                .replace("！", " ")
                                .replace(":", " ")
                                .replace("：", " ")
                                .replace("'", " ")
                                .replace("  ", " ")
                                .replace("   ", " ")
                ).useDelimiter(" ");
                String item;
                while (scanner.hasNext()) {
                    count++;
                    item = scanner.next().toString();
                    Word.WordUtil.list.add(item);
                    dlm.addElement(item);
                }
                textField2.setText(String.valueOf(count));
            }
        });
        //清除按钮
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Word.WordUtil.list.clear();
                textField1.setText("");
                textField2.setText("");
                dlm.clear();
            }
        });
        //导出按钮监听
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WriteFile writeFile = new WriteFile();
                dlm.addElement(writeFile.createFile());
            }
        });
    }
}


