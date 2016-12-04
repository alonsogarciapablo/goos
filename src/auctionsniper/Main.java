package auctionsniper;

import javax.swing.SwingUtilities;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Message;

public class Main {

	private static final int ARG_HOSTNAME = 0;
	private static final int ARG_USERNAME = 1;
	private static final int ARG_PASSWORD = 2;
	private static final int ARG_ITEM_ID = 3;
	
	public static final String SNIPER_STATUS_NAME = "auction status";
	public static final String STATUS_JOINING = "joining";
	public static final String STATUS_LOST = "lost";

	public static final String AUCTION_RESOURCE = "Auction";
	public static final String ITEM_ID_AS_LOGIN = "auction-%s";
	public static final String AUCTION_ID_FORMAT = ITEM_ID_AS_LOGIN + "@%s/" + AUCTION_RESOURCE;
	
	private MainWindow ui;
    private Chat notToBeGCd;

    public Main() throws Exception {
		startUserInterface();
	}

	public static void main(String...args) throws Exception {
		Main main = new Main();
		main.joinAuction(connection(args[ARG_HOSTNAME], args[ARG_USERNAME], args[ARG_PASSWORD]), args[ARG_ITEM_ID]);
	}
	
	private void joinAuction(XMPPConnection connection, String itemId) throws XMPPException {
		MessageListener listener = new MessageListener() {
			public void processMessage(Chat arg0, Message arg1) {
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						MainWindow.showStatus(STATUS_LOST);
					}
				});
			}
		};

		final Chat chat = connection.getChatManager().createChat(auctionId(itemId, connection), listener);

        // If the chat is garbage-collected, the Smack runtime will hand the message to a new Chat which it
        // will create fo the purpose. In an interactive application, we would
		this.notToBeGCd = chat;
		chat.sendMessage(new Message());

	}
	
	private static String auctionId(String itemId, XMPPConnection connection) {
		return String.format(AUCTION_ID_FORMAT,  itemId, connection.getServiceName());
	}

	private static XMPPConnection connection(String hostname, String username, String password) throws XMPPException {
		XMPPConnection connection = new XMPPConnection(hostname);
		connection.connect();
		connection.login(username, password, AUCTION_RESOURCE);
		return connection;
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
