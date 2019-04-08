package com.wsl.tenaVisualization;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;

import javax.swing.JPanel;

import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;

import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.player.MediaPlayer;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;


public class TestClass extends JPanel {
private EmbeddedMediaPlayerComponent ourMediaPlayer;
TestClass(){
	System.loadLibrary("libvlccore");
	Native.loadLibrary("libvlccore",LibVlc.class);

    ourMediaPlayer = new EmbeddedMediaPlayerComponent();



    /* Set the canvas */
    Canvas c = new Canvas();
    c.setBackground(Color.black);
    c.setVisible(true);

    /* Set the layout */
    this.setLayout(new BorderLayout());

    /* Add the canvas */
    this.add(c, BorderLayout.CENTER);
    this.add(ourMediaPlayer);
    this.setVisible(true);



}
public void play() {
    /* Play the video */
	String[] options = {"rtsp-tcp", "network-caching=300"};
    ourMediaPlayer.getMediaPlayer().playMedia("rtsp://admin:123456@192.168.10.20:554/mpeg4", options);
}
}