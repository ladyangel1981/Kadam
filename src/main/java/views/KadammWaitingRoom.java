package views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import ServerConfig.Server;
import XMLconfig.ReadXMLDomParser;

public class KadammWaitingRoom extends JFrame {

	private static final long serialVersionUID = 3057569290696129932L;
	private JPanel contentPane;
	private static String selectedTitle;
	private Timer timer = new Timer();
	private JLabel timerJLabel = new JLabel();
	private static ReadXMLDomParser readDomParser;
	private static Long kahootIndex;
	private static Server server = new Server();

	/**
	 * Launch the application.
	 */
	public static void main(String selected, Long indexKahoot) {
		kahootIndex = indexKahoot;
		selectedTitle = selected;
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					KadammWaitingRoom frame = new KadammWaitingRoom();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes", "serial" })
	public KadammWaitingRoom() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("WAITING ROOM");
		ImageIcon img = new ImageIcon("src" + File.separator + "main" + File.separator + "java" + File.separator
				+ "images" + File.separator + "logoKadamm.PNG");
		setIconImage(img.getImage());
		setResizable(false);
		setSize(450, 320);
		contentPane = new JPanel();
		contentPane.setBackground(Color.decode("#374151"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblKadamms = new JLabel("Chosen Kadamm: " + selectedTitle);
		lblKadamms.setHorizontalAlignment(SwingConstants.LEFT);
		lblKadamms.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblKadamms.setForeground(new Color(175, 238, 238));
		lblKadamms.setBounds(12, 5, 423, 35);
		contentPane.add(lblKadamms);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 97, 330, 120);
		contentPane.add(scrollPane);

		JButton btnStartComp = new JButton("START COMPETITION");
		btnStartComp.setBounds(137, 234, 180, 27);
		contentPane.add(btnStartComp);
		btnStartComp.addActionListener(new ActionListener() {
			@Override
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {
				btnStartComp.setEnabled(false);
				timer.scheduleAtFixedRate(new TimerTask() {
					int i = readDomParser.getConfiguration().getCountdown();

					@Override
					public void run() {

						timerJLabel.setText(String.valueOf(i));

						i--;

						if (i < 0) {
							timer.cancel();
							KadammCompetition kadammCompetition = new KadammCompetition(kahootIndex);
							kadammCompetition.main(kahootIndex);

							dispose();
						}
					}
				}, 0, 1000);
			}
		});

		JList list = new JList();
		DefaultListModel listModelPlayers = new DefaultListModel();
		server.setIsActive(true);
		if (btnStartComp.isEnabled()) {
			if (server.getUsers() != null) {
				List<String> players = server.getUsers();
				int contador = 0;
				for (String element : players) {
					if (contador < players.size()) {
						listModelPlayers.add(contador, element);
					}
					contador++;
				}

			} else {
				while (btnStartComp.isEnabled()) {
					List<String> players = server.getUsers();
					int contador = 0;
					for (String element : players) {
						if (contador < players.size()) {
							listModelPlayers.add(contador, element);
						}
						contador++;
					}
				}
			}
			list.setModel(listModelPlayers);
			scrollPane.setViewportView(list);
		}

		try {
			InetAddress IP = InetAddress.getLocalHost();
			JLabel lblKadamms_1 = new JLabel("IP: " + IP.getHostAddress());
			lblKadamms_1.setHorizontalAlignment(SwingConstants.LEFT);
			lblKadamms_1.setForeground(new Color(175, 238, 238));
			lblKadamms_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblKadamms_1.setBounds(12, 50, 136, 35);
			contentPane.add(lblKadamms_1);
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		}

		JLabel lblKadamms_1_1 = new JLabel("Waiting for players...");
		lblKadamms_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblKadamms_1_1.setForeground(new Color(175, 238, 238));
		lblKadamms_1_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblKadamms_1_1.setBounds(12, 70, 117, 27);
		contentPane.add(lblKadamms_1_1);

		timerJLabel.setFont(new Font("Tahoma", Font.PLAIN, 50));
		timerJLabel.setHorizontalAlignment(SwingConstants.CENTER);
		timerJLabel.setBounds(346, 17, 80, 80);
		timerJLabel.setForeground(new Color(175, 238, 238));
		contentPane.add(timerJLabel);

	}
}