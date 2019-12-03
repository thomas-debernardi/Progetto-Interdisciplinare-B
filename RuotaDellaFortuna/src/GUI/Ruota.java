package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Ruota extends JPanel {
	public Ruota() {
	}

	public static void main(String[] args) {
		FlowLayout layout = new FlowLayout();
		layout.setAlignment(FlowLayout.CENTER);
		Ruota ruota = new Ruota();
		JFrame F = new JFrame();
		ruota.setBounds(300, 300, 100, 100);

		JButton btn = new JButton("SPIN");
		btn.setBounds(280, 50, 100, 30);

		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
			}
		});

		F.getContentPane().add(btn);
		F.getContentPane().add(ruota);
		F.setSize(700, 700);
		F.setVisible(true);
		F.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void paintComponent(Graphics g) {
		int i = 0;
		BufferedImage wheel = LoadImage("wheel.png");
		AffineTransform at = AffineTransform.getTranslateInstance(100, 100);
		at.rotate(Math.toRadians(i++), wheel.getWidth() / 2, wheel.getHeight() / 2);
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(wheel, at, null);
		repaint();
	    g2d.rotate(Math.toRadians(45));
		repaint();

	}

	BufferedImage LoadImage(String FileName) {
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File(FileName));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return img;
	}

}
