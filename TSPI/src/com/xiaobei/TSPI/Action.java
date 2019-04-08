package com.xiaobei.TSPI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;

import com.tena.mq.MessageUtil;


public class Action<property> {
	
	public Action(){
	}
	public void addAction(JButton button,String buttonName,JPanel[] Jpanels,JPanel jpanel) {
		button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				addButtonAction(button,buttonName,Jpanels,jpanel);
				}
		});
	}

	public void addButtonAction(JButton button,String buttonName,JPanel[] Jpanels,JPanel jpanel) {
		switch(buttonName)
		{
		case "BLH":
		{
			for(int i=0;i<Jpanels.length;i++)
			{
				jpanel.remove(Jpanels[i]);
			}
			for(int i=3;i<=5;i++)
			{
				jpanel.add(Jpanels[i]);
				jpanel.repaint();
			}
			break;
		}
		case "XYZ":
		{
			for(int i=0;i<Jpanels.length;i++)
			{
				jpanel.remove(Jpanels[i]);
			}
			for(int i=0;i<=2;i++)
			{
				jpanel.add(Jpanels[i]);
				jpanel.repaint();
			}
			break;
		}
		case "AER":
		{
			for(int i=0;i<Jpanels.length;i++)
			{
				jpanel.remove(Jpanels[i]);
			}
			for(int i=6;i<=8;i++)
			{
				jpanel.add(Jpanels[i]);
				jpanel.repaint();
			}
			break;
		}
		}
	}
	public void addListenerToMM(JTextField text,String property) {
		String mes=text.getText();
		text.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("正在输入信息");
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				// TODO Auto-generated method stub
				if(mes.equals(text.getText())) {
					System.out.println("信息没有更改");
				}
				else {
					System.out.println(text.getText());
					Map<String,String> map=new HashMap<String,String>();
					map.put("name", "TSP");
					String modeljson="";
					modeljson=modeljson+"{"+property+":'"+text.getText()+"'}";
					System.out.println(modeljson);
					map.put("modeljson", modeljson);
					MessageUtil.sendToTena(map);
				}
			}
			
		});
		}
}
