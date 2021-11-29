package views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
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
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Exception.ErrorControl;
import Objects.Kahoot;
import Objects.Topic;
import modelDAO.KahootDao;
import modelDAO.TopicDao;

public class KadammCreator extends JFrame {

	private static final long serialVersionUID = -7760529119031318954L;
	private JPanel contentPane;
	private JTextField txtTitle;
	private static long indexTopic;
	private static List<String> selectedTopics = new ArrayList<>();
	private JTextField txtQuestion;
	private JTextField txtAnswer1;
	private JTextField txtAnswer2;
	private JTextField txtAnswer3;
	private JTextField txtAnswer4;
	private static Long userID;

	/**
	 * Launch the application.
	 */
	public static void main(Long userId) {
		userID = userId;
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
	@SuppressWarnings({ "rawtypes", "unchecked" })
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

		JLabel lblTitle = new JLabel("TITLE");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTitle.setForeground(new Color(175, 238, 238));
		lblTitle.setBounds(23, 20, 89, 17);
		contentPane.add(lblTitle);

		txtTitle = new JTextField();
		txtTitle.setBounds(82, 19, 340, 21);
		contentPane.add(txtTitle);
		txtTitle.setColumns(10);

		JScrollPane scrPaneQuestion = new JScrollPane();
		scrPaneQuestion.setBounds(23, 72, 390, 110);
		contentPane.add(scrPaneQuestion);

		JList listQuestions = new JList();
		listQuestions.setFont(new Font("Tahoma", Font.PLAIN, 12));
		scrPaneQuestion.setViewportView(listQuestions);

		JLabel lblListQuestion = new JLabel("QUESTION LIST");
		lblListQuestion.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblListQuestion.setForeground(new Color(175, 238, 238));
		lblListQuestion.setBounds(23, 52, 126, 17);
		contentPane.add(lblListQuestion);

		JLabel lblNewQuestion = new JLabel("NEW QUESTION");
		lblNewQuestion.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewQuestion.setForeground(new Color(175, 238, 238));
		lblNewQuestion.setBounds(23, 192, 106, 17);
		contentPane.add(lblNewQuestion);

		txtQuestion = new JTextField();
		txtQuestion.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtQuestion.setBounds(23, 213, 390, 42);
		contentPane.add(txtQuestion);
		txtQuestion.setColumns(10);

		JScrollPane scrPaneTags = new JScrollPane();
		scrPaneTags.setBounds(428, 72, 225, 110);
		contentPane.add(scrPaneTags);

		JList listTopic = new JList();
		DefaultListModel listModelTopic = new DefaultListModel();
		listTopic.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		listTopic.setFont(new Font("Tahoma", Font.PLAIN, 12));

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

		listTopic.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				selectedTopics.add(listTopic.getSelectedValuesList().toString());
			}
		});
		listTopic.setModel(listModelTopic);
		scrPaneTags.setViewportView(listTopic);

		System.out.println(selectedTopics);

		JLabel lblTopics = new JLabel("TAGS");
		lblTopics.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTopics.setForeground(new Color(175, 238, 238));
		lblTopics.setBounds(428, 52, 106, 17);
		contentPane.add(lblTopics);

		JLabel lblHowtoReply = new JLabel("How to reply...");
		lblHowtoReply.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblHowtoReply.setForeground(new Color(175, 238, 238));
		lblHowtoReply.setBounds(252, 308, 106, 17);
		contentPane.add(lblHowtoReply);

		JRadioButton rdbtnhowReplychoise = new JRadioButton("At least 1 correct answer");
		rdbtnhowReplychoise.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdbtnhowReplychoise.setBounds(252, 328, 170, 25);
		rdbtnhowReplychoise.setSelected(true);
		contentPane.add(rdbtnhowReplychoise);

		JLabel lblCorrecta = new JLabel("CORRECT");
		lblCorrecta.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCorrecta.setForeground(new Color(175, 238, 238));
		lblCorrecta.setBounds(608, 192, 55, 17);
		contentPane.add(lblCorrecta);

		JLabel lblAnswer = new JLabel("ANSWERS");
		lblAnswer.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblAnswer.setForeground(new Color(175, 238, 238));
		lblAnswer.setBounds(428, 192, 85, 17);
		contentPane.add(lblAnswer);

		JCheckBox chkBoxAnswer1 = new JCheckBox("");
		chkBoxAnswer1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (txtAnswer1.getText().isEmpty()) {
					new ErrorControl("Please write an answer", "WARNING");
				}
			}
		});
		chkBoxAnswer1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		chkBoxAnswer1.setBounds(625, 213, 21, 21);
		contentPane.add(chkBoxAnswer1);

		JCheckBox chkBoxAnswer2 = new JCheckBox("");
		chkBoxAnswer2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		chkBoxAnswer2.setBounds(625, 234, 21, 21);
		chkBoxAnswer2.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (txtAnswer2.getText().isEmpty()) {
					new ErrorControl("Please write an answer", "WARNING");
				}
			}
		});
		contentPane.add(chkBoxAnswer2);

		JCheckBox chkBoxAnswer3 = new JCheckBox("");
		chkBoxAnswer3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		chkBoxAnswer3.setBounds(625, 255, 21, 21);
		chkBoxAnswer3.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (txtAnswer3.getText().isEmpty()) {
					new ErrorControl("Please write an answer", "WARNING");
				}
			}
		});
		contentPane.add(chkBoxAnswer3);

		JCheckBox chkBoxAnswer4 = new JCheckBox("");

		chkBoxAnswer4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		chkBoxAnswer4.setBounds(625, 276, 21, 21);
		chkBoxAnswer4.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (txtAnswer4.getText().isEmpty()) {
					new ErrorControl("Please write an answer", "WARNING");
				}
			}
		});
		contentPane.add(chkBoxAnswer4);

		JButton btnSaveQuestion = new JButton("Save Question");
		btnSaveQuestion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				while (txtQuestion.getText().isEmpty() && !chkBoxAnswer1.isSelected()
						|| !chkBoxAnswer2.isSelected() || !chkBoxAnswer3.isSelected() || !chkBoxAnswer4.isSelected()
								&& txtAnswer1.getText().isEmpty() && txtAnswer2.getText().isEmpty()
						|| txtAnswer3.getText().isEmpty()) {
					if (txtQuestion.getText().isEmpty()) {
						new ErrorControl("Please write a question", "WARNING");
					}
					if (!chkBoxAnswer1.isSelected() || !chkBoxAnswer2.isSelected() || !chkBoxAnswer3.isSelected()
							|| !chkBoxAnswer4.isSelected()) {
						new ErrorControl("At least one answer must be corrected.", "WARNING");
					}
					if (txtAnswer1.getText().isEmpty() && txtAnswer2.getText().isEmpty()
							|| txtAnswer3.getText().isEmpty()) {
						new ErrorControl("At least each question needs minimun 2 optional answers", "WARNING");
					}
					break;
				}

			}
		});
		btnSaveQuestion.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnSaveQuestion.setBounds(160, 370, 166, 36);
		contentPane.add(btnSaveQuestion);

		JButton btnSaveKadam = new JButton("Save New Kadamm!!");
		btnSaveKadam.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (txtTitle.getText().isEmpty()) {
					new ErrorControl("Title must be filled", "ERROR");
				} else {
					Kahoot kahoot = new Kahoot(txtTitle.getText(), "ES");
					KahootDao kahootDao = new KahootDao();
					kahootDao.saveKahoot(kahoot);
				}
			}
		});
		btnSaveKadam.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnSaveKadam.setBounds(349, 370, 166, 36);
		contentPane.add(btnSaveKadam);

		txtAnswer1 = new JTextField();
		txtAnswer1.setBounds(428, 213, 191, 19);
		contentPane.add(txtAnswer1);
		txtAnswer1.setColumns(10);

		txtAnswer2 = new JTextField();
		txtAnswer2.setColumns(10);
		txtAnswer2.setBounds(428, 234, 191, 19);
		contentPane.add(txtAnswer2);

		txtAnswer3 = new JTextField();
		txtAnswer3.setColumns(10);
		txtAnswer3.setBounds(428, 255, 191, 19);
		contentPane.add(txtAnswer3);

		txtAnswer4 = new JTextField();
		txtAnswer4.setColumns(10);
		txtAnswer4.setBounds(428, 276, 191, 19);
		contentPane.add(txtAnswer4);

	}
}