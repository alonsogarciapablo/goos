package auctionsniper;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;

import auctionsniper.Main;

public class MainWindow extends JFrame {
	public static final String MAIN_WINDOW_NAME = "Auction Sniper Main";
	private static final JLabel sniperStatus = createLabel(Main.STATUS_JOINING);

	public MainWindow() {
		super("Auction Sniper");
		setName(MAIN_WINDOW_NAME);
		add(sniperStatus);
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public static void showStatus(String status) {
		sniperStatus.setText(status);
	}

	private static JLabel createLabel(String initialText) {
		JLabel label = new JLabel(initialText);
		label.setName(Main.SNIPER_STATUS_NAME);
		label.setBorder(new LineBorder(Color.BLACK));
		return label;
	}
}
