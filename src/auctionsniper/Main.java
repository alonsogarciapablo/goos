package auctionsniper;

import javax.swing.SwingUtilities;

public class Main {
	public static final String SNIPER_STATUS_NAME = "auction status";
	public static final String STATUS_JOINING = "joining";
	public static final String STATUS_LOST = "lost";
	private MainWindow ui;

	public Main() throws Exception {
		startUserInterface();
	}

	public static void main(String xmppHostname, String sniperId, String sniperPassword, Object itemId) throws Exception {
		new Main();
	}
	
	private void startUserInterface() throws Exception {
		SwingUtilities.invokeAndWait(new Runnable() {
			@Override
			public void run() {
				ui = new MainWindow();
			}
		});
	}
}
