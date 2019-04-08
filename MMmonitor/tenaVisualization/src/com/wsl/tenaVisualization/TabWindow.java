package com.wsl.tenaVisualization;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class TabWindow extends JFrame{
	public static void main(String[] args) {
		init();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TabWindow frame = new TabWindow();
					frame.setVisible(true);
				}catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * 界面皮肤
	 */
	public static void init() {
		try {
//		          javax.swing.UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
//+1		          javax.swing.UIManager.setLookAndFeel("com.jtattoo.plaf.bernstein.BernsteinLookAndFeel");
//		          javax.swing.UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
			javax.swing.UIManager.setLookAndFeel("com.jtattoo.plaf.mint.MintLookAndFeel");
//+3		          javax.swing.UIManager.setLookAndFeel("com.jtattoo.plaf.aero.AeroLookAndFeel");
//		          javax.swing.UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
			// handle exception
		}
	}
	
	public TabWindow() {
		getContentPane().setLayout(new BorderLayout());
		JTabbedPane tabbedPane = new JTabbedPane();
		getContentPane().add(BorderLayout.CENTER, tabbedPane);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				System.exit(0);
			}
		});
		setTitle("多区域靶场实时监控");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(new Dimension(screenSize.width, screenSize.height));
		ComponentWindow CW = new ComponentWindow();
		tabbedPane.add("仿真样机监控", CW.Component_TabbedPane_Create(this));
		DelayWindow DW = new DelayWindow();
		tabbedPane.add("时延监控", DW.Delay_TabbedPane_Create());
		
		CW.doFixThreadPool();
	}
}
