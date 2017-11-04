package Lab1final;

import java.io.*;
import java.util.Scanner;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.BatchUpdateException;
//import sun.awt.AWTAccessor.ToolkitAccessor;



/**
 *
 * @author OB
 *GUIdesign
 */







public class main extends JFrame implements ActionListener{
    private BufferedImage bgImage;
    public static String fileskin=new String();

    public  main() throws Exception{

    }
    public void paint(Graphics g) {
        g.drawImage(bgImage, 0, 0, this.getWidth(), this.getHeight(), this);
    }
    public static String randomwalklocate="random.txt"; //随机游走存储路径
    public static int flsg=1;
    public static String finalstr0=new String();
    public static Graph G0;
    public static void clear() throws AWTException
    {
        Robot r = new Robot();
        r.mousePress(InputEvent.BUTTON3_MASK);       // 按下鼠标右键
        r.mouseRelease(InputEvent.BUTTON3_MASK);    // 释放鼠标右键
        r.keyPress(KeyEvent.VK_CONTROL);             // 按下Ctrl键
        r.keyPress(KeyEvent.VK_R);                    // 按下R键
        r.keyRelease(KeyEvent.VK_R);                  // 释放R键
        r.keyRelease(KeyEvent.VK_CONTROL);            // 释放Ctrl键
        r.delay(100);

    }
    public static void main(String[] args) throws Exception{



        main mf=new main();

        fileskin="z.jpg";
        mf.bgImage=ImageIO.read(new File(fileskin));
        mf.setTitle("测试");
        mf.setBounds(300, 100, 1200, 750);
        mf.setBackground(Color.GRAY);
        mf.setResizable(false);
        mf.setVisible(true);
        mf.setLayout(null);
        mf.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                // TODO Auto-generated method stub
                System.exit(0);
            }
        });
        //获取文件
        JButton buttonreadfile =new JButton("File Open");
        buttonreadfile.setBackground(Color.white);
        buttonreadfile.setBounds(30, 40, 100, 50);
        mf.add(buttonreadfile);


        Label labelreadfile=new Label();
        labelreadfile.setText("←please enter your file located");
        labelreadfile.setBackground(Color.GRAY);
        labelreadfile.setFont(new Font("宋体",Font.PLAIN,25));

        labelreadfile.setBounds(130, 40, 470, 50);
        mf.add(labelreadfile);


        //显示预处理文本
        Label labelstrdeal=new Label();
        labelstrdeal.setText("show the dealed string");
        labelstrdeal.setBackground(Color.GRAY);
        labelstrdeal.setFont(new Font("宋体",Font.PLAIN,25));
        labelstrdeal.setBounds(100, 150, 500, 100);
        mf.add(labelstrdeal);
        Button buttonstrdeal=new Button("ensure");
        buttonstrdeal.setBounds(30, 150, 70, 100);
        buttonstrdeal.setBackground(Color.white);
        mf.add(buttonstrdeal);
        //展示邻接表
        Label labelLink=new Label();
        labelLink.setText("show the linked graph:");
        labelLink.setBackground(Color.GRAY);
        labelLink.setFont(new Font("宋体",Font.PLAIN,25));
        labelLink.setBounds(100, 300, 500, 100);
        mf.add(labelLink);
        Button buttonLink=new Button("ensure");
        buttonLink.setBounds(30, 300, 70, 100);
        buttonLink.setBackground(Color.WHITE);
        mf.add(buttonLink);

        //显示图片
        Label labelgraph=new Label("Show the Graph:");
        labelgraph.setBackground(Color.GRAY);
        labelgraph.setFont(new Font("宋体",Font.PLAIN,25));
        labelgraph.setBounds(650, 500,250, 100);
        mf.add(labelgraph);
        Button buttongraph=new Button("buttongraph");
        buttongraph.setBounds(900, 500, 100, 100);
        buttongraph.setBackground(Color.WHITE);
        mf.add(buttongraph);
        //桥接词
        Label labelbridgeword1=new Label("word1");
        Label labelbridgeword2=new Label("word2");
        labelbridgeword1.setBackground(Color.GRAY);
        labelbridgeword1.setFont(new Font("宋体",Font.PLAIN,25));
        labelbridgeword1.setBounds(100, 400, 250, 50);
        labelbridgeword2.setBackground(Color.GRAY);
        labelbridgeword2.setFont(new Font("宋体",Font.PLAIN,25));
        labelbridgeword2.setBounds(350, 400, 250, 50);
        mf.add(labelbridgeword1);
        mf.add(labelbridgeword2);

        TextField textfieldbridgeword1=new TextField();
        textfieldbridgeword1.setFont(new Font("宋体",Font.PLAIN,25));
        textfieldbridgeword1.setBounds(100, 450, 250, 50);
        mf.add(textfieldbridgeword1);
        TextField textfieldbridgeword2=new TextField();
        textfieldbridgeword2.setFont(new Font("宋体",Font.PLAIN,25));
        textfieldbridgeword2.setBounds(350, 450, 250, 50);
        mf.add(textfieldbridgeword2);

        Label labelbridgeresult=new Label("bridge:");
        labelbridgeresult.setBackground(Color.GRAY);
        labelbridgeresult.setFont(new Font("宋体",Font.PLAIN,25));
        labelbridgeresult.setBounds(100, 500, 250, 50);
        mf.add(labelbridgeresult);
        Label textfieldbridgeresult=new Label();

        Button buttonbridge =new Button("ensure");
        buttonbridge.setBounds(30, 450, 70, 100);
        buttonbridge.setBackground(Color.white);
        mf.add(buttonbridge);
        //生成新文本

        Button buttonnewstr=new Button("ensure");
        buttonnewstr.setBounds(30, 600, 70, 100);
        buttonnewstr.setBackground(Color.WHITE);
        mf.add(buttonnewstr);
        Label labelnewstrinput=new Label("input new article:");
        labelnewstrinput.setBackground(Color.GRAY);
        labelnewstrinput.setFont(new Font("宋体",Font.PLAIN,25));
        labelnewstrinput.setBounds(100, 550, 500, 50);
        mf.add(labelnewstrinput);
        TextField textfieldnewstrinput=new TextField();
        textfieldnewstrinput.setFont(new Font("宋体",Font.PLAIN,25));
        textfieldnewstrinput.setBounds(100, 600, 500, 50);
        mf.add(textfieldnewstrinput);
        Label labelnewstr=new Label("new created article:");
        labelnewstr.setBackground(Color.GRAY);
        labelnewstr.setFont(new Font("宋体",Font.PLAIN,25));
        labelnewstr.setBounds(100, 650, 500, 50);
        mf.add(labelnewstr);
        //最短路径
        Label labelshortestpath=new Label("calc the shortest path:");
        labelshortestpath.setBackground(Color.GRAY);
        labelshortestpath.setFont(new Font("宋体",Font.PLAIN,25));
        labelshortestpath.setBounds(650, 30, 500, 50);
        mf.add(labelshortestpath);
        Label labelshortestpathword1=new Label("Word1");
        labelshortestpathword1.setBackground(Color.GRAY);
        labelshortestpathword1.setFont(new Font("宋体",Font.PLAIN,25));
        labelshortestpathword1.setBounds(650, 80, 250, 50);
        mf.add(labelshortestpathword1);
        Label labelshortestpathword2=new Label("Word2");
        labelshortestpathword2.setBackground(Color.GRAY);
        labelshortestpathword2.setFont(new Font("宋体",Font.PLAIN,25));
        labelshortestpathword2.setBounds(900, 80, 250, 50);
        mf.add(labelshortestpathword2);
        TextField textfieldshortestpathword1=new TextField();
        textfieldshortestpathword1.setFont(new Font("宋体",Font.PLAIN,25));
        textfieldshortestpathword1.setBounds(650, 130, 250, 50);
        mf.add(textfieldshortestpathword1);
        TextField textfieldshortestpathword2=new TextField();
        textfieldshortestpathword2.setFont(new Font("宋体",Font.PLAIN,25));
        textfieldshortestpathword2.setBounds(900, 130, 250, 50);
        mf.add(textfieldshortestpathword2);
        Label labelshortestpathshow=new Label("Show shortest path");
        labelshortestpathshow.setBackground(Color.GRAY);
        labelshortestpathshow.setFont(new Font("宋体",Font.PLAIN,25));
        labelshortestpathshow.setBounds(650, 180, 250, 50);
        mf.add(labelshortestpathshow);
        Button buttonshortestpath =new Button("ensure");
        buttonshortestpath.setBackground(Color.WHITE);
        buttonshortestpath.setBounds(900, 180, 250, 50);
        mf.add(buttonshortestpath);
        //随机游走
        TextArea textArea1=new TextArea();
        Label labelrandom=new Label("Show the random string");
        labelrandom.setBackground(Color.GRAY);
        labelrandom.setFont(new Font("宋体",Font.PLAIN,25));
        labelrandom.setBounds(650, 330, 300, 50);
        mf.add(labelrandom);
        Button buttonrandom=new Button("ensure");
        buttonrandom.setBackground(Color.WHITE);
        buttonrandom.setBounds(900, 330, 250, 50);
        mf.add(buttonrandom);
        Label labelstop=new Label("Stop Showing String!");
        labelstop.setBackground(Color.GRAY);
        labelstop.setFont(new Font("宋体",Font.PLAIN,25));
        labelstop.setBounds(650, 380,300, 50);
        mf.add(labelstop);
        Button buttonstop=new Button("ensure");
        buttonstop.setBackground(Color.WHITE);
        buttonstop.setBounds(900, 380, 250, 50);
        mf.add(buttonstop);

        //开始执行
        buttonreadfile.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                StrDeal sd1=new StrDeal();

                String strFile1 = null;
                //strFile1=textfieldreadfile.getText().toString();
                JFileChooser jfc=new JFileChooser();
                jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );
                jfc.showDialog(new JLabel(), "选择");
                File file=jfc.getSelectedFile();
                if(file.exists()&&file.isFile())strFile1 =file.getAbsolutePath();
                finalstr0 = null;
                try {
                    finalstr0 = sd1.Strdeal(strFile1);
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                if(sd1.Strexit(strFile1)==0) {
                    Dialog dialogstr=new Dialog(mf, "NOTICE!");
                    dialogstr.setVisible(true);
                    dialogstr.setBounds(200, 200,400, 400);
                    dialogstr.setLayout(null);
                    Label labeldialog=new Label("文件不存在！程序即将关闭!");
                    labeldialog.setBackground(Color.red);
                    labeldialog.setBounds(0, 0, 400, 350);
                    labeldialog.setFont(new Font("宋体",Font.PLAIN,25));
                    dialogstr.add(labeldialog);
                    Button buttonnofile=new Button("ensure");
                    buttonnofile.setBackground(Color.WHITE);
                    buttonnofile.setBounds(0, 350, 400, 50);
                    dialogstr.add(buttonnofile);
                    buttonnofile.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            // TODO Auto-generated method stub
                            System.exit(0);
                        }
                    });
                }else {
                    Dialog dialogstr=new Dialog(mf, "NOTICE!");
                    dialogstr.setVisible(true);
                    dialogstr.setBounds(200, 200, 200, 200);
                    Label labeldialog=new Label("文件读取成功！");
                    labeldialog.setFont(new Font("宋体",Font.PLAIN,25));
                    dialogstr.add(labeldialog);
                    dialogstr.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosing(WindowEvent e) {
                            // TODO Auto-generated method stub
                            dialogstr.dispose();
                        }
                    });
                    buttonstrdeal.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            // TODO Auto-generated method stub
                            Frame mfstr=new Frame("SHOWSTR");
                            mfstr.setBackground(Color.GRAY);
                            mfstr.setBounds(250, 250, 700, 400);
                            mfstr.setVisible(true);
                            TextArea textArea=new TextArea();
                            textArea.setFont(new Font("宋体",Font.PLAIN,25));
                            textArea.setBounds(50, 20, 600, 360);
                            mfstr.add(textArea);
                            textArea.setText(finalstr0);
                            mfstr.addWindowListener(new WindowAdapter() {
                                @Override
                                public void windowClosing(WindowEvent e) {
                                    // TODO Auto-generated method stub
                                    mfstr.dispose();
                                }
                            });

                        }
                    });
                    //哈希表
                    ExerciseHash Ehash0=new ExerciseHash();
                    Ehash0.calcletter(finalstr0);
                    /**
                     * 根据哈希表建立邻接矩阵
                     */
                    Matrix mt0=new Matrix();
                    mt0.Creatematrix(Ehash0, finalstr0);
                    /**
                     * 根据邻接矩阵建立有向图(邻接表)
                     */
                    String[] splitstr0=finalstr0.split(" ");
                    G0=new Graph(mt0.size, splitstr0.length-1, mt0);
                    /**
                     * 最短路径
                     */
                    int[][] path0=G0.Floyd(mt0);
                    /**
                     * 随机游走
                     */
                    /**
                     * 展示有向图
                     **/

                    buttongraph.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            // TODO Auto-generated method stub
                            Graphv GG0=new Graphv();
                            String dotFormat0=G0.Createpicture(mt0);
                            //System.out.println(dotFormat0);
                            GG0.createDotGraph(dotFormat0,"DotGraph");
                            DrawFrame frame0 = new DrawFrame("DotGraph");
                            //frame0.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                            frame0.pack();
                            frame0.show();

                        }
                    });

                    String[] printstr0=G0.LinklistPrint1(mt0);
                    buttonLink.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            // TODO Auto-generated method stub
                            Frame mflink=new Frame("SHOWLINK");
                            mflink.setBackground(Color.GRAY);
                            mflink.setBounds(200, 200, 400, 400);
                            mflink.setVisible(true);
                            mflink.addWindowListener(new WindowAdapter() {

                                @Override
                                public void windowClosing(WindowEvent e) {
                                    // TODO Auto-generated method stub
                                    mflink.dispose();
                                }
                            });
                            TextArea textArea1=new TextArea();
                            textArea1.setFont(new Font("宋体",Font.PLAIN,25));
                            textArea1.setBounds(50, 50, 300, 300);
                            mflink.add(textArea1);
                            for(int i=0;i<printstr0.length;i++) {
                                textArea1.append(printstr0[i]);
                                textArea1.append("\n");
                            }

                        }
                    });
                    buttonbridge.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            // TODO Auto-generated method stub

//							if((flag1==-1)&&(flag2==1)) {
//								System.out.println("No "+"\""+word1+"\""+" in the graph!");
//							}else if((flag1==1)&&(flag2==-1)){
//								System.out.println("No "+"\""+word2+"\""+ "in the graph!");
//							}else if((flag1==-1)&&(flag2==-1)){
//								System.out.println("No "+"\""+word1+"\""+" and "+"\""+word2+"\""+ " in the graph!");
//							}else if((flag1==1)&&(flag2==1)&&(flagc==0)){
//								System.out.println("No bridge words from \""+word1+"\" to \""+word2+"\"!");
//							}


                            String bridge1st=null,bridge2nd=null;
                            bridge1st=textfieldbridgeword1.getText().toString();
                            bridge2nd=textfieldbridgeword2.getText().toString();
                            String finalbridge=G0.queryBridgeWords(bridge1st, bridge2nd);
                            Frame mfb=new Frame("测试");
                            mfb.setBackground(Color.gray);
                            mfb.setBounds(300, 300, 300, 300);
                            TextArea textAreabridgeresult=new TextArea();
                            textAreabridgeresult.setFont(new Font("宋体",Font.PLAIN,25));
                            textAreabridgeresult.setBounds(350, 500, 300, 300);
                            mfb.setVisible(true);
                            mfb.add(textAreabridgeresult);
                            mfb.addWindowListener(new WindowAdapter() {

                                @Override
                                public void windowClosing(WindowEvent e) {
                                    // TODO Auto-generated method stub
                                    mfb.dispose();
                                }
                            });
                            if(G0.bridgestrnote[0]==null) {
                                finalbridge="null";
                                textAreabridgeresult.setText(finalbridge);
                            }else if(G0.bridgestrnote[0]=="0"){
                                finalbridge="word "+bridge1st+" not exist!";
                                textAreabridgeresult.setText(finalbridge);
                            }else if(G0.bridgestrnote[0]=="1"){
                                finalbridge="word "+bridge2nd+" not exist!";
                                textAreabridgeresult.setText(finalbridge);
                            }else if(G0.bridgestrnote[0]=="2"){
                                finalbridge="word "+bridge1st+" and "+bridge2nd+" not exist!";
                                textAreabridgeresult.setText(finalbridge);
                            }else {
                                for(int i=0;i<G0.bridgenumnote;i++) {
                                    textAreabridgeresult.append(G0.bridgestrnote[i]);
                                    textAreabridgeresult.append("\n");
                                }


                            }
                        }
                    });
                    buttonnewstr.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            // TODO Auto-generated method stub
                            Frame mfnewstr=new Frame("SHOWNEWSTR");
                            mfnewstr.setBounds(230, 230, 400, 400);
                            mfnewstr.setBackground(Color.GRAY);
                            mfnewstr.setVisible(true);
                            mfnewstr.addWindowListener(new WindowAdapter() {
                                @Override
                                public void windowClosing(WindowEvent e) {
                                    // TODO Auto-generated method stub
                                    mfnewstr.dispose();
                                }
                            });
                            TextField textfieldnewstroutput=new TextField();
                            textfieldnewstroutput.setFont(new Font("宋体",Font.PLAIN,25));
                            textfieldnewstroutput.setBounds(100, 700, 500, 50);
                            mfnewstr.add(textfieldnewstroutput);

                            String sysinput=textfieldnewstrinput.getText();
                            String[] newsplit=sysinput.split(" ");
                            String[] copystr=new String[newsplit.length];
                            String getstr=new String();
                            copystr=G0.generateNewText(sysinput);

                            for(int i=0;i<copystr.length;i++) {
                                getstr=getstr+(newsplit[i]).toString()+" ";
                                if(copystr[i]!=null) {
                                    getstr=getstr+(copystr[i]).toString()+" ";
                                }
                            }
                            getstr+=(newsplit[newsplit.length-1]).toString();
                            textfieldnewstroutput.setText(getstr);
                        }
                    });

                    buttonshortestpath.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String CalcStart=textfieldshortestpathword1.getText();
                            String CalcEnd=textfieldshortestpathword2.getText();
                            Frame mfshortpath=new Frame("SHOWSHORTPATH");
                            mfshortpath.setBackground(Color.GRAY);
                            mfshortpath.setLayout(null);
                            mfshortpath.setBounds(500, 500, 400, 400);
                            mfshortpath.setVisible(true);
                            mfshortpath.addWindowListener(new WindowAdapter() {

                                @Override
                                public void windowClosing(WindowEvent e) {
                                    // TODO Auto-generated method stub
                                    mfshortpath.dispose();
                                }
                            });
                            TextArea textArea0=new TextArea();
                            textArea0.setFont(new Font("宋体",Font.PLAIN,25));
                            textArea0.setBounds(50, 50, 300, 300);
                            mfshortpath.add(textArea0);
                            String shortestpath;
                            if(textfieldshortestpathword1.getText()==null||textfieldshortestpathword1.getText().trim().equals("")||textfieldshortestpathword2.getText()==null||textfieldshortestpathword2.getText().trim().equals("")) {
                                shortestpath=null;
                            }else {
                                shortestpath=G0.calcShortestPath1(CalcStart, CalcEnd, mt0, path0);//String calcShortestPath(String word1, String word2)：计算两个单词之间的最短路径
                            }
                            if(shortestpath.equals("0")) textArea0.setText(CalcStart+" not exist");
                            else if (shortestpath.equals("1")) textArea0.setText(CalcEnd+" not exist");

                            else if (shortestpath.equals("2")) textArea0.setText(CalcStart+" and "+CalcEnd+" not exist");

                            else {textArea0.setText(shortestpath);




                                if (!CalcStart.equals(CalcEnd)) {//CreateShortestPicture
                                    Graphv GG0=new Graphv();
                                    String dotFormat0=G0.CreateShortestPicture(CalcStart,CalcEnd,mt0,path0);
                                    //System.out.println(dotFormat0);
                                    GG0.createDotGraph(dotFormat0,"ShortDotGraph"+CalcStart+CalcEnd);
                                    DrawFrame frame0 = new DrawFrame("ShortDotGraph"+CalcStart+CalcEnd);
                                    frame0.pack();
                                    frame0.show();
                                    frame0.setDefaultCloseOperation(frame0.DISPOSE_ON_CLOSE);
                                }
                                File file = new File("ShortDotGraph"+CalcStart+CalcEnd+".jpg");
//							if (file.exists()&& file.isFile()) file.delete();


                                if(G0.shortlength>0) {
                                    System.out.println(G0.shortlength);
                                    String zdlj="最短路径长度是";
                                    zdlj+=G0.shortlength;
                                    textArea0.append(zdlj);}
                                else {
                                    textArea0.append("no path!");
                                }
                            }}






                    });

                    buttonrandom.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            // TODO Auto-generated method stub
                            Thread thread=new Thread() {
                                public void run() {
                                    Frame lmf=new Frame("random");
                                    lmf.setBackground(Color.GRAY);
                                    lmf.setLayout(null);
                                    lmf.setBounds(300, 300, 500, 500);
                                    lmf.setVisible(true);
                                    lmf.addWindowListener(new WindowAdapter() {
                                        @Override
                                        public void windowClosing(WindowEvent e) {
                                            // TODO Auto-generated method stub
                                            lmf.dispose();
                                        }
                                    });

                                    textArea1.setFont(new Font("宋体",Font.PLAIN,25));
                                    textArea1.setBounds(50, 50, 400, 400);
                                    lmf.add(textArea1);
                                    while(flsg==1) {
                                        try {
                                            Thread.sleep(500);
                                        } catch (InterruptedException e) {
                                            // TODO Auto-generated catch block
                                            e.printStackTrace();
                                        }
                                        String dlstr=G0.travelRandom(mt0);
                                        System.out.println(dlstr);
                                        textArea1.append(dlstr);
                                        textArea1.append("\n");
                                        dlstr+="\n";
                                    }
                                }
                            };
                            thread.start();
                            buttonstop.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    // TODO Auto-generated method stub
//									lmf.dispose();
                                    thread.stop();
                                    //设置随机游走串save提示
                                    Dialog Dialogsave=new Dialog(mf);
                                    Dialogsave.setTitle("Notice!");
                                    Dialogsave.setBounds(300, 300, 600, 300);
                                    Dialogsave.setLayout(null);
                                    Dialogsave.setVisible(true);
                                    Label labelsave =new Label();
                                    labelsave.setText("    Enter save and save random strings into file");
                                    labelsave.setFont(new Font("宋体",Font.PLAIN,20));
                                    labelsave.setBackground(Color.GREEN);
                                    labelsave.setBounds(0, 0, 600, 250);
                                    Dialogsave.add(labelsave);

                                    Button buttonsave=new Button("SAVE");
                                    buttonsave.setBounds(0, 250, 600, 50);
                                    Dialogsave.add(buttonsave);
                                    buttonsave.addActionListener(new ActionListener() {

                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            // TODO Auto-generated method stub
                                            File randomfile=new File(randomwalklocate);
                                            FileWriter fw = null;
                                            try {
                                                fw = new FileWriter(randomfile);
                                            } catch (IOException e2) {
                                                // TODO Auto-generated catch block
                                                e2.printStackTrace();
                                            }
                                            BufferedWriter bw=new BufferedWriter(fw);
                                            try {
                                                randomfile.createNewFile();
                                            } catch (IOException e1) {
                                                // TODO Auto-generated catch block
                                                e1.printStackTrace();
                                            }
                                            if(randomfile.exists()) {
                                                String randomstr=new String();
                                                randomstr=textArea1.getText();
                                                String line=new String();
                                                line=textArea1.getText().toString();


                                                try {
                                                    bw.write(line);
                                                } catch (IOException e1) {
                                                    // TODO Auto-generated catch block
                                                    e1.printStackTrace();
                                                }

                                            }
                                            try {
                                                bw.close();
                                                fw.close();
                                            } catch (IOException e1) {
                                                // TODO Auto-generated catch block
                                                e1.printStackTrace();
                                            }
                                            Dialogsave.dispose();
                                            Dialog dialogsuccess=new Dialog(mf, "NOTICE");
                                            dialogsuccess.setVisible(true);
                                            dialogsuccess.setBounds(300, 200, 400, 400);
                                            dialogsuccess.setBackground(Color.white);
                                            Label labelsuccess=new Label("    Had succeed to save in file:"+randomfile);
                                            labelsuccess.setBounds(0, 0, 400, 350);
                                            labelsuccess.setFont(new Font("宋体",Font.PLAIN,20));
                                            labelsuccess.setBackground(Color.GREEN);
                                            dialogsuccess.add(labelsuccess);

                                            Button buttonsuccess=new Button("Get that");
                                            buttonsuccess.setBounds(0, 350, 400, 50);
                                            dialogsuccess.add(buttonsuccess);
                                            buttonsuccess.addActionListener(new ActionListener() {

                                                @Override
                                                public void actionPerformed(ActionEvent e) {
                                                    // TODO Auto-generated method stub
                                                    dialogsuccess.dispose();

                                                }
                                            });

                                        }
                                    });

                                }
                            });
                        }
                    });

                }//else的结束
            }
        });

        /**
         * 获取文件内容
         */
        StrDeal sd=new StrDeal();

        /**
         *建立哈希表
         */
        ExerciseHash Ehash=new ExerciseHash();






        /**
         * 根据哈希表建立邻接矩阵
         */
        Matrix mt=new Matrix();

        /**
         * 根据邻接矩阵建立有向图(邻接表)
         */








        /**
         * 查找桥接词
         */




        /**
         * 创建新字符串
         */




        /**
         * 最短路径
         */


        /**
         * 随机游走
         */


        /**
         * 展示有向图
         **/






//		String inword1,inword2=new String();
//		System.out.println("Input the word1 of shortstpath:");
//		Scanner oinput1=new Scanner(System.in);
//		System.out.println("Input the word2 of shortstpath:");
//		Scanner oinput2=new Scanner(System.in);
//		inword1=oinput1.nextLine();
//		inword2=oinput2.nextLine();




//		int flaga=-1;
//		int flagb=-1;
//		int flagc=-1;
//		for(int i=0;i<G.Vnum;i++) {
//			if(splitstr[i].equals(inword1)) {
//				flaga=1;
//			}
//		}
//		for(int i=0;i<G.Vnum;i++) {
//			if(splitstr[i].equals(inword2)) {
//				flagb=1;
//			}
//		}
//		if(flaga==-1||flagb==-1) {
//			System.out.println("不存在最短路径！");
//		}else {
//			for(int x=0;x<mt.) {
//
//			}
//		}
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

    }
}

class DrawFrame extends JFrame  //图片显示
{
    public DrawFrame(String PName)
    {
        setTitle("DirectedGraph");
        ImageIcon img = new ImageIcon(PName+".jpg");
        JLabel n1=new JLabel(img);
        setLayout(new FlowLayout());
        add(n1);
        setSize(WIDTH, HEIGHT);
        DrawPanel panel = new DrawPanel();
        Container contentPane = getContentPane();
        contentPane.add(panel);
    }
    public static final int WIDTH = 500;
    public static final int HEIGHT = 500;
}


class DrawPanel extends JPanel  //图片显示
{
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Font f = new Font("Serif", Font.BOLD, 36);
        g.setFont(f);
        g.setColor(Color.red);
    }
}


class ExerciseHash {
    public HashMap<String,Integer> hash=new HashMap<>();
    public HashMap calcletter(String origan) {
        String[] str=origan.split(" ");
        for(int i=0;i<str.length;i++) {
            if(!hash.containsKey(str[i])) {

                hash.put(str[i], 1);
            }else {
                int counts;
                counts=hash.get(str[i]);
                hash.put(str[i], counts+1);
            }
        }
        return hash;
    }



    public void hashshow() {
        Iterator iter=hash.keySet().iterator();
        while(iter.hasNext()) {
            String key=iter.next().toString();
            int value=hash.get(key);
            System.out.println(key+"  "+value);
        }
    }
}

class Graph {
    //
    int Enum;
    int Vnum;
    public static int shortlength;
    private static final int INF=Integer.MAX_VALUE;

    private class Enode{
        int serial;
        Enode nextedge;
    }
    private class Vnode{
        String data;
        Enode firstedge;
    }
    private Vnode[] Varray;
    public String[] bridgestrnote=new String[100];
    public int  bridgenumnote;
    public  Graph(int Vnum,int Enum,Matrix mt){


        Varray=new Vnode[Vnum];

        for(int i=0;i<Vnum;i++) {
            Varray[i]=new Vnode();
            Varray[i].data=mt.hashkey[i];
            Varray[i].firstedge=null;
        }


        for(int i=0;i<Vnum;i++){
            for(int j=0;j<Vnum;j++) {

                if(mt.maxArray[i][j]!=0) {
                    int posin=getVnodeposition(mt.hashkey[i]);
                    int posout=getVnodeposition(mt.hashkey[j]);
                    Enode enode=new Enode();
                    enode.serial=posout;

                    if(Varray[posin].firstedge==null) {
                        Varray[posin].firstedge=enode;
                    }else {
                        Linklast(Varray[posin].firstedge, enode);
                    }
                }
            }
        }

    }

    public int getVnodeposition(String Vname) {
        for(int i=0;i<Varray.length;i++) {
            if(Varray[i].data.equals(Vname)) {
                return i;
            }
        }
        return -1;
    }
    public void Linklast(Enode list,Enode enodeadd) {
        Enode pe=list;

        while(pe.nextedge!=null)
            pe=pe.nextedge;
        pe.nextedge=enodeadd;

    }
    public void LinklistPrint(Matrix mt) {
        for(int i=0;i<Varray.length;i++) {
            System.out.print("point"+ i +" name:"+Varray[i].data+" :");
            Enode pe=Varray[i].firstedge;
            if(pe==null) {
                System.out.println("null");
            }else {
                while(pe!=null) {
                    System.out.print("->"+mt.hashkey[pe.serial]);
                    pe=pe.nextedge;
                }
                System.out.println();
            }

        }
    }
    public String[] LinklistPrint1(Matrix mt) {
        String[] printstr=new String[Varray.length];
        for(int i=0;i<Varray.length;i++) {
            printstr[i]="point"+ i +" name:"+Varray[i].data+" :";
            Enode pe=Varray[i].firstedge;
            if(pe==null) {
                printstr[i]+=null;
            }else {
                while(pe!=null) {
                    printstr[i]=printstr[i]+"->"+mt.hashkey[pe.serial];
                    pe=pe.nextedge;
                }
            }

        }
        return printstr;
    }

    public String Createpicture(Matrix mt) {
        String picturestr=new String();
        int size=mt.size;
        for(int i=0;i<size;i++) {
            for(int j=0;j<size;j++) {
                if(mt.maxArray[i][j]!=0) {
                    picturestr=picturestr+mt.hashkey[i]+"->"+mt.hashkey[j]+" [ label = "+mt.maxArray[i][j]+" ]"+";";
                }
            }
        }
        return picturestr;
    }

    public String CreateShortestPicture(String word1,String word2,Matrix mt,int[][] path) {
        shortlength=0;
        String picturestr=new String();
        picturestr="ratio = fill;\n	  node [style=filled];";
        int size=mt.size;
        int [][] colorpath=new int[size][size];
        for(int i=0;i<mt.size;i++)
            for(int j=0;j<mt.size;j++) colorpath[i][j]=0;
        int begin = 0;
        int over = 0;
        for(int i=0;i<mt.size;i++) {
            if(word1.equals(mt.hashkey[i])){
                begin=i;break;
            }
        }
        for(int i=0;i<mt.size;i++) {
            if(word2.equals(mt.hashkey[i])) {
                over=i;break;
            }
        }
        int mid=path[begin][over];
        colorpath[begin][mid]=1;
        while(mid!=over) {

            begin=mid;
            mid=path[begin][over];
            colorpath[begin][mid]=1;
        }
        for(int i=0;i<size;i++) {
            for(int j=0;j<size;j++) {
                if(mt.maxArray[i][j]!=0) {
                    if(colorpath[i][j]==1){

                        picturestr=picturestr+mt.hashkey[i]+"->"+mt.hashkey[j]+" [color=\"0.649 0.701 0.701\"]  [ label = "+mt.maxArray[i][j]+" ]"+";";
                        picturestr=picturestr+mt.hashkey[i]+" [color=\"0.408 0.498 1.000\"];"+mt.hashkey[j]+" [color=\"0.408 0.498 1.000\"];";
                        shortlength+=mt.maxArray[i][j];
                    }
                    else picturestr=picturestr+mt.hashkey[i]+"->"+mt.hashkey[j]+"  [ label = "+mt.maxArray[i][j]+" ]"+";";

                }
            }
        }


        return picturestr;
    }


    public void findBridge(String word1,String word2) {

        int flag1=-1;
        int flag2=-1;
        for(int i=0;i<Varray.length;i++) {
            if(word1.equals(Varray[i].data)) {
                flag1=1;
            }
            if(word2.equals(Varray[i].data)) {
                flag2=1;
            }
        }
        int flagc=-1;

        if((flag1==1)&&(flag2==1)) {
            flagc=0;
            for(int i=0;i<Varray.length;i++) {
                if(word1.equals(Varray[i].data)) {

                    Enode pe=Varray[i].firstedge;
                    while((pe!=null)) {
                        String bridgestr=Varray[pe.serial].data;
                        for(int j=0;j<Varray.length;j++) {
                            if(bridgestr.equals(Varray[j].data)) {

                                Enode px=Varray[j].firstedge;
//								System.out.println(Varray[px.serial].data);
                                while((px!=null)) {
                                    if(Varray[px.serial].data.equals(word2)) {
                                        flagc=1;
                                        System.out.println("The bridge words from \""+word1+" to \""+ word2+"\" is "+ bridgestr);
                                    }
                                    px=px.nextedge;
                                }
                            }
                        }
                        pe=pe.nextedge;
                    }
                }else ;
            }
        }
        if((flag1==-1)&&(flag2==1)) {
            System.out.println("No "+"\""+word1+"\""+" in the graph!");
        }else if((flag1==1)&&(flag2==-1)){
            System.out.println("No "+"\""+word2+"\""+ "in the graph!");
        }else if((flag1==-1)&&(flag2==-1)){
            System.out.println("No "+"\""+word1+"\""+" and "+"\""+word2+"\""+ " in the graph!");
        }else if((flag1==1)&&(flag2==1)&&(flagc==0)){
            System.out.println("No bridge words from \""+word1+"\" to \""+word2+"\"!");
        }
    }
    public String queryBridgeWords(String word1,String word2) {

        int flag1=-1;
        int flag2=-1;
        for(int i=0;i<Varray.length;i++) {
            if(word1.equals(Varray[i].data)) {
                flag1=1;
            }
            if(word2.equals(Varray[i].data)) {
                flag2=1;
            }
        }
        int flagc=-1;

        bridgenumnote=0;
        if((flag1==1)&&(flag2==1)) {
            flagc=0;

            for(int i=0;i<Varray.length;i++) {
                if(word1.equals(Varray[i].data)) {

                    Enode pe=Varray[i].firstedge;
                    while((pe!=null)) {
                        String bridgestr=Varray[pe.serial].data;
                        for(int j=0;j<Varray.length;j++) {
                            if(bridgestr.equals(Varray[j].data)) {

                                Enode px=Varray[j].firstedge;
//								System.out.println(Varray[px.serial].data);
                                while((px!=null)) {
                                    if(Varray[px.serial].data.equals(word2)) {
                                        flagc=1;
                                        bridgestrnote[bridgenumnote]=bridgestr;
                                        bridgenumnote++;

//										System.out.println("The bridge words from \""+word1+" to \""+ word2+"\" is "+ bridgestr);
                                    }
                                    px=px.nextedge;
                                }
                            }
                        }
                        pe=pe.nextedge;
                    }
                }else ;
            }
        }

        if((flag1==-1)&&(flag2==1)) {
//			System.out.println("No "+"\""+word1+"\""+" in the graph!");
            bridgestrnote[0]="0";
            return null;
        }else if((flag1==1)&&(flag2==-1)){
//			System.out.println("No "+"\""+word2+"\""+ "in the graph!");
            bridgestrnote[0]="1";
            return null;
        }else if((flag1==-1)&&(flag2==-1)){
//			System.out.println("No "+"\""+word1+"\""+" and "+"\""+word2+"\""+ " in the graph!");
            bridgestrnote[0]="2";
            return null;
        }else if((flag1==1)&&(flag2==1)&&(flagc==0)){
//			System.out.println("No bridge words from \""+word1+"\" to \""+word2+"\"!");
            return null;
        }
        return null;
    }



    public String queryBridgeWords1(String word1,String word2) {

        int flag1=-1;
        int flag2=-1;
        for(int i=0;i<Varray.length;i++) {
            if(word1.equals(Varray[i].data)) {
                flag1=1;
            }
            if(word2.equals(Varray[i].data)) {
                flag2=1;
            }
        }
        int flagc=-1;

        if((flag1==1)&&(flag2==1)) {
            flagc=0;
            for(int i=0;i<Varray.length;i++) {
                if(word1.equals(Varray[i].data)) {

                    Enode pe=Varray[i].firstedge;
                    while((pe!=null)) {
                        String bridgestr=Varray[pe.serial].data;
                        for(int j=0;j<Varray.length;j++) {
                            if(bridgestr.equals(Varray[j].data)) {

                                Enode px=Varray[j].firstedge;
//							System.out.println(Varray[px.serial].data);
                                while((px!=null)) {
                                    if(Varray[px.serial].data.equals(word2)) {
                                        flagc=1;
                                        return bridgestr;
//									System.out.println("The bridge words from \""+word1+" to \""+ word2+"\" is "+ bridgestr);
                                    }
                                    px=px.nextedge;
                                }
                            }
                        }
                        pe=pe.nextedge;
                    }
                }else ;
            }
        }
        if((flag1==-1)&&(flag2==1)) {
//		System.out.println("No "+"\""+word1+"\""+" in the graph!");
            return null;
        }else if((flag1==1)&&(flag2==-1)){
//		System.out.println("No "+"\""+word2+"\""+ "in the graph!");
            return null;
        }else if((flag1==-1)&&(flag2==-1)){
//		System.out.println("No "+"\""+word1+"\""+" and "+"\""+word2+"\""+ " in the graph!");
            return null;
        }else if((flag1==1)&&(flag2==1)&&(flagc==0)){
//		System.out.println("No bridge words from \""+word1+"\" to \""+word2+"\"!");
            return null;
        }
        return null;
    }

    public String[] generateNewText(String sysinput) {
        String[] predealstrsys=sysinput.split(" ");
        for(int i=0;i<predealstrsys.length-2;i++) {
            System.out.println(predealstrsys[i].toString());
        }
        String[] notestr=new String[predealstrsys.length-1];
        for(int i=0;i<predealstrsys.length-1;i++) {
            String word1=predealstrsys[i];
            String word2=predealstrsys[i+1];
            notestr[i]=queryBridgeWords1(word1, word2);
        }
        return notestr;
    }



    public int[][] Floyd(Matrix mt) {


        int size=mt.size;
        int[][] a=new int[size][size];
        int[][] path=new int[size][size];

        for(int i=0;i<size;i++) {
            for(int j=0;j<size;j++) {
                path[i][j]=j;
                if(mt.maxArray[i][j]==0) {
                    a[i][j]=10000;
                }else {
                    a[i][j]=mt.maxArray[i][j];
                }
            }
        }


        for(int k=0;k<size;k++) {
            for(int i=0;i<size;i++) {
                for(int j=0;j<size;j++) {
                    int temp=a[i][k]+a[k][j];
                    if(temp<a[i][j]) {
                        a[i][j]=temp;
                        path[i][j]=path[i][k];
                    }
                }
            }
        }
        return path;
    }

    public void calcShortestPath(String word1,String word2,Matrix mt,int[][] path) {

        int begin = 0;
        int over = 0;
        for(int i=0;i<mt.size;i++) {
            if(word1.equals(mt.hashkey[i])){
                begin=i;
            }
        }
        for(int i=0;i<mt.size;i++) {
            if(word2.equals(mt.hashkey[i])) {
                over=i;
            }
        }
        int mid=path[begin][over];
        System.out.print(mt.hashkey[begin]+"->");
        System.out.print(mt.hashkey[mid]);
        while(mid!=over) {

            begin=mid;
            mid=path[begin][over];
            System.out.print("->"+mt.hashkey[mid]);
        }
        System.out.println();

    }
    public String calcShortestPath1(String word1,String word2,Matrix mt,int[][] path) {

        String shortestpath=new String();
        int begin = -1;
        int over = -1;
        for(int i=0;i<mt.size;i++) {
            if(word1.equals(mt.hashkey[i])){
                begin=i;break;
            }
        }
        for(int i=0;i<mt.size;i++) {
            if(word2.equals(mt.hashkey[i])) {
                over=i;break;
            }
        }
        if(begin!=-1&&over!=-1){
            int mid=path[begin][over];
            shortestpath=shortestpath+mt.hashkey[begin]+"->";
            shortestpath=shortestpath+mt.hashkey[mid];
            while(mid!=over) {

                begin=mid;
                mid=path[begin][over];
                shortestpath=shortestpath+"->"+mt.hashkey[mid];

            }
            shortestpath=shortestpath+"\n";
            return shortestpath;}
        else if(begin==-1&&over!=-1)
        {
            return "0";
        }
        else if(begin!=-1&&over==-1)
        {
            return "1";
        }
        else if(begin==-1&&over==-1) {
            return "2";
        }
        else
        {
            return "3";
        }
    }



    public String travelRandom(Matrix mt) {

        int size=mt.size;
        String dlstr=new String();

        int[][] copymt=new int[size][size];
        for(int i=0;i<size;i++) {
            for(int j=0;j<size;j++)
                copymt[i][j]=mt.maxArray[i][j];
        }

        int randomx=(int)(Math.random()*100%10);
        dlstr+=mt.hashkey[randomx];

        int j=0;
        while(j<size) {
            if(copymt[randomx][j]!=1) {
                j++;
            }else {
                dlstr=dlstr+" "+mt.hashkey[j];
                copymt[randomx][j]=-1;
                randomx=j;
                j=0;
            }
        }
        return dlstr;
    }





}


class Graphv {
    public static void createDotGraph(String dotFormat,String fileName)
    {
        GraphViz gv=new GraphViz();
        gv.addln(gv.start_graph());
        gv.add(dotFormat);
        gv.addln(gv.end_graph());
//	    String type = "gif";
        String type = "jpg";
        gv.increaseDpi();
        gv.decreaseDpi();
        gv.decreaseDpi();
        File out = new File(fileName+"."+ type);
        gv.writeGraphToFile( gv.getGraph( gv.getDotSource(), type ), out );
    }

}

class GraphViz
{
    /**
     * Detects the client's operating system.
     */
    private final static String osName = System.getProperty("os.name").replaceAll("\\s","");

    /**
     * Load the config.properties file.
     */
    private final static String cfgProp = "config/config.properties";
    private final static Properties configFile = new Properties() {
        private final static long serialVersionUID = 1L; {
            try {
                load(new FileInputStream(cfgProp));
            } catch (Exception e) {}
        }
    };

    /**
     * The dir. where temporary files will be created.
     */
    private static String TEMP_DIR = "A:\\Document\\Code\\IDEA\\Lab3";

    /**
     * Where is your dot program located? It will be called externally.
     */
    private static String DOT = "A:\\WorkingSpace\\GraphViz\\bin\\dot.exe";

    /**
     * The image size in dpi. 96 dpi is normal size. Higher values are 10% higher each.
     * Lower values 10% lower each.
     *
     * dpi patch by Peter Mueller
     */
    private int[] dpiSizes = {46, 51, 57, 63, 70, 78, 86, 96, 106, 116, 128, 141, 155, 170, 187, 206, 226, 249};

    /**
     * Define the index in the image size array.
     */
    private int currentDpiPos = 7;

    /**
     * Increase the image size (dpi).
     */
    public void increaseDpi() {
        if ( this.currentDpiPos < (this.dpiSizes.length - 1) ) {
            ++this.currentDpiPos;
        }
    }

    /**
     * Decrease the image size (dpi).
     */
    public void decreaseDpi() {
        if (this.currentDpiPos > 0) {
            --this.currentDpiPos;
        }
    }

    public int getImageDpi() {
        return this.dpiSizes[this.currentDpiPos];
    }

    /**
     * The source of the graph written in dot language.
     */
    private StringBuilder graph = new StringBuilder();

    /**
     * Constructor: creates a new GraphViz object that will contain
     * a graph.
     */
    public GraphViz() {
    }

    /**
     * Returns the graph's source description in dot language.
     * @return Source of the graph in dot language.
     */
    public String getDotSource() {
        return this.graph.toString();
    }

    /**
     * Adds a string to the graph's source (without newline).
     */
    public void add(String line) {
        this.graph.append(line);
    }

    /**
     * Adds a string to the graph's source (with newline).
     */
    public void addln(String line) {
        this.graph.append(line + "\n");
    }

    /**
     * Adds a newline to the graph's source.
     */
    public void addln() {
        this.graph.append('\n');
    }

    public void clearGraph(){
        this.graph = new StringBuilder();
    }

    /**
     * Returns the graph as an image in binary format.
     * @param dot_source Source of the graph to be drawn.
     * @param type Type of the output image to be produced, e.g.: gif, dot, fig, pdf, ps, svg, png.
     * @return A byte array containing the image of the graph.
     */
    public byte[] getGraph(String dot_source, String type)
    {
        File dot;
        byte[] img_stream = null;

        try {
            dot = writeDotSourceToFile(dot_source);
            if (dot != null)
            {
                img_stream = get_img_stream(dot, type);
                if (dot.delete() == false)
                    System.err.println("Warning: " + dot.getAbsolutePath() + " could not be deleted!");
                return img_stream;
            }
            return null;
        } catch (java.io.IOException ioe) { return null; }
    }

    /**
     * Writes the graph's image in a file.
     * @param img   A byte array containing the image of the graph.
     * @param file  Name of the file to where we want to write.
     * @return Success: 1, Failure: -1
     */
    public int writeGraphToFile(byte[] img, String file)
    {
        File to = new File(file);
        return writeGraphToFile(img, to);
    }

    /**
     * Writes the graph's image in a file.
     * @param img   A byte array containing the image of the graph.
     * @param to    A File object to where we want to write.
     * @return Success: 1, Failure: -1
     */
    public int writeGraphToFile(byte[] img, File to)
    {
        try {
            FileOutputStream fos = new FileOutputStream(to);
            fos.write(img);
            fos.close();
        } catch (java.io.IOException ioe) { return -1; }
        return 1;
    }

    /**
     * It will call the external dot program, and return the image in
     * binary format.
     * @param dot Source of the graph (in dot language).
     * @param type Type of the output image to be produced, e.g.: gif, dot, fig, pdf, ps, svg, png.
     * @return The image of the graph in .gif format.
     */
    private byte[] get_img_stream(File dot, String type)
    {
        File img;
        byte[] img_stream = null;

        try {
            img = File.createTempFile("graph_", "."+type, new File(GraphViz.TEMP_DIR));
            Runtime rt = Runtime.getRuntime();

            // patch by Mike Chenault
            String[] args = {DOT, "-T"+type, "-Gdpi="+dpiSizes[this.currentDpiPos], dot.getAbsolutePath(), "-o", img.getAbsolutePath()};
            Process p = rt.exec(args);

            p.waitFor();

            FileInputStream in = new FileInputStream(img.getAbsolutePath());
            img_stream = new byte[in.available()];
            in.read(img_stream);
            // Close it if we need to
            if( in != null ) in.close();

            if (img.delete() == false)
                System.err.println("Warning: " + img.getAbsolutePath() + " could not be deleted!");
        }
        catch (java.io.IOException ioe) {
            System.err.println("Error:    in I/O processing of tempfile in dir " + GraphViz.TEMP_DIR+"\n");
            System.err.println("       or in calling external command");
            ioe.printStackTrace();
        }
        catch (java.lang.InterruptedException ie) {
            System.err.println("Error: the execution of the external program was interrupted");
            ie.printStackTrace();
        }

        return img_stream;
    }

    /**
     * Writes the source of the graph in a file, and returns the written file
     * as a File object.
     * @param str Source of the graph (in dot language).
     * @return The file (as a File object) that contains the source of the graph.
     */
    private File writeDotSourceToFile(String str) throws java.io.IOException
    {
        File temp;
        try {
            temp = File.createTempFile("dorrr",".dot", new File(GraphViz.TEMP_DIR));
            FileWriter fout = new FileWriter(temp);
            fout.write(str);
            BufferedWriter br=new BufferedWriter(new FileWriter("dotsource.dot"));
            br.write(str);
            br.flush();
            br.close();
            fout.close();
        }
        catch (Exception e) {
            System.err.println("Error: I/O error while writing the dot source to temp file!");
            return null;
        }
        return temp;
    }

    /**
     * Returns a string that is used to start a graph.
     * @return A string to open a graph.
     */
    public String start_graph() {
        return "digraph G {";
    }

    /**
     * Returns a string that is used to end a graph.
     * @return A string to close a graph.
     */
    public String end_graph() {
        return "}";
    }

    /**
     * Takes the cluster or subgraph id as input parameter and returns a string
     * that is used to start a subgraph.
     * @return A string to open a subgraph.
     */
    public String start_subgraph(int clusterid) {
        return "subgraph cluster_" + clusterid + " {";
    }

    /**
     * Returns a string that is used to end a graph.
     * @return A string to close a graph.
     */
    public String end_subgraph() {
        return "}";
    }

    /**
     * Read a DOT graph from a text file.
     *
     * @param input Input text file containing the DOT graph
     * source.
     */
    public void readSource(String input)
    {
        StringBuilder sb = new StringBuilder();

        try
        {
            FileInputStream fis = new FileInputStream(input);
            DataInputStream dis = new DataInputStream(fis);
            BufferedReader br = new BufferedReader(new InputStreamReader(dis));
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            dis.close();
        }
        catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }

        this.graph = sb;
    }
}

class Matrix {
    int size;
    int[][] maxArray=new int[1000][1000];
    int[] hashvalue=new int[1000];
    String[] hashkey=new String[1000];


    public void copymtreset(int[][] copymt){
        for(int i=0;i<copymt.length;i++) {
            for(int j=0;j<copymt.length;j++) {
                copymt[i][j]=maxArray[i][j];
            }
        }
    }
    public String randomWalk() {

        String dlstr=new String();

        int[][] copymt=new int[size][size];

        copymtreset(copymt);

        int randomx=(int)(Math.random()*100%10);
        dlstr+=hashkey[randomx];

        int j=0;
        while(j<size) {
            if(copymt[randomx][j]!=1) {
                j++;
            }else {
                dlstr=dlstr+" "+hashkey[j];
                copymt[randomx][j]=-1;
                randomx=j;
                j=0;
            }
        }

        copymtreset(copymt);
        return dlstr;
    }

    public void Creatematrix(ExerciseHash Ehash,String finalstr) {
        size=Ehash.hash.size();

        for(int i=0;i<size;i++) {
            for(int j=0;j<size;j++) {
                maxArray[i][j]=0;
            }

        }

        String[] splitstr=finalstr.split(" ");
        int i=0;

        Iterator iter =Ehash.hash.keySet().iterator();
        while(iter.hasNext()) {
            hashkey[i]=iter.next().toString();
            hashvalue[i]=Ehash.hash.get(hashkey[i]);
            i++;
        }

        for(int j=0;j<splitstr.length-1;j++) {
            int intx=0;
            int inty=0;
            Iterator iter2=Ehash.hash.keySet().iterator();
            while(iter2.hasNext()) {
                if((iter2.next().toString()).equals(splitstr[j])) {
                    break;
                }
                intx++;
            }
            Iterator iter3=Ehash.hash.keySet().iterator();
            while(iter3.hasNext()) {
                if((iter3.next().toString()).equals(splitstr[j+1])) {
                    break;
                }
                inty++;
            }
            maxArray[intx][inty]+=1;

        }
    }


    public void showDirectedGraph() {
        for(int i=0;i<size;i++)
        {
            for(int j=0;j<size;j++) {
                System.out.print(maxArray[i][j]+" ");
            }
            System.out.println();
        }
    }

}


class StrDeal {
    public int Strexit(String filename) {
        File frpt=new File(filename);
        if(!frpt.exists()) {
            return 0;
        }else {
            return 1;
        }
    }

    public String Strdeal(String filename) throws IOException {
        /**
         * 读取文件存入字符串
         */
//			Scanner input1=new Scanner(System.in);
//			String fileposition;
//			System.out.println("Input the position of input file: ");
//			fileposition=input1.nextLine();

        File frpt=new File(filename);
        FileReader fr =null;
        BufferedReader br=null;
        String finalStr =new String();
        if(!frpt.exists()) {
        }else {
            fr=new FileReader(frpt);
            br=new BufferedReader(fr);
            String line;
            while((line=br.readLine())!=null) {

                finalStr=finalStr.concat(line)+" ";

            }

            try {
                br.close();
                fr.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            /**
             * 字符串处理
             */
            String copyStr=new String();
            finalStr=finalStr.trim();
            finalStr=finalStr.replaceAll("\\pP"," ");

            finalStr=finalStr.toLowerCase();

            int i=0;
            while(i<(finalStr.length()-1)) {
                if((finalStr.charAt(i)==' ')&&(finalStr.charAt(i+1)==' ')) {
                    i++;
                }else {
                    copyStr+=finalStr.charAt(i);
                    i++;
                }
            }
            copyStr+=finalStr.charAt((finalStr.length()-1));
            finalStr=copyStr;
        }

        //A://lab1test.txt


        /**
         * 写回文件
         */
//			if(finalStr.length()!=0) {
//				File frpt2=new File("A://a.txt");
//				FileWriter fw=null;
//				BufferedWriter bw=null;
//
//				frpt2.createNewFile();
//				if(frpt2.exists()) {
//					System.out.println("Success to setup new file!");
//					fw=new FileWriter(frpt2);
//					bw=new BufferedWriter(fw);
//					bw.write(finalStr);
//				}else {
//					System.out.println("Fail to setup new file!");
//				}
//				bw.close();
//				fw.close();
//			}

        return finalStr;
    }

    public static void main(String[] args) {
        System.out.println("Just add something...");
    }

}
