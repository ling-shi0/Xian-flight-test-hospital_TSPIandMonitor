package com.xiaobei.TSPI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.SimpleTimeZone;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.basic.BasicButtonUI;

import org.json.JSONObject;

import com.tena.mq.AbstractMessageExecutor;
import com.tena.mq.IMessageExecutor;
import com.tena.mq.MessageUtil;
import com.usbxyz.USB2GPIO;
import com.usbxyz.USB_Device;

import app.USB2XXXUSBDeviceTest;

/**
 * TSPI
 * 
 * @date 2018-11-7
 */
public class Tspi extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel topLabel;
	private JPanel leftPanel;
	private JPanel rightPanel;
	private JPanel endJPanel;
	private JMenuBar menuBar;
	private JButton stopButton;
	private JButton startButton;
	private JButton aboutButton;
	private JButton zantingButton;
	private Timer timer;
	private String DEFAULT_TIME_FORMAT = "HH:mm:ss.SSS";
	private JTextField timeField;
	private boolean bool;
	private JScrollPane showJScrollPane;
	private JTextArea showField;
	private JTextField b1_text;
	private JTextField l1_text;
	private JTextField h1_text;
	private JTextField b2_text;
	private JTextField l2_text;
	private JTextField h2_text;
	private JTextField b3_text;
	private JTextField l3_text;
	private JTextField h3_text;
	private JTextField b4_text;
	private JTextField l4_text;
	private JTextField h4_text;
	private JTextField f_text;
	private JTextField h_text;
	private JTextField g_text;
	private JTextField j_text;
	private JTextField l_text;
	private JTextField b8_text;
	private JTextField l8_text;
	private JTextField ges_text;
	private JTextField b9_text;
	private JTextField l9_text;
	private JTextField bjTime_text;
	private JTextField gpsTime_text;
	private JTextField wholeSecond_text;
	private JLabel shine;
	private Icon grey;
	private Icon green;
	private Icon icon5;
	private String format;
	private JLabel rember;
	private JLabel lowHZJLabel;
	private JLabel highHZJLabel;
	private JLabel midHZJLabel;
	private static JLabel vField;
	private double b0, l0, h0, b1, l1, h1, b2, l2, h2, b3, l3, h3, ang;
	private int flag1 = 0, flag2 = 0, flag3 = 0, flag4 = 0;// 转换按钮转换方式判定值
	private String choosedFileName;
	private String choosedStaticFile;
	private String choosedStaticFile2;
	private String option;
	private JComboBox<String> Files = new JComboBox<String>();
	int light1 = 0, light2 = 0, light3 = 0, light4 = 0;// 灯标记 0是灭 1是亮
	SocketClient sc = new SocketClient();
	int ret;
	int DevHandle = 0;
	int[] DevHandleArry = new int[20];
	boolean state;
	int[] ss = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
	int ledstate = (int) (ss[0]);
	private String ftpHost = "192.168.1.2";
	private String ftpUserName = "root";
	private String ftpPassword = "123456";
	private int ftpPort = 21;
	private String ftpPath = "C:\\ftpserver\\tspi";
	private String localPath = "";
	private String fileName = "";
	Font font2 = new Font("黑体", Font.PLAIN, 18);
	Font font3 = new Font("黑体", Font.ROMAN_BASELINE, 16);
	Font font4 = new Font("Arial", Font.ROMAN_BASELINE, 17);
	Font defaultfont_ch = new Font("黑体", Font.ROMAN_BASELINE, 15);
	Font defaultfont_en = new Font("Arial", Font.ROMAN_BASELINE, 15);

	public static void main(String[] args) {

		IMessageExecutor executor = new Tspi().new MMconsumer();
		MessageUtil.addTena2MMMessageExecutor(executor);
	}

	public Tspi() {
		setLookAndFeel();
		bool = false;
		init();
	}

	/**
	 * 初始化
	 */
	public void init() {
		this.setUndecorated(true);// 去边框处理
		this.setLayout(null);
//		this.setExtendedState(JFrame.MAXIMIZED_BOTH);//最大化
		this.setSize(1024, 768);
//		this.setResizable(false);//不能改变大小
		this.setAlwaysOnTop(true);// 总在最前面
		this.setVisible(true);
//		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container mainContainer = this.getContentPane();

		// 标题标签
		topLabel = new JLabel(" TSPI多区域联合仿真样机-TSPI multi-area joint simulation prototype");
		topLabel.setOpaque(true);// 设置不透明
		topLabel.setBounds(0, 0, mainContainer.getWidth(), 20);
		topLabel.setBackground(Color.GRAY);
		topLabel.setForeground(Color.white);
		mainContainer.add(topLabel);

		// 菜单栏
		JMenuBar menuBar = addMenuBar();
		// menuBar.setBounds(0, 21, mainContainer.getWidth(), 72);
		menuBar.setBounds(0, 21, mainContainer.getWidth(), 63);
		mainContainer.add(menuBar);
		addListener();

		// 信息显示区（左侧）
		leftPanel = new JPanel();
		leftPanel.setOpaque(false);
		// leftPanel.setBounds(2, 95, 450, 670);
		leftPanel.setBounds(2, 90, 470, 670);
		leftPanel.setLayout(new BorderLayout(1, 1));
		leftPanel.setBorder(BorderFactory.createLoweredBevelBorder());
		mainContainer.add(leftPanel);

		// 左侧添加时序图

		JTabbedPane ChartTabbesd = new JTabbedPane();
		JPanel XYZ_Series = RealTimeChart.createPanel_XYZ();
		ChartTabbesd.add("XYZ坐标系", XYZ_Series);
		// ChartTabbesd.setBounds(5, 10, 440, 660);
		ChartTabbesd.setBounds(5, 10, 460, 660);
		ChartTabbesd.setVisible(true);
		// ChartTabbesd.setSize(430, 650);
		ChartTabbesd.setSize(450, 650);

		// 时序图中添加第一个功能
		JPanel calTransform = new JPanel();
		GridLayout grid = new GridLayout(2, 1);
		calTransform.setLayout(grid);

		// 上半部分的功能
		JPanel top = new JPanel();
		top.setLayout(null);
		top.setBorder(BorderFactory.createTitledBorder(null, "求切平面坐标", TitledBorder.LEFT, TitledBorder.TOP, font2,
				Color.black));
		// 导入文件转换功能
		JComboBox<String> but=new JComboBox<String>();
		but.addItem("--请选择文件--");
		but.setBounds(10, 30, 200, 30);
		but.setFont(font3);
		JLabel faceCombo = new JLabel("BLH-XYZ");
		faceCombo.setBounds(355, 30, 100, 30);
		faceCombo.setEnabled(true);
		faceCombo.setFont(font3);
		JLabel ori1 = new JLabel("原点：");
		ori1.setFont(font3);
		ori1.setBounds(15, 65, 75, 30);
		JTextField oriB1 = new JTextField();
		oriB1.setBounds(65, 65, 130, 30);
		oriB1.setText("34.65326570");
		oriB1.setFont(font3);
		JTextField oriL1 = new JTextField();
		oriL1.setBounds(195, 65, 130, 30);
		oriL1.setText("109.25585820");
		oriL1.setFont(font3);
		JTextField oriH1 = new JTextField();
		oriH1.setBounds(325, 65, 130, 30);
		oriH1.setText("359.53200");
		oriH1.setFont(font3);
		JButton tran2 = new JButton("转换");
		tran2.setBounds(220, 92, 80, 30);
		tran2.setFont(font3);

		//分割线
		JLabel line2=new JLabel("----------------------------------------------------------------------------------------------");
		line2.setFont(font3);
		line2.setBounds(5,124,480,3);
		// 在线计算功能
		JLabel til = new JLabel("转换计算：");
		til.setFont(font3);
		til.setBounds(15, 123, 80, 30);
		JLabel faceCombo2 = new JLabel("BLH-XYZ");
		faceCombo2.setBounds(355, 123, 100, 30);
		faceCombo2.setEnabled(true);
		faceCombo2.setFont(font3);
		JLabel inst1 = new JLabel("转换前");
		inst1.setFont(font3);
		inst1.setBounds(65, 155, 60, 15);
		JLabel aftInst1 = new JLabel("转换后");
		aftInst1.setFont(font3);
		aftInst1.setBounds(325, 155, 60, 15);
		JLabel fangweij = new JLabel();
		fangweij.setText("B:");
		fangweij.setBounds(50, 175, 25, 30);
		fangweij.setFont(font3);
		JTextField resu = new JTextField();
		resu.setText("34.6353506001");
		resu.setBounds(65, 175, 130, 30);
		resu.setFont(font3);
		JLabel pianzhuanj = new JLabel();
		pianzhuanj.setText("L:");
		pianzhuanj.setBounds(50, 205, 25, 30);
		pianzhuanj.setFont(font3);
		JTextField resu2 = new JTextField();
		resu2.setText("109.2260444661");
		resu2.setBounds(65, 205, 130, 30);
		resu2.setFont(font3);
		JLabel celiangj = new JLabel();
		celiangj.setText("H:");
		celiangj.setBounds(50, 235, 25, 30);
		celiangj.setFont(font3);
		JTextField resu3 = new JTextField();
		resu3.setText("351.4410000001");
		resu3.setBounds(65, 235, 130, 30);
		resu3.setFont(font3);

		JLabel fangweij2 = new JLabel();
		fangweij2.setText("X:");
		fangweij2.setBounds(310, 175, 25, 30);
		fangweij2.setFont(font3);
		JTextField resu4 = new JTextField();
		resu4.setText("");
		resu4.setBounds(325, 175, 130, 30);
		resu4.setFont(font3);
		JLabel pianzhuanj2 = new JLabel();
		pianzhuanj2.setText("Y:");
		pianzhuanj2.setBounds(310, 205, 25, 30);
		pianzhuanj2.setFont(font3);
		JTextField resu5 = new JTextField();
		resu5.setText("");
		resu5.setBounds(325, 205, 130, 30);
		resu5.setFont(font3);
		JLabel celiangj2 = new JLabel();
		celiangj2.setText("Z:");
		celiangj2.setBounds(310, 235, 25, 30);
		celiangj2.setFont(font3);
		JTextField resu6 = new JTextField();
		resu6.setText("");
		resu6.setBounds(325, 235, 130, 30);
		resu6.setFont(font3);
		JButton tran = new JButton("转换");
		tran.setBounds(220, 200, 80, 30);
		tran.setFont(font3);
		JLabel ori = new JLabel("原点：");
		ori.setFont(font3);
		ori.setBounds(15, 270, 75, 30);
		JTextField oriB = new JTextField();
		oriB.setText("34.65326570");
		oriB.setBounds(65, 270, 130, 30);
		oriB.setFont(font3);
		JTextField oriL = new JTextField();
		oriL.setText("109.25585820");
		oriL.setBounds(195, 270, 130, 30);
		oriL.setFont(font3);
		JTextField oriH = new JTextField();
		oriH.setBounds(325, 270, 130, 30);
		oriH.setFont(font3);
		oriH.setText("359.53200");
		// 加入上半部分
		top.add(ori);
		top.add(oriB);
		top.add(oriL);
		top.add(oriH);
		top.add(ori1);
		top.add(oriB1);
		top.add(oriL1);
		top.add(oriH1);
		top.add(til);
		top.add(tran);
		top.add(tran2);
		top.add(faceCombo);
		top.add(faceCombo2);
		top.add(inst1);
		top.add(aftInst1);
		top.add(but);
		top.add(resu);
		top.add(resu2);
		top.add(resu3);
		top.add(resu4);
		top.add(resu5);
		top.add(resu6);
		top.add(line2);
		top.add(fangweij);
		top.add(fangweij2);
		top.add(celiangj2);
		top.add(celiangj);
		top.add(pianzhuanj2);
		top.add(pianzhuanj);
		
		//
		File sf=new File("D:\\staticText");
		readFiles(sf,but);
		// 为按钮添加点击事件
		// 文件加载按钮
		but.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if (e.getStateChange() == ItemEvent.SELECTED) {
					choosedStaticFile=e.getItem().toString();
				}
			}
			
		});

				
				// 添加转换按钮事件
				// flag=0：BLH-AER flag=1:XYZ-AER
				tran2.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						// 数据处理部分写文件
						String aPath="";
						String pathname ="D:\\staticText\\"+choosedStaticFile;
						ArrayList dataB = new ArrayList();
						ArrayList dataL = new ArrayList();
						ArrayList dataH = new ArrayList();
						ArrayList dataT = new ArrayList();
						ArrayList dataA = new ArrayList();
						ArrayList dataE = new ArrayList();
						ArrayList dataR = new ArrayList();
						// 数据导入部分,读文件
						try (FileReader reader = new FileReader(pathname); BufferedReader br = new BufferedReader(reader)) {
							String line;
							int i = 0;// 行号
							int B = -1, L = -1, H = -1, T = -1, A = -1, E = -1, R = -1;
							while ((line = br.readLine()) != null) {
								// 一次读入一行数据
								String[] arr = line.split("\\s+");
								if (i == 0) {// 记录表头顺序
									for (String ss : arr) {
										if ("B".equals(ss)) {
											B = i;
											i++;
										} else if ("L".equals(ss)) {
											L = i;
											i++;
										} else if ("H".equals(ss)) {
											H = i;
											i++;
										} else if ("T".equals(ss)) {
											T = i;
											i++;
										} else if ("A".equals(ss)) {
											A = i;
											i++;
										} else if ("E".equals(ss)) {
											E = i;
											i++;
										} else if ("R".equals(ss)) {
											R = i;
											i++;
										}
									}
									arr = null;// 清空arr
								} else {
									// System.out.print(B+" "+L+" "+H);
									int it = 0;
									for (String ss : arr) {
										if (it == B) {
											System.out.print(ss + " ");
											dataB.add(ss);
											it++;
										} else if (it == L) {
											System.out.println(ss + " ");
											dataL.add(ss);
											it++;
										} else if (it == H) {
											System.out.print(ss + " ");
											dataH.add(ss);
											it++;
										} else if (it == T) {
											System.out.print(ss + " ");
											dataT.add(ss);
											it++;
										} else if (it == A) {
											System.out.println(ss + " ");
											dataA.add(ss);
											it++;
										} else if (it == E) {
											System.out.print(ss + " ");
											dataE.add(ss);
											it++;
										} else if (it == R) {
											System.out.print(ss + " ");
											dataR.add(ss);
											it++;
										}
									}
									arr = null;
								}
							}
						} catch (IOException a) {
							a.printStackTrace();
						}
						try {
							aPath=("D:\\staticText\\"+choosedStaticFile).replace(choosedStaticFile,
									choosedStaticFile.replace(".txt", "Result.txt"));
							File writeName = new File(aPath); // 更改文件名并把它放在相对于文件的位置
							writeName.createNewFile();
							try (FileWriter writer = new FileWriter(writeName);
									BufferedWriter out = new BufferedWriter(writer)) {
								TransformZuoBiao tr = new TransformZuoBiao();
								Map<String, String> map = new HashMap<String, String>();
								if (flag1 == 0) {
									out.write("X\tY\tZ\r\n");
									for (int i = 0; i < dataB.size(); i++) {
										map = tr.transformbTx(Double.parseDouble(dataB.get(i).toString()),
												Double.parseDouble(dataL.get(i).toString()),
												Double.parseDouble(dataH.get(i).toString()),
												Double.parseDouble(oriB1.getText()),
												Double.parseDouble(oriL1.getText()),
												Double.parseDouble(oriH1.getText()));
										out.write(map.get("X") + "\t" + map.get("Y") + "\t" + map.get("Z") + "\r\n");
										map.clear();
									}
								} else if (flag1 == 1) {
									out.write("X\tY\tZ\r\n");
									for (int i = 0; i < dataA.size(); i++) {
										map = tr.transformxTa(Double.parseDouble(dataA.get(i).toString()),
												Double.parseDouble(dataE.get(i).toString()),
												Double.parseDouble(dataR.get(i).toString()),
												Double.parseDouble(oriB1.getText()),
												Double.parseDouble(oriL1.getText()),
												Double.parseDouble(oriH1.getText()));
										out.write(map.get("X") + "\t" + map.get("Y") + "\t" + map.get("Z") + "\r\n");
										map.clear();
									}
								}
								out.flush();
							}
						} catch (IOException b) {
							b.printStackTrace();
						}
						but.removeAllItems();
						File sfs=new File("D:\\staticText");
						readFiles(sfs,but);
						localPath=aPath;
						fileName=choosedStaticFile.replace(".txt", "Result.txt");
						try{
				            FileInputStream in=new FileInputStream(new File(localPath));
				            boolean test = FtpUtils.uploadFile(ftpHost, ftpUserName, ftpPassword, ftpPort, ftpPath, fileName,in);
				            System.out.println(test);
				        } catch (FileNotFoundException ag){
				            ag.printStackTrace();
				            System.out.println(ag);
				        }
						alert alert = new alert(Tspi.this);
						alert.setVisible(true);
					}
				});

//		// 下拉菜单按钮
//		// flag=0：BLH-XYZ flag=1:AER-XYZ
//		faceCombo.addItemListener(new ItemListener() {
//			@Override
//			public void itemStateChanged(ItemEvent e) {
//				if (e.getStateChange() == ItemEvent.SELECTED) {
//					// 选择的下拉框选项
//					System.out.println(e.getItem());
//					if ("BLH-XYZ".equals(e.getItem())) {
//						flag1 = 0;
//						resu4.setText("");
//						resu5.setText("");
//						resu6.setText("");
//					} else {
//						flag1 = 1;
//						resu4.setText("");
//						resu5.setText("");
//						resu6.setText("");
//					}
//				}
//			}
//		});
//		faceCombo2.addItemListener(new ItemListener() {
//			@Override
//			public void itemStateChanged(ItemEvent e) {
//				if (e.getStateChange() == ItemEvent.SELECTED) {
//					// 选择的下拉框选项
//					System.out.println(e.getItem());
//					if ("BLH-XYZ".equals(e.getItem())) {
//						fangweij.setText("B:");
//						pianzhuanj.setText("L:");
//						celiangj.setText("H:");
//						flag2 = 0;
//					} else {
//						fangweij.setText("A:");
//						pianzhuanj.setText("E:");
//						celiangj.setText("R:");
//						flag2 = 1;
//					}
//				}
//			}
//		});
		// 转换按钮
		// flag=0：BLH-XYZ flag=1:AER-XYZ
		tran.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {	
				if (flag2 == 1) {
					double res = Double.parseDouble(resu.getText());
					double res2 = Double.parseDouble(resu2.getText());
					double res3 = Double.parseDouble(resu3.getText());
					double yuandianB = Double.parseDouble(oriB.getText());
					double yuandianL = Double.parseDouble(oriL.getText());
					double yuandianH = Double.parseDouble(oriH.getText());
					TransformZuoBiao tr = new TransformZuoBiao();
					Map<String, String> map = new HashMap<String, String>();
					map = tr.transformbTx(res, res2, res3, yuandianB, yuandianL, yuandianH);
					resu4.setText(map.get("X"));
					resu5.setText(map.get("Y"));
					resu6.setText(map.get("Z"));
				} else if (flag2 == 0) {
					double res = Double.parseDouble(resu.getText());
					double res2 = Double.parseDouble(resu2.getText());
					double res3 = Double.parseDouble(resu3.getText());
					double yuandianB = Double.parseDouble(oriB.getText());
					double yuandianL = Double.parseDouble(oriL.getText());
					double yuandianH = Double.parseDouble(oriH.getText());
					TransformZuoBiao tr = new TransformZuoBiao();
					Map<String, String> map = new HashMap<String, String>();
					map = tr.transformbTx(res, res2, res3, yuandianB, yuandianL, yuandianH);
					resu4.setText(map.get("X"));
					resu5.setText(map.get("Y"));
					resu6.setText(map.get("Z"));
				}
			}
		});

		// 下半部分的功能
		JPanel bot = new JPanel();
		bot.setLayout(null);
		bot.setBorder(BorderFactory.createTitledBorder(null, "求AER", TitledBorder.LEFT, TitledBorder.TOP, font2,
				Color.black));
		JComboBox<String> but2 = new JComboBox<String>();
		but2.setBounds(10, 30, 200, 30);
		but2.setFont(font3);
		but2.addItem("--请选择文件--");
		JComboBox<String> faceCombo3 = new JComboBox<String>();
		faceCombo3.setEditable(true);
		faceCombo3.setBounds(355, 30, 100, 30);
		faceCombo3.setEnabled(true);
		faceCombo3.addItem("BLH-AER");
		faceCombo3.addItem("XYZ-AER");
		faceCombo3.setFont(font3);
		JLabel ori2 = new JLabel("原点：");
		ori2.setFont(font3);
		ori2.setBounds(15, 65, 75, 30);
		JTextField oriB2 = new JTextField();
		oriB2.setBounds(65, 65, 130, 30);
		oriB2.setText("34.65326570");
		oriB2.setFont(font3);
		JTextField oriL2 = new JTextField();
		oriL2.setBounds(195, 65, 130, 30);
		oriL2.setText("109.25585820");
		oriL2.setFont(font3);
		JTextField oriH2 = new JTextField();
		oriH2.setBounds(325, 65, 120, 30);
		oriH2.setText("359.53200");
		oriH2.setFont(font3);
		JButton tran3 = new JButton("转换");
		tran3.setBounds(220, 95, 80, 30);
		tran3.setFont(font3);

		//分割线
		JLabel line=new JLabel("----------------------------------------------------------------------------------------------");
		line.setFont(font3);
		line.setBounds(5,125,480,5);
		// 在线计算功能
		JLabel til1 = new JLabel("转换计算：");
		til1.setFont(font3);
		til1.setBounds(15, 130, 80, 30);
		JComboBox<String> faceCombo4 = new JComboBox<String>();
		faceCombo4.setEditable(true);
		faceCombo4.setBounds(355, 130, 100, 30);
		faceCombo4.setEnabled(true);
		faceCombo4.addItem("BLH-AER");
		faceCombo4.addItem("XYZ-AER");
		faceCombo4.setFont(font3);
		JLabel inst4 = new JLabel("转换前");
		inst4.setFont(font3);
		inst4.setBounds(65, 160, 60, 15);
		JLabel aftInst4 = new JLabel("转换后");
		aftInst4.setFont(font3);
		aftInst4.setBounds(325, 160, 60, 15);
		JLabel fangweijiao = new JLabel();
		fangweijiao.setText("B:");
		fangweijiao.setBounds(50, 175, 25, 30);
		fangweijiao.setFont(font3);
		JTextField resul7 = new JTextField();
		resul7.setText("34.6353506001");
		resul7.setBounds(65, 175, 130, 30);
		resul7.setFont(font3);
		JLabel pianzhuanjiao2 = new JLabel();
		pianzhuanjiao2.setText("L:");
		pianzhuanjiao2.setBounds(50, 205, 25, 30);
		pianzhuanjiao2.setFont(font3);
		JTextField resul8 = new JTextField();
		resul8.setText("109.2260444661");
		resul8.setBounds(65, 205, 130, 30);
		resul8.setFont(font3);
		JLabel celiangjiao3 = new JLabel();
		celiangjiao3.setText("H");
		celiangjiao3.setBounds(50, 235, 25, 30);
		celiangjiao3.setFont(font3);
		JTextField resul9 = new JTextField();
		resul9.setText("351.4410000001");
		resul9.setBounds(65, 235, 130, 30);
		resul9.setFont(font3);

		JLabel fangweijiao3 = new JLabel();
		fangweijiao3.setText("A:");
		fangweijiao3.setBounds(310, 175, 25, 30);
		fangweijiao3.setFont(font3);
		JTextField result = new JTextField();
		result.setText("");
		result.setBounds(325, 175, 130, 30);
		result.setFont(font3);
		JLabel pianzhuanjiao4 = new JLabel();
		pianzhuanjiao4.setText("E:");
		pianzhuanjiao4.setBounds(310, 205, 25, 30);
		pianzhuanjiao4.setFont(font3);
		JTextField result3 = new JTextField();
		result3.setText("");
		result3.setBounds(325, 205, 130, 30);
		result3.setFont(font3);
		JLabel celiangjiao4 = new JLabel();
		celiangjiao4.setText("R:");
		celiangjiao4.setBounds(310, 235, 25, 30);
		celiangjiao4.setFont(font3);
		JTextField result6 = new JTextField();
		result6.setText("");
		result6.setBounds(325, 235, 130, 30);
		result6.setFont(font3);
		JButton tran4 = new JButton("转换");
		tran4.setBounds(220, 205, 80, 30);
		tran4.setFont(font3);
		JLabel ori3 = new JLabel("原点：");
		ori3.setFont(font3);
		ori3.setBounds(15, 270, 75, 30);
		JTextField oriB3 = new JTextField();
		oriB3.setBounds(65, 270, 130, 30);
		oriB3.setText("34.65326570");
		oriB3.setFont(font3);
		JTextField oriL3 = new JTextField();
		oriL3.setBounds(195, 270, 130, 30);
		oriL3.setText("109.25585820");
		oriL3.setFont(font3);
		JTextField oriH3 = new JTextField();
		oriH3.setBounds(325, 270, 130, 30);
		oriH3.setText("359.53200");
		oriH3.setFont(font3);

		bot.add(but2);
		bot.add(line);
		bot.add(tran4);
		bot.add(result6);
		bot.add(celiangjiao4);
		bot.add(result3);
		bot.add(pianzhuanjiao4);
		bot.add(result);
		bot.add(fangweijiao3);
		bot.add(resul9);
		bot.add(celiangjiao3);
		bot.add(resul8);
		bot.add(pianzhuanjiao2);
		bot.add(resul7);
		bot.add(fangweijiao);
		bot.add(aftInst4);
		bot.add(inst4);
		bot.add(faceCombo4);
		bot.add(til1);
		bot.add(tran3);
		bot.add(faceCombo3);
		bot.add(ori2);
		bot.add(oriB2);
		bot.add(oriL2);
		bot.add(oriH2);
		bot.add(ori3);
		bot.add(oriB3);
		bot.add(oriL3);
		bot.add(oriH3);
		
		File sfs=new File("D:\\staticText");
		readFiles(sfs,but2);
		// 为按钮添加点击事件
		// 加载文件按钮
		but2.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if (e.getStateChange() == ItemEvent.SELECTED) {
					choosedStaticFile2=e.getItem().toString();
				}
			}
			
		});
				// 添加转换按钮事件
				// flag=0：BLH-AER flag=1:XYZ-AER
				tran3.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						// 数据处理部分写文件
						String aPath="";
						String pathname = "D:\\staticText\\"+choosedStaticFile2;
						ArrayList dataB = new ArrayList();
						ArrayList dataL = new ArrayList();
						ArrayList dataH = new ArrayList();
						ArrayList dataX = new ArrayList();
						ArrayList dataY = new ArrayList();
						ArrayList dataZ = new ArrayList();
						// 数据导入部分,读文件
						try (FileReader reader = new FileReader(pathname); BufferedReader br = new BufferedReader(reader)) {
							String line;
							int i = 0;
							int B = -1, L = -1, H = -1, X = -1, Y = -1, Z = -1;
							while ((line = br.readLine()) != null) {
								// 一次读入一行数据
								String[] arr = line.split("\\s+");
								if (i == 0) {// 记录表头顺序
									for (String ss : arr) {
										if ("B".equals(ss)) {
											B = i;
											i++;
										} else if ("L".equals(ss)) {
											L = i;
											i++;
										} else if ("H".equals(ss)) {
											H = i;
											i++;
										} else if ("X".equals(ss)) {
											X = i;
											i++;
										} else if ("Y".equals(ss)) {
											Y = i;
											i++;
										} else if ("Z".equals(ss)) {
											Z = i;
											i++;
										}
									}
									arr = null;// 清空arr
								} else {
									// System.out.print(B+" "+L+" "+H);
									int it = 0;
									for (String ss : arr) {
										if (it == B) {
											System.out.print(ss + " ");
											dataB.add(ss);
											it++;
										} else if (it == L) {
											System.out.println(ss + " ");
											dataL.add(ss);
											it++;
										} else if (it == H) {
											System.out.print(ss + " ");
											dataH.add(ss);
											it++;
										} else if (it == X) {
											System.out.println(ss + " ");
											dataX.add(ss);
											it++;
										} else if (it == Y) {
											System.out.print(ss + " ");
											dataY.add(ss);
											it++;
										} else if (it == Z) {
											System.out.print(ss + " ");
											dataZ.add(ss);
											it++;
										}
									}
									arr = null;
								}
							}
						} catch (IOException a) {
							a.printStackTrace();
						}
						try {
							aPath=("D:\\staticText\\"+choosedStaticFile2).replace(choosedStaticFile2,
									choosedStaticFile2.replace(".txt", "Result.txt"));
							File writeName = new File(aPath);
							writeName.createNewFile();
							try (FileWriter writer = new FileWriter(writeName);
									BufferedWriter out = new BufferedWriter(writer)) {
								TransformZuoBiao tr = new TransformZuoBiao();
								Map<String, String> map = new HashMap<String, String>();
								if (flag3 == 0) {
									out.write("A\tE\tR\r\n");
									for (int i = 0; i < dataB.size(); i++) {
										map = tr.transformbTx(Double.parseDouble(dataB.get(i).toString()),
												Double.parseDouble(dataL.get(i).toString()),
												Double.parseDouble(dataH.get(i).toString()),
												Double.parseDouble(oriB2.getText()),
												Double.parseDouble(oriL2.getText()),
												Double.parseDouble(oriH2.getText()));
										out.write(map.get("A") + "\t" + map.get("E") + "\t" + map.get("R") + "\r\n");
										map.clear();
									}
								} else if (flag3 == 1) {
									out.write("R\tE\tA\r\n");
									for (int i = 0; i < dataX.size(); i++) {
										map = tr.transformxTa(Double.parseDouble(dataX.get(i).toString()),
												Double.parseDouble(dataY.get(i).toString()),
												Double.parseDouble(dataZ.get(i).toString()),
												Double.parseDouble(oriB2.getText()),
												Double.parseDouble(oriL2.getText()),
												Double.parseDouble(oriH2.getText()));
										out.write(map.get("A") + "\t" + map.get("E") + "\t" + map.get("R") + "\r\n");
										map.clear();
									}
								}
								out.flush();
							}
						} catch (IOException b) {
							b.printStackTrace();
						}
						but2.removeAllItems();
						File sfs=new File("D:\\staticText");
						readFiles(sfs,but2);
						localPath=aPath;
						fileName=choosedStaticFile.replace(".txt", "Result.txt");
						try{
				            FileInputStream in=new FileInputStream(new File(localPath));
				            boolean test = FtpUtils.uploadFile(ftpHost, ftpUserName, ftpPassword, ftpPort, ftpPath, fileName,in);
				            System.out.println(test);
				        } catch (FileNotFoundException ag){
				            ag.printStackTrace();
				            System.out.println(ag);
				        }
						alert alert = new alert(Tspi.this);
						alert.setVisible(true);
					}
				});

			

		// 下拉菜单按钮
		// flag=0：BLH-AER flag=1:XYZ-AER
		faceCombo4.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					// 选择的下拉框选项
					System.out.println(e.getItem());
					if ("XYZ-AER".equals(e.getItem())) {
						fangweijiao.setText("X:");
						pianzhuanjiao2.setText("Y:");
						celiangjiao3.setText("Z:");
						flag4 = 1;
					} else {
						fangweijiao.setText("B:");
						pianzhuanjiao2.setText("L:");
						celiangjiao3.setText("H:");
						flag4 = 0;
					}
				}
			}
		});
		faceCombo3.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					// 选择的下拉框选项
					System.out.println(e.getItem());
					if ("XYZ-AER".equals(e.getItem())) {
						flag3 = 1;
					} else {
						flag3 = 0;
					}
				}
			}
		});
		// 转换按钮
		// flag=0：BLH-AER flag=1:XYZ-AER
		tran4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				double res1 = Double.parseDouble(resul7.getText());
				double res2 = Double.parseDouble(resul8.getText());
				double res3 = Double.parseDouble(resul9.getText());
				double ori1 = Double.parseDouble(oriB3.getText());
				double ori2 = Double.parseDouble(oriL3.getText());
				double ori3 = Double.parseDouble(oriH3.getText());
				TransformZuoBiao tr = new TransformZuoBiao();
				Map<String, String> map = new HashMap<String, String>();
				if (flag4 == 0) {
					map = tr.transformbTx(res1, res2, res3, ori1, ori2, ori3);
					result.setText(map.get("A"));
					result3.setText(map.get("E"));
					result6.setText(map.get("R"));
					map.clear();
				} else if (flag4 == 1) {
					map = tr.transformxTa(res1, res2, res3, ori1, ori2, ori3);
					result.setText(map.get("A"));
					result3.setText(map.get("E"));
					result6.setText(map.get("R"));
					map.clear();
				}
			}
		});

		// 汇总于坐标转换tab页
		calTransform.add(top);
		calTransform.add(bot);
		ChartTabbesd.add("坐标转换", calTransform);

		// 添加第二个功能
		JPanel XYZtoXYZ = new JPanel();
		XYZtoXYZ.setLayout(grid);
		JPanel up = new JPanel();
		up.setLayout(null);
		up.setBorder(BorderFactory.createTitledBorder(null, "XYZ转XYZ", TitledBorder.LEFT, TitledBorder.TOP, font2,
				Color.black));
		up.setLayout(null);
		JButton but3 = new JButton("选择文件");
		but3.setBounds(5, 30, 130, 30);
		but3.setFont(font3);
		JLabel txt_name3 = new JLabel();
		txt_name3.setText("");
		txt_name3.setBounds(140, 30, 200, 30);
		txt_name3.setFont(font3);
		JLabel quest1 = new JLabel("是否进行抗差估计,如果是,请输入抗差估计迭代结束条件：(如0.05)：");
		quest1.setFont(font3);
		quest1.setBounds(5, 65, 450, 30);
		JRadioButton radioButton1 = new JRadioButton("是", true);
		JRadioButton radioButton2 = new JRadioButton("否");
		ButtonGroup group = new ButtonGroup();
		group.add(radioButton1);
		group.add(radioButton2);
		radioButton1.setBounds(5, 100, 50, 30);
		radioButton2.setBounds(55, 100, 50, 30);
		JTextField condition = new JTextField();
		condition.setBounds(110, 100, 120, 30);
		JButton tran5 = new JButton("转换");
		tran5.setBounds(140, 130, 130, 30);
		tran5.setFont(font3);

		up.add(tran5);
		up.add(condition);
		up.add(but3);
		up.add(txt_name3);
		up.add(quest1);
		up.add(radioButton1);
		up.add(radioButton2);
		XYZtoXYZ.add(up);

		// 为按钮添加监听事件
		but3.addActionListener(new ActionListener() { // 为按钮添加点击事件
			@Override
			public void actionPerformed(ActionEvent e) {
				MyJDialog dialog = new MyJDialog(Tspi.this);
				dialog.setVisible(true); // 使MyJDialog窗体可见
				txt_name3.setText(dialog.getFileAdress());
				String pathname = dialog.getFileAdress();
			}
		});

		// 加入总体
		// ChartTabbesd.add("xyz坐标转换", XYZtoXYZ);

		// 添加第三个读取文件功能
		JPanel readFile = new JPanel(null);
		readFile.setBounds(5, 5, 450, 550);
		Files.addItem("请选择文件");
		JComboBox<String> chooseKinds = new JComboBox<String>();
		chooseKinds.addItem("请选择文件类型");
		chooseKinds.addItem("日志文件");
		chooseKinds.addItem("数据文件");
		Files.setBounds(160, 5, 200, 30);
		Files.setFont(font3);
		chooseKinds.setBounds(5, 5, 150, 30);
		chooseKinds.setFont(font3);
		JButton watch=new JButton("查看");
		watch.setBounds(365, 5, 100, 30);
		watch.setFont(font3);
		JTextArea jta = new JTextArea();
		JScrollPane jsp = new JScrollPane(jta);
		jta.setBounds(5, 50, 430,450);
		jsp.setBackground(Color.white);
		jsp.setBorder(null);
		jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jta.setFont(font3);
		jsp.setBounds(5, 50, 430, 450);
		JButton clear = new JButton("清空");
		clear.setFont(font3);
		clear.setBounds(200, 510, 100, 30);
		readFile.add(Files);
		readFile.add(jsp);
		readFile.add(clear);
		readFile.add(chooseKinds);
		readFile.add(watch);

		//为下拉菜单加选择事件
		chooseKinds.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if (e.getStateChange() == ItemEvent.SELECTED) {
					// 选择的下拉框选项
					System.out.println(e.getItem());
					if ("数据文件".equals(e.getItem())) {
						Files.removeAllItems();
						File file = new File("D:\\staticText");
						readAllFile(file);
						option="static";
					} else {
						Files.removeAllItems();
						File file = new File("D:\\logText");
						readAllFile(file);
						option="log";
					}
				}
			}
			
		});
		Files.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if (e.getStateChange() == ItemEvent.SELECTED) {
					// 选择的下拉框选项
					choosedFileName=e.getItem().toString();
				}
			}
			
		});
		// 为按钮添加点击事件
		watch.addActionListener(new ActionListener() {
			String txtLine=null;
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jta.setText("");
				if("log".equals(option)) {
					File file = new File("D:\\logText\\"+choosedFileName);
					BufferedReader reader = null;
					try {
						reader = new BufferedReader(new InputStreamReader(new FileInputStream(file),"GB2312"));
						while ((txtLine = reader.readLine()) != null) {
							jta.append(txtLine+ "\n");
						}
						reader.close();
					} catch (UnsupportedEncodingException | FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}else {
					File file = new File("D:\\staticText\\"+choosedFileName);
					BufferedReader reader = null;
					try {
						reader = new BufferedReader(new InputStreamReader(new FileInputStream(file),"utf-8"));
						while ((txtLine = reader.readLine()) != null) {
							jta.append(txtLine+ "\n");
						}
						reader.close();
					} catch (UnsupportedEncodingException | FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
						
			}

		});
		clear.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jta.setText("");
			}

		});
		ChartTabbesd.add("读取文件", readFile);
		leftPanel.add(ChartTabbesd);

		// 操控区（右侧）
		rightPanel = new JPanel();
		rightPanel.setBounds(480, 95, 535, 670);
		rightPanel.setBorder(BorderFactory.createLoweredBevelBorder());
		rightPanel.setLayout(null);
		mainContainer.add(BorderLayout.EAST, rightPanel);

		JLabel titleJLabel = new JLabel("TSPI多区域联合仿真样机");
		titleJLabel.setBounds(25, 8, 460, 60);
		titleJLabel.setFont(new Font("黑体", Font.BOLD, 35));
		rightPanel.add(titleJLabel);

		JLabel titJLabel = new JLabel("TSPI multi-area joint simulation prototype");
		titJLabel.setBounds(25, 60, 460, 30);
		titJLabel.setFont(new Font("Arial", Font.ROMAN_BASELINE, 23));
		rightPanel.add(titJLabel);

		JLabel iconLabel5 = new JLabel();
		iconLabel5.setBounds(450, 0, 120, 100);
		icon5 = new ImageIcon("./image/TSPI_700.png");
		iconLabel5.setIcon(icon5);
		rightPanel.add(iconLabel5);

		JPanel timeJPanel = new JPanel(null);
		timeJPanel.setBackground(Color.DARK_GRAY);
		timeJPanel.setBounds(10, 98, 515, 80);
		timeJPanel.setBorder(BorderFactory.createLoweredBevelBorder());
		rightPanel.add(timeJPanel);

		timeField = new JTextField("00:00:00.000");
		timeField.setBounds(310, 10, 200, 60);
		timeField.setBackground(Color.black);
		timeField.setForeground(Color.white);
		timeField.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
		timeField.setFont(new Font("黑体", Font.ROMAN_BASELINE, 31));
		timeField.setFocusable(false);
		timeJPanel.add(timeField);

		// 显示操作记录

		showField = new JTextArea();
		showField.setBounds(3, 3, 521, 100);// 3, 3, 758, 152
		showField.setFont(font3);
		showJScrollPane = new JScrollPane(showField);
		showJScrollPane.setBounds(10, 183, 520, 100);// 10, 165, 766, 160
		showJScrollPane.setBorder(null);
		showJScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		rightPanel.add(showJScrollPane);

		// 点亮(TSPI)
		grey = new ImageIcon("./image/grey.png");
		green = new ImageIcon("./image/green.png");
		shine = new JLabel();
		shine.setBounds(15, 15, 35, 35);
		shine.setIcon(grey);
		shine.setOpaque(false);
		timeJPanel.add(shine);
		JLabel shineText = new JLabel("TSPI");
		shineText.setBounds(10, 40, 40, 40);
		shineText.setForeground(Color.WHITE);
		shineText.setFont(defaultfont_en);
		timeJPanel.add(shineText);

		// 点亮(记录)
		grey = new ImageIcon("./image/grey.png");
		green = new ImageIcon("./image/green.png");
		rember = new JLabel();
		rember.setBounds(65, 15, 35, 35);
		rember.setIcon(grey);
		rember.setOpaque(false);
		timeJPanel.add(rember);
		JLabel remberText = new JLabel("记录");
		remberText.setBounds(63, 40, 40, 40);
		remberText.setForeground(Color.WHITE);
		remberText.setFont(defaultfont_ch);
		timeJPanel.add(remberText);

		// 显示信息窗口
		JPanel showParam = new JPanel(new GridLayout(6, 4, 0, 0));
		showParam.setBounds(10, 290, 522, 195);
		showParam.setLayout(null);
		showParam.setBorder(BorderFactory.createTitledBorder(null, "TSPI站址信息", TitledBorder.LEFT, TitledBorder.TOP,
				font2, Color.black)); // 设置边框
		rightPanel.add(showParam);
		JLabel kon = new JLabel(" ");
		kon.setBounds(10, 15, 120, 20);
		JLabel b0 = new JLabel("B0");
		b0.setBounds(125, 15, 120, 20);
		JLabel l0 = new JLabel("L0");
		l0.setBounds(255, 15, 120, 20);
		JLabel h0 = new JLabel("H0");
		h0.setBounds(385, 15, 120, 20);
		showParam.add(kon);
		showParam.add(b0);
		showParam.add(l0);
		showParam.add(h0);

		JLabel A = new JLabel("原点:");
		A.setBounds(10, 35, 120, 30);
		A.setFont(font3);
		showParam.add(A);
		b1_text = new JTextField();
		b1_text.setBackground(Color.lightGray);
		b1_text.setBounds(120, 35, 130, 30);
		b1_text.setFont(font3);
		b1_text.setText("");
		showParam.add(b1_text);
		l1_text = new JTextField();
		h1_text = new JTextField();

		l1_text.setBackground(Color.lightGray);
		l1_text.setBounds(250, 35, 130, 30);
		l1_text.setFont(font3);
		l1_text.setText("");
		h1_text.setBackground(Color.lightGray);
		h1_text.setBounds(380, 35, 130, 30);
		h1_text.setFont(font3);
		h1_text.setText("");
		showParam.add(l1_text);
		showParam.add(h1_text);

		JLabel RAD = new JLabel("雷达:");
		RAD.setBounds(10, 65, 120, 30);
		RAD.setFont(font3);
		showParam.add(RAD);
		b4_text = new JTextField();
		b4_text.setBackground(Color.lightGray);
		b4_text.setFont(font3);
		b4_text.setBounds(120, 65, 130, 30);
		b4_text.setText("");
		showParam.add(b4_text);
		l4_text = new JTextField();
		h4_text = new JTextField();

		l4_text.setBackground(Color.lightGray);
		l4_text.setBounds(250, 65, 130, 30);
		l4_text.setFont(font3);
		l4_text.setText("");
		h4_text.setBackground(Color.lightGray);
		h4_text.setBounds(380, 65, 130, 30);
		h4_text.setFont(font3);
		h4_text.setText("");
		showParam.add(l4_text);
		showParam.add(h4_text);

		JLabel E = new JLabel("跑道原点:");
		E.setBounds(10, 95, 120, 30);
		E.setFont(font3);
		showParam.add(E);
		b3_text = new JTextField();
		b3_text.setBackground(Color.lightGray);
		b3_text.setFont(font3);
		b3_text.setBounds(120, 95, 130, 30);
		b3_text.setText("");
		showParam.add(b3_text);

		l3_text = new JTextField();
		h3_text = new JTextField();

		l3_text.setBackground(Color.lightGray);
		l3_text.setBounds(250, 95, 130, 30);
		l3_text.setFont(font3);
		l3_text.setText("");
		h3_text.setBackground(Color.lightGray);
		h3_text.setBounds(380, 95, 130, 30);
		h3_text.setFont(font3);
		h3_text.setText("");
		showParam.add(l3_text);
		showParam.add(h3_text);

		JLabel f = new JLabel("跑道方向:");
		f.setBounds(10, 125, 120, 30);
		f.setFont(font3);
		showParam.add(f);
		f_text = new JTextField();
		f_text.setBackground(Color.lightGray);
		f_text.setFont(font3);
		f_text.setBounds(120, 125, 130, 30);
		f_text.setText("");
		showParam.add(f_text);

		JLabel R = new JLabel("固定站经纬仪:");
		R.setBounds(10, 155, 120, 30);
		R.setFont(font3);
		showParam.add(R);
		b2_text = new JTextField();
		b2_text.setBackground(Color.lightGray);
		b2_text.setFont(font3);
		b2_text.setBounds(120, 155, 130, 30);
		b2_text.setText("");
		showParam.add(b2_text);

		l2_text = new JTextField();
		h2_text = new JTextField();

		l2_text.setBackground(Color.lightGray);
		l2_text.setBounds(250, 155, 130, 30);
		l2_text.setFont(font3);
		l2_text.setText("");
		h2_text.setBackground(Color.lightGray);
		h2_text.setBounds(380, 155, 130, 30);
		h2_text.setFont(font3);
		h2_text.setText("");
		showParam.add(l2_text);
		showParam.add(h2_text);

		// 右下角板块
		GridLayout Grid = new GridLayout(5, 4, 0, 0);
		endJPanel = new JPanel();
		endJPanel.setLayout(Grid);
		endJPanel.setLayout(null);
		endJPanel.setBounds(10, 485, 522, 658);
		endJPanel.setSize(520, 185);
		endJPanel.setBorder(BorderFactory.createTitledBorder(null, "TSPI基本信息", TitledBorder.LEFT, TitledBorder.TOP,
				font2, Color.black)); // 设置边框
		rightPanel.add(endJPanel);

		// TSPI基本参数
		JLabel l = new JLabel("当前位置(BLH):");
		l.setBounds(10, 25, 120, 30);
		l.setFont(font3);
		endJPanel.add(l);
		l_text = new JTextField();
		l_text.setBackground(Color.lightGray);
		l_text.setFont(font3);
		l_text.setBounds(120, 25, 130, 30);
		l_text.setText("");
		endJPanel.add(l_text);

		b8_text = new JTextField();
		l8_text = new JTextField();

		b8_text.setBackground(Color.lightGray);
		b8_text.setBounds(250, 25, 130, 30);
		b8_text.setFont(font3);
		b8_text.setText("");
		l8_text.setBackground(Color.lightGray);
		l8_text.setBounds(380, 25, 130, 30);
		l8_text.setFont(font3);
		l8_text.setText("");
		endJPanel.add(b8_text);
		endJPanel.add(l8_text);
		JLabel ges = new JLabel("当前姿态(AER):");
		ges.setBounds(10, 55, 120, 30);
		ges.setFont(font3);
		endJPanel.add(ges);
		ges_text = new JTextField();
		ges_text.setBackground(Color.lightGray);
		ges_text.setFont(font3);
		ges_text.setBounds(120, 55, 130, 30);
		ges_text.setText("");
		endJPanel.add(ges_text);

		b9_text = new JTextField();
		l9_text = new JTextField();

		b9_text.setBackground(Color.lightGray);
		b9_text.setBounds(250, 55, 130, 30);
		b9_text.setFont(font3);
		b9_text.setText("");
		l9_text.setBackground(Color.lightGray);
		l9_text.setBounds(380, 55, 130, 30);
		l9_text.setFont(font3);
		l9_text.setText("");
		endJPanel.add(b9_text);
		endJPanel.add(l9_text);

		JLabel bjTimeJLabel = new JLabel("北京时间：");
		bjTimeJLabel.setBounds(10, 85, 120, 30);
		bjTimeJLabel.setFont(font3);
		endJPanel.add(bjTimeJLabel);
		bjTime_text = new JTextField();
		bjTime_text.setBackground(Color.lightGray);
		bjTime_text.setFont(font3);
		bjTime_text.setBounds(120, 85, 130, 30);
		bjTime_text.setFocusable(false);
		endJPanel.add(bjTime_text);

		JLabel gpsTimeJLabel = new JLabel("GPS时间：");
		gpsTimeJLabel.setBounds(260, 85, 120, 30);
		gpsTimeJLabel.setFont(font3);
		endJPanel.add(gpsTimeJLabel);
		gpsTime_text = new JTextField();
		gpsTime_text.setBackground(Color.lightGray);
		gpsTime_text.setFont(font3);
		gpsTime_text.setBounds(380, 85, 130, 30);
		gpsTime_text.setFocusable(false);
		endJPanel.add(gpsTime_text);

		JLabel wholeSecondJLabel = new JLabel("整秒时间：");
		wholeSecondJLabel.setBounds(10, 115, 120, 30);
		wholeSecondJLabel.setFont(font3);
		endJPanel.add(wholeSecondJLabel);
		wholeSecond_text = new JTextField();
		wholeSecond_text.setBackground(Color.lightGray);
		wholeSecond_text.setFont(font3);
		wholeSecond_text.setBounds(120, 115, 130, 30);
		wholeSecond_text.setFocusable(false);
		endJPanel.add(wholeSecond_text);

		JLabel h = new JLabel("加速度:");
		h.setBounds(260, 115, 120, 30);
		h.setFont(font3);
		endJPanel.add(h);
		h_text = new JTextField();
		h_text.setBackground(Color.lightGray);
		h_text.setFont(font3);
		h_text.setBounds(380, 115, 130, 30);
		endJPanel.add(h_text);

		JLabel j = new JLabel("角速度:");
		j.setBounds(10, 145, 120, 30);
		j.setFont(font3);
		endJPanel.add(j);
		j_text = new JTextField();
		j_text.setBackground(Color.lightGray);
		j_text.setFont(font3);
		j_text.setBounds(120, 145, 130, 30);
		endJPanel.add(j_text);

		JLabel g = new JLabel("角加速度:");
		g.setBounds(260, 145, 120, 30);
		g.setFont(font3);
		endJPanel.add(g);
		g_text = new JTextField();
		g_text.setBackground(Color.lightGray);
		g_text.setFont(font3);
		g_text.setBounds(380, 145, 130, 30);
		endJPanel.add(g_text);

		addMessageTrans(b8_text, "locationL","L");
		addMessageTrans(l_text, "locationB","B");
		addMessageTrans(l8_text, "locationH","l8_text");
		addMessageTrans(j_text, "角速度","j_text");
		addMessageTrans(h_text, "acceleration","h_text");
		addMessageTrans(g_text, "angAcceleration","g_text");
		addMessageTrans(ges_text,"gesA","");
		addMessageTrans(b9_text,"gesE","");
		addMessageTrans(l9_text,"gesR","");
		JLabel turnJLabel = new JLabel();
		turnJLabel.setBackground(Color.white);
		turnJLabel.setBounds(450, 10, 24, 24);
		Icon icon2 = new ImageIcon("./image/turn.png");
		turnJLabel.setIcon(icon2);
		turnJLabel.setOpaque(false);
		turnJLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// 循环点亮
				if (StateHandler.State == 1) {
					// 点亮10HZ
					lowHZJLabel.setForeground(Color.WHITE);
					highHZJLabel.setForeground(Color.gray);
				}
				if (StateHandler.State == 2) {
					// 点亮25HZ
					midHZJLabel.setForeground(Color.WHITE);
					lowHZJLabel.setForeground(Color.gray);
				}
				if (StateHandler.State == 3) {
					// 点亮50HZ
					highHZJLabel.setForeground(Color.WHITE);
					midHZJLabel.setForeground(Color.gray);
				}
				StateHandler.flipState();
			}
		});
		// 重绘
		this.repaint();
//		vutilsThread.run();
	}

	/**
	 * 创建菜单栏
	 * 
	 * @return
	 */
	private JMenuBar addMenuBar() {
		menuBar = new JMenuBar();
		menuBar.setLayout(null);

		startButton = createBtn("打开TSPI", "./image/start.png");
		startButton.setBorder(BorderFactory.createRaisedBevelBorder());
		startButton.setFont(defaultfont_ch);
		stopButton = createBtn("关闭TSPI", "./image/stop.png");
		stopButton.setBorder(BorderFactory.createRaisedBevelBorder());
		stopButton.setFont(defaultfont_ch);
		aboutButton = createBtn("开始记录", "./image/about.png");
		aboutButton.setBorder(BorderFactory.createRaisedBevelBorder());
		aboutButton.setFont(defaultfont_ch);
		zantingButton = createBtn("暂停记录", "./image/zanting.png");
		zantingButton.setBorder(BorderFactory.createRaisedBevelBorder());
		zantingButton.setFont(defaultfont_ch);

		startButton.setBounds(0, 2, 80, 60);
		stopButton.setBounds(81, 2, 80, 60);
		aboutButton.setBounds(162, 2, 80, 60);
		zantingButton.setBounds(243, 2, 80, 60);
		// 增加按钮

		menuBar.add(startButton);
		menuBar.add(stopButton);
		menuBar.add(aboutButton);
		menuBar.add(zantingButton);

		return menuBar;
	}

	/**
	 * 设置时间
	 */
	public void setTime() {
		timer = new Timer();
		timer.schedule(new TimerTask() {
			SimpleDateFormat dateFormatter = new SimpleDateFormat(DEFAULT_TIME_FORMAT);

			@SuppressWarnings("deprecation")
			@Override
			public void run() {
				format = dateFormatter.format(new Date());
				if (bool) {
					timeField.setText(format);
					bjTime_text.setText(format);
					Date d = new Date();
					int ns = d.getHours() * 3600 + d.getMinutes() * 3600 + d.getSeconds();
					wholeSecond_text.setText("00" + ns);
					String timeString = getFormatedDateString(0);
					gpsTime_text.setText(timeString);
				}
			}
		}, 0, 1);
	}

	/**
	 * 此函数非原创，从网上搜索而来，timeZoneOffset原为int类型，为班加罗尔调整成float类型
	 * timeZoneOffset表示时区，如中国一般使用东八区，因此timeZoneOffset就是8
	 * 
	 * @param timeZoneOffset
	 * @return
	 */
	public static String getFormatedDateString(float timeZoneOffset) {
		if (timeZoneOffset > 13 || timeZoneOffset < -12) {
			timeZoneOffset = 0;
		}

		int newTime = (int) (timeZoneOffset * 60 * 60 * 1000);
		TimeZone timeZone;
		String[] ids = TimeZone.getAvailableIDs(newTime);
		if (ids.length == 0) {
			timeZone = TimeZone.getDefault();
		} else {
			timeZone = new SimpleTimeZone(newTime, ids[0]);
		}
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS");
		sdf.setTimeZone(timeZone);
		return sdf.format(new Date());
	}
	private String getTime() {
		Calendar c = Calendar.getInstance();//可以对每个时间域单独修改
		int year = c.get(Calendar.YEAR); 
		int month = c.get(Calendar.MONTH); 
		int date = c.get(Calendar.DATE); 
		int hour = c.get(Calendar.HOUR_OF_DAY); 
		int minute = c.get(Calendar.MINUTE); 
		int second = c.get(Calendar.SECOND); 
		return String.valueOf(year)+"-"+String.valueOf(month)+"-"+String.valueOf(date)+"-"+String.valueOf(hour)+"-"+String.valueOf(minute)+"-"+String.valueOf(second);
	}
	/**
	 * 创建按钮
	 * 
	 * @param text
	 * @param icon
	 * @return
	 */
	private JButton createBtn(String text, String icon) {
		ImageIcon imageIcon = new ImageIcon(icon);
		JButton btn = new JButton(text, imageIcon);
		btn.setUI(new BasicButtonUI());// 恢复基本视觉效果
		btn.setPreferredSize(new Dimension(80, 27));// 设置按钮大小
		btn.setContentAreaFilled(false);// 设置按钮透明
		btn.setFont(new Font("粗体", Font.PLAIN, 10));// 按钮文本样式
		btn.setMargin(new Insets(0, 0, 0, 0));// 按钮内容与边框距离
		btn.setVerticalTextPosition(JButton.BOTTOM);
		btn.setHorizontalTextPosition(JButton.CENTER);
		return btn;
	}

	/**
	 * 设置皮肤
	 */
	private void setLookAndFeel() {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 添加监听
	 */
	private void addListener() {

		// 扫描设备
		ret = USB_Device.INSTANCE.USB_ScanDevice(DevHandleArry);
		if (ret > 0) {
			System.out.println("DeviceNum = " + ret);
			DevHandle = DevHandleArry[0];
			System.out.println("DevHandle = " + DevHandle);
		} else {
			System.out.println("No device");
			return;
		}
		// 打开设备
		state = USB_Device.INSTANCE.USB_OpenDevice(DevHandle);
		if (!state) {
			System.out.println("open device error");
			return;
		}
		// 获取设备信息
		USB_Device.DEVICE_INFO DevInfo = new USB_Device.DEVICE_INFO();
		byte[] funcStr = new byte[128];
		state = USB_Device.INSTANCE.DEV_GetDeviceInfo(DevHandle, DevInfo, funcStr);
		if (!state) {
			System.out.println("get device infomation error");
			return;
		} else {
			try {
				System.out.println("Firmware Info:");
				System.out.println("--Name:" + new String(DevInfo.FirmwareName, "UTF-8"));
				System.out.println("--Build Date:" + new String(DevInfo.BuildDate, "UTF-8"));
				System.out.println(String.format("--Firmware Version:v%d.%d.%d", (DevInfo.FirmwareVersion >> 24) & 0xFF,
						(DevInfo.FirmwareVersion >> 16) & 0xFF, DevInfo.FirmwareVersion & 0xFFFF));
				System.out.println(String.format("--Hardware Version:v%d.%d.%d", (DevInfo.HardwareVersion >> 24) & 0xFF,
						(DevInfo.HardwareVersion >> 16) & 0xFF, DevInfo.HardwareVersion & 0xFFFF));
				System.out.println("--Functions:" + new String(funcStr, "UTF-8"));
				int s = 0;
				/**
				 * 0x0001--1 0x0010--16 0x0100--256 0x1000--4096 0x1111--4369
				 *
				 */
				int w = 4369;

				USB2GPIO.INSTANCE.GPIO_SetOutput(DevHandle, 4369, (byte) 0);
				// s = USB2GPIO.INSTANCE.GPIO_Write(DevHandle, 4369, w);

				s = USB2GPIO.INSTANCE.GPIO_Read(DevHandle, 0x1111, ss);

				int r = Integer.decode(Integer.toHexString(ss[0]));
				System.out.println(r);
				startButton.addMouseListener(new MouseListener() {
					@Override
					public void mouseReleased(MouseEvent e) {
					}

					@Override
					public void mousePressed(MouseEvent e) {
					}

					@Override
					public void mouseExited(MouseEvent e) {
						// 鼠标移出
						startButton.setBorder(BorderFactory.createRaisedBevelBorder());
					}

					@Override
					public void mouseEntered(MouseEvent e) {
						// 鼠标移进时
						startButton.setBorder(BorderFactory.createLoweredBevelBorder());
					}

					@Override
					public void mouseClicked(MouseEvent e) {
						setTime();
						SocketClient sc = new SocketClient();
						Map<String, String> mes = new HashMap<String, String>();
						bool = true;
						shine.setIcon(green);
						MyJDialog3 dialog2 = new MyJDialog3(Tspi.this);
						dialog2.setVisible(true); // 使MyJDialog窗体可见
						Map<String, String> map = new HashMap<String, String>();
						map = dialog2.getInit();
						b0 = Double.parseDouble(map.get("B0"));
						l0 = Double.parseDouble(map.get("L0"));
						h0 = Double.parseDouble(map.get("H0"));
						b1 = Double.parseDouble(map.get("B2"));
						l1 = Double.parseDouble(map.get("L2"));
						h1 = Double.parseDouble(map.get("H2"));
						b2 = Double.parseDouble(map.get("B3"));
						l2 = Double.parseDouble(map.get("L3"));
						h2 = Double.parseDouble(map.get("H3"));
						b3 = Double.parseDouble(map.get("B4"));
						l3 = Double.parseDouble(map.get("L4"));
						h3 = Double.parseDouble(map.get("H4"));
						ang = Double.parseDouble(map.get("ang"));
						b1_text.setText(String.valueOf(b0));
						l1_text.setText(String.valueOf(l0));
						h1_text.setText(String.valueOf(h0));
						b2_text.setText(String.valueOf(b1));
						l2_text.setText(String.valueOf(l1));
						h2_text.setText(String.valueOf(h1));
						b3_text.setText(String.valueOf(b2));
						l3_text.setText(String.valueOf(l2));
						h3_text.setText(String.valueOf(h2));
						b4_text.setText(String.valueOf(b3));
						l4_text.setText(String.valueOf(l3));
						h4_text.setText(String.valueOf(h3));
						f_text.setText(String.valueOf(ang));
						l_text.setText("34.6409908");
						b8_text.setText("109.2393617");
						l8_text.setText("367.807");
						ges_text.setText("222.95470");
						b9_text.setText("0.724184");
						l9_text.setText("2393.80000");
						mes.put("name", "TSP");
						mes.put("oriB", String.valueOf(b0));
						mes.put("oriL", String.valueOf(l0));
						mes.put("oriH", String.valueOf(h0));
						mes.put("jingweiB", String.valueOf(b1));
						mes.put("jingweiL", String.valueOf(l1));
						mes.put("jingweiH", String.valueOf(h1));
						mes.put("runB", String.valueOf(b2));
						mes.put("runL", String.valueOf(l2));
						mes.put("runH", String.valueOf(h2));
						mes.put("radarB", String.valueOf(b3));
						mes.put("radarL", String.valueOf(l3));
						mes.put("radarH", String.valueOf(h3));
						mes.put("locationB", "34.6409908");
						mes.put("locationL", "109.2393617");
						mes.put("locationH", "367.807");
						mes.put("gesA", "222.95470");
						mes.put("gesE", "0.724184");
						mes.put("gesR", "2393.80000");
						mes.put("acceleration", "8.25    m/s2");
						mes.put("angAcceleration", "306.5   rad/s2");
						try {
							sc.SocketSendMes(mes);
						} catch (SocketException | UnknownHostException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						h_text.setText("8.25    m/s2");
						j_text.setText("6.35   rad/s");
						g_text.setText("306.5   rad/s2");
						showField.append("  " + format + "[操作手]：打开TSPI" + "\r\n");
						if (light1 == 0)// p0亮
						{
							USB2GPIO.INSTANCE.GPIO_Write(DevHandle, 4369, 1);// p0灭
							light1 = 1;
						}
					}
				});
				stopButton.addMouseListener(new MouseListener() {
					@Override
					public void mouseReleased(MouseEvent e) {
					}

					@Override
					public void mousePressed(MouseEvent e) {
					}

					@Override
					public void mouseExited(MouseEvent e) {
						// 鼠标移出
						stopButton.setBorder(BorderFactory.createRaisedBevelBorder());
					}

					@Override
					public void mouseEntered(MouseEvent e) {
						// 鼠标移进时
						stopButton.setBorder(BorderFactory.createLoweredBevelBorder());
					}

					public void mouseClicked(MouseEvent e) {
						bool = false;
						shine.setIcon(grey);
						rember.setIcon(grey);
						showField.append("  " + format + "[操作手]：关闭TSPI" + "\r\n");
						if (light1 == 1)// p0亮
						{
							USB2GPIO.INSTANCE.GPIO_Write(DevHandle, 4369, 0);// p0灭
							light1 = 0;
						}
					}
				});

				aboutButton.addMouseListener(new MouseListener() {
					@Override
					public void mouseReleased(MouseEvent e) {
					}

					@Override
					public void mousePressed(MouseEvent e) {
					}

					@Override
					public void mouseExited(MouseEvent e) {
						// 鼠标移出
						aboutButton.setBorder(BorderFactory.createRaisedBevelBorder());
					}

					@Override
					public void mouseEntered(MouseEvent e) {
						// 鼠标移进时
						aboutButton.setBorder(BorderFactory.createLoweredBevelBorder());
					}

					@Override
					public void mouseClicked(MouseEvent e) {
						if (bool == true) {
							rember.setIcon(green);
							showField.append("  " + format + "[操作手]：开始记录" + "\r\n");
							Properties prop = new Properties();
							try {
								BufferedWriter bw = new BufferedWriter(new FileWriter("D:\\logText\\log"+getTime()+".txt"));
								// 设置文本内容
								prop.setProperty("原点(BLH)",
										b1_text.getText() + " " + l1_text.getText() + " " + h1_text.getText());
								prop.setProperty("固定站经纬仪",
										b2_text.getText() + " " + l2_text.getText() + " " + h2_text.getText());
								prop.setProperty("跑道原点",
										b3_text.getText() + " " + l3_text.getText() + " " + h3_text.getText());
								prop.setProperty("雷达",
										b4_text.getText() + " " + l4_text.getText() + " " + h4_text.getText());
								prop.setProperty("跑道方向", f_text.getText());
								prop.setProperty("当前位置",
										l_text.getText() + " " + b8_text.getText() + " " + l8_text.getText());
								prop.setProperty("当前姿态",
										ges_text.getText() + " " + b9_text.getText() + " " + l9_text.getText());
								prop.setProperty("北京时间", bjTime_text.getText());
								prop.setProperty("GPS时间", gpsTime_text.getText());
								prop.setProperty("整秒时间", wholeSecond_text.getText());
								prop.setProperty("加速度", h_text.getText());
								prop.setProperty("角速度", j_text.getText());
								prop.setProperty("角加速度", g_text.getText());
								String time=getTime();
								prop.store(bw,"D:\\logText\\log"+time+".txt");
								localPath="D:\\logText\\log"+time+".txt";
								fileName="log"+time+".txt";
								String a = prop.toString();
								bw.write(a);
								if (light2 == 0)// p4亮
								{
									USB2GPIO.INSTANCE.GPIO_Write(DevHandle, 4369, 17);// p4灭
								}
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					}
				});
				zantingButton.addMouseListener(new MouseListener() {
					@Override
					public void mouseReleased(MouseEvent e) {
					}

					@Override
					public void mousePressed(MouseEvent e) {
					}

					@Override
					public void mouseExited(MouseEvent e) {
						// 鼠标移出
						zantingButton.setBorder(BorderFactory.createRaisedBevelBorder());
					}

					@Override
					public void mouseEntered(MouseEvent e) {
						// 鼠标移进时
						zantingButton.setBorder(BorderFactory.createLoweredBevelBorder());
					}

					@Override
					public void mouseClicked(MouseEvent e) {
						if (bool == true) {
							rember.setIcon(grey);
							showField.append("  " + format + "[操作手]：信息记录完成,文件发送完毕" + "\r\n");
							USB2GPIO.INSTANCE.GPIO_Write(DevHandle, 4369, 1);
							light2 = 0;
							try{
					            FileInputStream in=new FileInputStream(new File(localPath));
					            boolean test = FtpUtils.uploadFile(ftpHost, ftpUserName, ftpPassword, ftpPort, ftpPath, fileName,in);
					            System.out.println(test);
					        } catch (FileNotFoundException ag){
					            ag.printStackTrace();
					            System.out.println(ag);
					        }
						}
					}
				});
			} catch (Exception ep) {
				ep.printStackTrace();
			}}

	}

	/**
	 * 设置速度
	 * 
	 * @param v
	 */
	public static void setVField(String v) {
		vField.setText(v + "  m/s");
	}
	//遍历文件夹中的文件函数
	public void readAllFile(File dir) {
		 if (dir.exists()) {
	            for (File f : dir.listFiles()) {
	                if (f.isDirectory()) {
	                    System.out.println("DIR: " + f.getName());
	                    readAllFile(f);
	                } else {
	                	Files.addItem(f.getName());
	                    System.out.println(" File: " + f.getName());
	                }
	            }
	        }
	}
	public void readFiles(File dir,JComboBox<String> a) {
		 if (dir.exists()) {
	            for (File f : dir.listFiles()) {
	                if (f.isDirectory()) {
	                    System.out.println("DIR: " + f.getName());
	                    readAllFile(f);
	                } else {
	                	a.addItem(f.getName());
	                    System.out.println(" File: " + f.getName());
	                }
	            }
	        }
	}
	//添加通信功能函数
	public static void addMessageTrans(JTextField a, String name,String mapName) {

		a.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					System.out.println(a.getText());
					SocketClient sc = new SocketClient();
					Map<String, String> map = new HashMap<String, String>();
					Map<String, String> mes = new HashMap<String, String>();
					mes.put(name , a.getText());
					mes.put("name", "TSP");
					map.put("name", "TSPI");
					map.put("ID", "TSPI_DT2019001");
					map.put("modeljson", "{\"" + mapName + "\":\"" + a.getText()+"\"}");
					MessageUtil.sendToTena(map);
					try {
						sc.SocketSendMes(mes);
					} catch (SocketException | UnknownHostException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

		});

	}// 通信监听发送函数

	class MMconsumer extends AbstractMessageExecutor {

		private MMconsumer() {
			// TODO Auto-generated method stub

		}

		@Override
		protected void run(Map<String, String> map) {
			// TODO Auto-generated method stub
			String name = map.get("ID");
			Map<String, String> mes = new HashMap<String, String>();
			if ("TSPI_DT2019001".equals(name.toUpperCase())) {
				System.out.println("这是map" + map);
				JSONObject jsonObject = new JSONObject(map.get("modeljson"));
				Iterator iterator = jsonObject.keys();
				while (iterator.hasNext()) {
					String key = (String) iterator.next();
					String value = jsonObject.getString(key);
					switch (key) {
					case "L": {
						b8_text.setText(value);
						mes.clear();
						mes.put("locationL", value);
						mes.put("name", "TSP");
						try {
							sc.SocketSendMes(mes);
						} catch (SocketException | UnknownHostException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						break;
					}
					case "B": {
						l_text.setText(value);
						mes.clear();
						mes.put("locationB", value);
						mes.put("name", "TSP");
						try {
							sc.SocketSendMes(mes);
						} catch (SocketException | UnknownHostException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						break;
					}
					case "l8_text": {
						l8_text.setText(value);
						mes.clear();
						mes.put("locationH", value);
						mes.put("name", "TSP");
						try {
							sc.SocketSendMes(mes);
						} catch (SocketException | UnknownHostException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						break;
					}
					case "j_text": {
						j_text.setText(value);
						break;
					}
					case "h_text": {
						h_text.setText(value);
						mes.clear();
						mes.put("acceleration", value);
						mes.put("name", "TSP");
						try {
							sc.SocketSendMes(mes);
						} catch (SocketException | UnknownHostException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						break;
					}
					case "g_text": {
						g_text.setText(value);
						mes.clear();
						mes.put("angAcceleration", value);
						mes.put("name", "TSP");
						try {
							sc.SocketSendMes(mes);
						} catch (SocketException | UnknownHostException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						break;
					}
					case "gpsTime_text": {
						gpsTime_text.setText(value);
						break;
					}
					case "bjTime_text": {
						bjTime_text.setText(value);
						break;
					}
					case "wholeSecond_text": {
						wholeSecond_text.setText(value);
						break;
					}
					case "menuAction": {
						if ("turnDownTSPI".equals(value)) {
							bool = false;
							shine.setIcon(grey);
							rember.setIcon(grey);
							showField.append("  " + format + "[操作手]：关闭TSPI" + "\r\n");
							if (light1 == 1)// p0亮
							{
								USB2GPIO.INSTANCE.GPIO_Write(DevHandle, 4369, 0);// p0灭
								light1 = 0;
							}
						} else if ("turnUpTSPI".equals(value)) {
							setTime();
							bool = true;
							shine.setIcon(green);
							setTime();
							showField.append("  " + format + "[操作手]：打开TSPI" + "\r\n");
							if (light1 == 0)// p0亮
							{
								USB2GPIO.INSTANCE.GPIO_Write(DevHandle, 4369, 1);// p0灭
								light1 = 1;
							}
							MyJDialog3 dialog2 = new MyJDialog3(Tspi.this);
							dialog2.setVisible(true); // 使MyJDialog窗体可见
							Map<String, String> mess = new HashMap<String, String>();
							Map<String, String> ma = new HashMap<String, String>();
							ma = dialog2.getInit();
							b0 = Double.parseDouble(ma.get("B0"));
							l0 = Double.parseDouble(ma.get("L0"));
							h0 = Double.parseDouble(ma.get("H0"));
							b1 = Double.parseDouble(ma.get("B2"));
							l1 = Double.parseDouble(ma.get("L2"));
							h1 = Double.parseDouble(ma.get("H2"));
							b2 = Double.parseDouble(ma.get("B3"));
							l2 = Double.parseDouble(ma.get("L3"));
							h2 = Double.parseDouble(ma.get("H3"));
							b3 = Double.parseDouble(ma.get("B4"));
							l3 = Double.parseDouble(ma.get("L4"));
							h3 = Double.parseDouble(ma.get("H4"));
							ang = Double.parseDouble(ma.get("ang"));
							b1_text.setText(String.valueOf(b0));
							l1_text.setText(String.valueOf(l0));
							h1_text.setText(String.valueOf(h0));
							b2_text.setText(String.valueOf(b1));
							l2_text.setText(String.valueOf(l1));
							h2_text.setText(String.valueOf(h1));
							b3_text.setText(String.valueOf(b2));
							l3_text.setText(String.valueOf(l2));
							h3_text.setText(String.valueOf(h2));
							b4_text.setText(String.valueOf(b3));
							l4_text.setText(String.valueOf(l3));
							h4_text.setText(String.valueOf(h3));
							f_text.setText(String.valueOf(ang));
							l_text.setText("34.6409908");
							b8_text.setText("109.2393617");
							l8_text.setText("367.807");
							ges_text.setText("222.95470");
							b9_text.setText("0.724184");
							l9_text.setText("2393.80000");
							h_text.setText("8.25    m/s2");
							j_text.setText("6.35   rad/s");
							g_text.setText("306.5   rad/s2");
							mess.put("name", "TSP");
							mess.put("oriB", String.valueOf(b0));
							mess.put("oriL", String.valueOf(l0));
							mess.put("oriH", String.valueOf(h0));
							mess.put("jingweiB", String.valueOf(b1));
							mess.put("jingweiL", String.valueOf(l1));
							mess.put("jingweiH", String.valueOf(h1));
							mess.put("runB", String.valueOf(b2));
							mess.put("runL", String.valueOf(l2));
							mess.put("runH", String.valueOf(h2));
							mess.put("radarB", String.valueOf(b3));
							mess.put("radarl", String.valueOf(l3));
							mess.put("radarH", String.valueOf(h3));
							mess.put("locationB", "34.6409908");
							mess.put("locationL", "109.2393617");
							mess.put("locationH", "367.807");
							mess.put("gesA", "222.95470");
							mess.put("gesE", "0.724184");
							mess.put("gesR", "2393.80000");
							mess.put("acceleration", "8.25    m/s2");
							mess.put("angAcceleration", "306.5   rad/s2");
							try {
								sc.SocketSendMes(mess);
							} catch (SocketException | UnknownHostException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						} else if ("turnUpRecord".equals(value)) {
							if (bool == true) {
								rember.setIcon(green);
								showField.append("  " + format + "[操作手]：开始记录" + "\r\n");
								if (light2 == 0)// p4亮
								{
									USB2GPIO.INSTANCE.GPIO_Write(DevHandle, 4369, 17);// p4灭
								}
								Properties prop = new Properties();
								try {
									String time=getTime();
									BufferedWriter bw = new BufferedWriter(new FileWriter("D:\\logText\\log"+time+".txt"));
									// 设置文本内容
									prop.setProperty("原点(BLH)",
											b1_text.getText() + " " + l1_text.getText() + " " + h1_text.getText());
									prop.setProperty("固定站经纬仪",
											b2_text.getText() + " " + l2_text.getText() + " " + h2_text.getText());
									prop.setProperty("跑道原点",
											b3_text.getText() + " " + l3_text.getText() + " " + h3_text.getText());
									prop.setProperty("雷达",
											b4_text.getText() + " " + l4_text.getText() + " " + h4_text.getText());
									prop.setProperty("跑道方向", f_text.getText());
									prop.setProperty("当前位置",
											l_text.getText() + " " + b8_text.getText() + " " + l8_text.getText());
									prop.setProperty("当前姿态",
											ges_text.getText() + " " + b9_text.getText() + " " + l9_text.getText());
									prop.setProperty("北京时间", bjTime_text.getText());
									prop.setProperty("GPS时间", gpsTime_text.getText());
									prop.setProperty("整秒时间", wholeSecond_text.getText());
									prop.setProperty("加速度", h_text.getText());
									prop.setProperty("角速度", j_text.getText());
									prop.setProperty("角加速度", g_text.getText());
									prop.store(bw,"D:\\logText\\log"+time+".txt");
									localPath="D:\\logText\\log"+time+".txt";
									fileName="log"+time+".txt";								
									String a = prop.toString();
									bw.write(a);
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}
						} else if ("turnDownRecord".equals(value)) {
							if (bool == true) {
								rember.setIcon(grey);
								showField.append("  " + format + "[操作手]：信息记录完成,文件发送完毕。" + "\r\n");
								USB2GPIO.INSTANCE.GPIO_Write(DevHandle, 4369, 1);
								light2 = 0;
								try{
						            FileInputStream in=new FileInputStream(new File(localPath));
						            boolean test = FtpUtils.uploadFile(ftpHost, ftpUserName, ftpPassword, ftpPort, ftpPath, fileName,in);
						            System.out.println(test);
						        } catch (FileNotFoundException ag){
						            ag.printStackTrace();
						            System.out.println(ag);
						        }
							}
						}
					}
					}
				}
			}
		}
	}

}

class MyJDialog2 extends JDialog {
	private String fileAdress;
	private String file_Name;

	public MyJDialog2(Tspi frame) {
		super(frame, "选择保存位置", true);
		JDialog a = this;
		Container container = getContentPane();
		container.setLayout(null);
		// 添加选择按钮
		JButton jbu = new JButton("选择位置");
		jbu.setBounds(20, 20, 80, 30);
		jbu.setSize(80, 30);
		container.add(jbu);
		JLabel fileName = new JLabel("请选择位置");
		fileName.setBounds(113, 20, 280, 30);
		jbu.addActionListener(new ActionListener() { // 为按钮添加点击事件

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc = new JFileChooser();
				jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				jfc.showDialog(jbu, "选择");
				File file = jfc.getSelectedFile();
				fileName.setText(file.getAbsolutePath());
				fileAdress = file.getAbsolutePath();
				file_Name = file.getName();
			}
		});

		JButton commit = new JButton("保存");
		commit.setBounds(150, 60, 80, 30);
		commit.setSize(80, 30);
		commit.addActionListener(new ActionListener() { // 为按钮添加点击事件

			@Override
			public void actionPerformed(ActionEvent e) {
				a.dispose();
			}
		});
		JButton cancel = new JButton("取消");
		cancel.setBounds(240, 60, 80, 30);
		cancel.setSize(80, 30);
		cancel.addActionListener(new ActionListener() { // 为按钮添加点击事件

			@Override
			public void actionPerformed(ActionEvent e) {
				a.dispose();
			}
		});
		container.add(fileName);
		container.add(commit);
		container.add(cancel);
		this.setBounds(200, 250, 400, 150);
		this.setSize(400, 150);
	}

	public String getFileAdress() {
		return this.fileAdress;
	}

	public String getFile_Name() {
		return this.file_Name;
	}
}

class MyJDialog extends JDialog {
	private String fileAdress;
	private String file_Name;

	public MyJDialog(Tspi frame) {
		super(frame, "选择文件", true);
		JDialog a = this;
		Container container = getContentPane();
		container.setLayout(null);
		// 添加选择按钮
		JButton jbu = new JButton("选择文件");
		jbu.setBounds(20, 20, 80, 30);
		jbu.setSize(80, 30);
		container.add(jbu);
		JLabel fileName = new JLabel("请选择文件");
		fileName.setBounds(113, 20, 280, 30);
		jbu.addActionListener(new ActionListener() { // 为按钮添加点击事件

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc = new JFileChooser();
				jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				jfc.showDialog(jbu, "选择");
				File file = jfc.getSelectedFile();
				fileAdress = file.getAbsolutePath();
				fileName.setText(file.getAbsolutePath());
				fileAdress = file.getAbsolutePath();
				fileName.setText(file.getAbsolutePath());
				file_Name = file.getName();
			}
		});

		JButton commit = new JButton("确定");
		commit.setBounds(150, 60, 80, 30);
		commit.setSize(80, 30);
		commit.addActionListener(new ActionListener() { // 为按钮添加点击事件

			@Override
			public void actionPerformed(ActionEvent e) {
				a.dispose();
			}
		});
		JButton cancel = new JButton("取消");
		cancel.setBounds(240, 60, 80, 30);
		cancel.setSize(80, 30);
		cancel.addActionListener(new ActionListener() { // 为按钮添加点击事件

			@Override
			public void actionPerformed(ActionEvent e) {
				a.dispose();
			}
		});
		container.add(fileName);
		container.add(commit);
		container.add(cancel);
		this.setBounds(200, 250, 400, 150);
		this.setSize(400, 150);
	}

	public String getFileAdress() {
		return this.fileAdress;
	}

	public String getFileName() {
		return this.file_Name;

	}
}

class alert extends JDialog {

	public alert(Tspi frame) {
		super(frame, "提示", true);
		JDialog a = this;
		Container container = getContentPane();
		container.setLayout(null);
		JLabel fileName = new JLabel("转换成功！");
		fileName.setBounds(113, 20, 280, 30);
		JButton commit = new JButton("确定");
		commit.setBounds(150, 60, 80, 30);
		commit.setSize(80, 30);
		commit.addActionListener(new ActionListener() { // 为按钮添加点击事件

			@Override
			public void actionPerformed(ActionEvent e) {
				a.dispose();
			}
		});
		JButton cancel = new JButton("取消");
		cancel.setBounds(240, 60, 80, 30);
		cancel.setSize(80, 30);
		cancel.addActionListener(new ActionListener() { // 为按钮添加点击事件

			@Override
			public void actionPerformed(ActionEvent e) {
				a.dispose();
			}
		});
		container.add(fileName);
		container.add(commit);
		container.add(cancel);
		this.setBounds(200, 250, 400, 150);
		this.setSize(400, 150);
	}
}

class MyJDialog3 extends JDialog {
	public double b, l, h, b2, l2, h2, b3, l3, h3, b4, l4, h4, ang;

	public MyJDialog3(Tspi frame) {
		super(frame, "输入初始站址信息", true);
		JDialog a = this;
		// 添加选择按钮
		Container showParam = getContentPane();
		showParam.setLayout(new GridLayout(7, 4, 0, 0));
		JLabel kon = new JLabel(" ");
		kon.setBounds(10, 20, 120, 20);
		JLabel b0 = new JLabel("       B0");
		b0.setBounds(130, 20, 120, 20);
		JLabel l0 = new JLabel("       L0");
		l0.setBounds(250, 20, 120, 20);
		JLabel h0 = new JLabel("       h0");
		h0.setBounds(370, 20, 120, 20);
		showParam.add(kon);
		showParam.add(b0);
		showParam.add(l0);
		showParam.add(h0);

		Font font3 = new Font("黑体", Font.ROMAN_BASELINE, 13);
		JLabel A = new JLabel("原点:");
		A.setBounds(10, 50, 120, 20);
		A.setFont(font3);
		showParam.add(A);
		JTextField B_text = new JTextField();
		B_text.setBackground(Color.lightGray);
		B_text.setBounds(130, 50, 120, 20);
		B_text.setFont(font3);
		B_text.setText("34.6353506001");
		showParam.add(B_text);
		JTextField L_text = new JTextField();
		JTextField H_text = new JTextField();
		L_text.setBackground(Color.lightGray);
		L_text.setBounds(250, 50, 120, 20);
		L_text.setFont(font3);
		L_text.setText("109.2260444661");
		H_text.setBackground(Color.lightGray);
		H_text.setBounds(370, 50, 120, 20);
		H_text.setFont(font3);
		H_text.setText("351.4410000001");
		showParam.add(L_text);
		showParam.add(H_text);

		JLabel R = new JLabel("固定站经纬仪:");
		R.setBounds(10, 80, 120, 20);
		R.setFont(font3);
		showParam.add(R);
		JTextField b2_text = new JTextField();
		b2_text.setBackground(Color.lightGray);
		b2_text.setFont(font3);
		b2_text.setBounds(130, 80, 120, 20);
		b2_text.setText("34.6462591831");
		showParam.add(b2_text);

		JTextField l2_text = new JTextField();
		JTextField h2_text = new JTextField();

		l2_text.setBackground(Color.lightGray);
		l2_text.setBounds(250, 80, 120, 20);
		l2_text.setFont(font3);
		l2_text.setText("109.2384398561");
		h2_text.setBackground(Color.lightGray);
		h2_text.setBounds(370, 80, 120, 20);
		h2_text.setFont(font3);
		h2_text.setText("370.8810000001");
		showParam.add(l2_text);
		showParam.add(h2_text);
		JLabel E = new JLabel("跑道原点:");
		E.setBounds(10, 110, 120, 20);
		E.setFont(font3);
		showParam.add(E);
		JTextField b3_text = new JTextField();
		b3_text.setBackground(Color.lightGray);
		b3_text.setFont(font3);
		b3_text.setBounds(130, 110, 120, 20);
		b3_text.setText("34.6354000001");
		showParam.add(b3_text);

		JTextField l3_text = new JTextField();
		JTextField h3_text = new JTextField();

		l3_text.setBackground(Color.lightGray);
		l3_text.setBounds(250, 110, 120, 20);
		l3_text.setFont(font3);
		l3_text.setText("109.2260000001");
		h3_text.setBackground(Color.lightGray);
		h3_text.setBounds(370, 110, 120, 20);
		h3_text.setFont(font3);
		h3_text.setText("351.4410000001");
		showParam.add(l3_text);
		showParam.add(h3_text);
		JLabel RAD = new JLabel("雷达:");
		RAD.setBounds(10, 140, 120, 20);
		RAD.setFont(font3);
		showParam.add(RAD);
		JTextField b4_text = new JTextField();
		b4_text.setBackground(Color.lightGray);
		b4_text.setFont(font3);
		b4_text.setBounds(130, 140, 120, 20);
		b4_text.setText("34.6485640001");
		showParam.add(b4_text);
		JTextField l4_text = new JTextField();
		JTextField h4_text = new JTextField();

		l4_text.setBackground(Color.lightGray);
		l4_text.setBounds(250, 140, 120, 20);
		l4_text.setFont(font3);
		l4_text.setText("109.2424960001");
		h4_text.setBackground(Color.lightGray);
		h4_text.setBounds(370, 140, 120, 20);
		h4_text.setFont(font3);
		h4_text.setText("367.6840000001");
		showParam.add(l4_text);
		showParam.add(h4_text);

		JLabel f = new JLabel("跑道方向:");
		f.setBounds(10, 170, 120, 20);
		f.setFont(font3);
		showParam.add(f);
		JTextField f_text = new JTextField();
		f_text.setBackground(Color.lightGray);
		f_text.setFont(font3);
		f_text.setBounds(130, 170, 120, 20);
		f_text.setText("55.8774240001");
		showParam.add(f_text);

		JLabel kon1 = new JLabel("");
		JLabel kon2 = new JLabel("");
		JLabel kon3 = new JLabel("");
		JLabel kon4 = new JLabel("");
		showParam.add(kon1);
		showParam.add(kon2);
		showParam.add(kon3);
		showParam.add(kon4);

		JButton commit = new JButton("确定");
		commit.setBounds(150, 200, 80, 30);
		commit.setSize(80, 30);
		commit.addActionListener(new ActionListener() { // 为按钮添加点击事件
			@Override
			public void actionPerformed(ActionEvent e) {
				b = Double.parseDouble(B_text.getText());
				l = (Double.parseDouble(L_text.getText()));
				h = (Double.parseDouble(H_text.getText()));
				b2 = (Double.parseDouble(b2_text.getText()));
				l2 = (Double.parseDouble(l2_text.getText()));
				h2 = (Double.parseDouble(h2_text.getText()));
				b3 = (Double.parseDouble(b3_text.getText()));
				l3 = (Double.parseDouble(l3_text.getText()));
				h3 = (Double.parseDouble(h3_text.getText()));
				b4 = (Double.parseDouble(b4_text.getText()));
				l4 = (Double.parseDouble(l4_text.getText()));
				h4 = (Double.parseDouble(h4_text.getText()));
				ang = (Double.parseDouble(f_text.getText()));
				a.dispose();
			}
		});
		JButton cancel = new JButton("取消");
		cancel.setBounds(240, 200, 80, 30);
		cancel.setSize(80, 30);
		cancel.addActionListener(new ActionListener() { // 为按钮添加点击事件
			@Override
			public void actionPerformed(ActionEvent e) {
				b = 0;
				l = 0;
				h = 0;
				b2 = 0;
				l2 = 0;
				h2 = 0;
				b3 = 0;
				l3 = 0;
				h3 = 0;
				b4 = 0;
				l4 = 0;
				h4 = 0;
				ang = 0;
				a.dispose();
			}
		});
		showParam.add(commit);
		showParam.add(cancel);
		this.setBounds(230, 250, 520, 280);
		this.setSize(520, 280);
	}

	public Map<String, String> getInit() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("B0", String.valueOf(b));
		map.put("L0", String.valueOf(l));
		map.put("H0", String.valueOf(h));
		map.put("B2", String.valueOf(b2));
		map.put("L2", String.valueOf(l2));
		map.put("H2", String.valueOf(h2));
		map.put("B3", String.valueOf(b3));
		map.put("L3", String.valueOf(l3));
		map.put("H3", String.valueOf(h3));
		map.put("B4", String.valueOf(b4));
		map.put("L4", String.valueOf(l4));
		map.put("H4", String.valueOf(h4));
		map.put("ang", String.valueOf(ang));
		return map;
	}
}
