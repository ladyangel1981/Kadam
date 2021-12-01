package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Exception.ErrorControl;
import Objects.Answer;
import Objects.Question;
import XMLconfig.ReadXMLDomParser;
import modelDAO.AnswerDao;
import modelDAO.QuestionDao;

public class KadammCompetition extends JFrame {

	private static final long serialVersionUID = -5768139670483928821L;

	private JPanel contentPane;
	private JTextField answer1;
	private JTextField answer2;
	private JTextField answer3;
	private JTextField answer4;
	private Timer timer = new Timer();
	private JLabel questionLabel;
	private JLabel timerJLabel = new JLabel();
	private static QuestionDao questionDao = new QuestionDao();
	private static List<Question> questionList = new ArrayList<>();
	private static List<Answer> answerList = new ArrayList<>();
	private static AnswerDao answerDao = new AnswerDao();
	private String question;
	private static int cont = 0;
	private static int contador = 0;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;

	/**
	 * Launch the application.
	 */
	public static void main(Long indexKahoot) {

		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {

					KadammCompetition frame = new KadammCompetition(indexKahoot);
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public KadammCompetition(Long indexKahoot) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("COMPETITION");
		ImageIcon img = new ImageIcon("src" + File.separator + "main" + File.separator + "java" + File.separator
				+ "images" + File.separator + "logoKadamm.PNG");
		setIconImage(img.getImage());
		setSize(769, 499);
		contentPane = new JPanel();
		contentPane.setBackground(Color.decode("#374151"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panelPrincipal = new JPanel();
		contentPane.add(panelPrincipal, BorderLayout.CENTER);
		panelPrincipal.setLayout(new BorderLayout(0, 0));

		JPanel panelSuperior = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelSuperior.getLayout();
		flowLayout.setVgap(20);
		flowLayout.setHgap(20);
		panelPrincipal.add(panelSuperior, BorderLayout.NORTH);
		panelSuperior.setBackground(Color.decode("#374151"));

		Long kahootIndex = indexKahoot;
		questionList = questionDao.getAllQuestionsByKahootID(kahootIndex);
		question = questionList.get(cont).getQuestionText();

		questionLabel = new JLabel(question); // Entre parentesi va questionText
		questionLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		questionLabel.setForeground(new Color(175, 238, 238));
		panelSuperior.add(questionLabel);

		JPanel panelInferior = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panelInferior.getLayout();
		flowLayout_1.setVgap(10);
		flowLayout_1.setHgap(10);
		panelPrincipal.add(panelInferior, BorderLayout.SOUTH);
		panelInferior.setBackground(Color.decode("#374151"));

		timerJLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		timerJLabel.setForeground(new Color(175, 238, 238));
		panelInferior.add(timerJLabel);

		lblNewLabel_2 = new JLabel("1\u00BA: " + "first" + "   ");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_2.setForeground(new Color(175, 238, 238));
		lblNewLabel_2.setVisible(false);
		panelInferior.add(lblNewLabel_2);

		lblNewLabel_1 = new JLabel("2\u00BA: " + "second" + "   ");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setForeground(new Color(175, 238, 238));
		lblNewLabel_1.setVisible(false);
		panelInferior.add(lblNewLabel_1);

		lblNewLabel = new JLabel("3\u00BA: " + "third" + "   ");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setForeground(new Color(175, 238, 238));
		lblNewLabel.setVisible(false);
		panelInferior.add(lblNewLabel);

		JButton btnNewButton = new JButton("Next Question");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.setVisible(false);
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cont++;
				if (cont == questionList.size()) {
					new ErrorControl("Questions are finished!!", "Warning");
					KadammPodium kadammPodium = new KadammPodium();
					kadammPodium.main(null);
					dispose();
				} else {
					answer1.setVisible(true);
					answer2.setVisible(true);
					answer3.setVisible(true);
					answer4.setVisible(true);
					timerJLabel.setVisible(true);
					btnNewButton.setVisible(false);
					lblNewLabel_2.setVisible(false);
					lblNewLabel_1.setVisible(false);
					lblNewLabel.setVisible(false);
					question = questionList.get(cont).getQuestionText();
					questionLabel.setText(question);
					answerList = answerDao.getAllAnswersByQuestionId(questionList.get(cont).getQuestionId());
					answer1.setText(answerList.get(0).getAnswerText());
					answer2.setText(answerList.get(1).getAnswerText());
					answer3.setText(answerList.get(2).getAnswerText());
					answer4.setText(answerList.get(3).getAnswerText());
					Timer timer = new Timer();
					timer.scheduleAtFixedRate(new TimerTask() {
						int i = ReadXMLDomParser.getConfiguration().getTimeout();

						@Override
						public void run() {

							timerJLabel.setText(String.valueOf(i));

							i--;

							if (i < 0) {
								timer.cancel();
								if (!answerList.get(0).isCorrect()) {
									answer1.setVisible(false);
								}
								if (!answerList.get(1).isCorrect()) {
									answer2.setVisible(false);
								}
								if (!answerList.get(2).isCorrect()) {
									answer3.setVisible(false);
								}
								if (!answerList.get(3).isCorrect()) {
									answer4.setVisible(false);
								}
								timerJLabel.setVisible(false);
								lblNewLabel_2.setVisible(true);
								lblNewLabel_1.setVisible(true);
								lblNewLabel.setVisible(true);
								btnNewButton.setVisible(true);
							}
						}
					}, 0, 1000);
				}
			}
		});
		panelInferior.add(btnNewButton);

		timer.scheduleAtFixedRate(new TimerTask() {
			int i = ReadXMLDomParser.getConfiguration().getTimeout();

			@Override
			public void run() {

				timerJLabel.setText(String.valueOf(i));

				i--;

				if (i < 0) {
					timer.cancel();
					if (!answerList.get(0).isCorrect()) {
						answer1.setVisible(false);
					}
					if (!answerList.get(1).isCorrect()) {
						answer2.setVisible(false);
					}
					if (!answerList.get(2).isCorrect()) {
						answer3.setVisible(false);
					}
					if (!answerList.get(3).isCorrect()) {
						answer4.setVisible(false);
					}
					timerJLabel.setVisible(false);
					lblNewLabel_2.setVisible(true);
					lblNewLabel_1.setVisible(true);
					lblNewLabel.setVisible(true);
					btnNewButton.setVisible(true);
				}
			}
		}, 0, 1000);

		JPanel panelCentral = new JPanel();
		panelPrincipal.add(panelCentral, BorderLayout.CENTER);
		panelCentral.setBackground(Color.decode("#374151"));
		panelCentral.setLayout(new GridLayout(2, 2, 5, 5));

		answer1 = new JTextField();
		answer1.setFont(new Font("Tahoma", Font.PLAIN, 22));
		answer1.setBackground(Color.RED);
		panelCentral.add(answer1);
		answer1.setColumns(10);

		answer2 = new JTextField();
		answer2.setFont(new Font("Tahoma", Font.PLAIN, 22));
		answer2.setBackground(Color.GREEN);
		panelCentral.add(answer2);
		answer2.setColumns(10);

		answer3 = new JTextField();
		answer3.setFont(new Font("Tahoma", Font.PLAIN, 22));
		answer3.setBackground(Color.BLUE);
		panelCentral.add(answer3);
		answer3.setColumns(10);

		answer4 = new JTextField();
		answer4.setFont(new Font("Tahoma", Font.PLAIN, 22));
		answer4.setBackground(Color.YELLOW);
		panelCentral.add(answer4);
		answer4.setColumns(10);

		answerList = answerDao.getAllAnswersByQuestionId(questionList.get(cont).getQuestionId());
		answer1.setText(answerList.get(0).getAnswerText());
		answer2.setText(answerList.get(1).getAnswerText());
		answer3.setText(answerList.get(2).getAnswerText());
		answer4.setText(answerList.get(3).getAnswerText());
	}

}
