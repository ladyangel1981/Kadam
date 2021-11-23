package views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.io.File;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Objects.Kahoot;
import Objects.Topic;
import modelDAO.KahootDao;
import modelDAO.TopicDao;

import javax.swing.JTextPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class KadammCreator extends JFrame {

	private static final long serialVersionUID = -7760529119031318954L;
	private JPanel contentPane;
	private JTextField txtTitle;
	private static long indexTopic;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					KadammCreator frame = new KadammCreator();
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
	@SuppressWarnings("rawtypes")
	public KadammCreator() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("CREATE KADAMM");
		ImageIcon img = new ImageIcon("src" + File.separator + "main" + File.separator + "java" + File.separator
				+ "images" + File.separator + "logoKadamm.PNG");
		setIconImage(img.getImage());
		setResizable(false);
		setSize(685, 455);
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.decode("#374151"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblKadamms = new JLabel("TITLE");
		lblKadamms.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblKadamms.setForeground(new Color(175, 238, 238));
		lblKadamms.setBounds(23, 20, 89, 17);
		contentPane.add(lblKadamms);
		
		txtTitle = new JTextField();
		txtTitle.setBounds(82, 19, 340, 21);
		contentPane.add(txtTitle);
		txtTitle.setColumns(10);
		
		JButton btnSaveQuestion = new JButton("Save Question");
		btnSaveQuestion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		btnSaveQuestion.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnSaveQuestion.setBounds(160, 370, 166, 36);
		contentPane.add(btnSaveQuestion);

		JButton btnSaveKadam = new JButton("Save New Kadamm!!");
		btnSaveKadam.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		btnSaveKadam.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnSaveKadam.setBounds(349, 370, 166, 36);
		contentPane.add(btnSaveKadam);

		JLabel lblAnswer = new JLabel("ANSWERS");
		lblAnswer.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblAnswer.setForeground(new Color(175, 238, 238));
		lblAnswer.setBounds(428, 192, 85, 17);
		contentPane.add(lblAnswer);

		JScrollPane scrollPane_1_1 = new JScrollPane();
		scrollPane_1_1.setBounds(428, 72, 225, 110);
		contentPane.add(scrollPane_1_1);

		JList listTopic = new JList();
		listTopic.setFont(new Font("Tahoma", Font.PLAIN, 12));
		DefaultListModel listModelTopic = new DefaultListModel();
		TopicDao topicDao = new TopicDao();
		List<Topic> topicList = topicDao.getAllTopics();
		int contador = 0;
		indexTopic = 0;
		for (Topic element : topicList) {
			if (contador < topicList.size()) {
				listModelTopic.add(contador, element.getTopic());
				indexTopic = element.getTopicId();
			}
			contador++;
		}
		listTopic.setModel(listModelTopic);
		scrollPane_1_1.setViewportView(listTopic);

		JLabel lblTopics = new JLabel("TAGS");
		lblTopics.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTopics.setForeground(new Color(175, 238, 238));
		lblTopics.setBounds(428, 52, 106, 17);
		contentPane.add(lblTopics);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(23, 72, 390, 110);
		contentPane.add(scrollPane_1);

		JList listQuestions = new JList();
		listQuestions.setFont(new Font("Tahoma", Font.PLAIN, 12));
		scrollPane_1.setViewportView(listQuestions);

		JLabel lblListQuestion = new JLabel("ANSWER LIST");
		lblListQuestion.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblListQuestion.setForeground(new Color(175, 238, 238));
		lblListQuestion.setBounds(23, 52, 126, 17);
		contentPane.add(lblListQuestion);

		JLabel lblNewQuestion = new JLabel("NEW QUESTION");
		lblNewQuestion.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewQuestion.setForeground(new Color(175, 238, 238));
		lblNewQuestion.setBounds(23, 192, 106, 17);
		contentPane.add(lblNewQuestion);

		JRadioButton rdbtnNewRadioButton = new JRadioButton("Select 1 answer");
		rdbtnNewRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdbtnNewRadioButton.setBounds(252, 328, 170, 25);
		rdbtnNewRadioButton.setSelected(true);
		contentPane.add(rdbtnNewRadioButton);

		JLabel lblKadamms_1_1_1 = new JLabel("How to reply...");
		lblKadamms_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblKadamms_1_1_1.setForeground(new Color(175, 238, 238));
		lblKadamms_1_1_1.setBounds(252, 308, 106, 17);
		contentPane.add(lblKadamms_1_1_1);

		JLabel lblCorrecta = new JLabel("CORRECT");
		lblCorrecta.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCorrecta.setForeground(new Color(175, 238, 238));
		lblCorrecta.setBounds(608, 192, 55, 17);
		contentPane.add(lblCorrecta);

		JCheckBox checkBox = new JCheckBox("");
		checkBox.setFont(new Font("Tahoma", Font.PLAIN, 12));
		checkBox.setBounds(625, 213, 21, 21);
		contentPane.add(checkBox);

		JCheckBox checkBox_1 = new JCheckBox("");
		checkBox_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		checkBox_1.setBounds(625, 234, 21, 21);
		contentPane.add(checkBox_1);

		JCheckBox checkBox_1_1 = new JCheckBox("");
		checkBox_1_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		checkBox_1_1.setBounds(625, 255, 21, 21);
		contentPane.add(checkBox_1_1);

		JCheckBox checkBox_1_1_1 = new JCheckBox("");
		checkBox_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		checkBox_1_1_1.setBounds(625, 276, 21, 21);
		contentPane.add(checkBox_1_1_1);

		

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(428, 213, 180, 83);
		contentPane.add(scrollPane_2);

		JList listAnswer = new JList();
		listAnswer.setFont(new Font("Tahoma", Font.PLAIN, 12));
		scrollPane_2.setViewportView(listAnswer);
		
		JTextPane txtNewQuestion = new JTextPane();
		txtNewQuestion.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtNewQuestion.setBackground(Color.WHITE);
		txtNewQuestion.setBounds(407, 223, -382, 75);
		contentPane.add(txtNewQuestion);
		
		
	}
}