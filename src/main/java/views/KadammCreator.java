package views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import Exception.ErrorControl;
import Objects.Answer;
import Objects.Kahoot;
import Objects.Question;
import Objects.Topic;
import modelDAO.AnswerDao;
import modelDAO.KahootDao;
import modelDAO.QuestionDao;
import modelDAO.QuestionTypeDao;
import modelDAO.TopicDao;
import modelDAO.UserDao;

public class KadammCreator extends JFrame {

	private static final long serialVersionUID = -7760529119031318954L;
	private JPanel contentPane;
	private JTextField txtTitle;
	private JTextField txtQuestion;
	private JTextField txtAnswer1;
	private JTextField txtAnswer2;
	private JTextField txtAnswer3;
	private JTextField txtAnswer4;
	private JLabel lblTitle, lblListQuestion, lblNewQuestion, lblTopics, lblHowtoReply, lblCorrecta, lblAnswer;
	private JScrollPane scrPaneQuestion, scrPaneTags;
	@SuppressWarnings("rawtypes")
	private JList listQuestions, listTopic;
	@SuppressWarnings("rawtypes")
	private DefaultListModel listModelTopic;
	@SuppressWarnings("rawtypes")
	private DefaultListModel listModelQuestion = new DefaultListModel();
	private JButton btnSaveKadam, btnSaveQuestion;
	private JCheckBox chkBoxAnswer1, chkBoxAnswer2, chkBoxAnswer3, chkBoxAnswer4;
	private static Long userID;
	boolean flagSave = false;
	boolean flagSave1 = false;
	boolean flagSave2 = false;
	boolean flagSave3 = false;
	private static int contadorQuestion = 0;
	private List<Answer> listAnswer = new ArrayList<>();
	private List<Question> listQuestion = new ArrayList<>();

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

		lblTitle = new JLabel("TITLE");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTitle.setForeground(new Color(175, 238, 238));
		lblTitle.setBounds(23, 20, 89, 17);
		contentPane.add(lblTitle);

		txtTitle = new JTextField();
		txtTitle.setBounds(82, 19, 340, 21);
		contentPane.add(txtTitle);
		txtTitle.setColumns(10);

		// QUESTION LIST

		lblListQuestion = new JLabel("QUESTION LIST");
		lblListQuestion.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblListQuestion.setForeground(new Color(175, 238, 238));
		lblListQuestion.setBounds(23, 52, 126, 17);
		contentPane.add(lblListQuestion);

		scrPaneQuestion = new JScrollPane();
		scrPaneQuestion.setBounds(23, 72, 390, 110);
		contentPane.add(scrPaneQuestion);

		listQuestions = new JList();
		listQuestions.setFont(new Font("Tahoma", Font.PLAIN, 12));
		scrPaneQuestion.setViewportView(listQuestions);

		// NEW QUESTION

		lblNewQuestion = new JLabel("NEW QUESTION");
		lblNewQuestion.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewQuestion.setForeground(new Color(175, 238, 238));
		lblNewQuestion.setBounds(23, 192, 106, 17);
		contentPane.add(lblNewQuestion);

		txtQuestion = new JTextField();
		txtQuestion.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtQuestion.setBounds(23, 213, 390, 42);
		contentPane.add(txtQuestion);
		txtQuestion.setColumns(10);

		// TOPIC LIST
		lblTopics = new JLabel("TAGS");
		lblTopics.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTopics.setForeground(new Color(175, 238, 238));
		lblTopics.setBounds(428, 52, 106, 17);
		contentPane.add(lblTopics);

		scrPaneTags = new JScrollPane();
		scrPaneTags.setBounds(428, 72, 225, 110);
		contentPane.add(scrPaneTags);

		listTopic = new JList();
		listModelTopic = new DefaultListModel();
		listTopic.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		listTopic.setFont(new Font("Tahoma", Font.PLAIN, 12));

		TopicDao topicDao = new TopicDao();
		List<Topic> topicList = topicDao.getAllTopics();
		int contador = 0;
		for (Topic element : topicList) {
			if (contador < topicList.size()) {
				listModelTopic.add(contador, element.getTopic());
				element.getTopicId();
			}
			contador++;
		}

		listTopic.setModel(listModelTopic);
		scrPaneTags.setViewportView(listTopic);

		// ANSWER OPTIONS

		lblHowtoReply = new JLabel("How to reply...");
		lblHowtoReply.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblHowtoReply.setForeground(new Color(175, 238, 238));
		lblHowtoReply.setBounds(252, 308, 106, 17);
		contentPane.add(lblHowtoReply);

		JRadioButton rdbtnhowReplychoise = new JRadioButton("At least 1 correct answer");
		rdbtnhowReplychoise.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (chkBoxAnswer1.isSelected()) {
					chkBoxAnswer2.setEnabled(false);
					chkBoxAnswer3.setEnabled(false);
					chkBoxAnswer4.setEnabled(false);
				} else if (chkBoxAnswer2.isSelected()) {
					chkBoxAnswer1.setEnabled(false);
					chkBoxAnswer3.setEnabled(false);
					chkBoxAnswer4.setEnabled(false);
				} else if (chkBoxAnswer3.isSelected()) {
					chkBoxAnswer1.setEnabled(false);
					chkBoxAnswer2.setEnabled(false);
					chkBoxAnswer4.setEnabled(false);
				} else if (chkBoxAnswer4.isSelected()) {
					chkBoxAnswer1.setEnabled(false);
					chkBoxAnswer2.setEnabled(false);
					chkBoxAnswer3.setEnabled(false);
				}
			}
		});
		rdbtnhowReplychoise.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdbtnhowReplychoise.setBounds(252, 328, 170, 25);
		rdbtnhowReplychoise.setSelected(true);
		contentPane.add(rdbtnhowReplychoise);

		// ANSWERS

		lblAnswer = new JLabel("ANSWERS");
		lblAnswer.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblAnswer.setForeground(new Color(175, 238, 238));
		lblAnswer.setBounds(428, 192, 85, 17);
		contentPane.add(lblAnswer);

		chkBoxAnswer1 = new JCheckBox("");
		chkBoxAnswer1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		chkBoxAnswer1.setBounds(625, 213, 21, 21);
		chkBoxAnswer1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (txtAnswer1.getText().isEmpty()) {
					chkBoxAnswer1.setSelected(false);
					new ErrorControl("Please write an answer", "WARNING");
				} else {
					chkBoxAnswer1.setEnabled(true);
				}
			}
		});
		contentPane.add(chkBoxAnswer1);

		chkBoxAnswer2 = new JCheckBox("");
		chkBoxAnswer2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		chkBoxAnswer2.setBounds(625, 234, 21, 21);
		chkBoxAnswer2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (txtAnswer2.getText().isEmpty()) {
					chkBoxAnswer2.setSelected(false);
					new ErrorControl("Please write an answer", "WARNING");
				} else {
					chkBoxAnswer2.setEnabled(true);
				}
			}
		});
		contentPane.add(chkBoxAnswer2);

		chkBoxAnswer3 = new JCheckBox("");
		chkBoxAnswer3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		chkBoxAnswer3.setBounds(625, 255, 21, 21);
		chkBoxAnswer3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (txtAnswer3.getText().isEmpty()) {
					chkBoxAnswer3.setSelected(false);
					new ErrorControl("Please write an answer", "WARNING");
				} else {
					chkBoxAnswer3.setEnabled(true);
				}
			}
		});
		contentPane.add(chkBoxAnswer3);

		chkBoxAnswer4 = new JCheckBox("");
		chkBoxAnswer4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		chkBoxAnswer4.setBounds(625, 276, 21, 21);
		chkBoxAnswer4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (txtAnswer4.getText().isEmpty()) {
					chkBoxAnswer4.setSelected(false);
					new ErrorControl("Please write an answer", "WARNING");
				} else {
					chkBoxAnswer4.setEnabled(true);
				}
			}
		});
		contentPane.add(chkBoxAnswer4);

		lblCorrecta = new JLabel("CORRECT");
		lblCorrecta.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCorrecta.setForeground(new Color(175, 238, 238));
		lblCorrecta.setBounds(608, 192, 55, 17);
		contentPane.add(lblCorrecta);

		txtAnswer1 = new JTextField();
		txtAnswer1.setBounds(428, 213, 191, 19);
		txtAnswer1.setColumns(10);
		contentPane.add(txtAnswer1);

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

		// BUTTONS

		btnSaveQuestion = new JButton("Save Question");
		btnSaveQuestion.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnSaveQuestion.setBounds(160, 370, 166, 36);
		btnSaveQuestion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				flagSave = false;
				if (txtQuestion.getText().isEmpty()) {
					new ErrorControl("Please write a question", "WARNING");
					flagSave1 = false;
				} else {
					flagSave1 = true;
				}
				if ((txtAnswer1.getText().isEmpty() && txtAnswer2.getText().isEmpty() && txtAnswer3.getText().isEmpty())
						|| (txtAnswer1.getText().isEmpty() && txtAnswer2.getText().isEmpty()
								&& txtAnswer4.getText().isEmpty())
						|| (txtAnswer1.getText().isEmpty() && txtAnswer3.getText().isEmpty()
								&& txtAnswer4.getText().isEmpty())
						|| (txtAnswer2.getText().isEmpty() && txtAnswer3.getText().isEmpty()
								&& txtAnswer4.getText().isEmpty())) {
					new ErrorControl("At least each question needs minimun 2 optional answers", "WARNING");
					flagSave2 = false;
				} else {
					flagSave2 = true;
				}
				if (!chkBoxAnswer1.isSelected() && !chkBoxAnswer2.isSelected() && !chkBoxAnswer3.isSelected()
						&& !chkBoxAnswer4.isSelected()) {
					new ErrorControl("At least one answer must be corrected.", "WARNING");
					flagSave3 = false;
				} else {
					flagSave3 = true;
				}
				if (flagSave1 && flagSave2 && flagSave3) {
					flagSave = true;
				}
				if (flagSave) {
					listModelQuestion.add(contadorQuestion, txtQuestion.getText());
					listQuestions.setModel(listModelQuestion);
					scrPaneQuestion.setViewportView(listQuestions);
					// String questionText
					listQuestion.add(new Question(txtQuestion.getText()));

					// String answerText, Question question, boolean isCorrect
					boolean corrected = true;
					if (!txtAnswer1.getText().isEmpty()) {
						if (chkBoxAnswer1.isSelected()) {
							corrected = true;
						} else {
							corrected = false;
						}
						listAnswer.add(new Answer(txtAnswer1.getText(), listQuestion.get(contadorQuestion), corrected));
					}
					if (!txtAnswer2.getText().isEmpty()) {
						if (chkBoxAnswer2.isSelected()) {
							corrected = true;
						} else {
							corrected = false;
						}
						listAnswer.add(new Answer(txtAnswer2.getText(), listQuestion.get(contadorQuestion), corrected));
					}
					if (!txtAnswer3.getText().isEmpty()) {
						if (chkBoxAnswer3.isSelected()) {
							corrected = true;
						} else {
							corrected = false;
						}
						listAnswer.add(new Answer(txtAnswer3.getText(), listQuestion.get(contadorQuestion), corrected));
					}
					if (!txtAnswer4.getText().isEmpty()) {
						if (chkBoxAnswer4.isSelected()) {
							corrected = true;
						} else {
							corrected = false;
						}
						listAnswer.add(new Answer(txtAnswer4.getText(), listQuestion.get(contadorQuestion), corrected));
					}
					contadorQuestion++;
					int reply = JOptionPane.showConfirmDialog(null, "Want to add another question?", "INFO",
							JOptionPane.YES_NO_OPTION);
					if (reply == JOptionPane.YES_OPTION) {
						clean();
					} else {
						btnSaveKadam.setEnabled(true);
						btnSaveQuestion.setEnabled(false);
					}
				}
			}
		});
		contentPane.add(btnSaveQuestion);

		btnSaveKadam = new JButton("Save New Kadamm!!");
		btnSaveKadam.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (txtTitle.getText().isEmpty()) {
					new ErrorControl("Title must be filled", "ERROR");
				} else {
					Kahoot kahoot = new Kahoot(txtTitle.getText(), "ES");
					UserDao userDao = new UserDao();
					KahootDao kahootDao = new KahootDao();
					kahoot.setUser(userDao.getUserById(userID));
					kahootDao.saveKahoot(kahoot);
					QuestionDao questionDao = new QuestionDao();
					QuestionTypeDao questionTypeDao = new QuestionTypeDao();
					AnswerDao answerDao = new AnswerDao();
					for (Question element : listQuestion) {
						element.setQuestionType(questionTypeDao.getQuestionTypeById(1L));
						element.setKahoot(kahoot);
						questionDao.saveQuestion(element);
					}
					for (Answer answerE : listAnswer) {
						answerDao.saveAnswer(answerE);
					}

				}
				dispose();
			}
		});
		btnSaveKadam.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnSaveKadam.setBounds(349, 370, 166, 36);
		btnSaveKadam.setEnabled(false);
		contentPane.add(btnSaveKadam);
	}

	protected void clean() {
		txtQuestion.setText("");
		txtAnswer1.setText("");
		txtAnswer2.setText("");
		txtAnswer3.setText("");
		txtAnswer4.setText("");
		chkBoxAnswer1.setSelected(false);
		chkBoxAnswer1.setEnabled(true);
		chkBoxAnswer2.setSelected(false);
		chkBoxAnswer2.setEnabled(true);
		chkBoxAnswer3.setSelected(false);
		chkBoxAnswer3.setEnabled(true);
		chkBoxAnswer4.setSelected(false);
		chkBoxAnswer4.setEnabled(true);

	}

}