package views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Exception.ErrorControl;
import Objects.Kahoot;
import Objects.Topic;
import modelDAO.KahootDao;
import modelDAO.TopicDao;

public class KadammManagement extends JFrame {

	private static final long serialVersionUID = 9176526853703877107L;
	private JPanel contentPane;
	private static String selected;
	private static Long indexKahoot;
	private static Long userId = 0L;
	private JButton btnPlay;
	private JButton btnViewDetail;
	private JButton btnCreateKadam;
	private JButton btnFilterTopic;
	private JButton btnEditTopic;
	private JLabel lblKadamms;
	private JLabel lblTopics;
	private JLabel lblChosenTopics;
	@SuppressWarnings("rawtypes")
	private JList jListKahoot, jListChosenTopics, jListTopic;
	@SuppressWarnings("rawtypes")
	private DefaultListModel listModelKahoot, listModelChosenTopic, listModelTopic;
	private JScrollPane scrkKadamList;
	private JScrollPane scrlChosenTopic;
	private JScrollPane scrlTopics;

	/**
	 * Launch the application.
	 */
	public static void main(Long userID) {
		userId = userID;
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					KadammManagement frame = new KadammManagement(userId);
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
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public KadammManagement(Long userId) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("KADAMM MANAGEMENT");
		ImageIcon img = new ImageIcon("src" + File.separator + "main" + File.separator + "java" + File.separator
				+ "images" + File.separator + "logoKadamm.PNG");
		setIconImage(img.getImage());
		setResizable(false);
		setSize(675, 470);
		contentPane = new JPanel();
		contentPane.setBackground(Color.decode("#374151"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblKadamms = new JLabel("Kadamms");
		lblKadamms.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblKadamms.setForeground(new Color(175, 238, 238));
		lblKadamms.setBounds(15, 11, 60, 17);
		contentPane.add(lblKadamms);

		scrkKadamList = new JScrollPane();
		scrkKadamList.setBounds(15, 33, 390, 300);
		contentPane.add(scrkKadamList);

		// KAHOOT LIST
		jListKahoot = new JList();
		listModelKahoot = new DefaultListModel();
		KahootDao kahootDao = new KahootDao();
		List<Kahoot> kahootList = kahootDao.getAllKahoots();
		if (kahootList.stream().filter(kahoot -> kahoot.getUser().getUserId() == userId).findAny()
				.orElse(null) != null) {
			int contador = 0;
			indexKahoot = 0L;
			for (Kahoot element : kahootList) {
				if (contador < kahootList.size() && element.getUser().getUserId() == userId) {
					listModelKahoot.add(contador, element.getTitle());
					contador++;
				}

			}
		} else {
			new ErrorControl("Wanna create your first Kadamm!, go to Create Kadam and start your journey.", "INFO");
		}

		jListKahoot.setModel(listModelKahoot);
		scrkKadamList.setViewportView(jListKahoot);

		jListKahoot.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					selected = jListKahoot.getSelectedValue().toString();
					indexKahoot = kahootDao.getKahootByTitle(selected).getKahootId();

					// CHOSEN TOPICS LIST

//					jListChosenTopics = new JList();
//					listModelChosenTopic = new DefaultListModel();
//					List<Topic> topicChosenList = new ArrayList<>();
//					for (Kahoot element : kahootList) {
//						System.out.println("element: " + element);
//						if (element.getKahootId() == indexKahoot) {
//							TopicDao topicDao = new TopicDao();
//							for (Topic topic : topicDao.geelement.getTopics()) {
//
//								System.out.println("topic: " + topic);
//								topicChosenList.add(topic);
//							}
//							break;
//						}
//					}
//					for (int i = 0; i < topicChosenList.size(); i++) {
//						listModelChosenTopic.add(i, topicChosenList.stream().map(Topic::getTopic));
//					}
//					jListChosenTopics.setModel(listModelChosenTopic);
//					scrlChosenTopic.setViewportView(jListChosenTopics);

					btnPlay.setEnabled(true);
				}
			}
		});

		// TOPICS LIST

		lblTopics = new JLabel("Topics");
		lblTopics.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTopics.setForeground(new Color(175, 238, 238));
		lblTopics.setBounds(420, 11, 38, 17);
		contentPane.add(lblTopics);

		scrlTopics = new JScrollPane();
		scrlTopics.setBounds(420, 33, 225, 135);
		contentPane.add(scrlTopics);

		jListTopic = new JList();
		listModelTopic = new DefaultListModel();
		TopicDao topicDao = new TopicDao();
		List<Topic> topicList = topicDao.getAllTopics();
		int contadorT = 0;
		for (Topic element : topicList) {
			if (contadorT < topicList.size()) {
				listModelTopic.add(contadorT, element.getTopic());
			}
			contadorT++;
		}
		jListTopic.setModel(listModelTopic);
		scrlTopics.setViewportView(jListTopic);

		jListTopic.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {

				}
			}
		});

		lblChosenTopics = new JLabel("Chosen Topics");
		lblChosenTopics.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblChosenTopics.setForeground(new Color(175, 238, 238));
		lblChosenTopics.setBounds(420, 178, 85, 17);
		contentPane.add(lblChosenTopics);

		scrlChosenTopic = new JScrollPane();
		scrlChosenTopic.setBounds(420, 198, 225, 135);
		contentPane.add(scrlChosenTopic);

		btnPlay = new JButton("PLAY");
		btnPlay.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnPlay.setBounds(124, 382, 190, 27);
		contentPane.add(btnPlay);
		btnPlay.setEnabled(false);
		btnPlay.addActionListener(new ActionListener() {
			@Override
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {
				KadammWaitingRoom kadammWaitingRoom = new KadammWaitingRoom();
				kadammWaitingRoom.main(selected, indexKahoot);
				dispose();
			}
		});

		btnCreateKadam = new JButton("Create Kadamm");
		btnCreateKadam.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnCreateKadam.setBounds(15, 345, 190, 27);
		btnCreateKadam.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				KadammCreator kadammCreator = new KadammCreator();
				kadammCreator.main(userId);

			}
		});

		contentPane.add(btnCreateKadam);

		btnFilterTopic = new JButton("Filter by Topic");
		btnFilterTopic.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnFilterTopic.setBounds(420, 345, 225, 27);
		btnFilterTopic.setEnabled(false);
		contentPane.add(btnFilterTopic);

		btnEditTopic = new JButton("Edit Topics");
		btnEditTopic.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnEditTopic.setBounds(420, 382, 225, 27);
		btnEditTopic.setEnabled(false);
		contentPane.add(btnEditTopic);

		btnViewDetail = new JButton("View Detail");
		btnViewDetail.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnViewDetail.setBounds(217, 345, 190, 27);
		btnViewDetail.setEnabled(false);
		contentPane.add(btnViewDetail);

	}
}