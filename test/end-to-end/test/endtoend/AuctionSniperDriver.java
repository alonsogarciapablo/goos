package test.endtoend;

import static org.hamcrest.CoreMatchers.equalTo;
import com.objogate.wl.swing.AWTEventQueueProber;
import com.objogate.wl.swing.driver.JFrameDriver;
import com.objogate.wl.swing.driver.JLabelDriver;
import com.objogate.wl.swing.gesture.GesturePerformer;
import auctionsniper.MainWindow;
import auctionsniper.Main;

public class AuctionSniperDriver extends JFrameDriver {
	public AuctionSniperDriver (int timeoutMillis) {
		super(new GesturePerformer(),
			JFrameDriver.topLevelFrame(
				named(MainWindow.MAIN_WINDOW_NAME),
				showingOnScreen()),
				new AWTEventQueueProber(timeoutMillis, 100));
	}
	
	public void showsSniperStatus(String statusText) {
		JLabelDriver jLabelDriver = new JLabelDriver(this, named(Main.SNIPER_STATUS_NAME));
		jLabelDriver.hasText(equalTo(statusText));
	}
}
