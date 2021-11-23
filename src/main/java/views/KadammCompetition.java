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

import Objects.Question;
import XMLconfig.ReadXMLDomParser;
import modelDAO.QuestionDao;

public class KadammCompetition extends JFrame {

	private static final long serialVersionUID = -5768139670483928821L;

	private JPanel contentPane;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;

	private JLabel lblNewLabel;
	private Timer timer = new Timer();
	private JLabel timerJLabel = new JLabel();
	private static QuestionDao questionDao;
	private static List<Question> questionList = new ArrayList<>();
	private String question;
	private static int cont = 0;

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
		setSize(675, 460);
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
		System.out.println("competition" + kahootIndex);
		System.out.println();
		questionList = questionDao.getAllQuestionsByKahootID(kahootIndex);

		lblNewLabel = new JLabel(question); // Entre parentesi va questionText
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblNewLabel.setForeground(new Color(175, 238, 238));
		panelSuperior.add(lblNewLabel);

		JPanel panelInferior = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panelInferior.getLayout();
		flowLayout_1.setVgap(10);
		flowLayout_1.setHgap(10);
		panelPrincipal.add(panelInferior, BorderLayout.SOUTH);
		panelInferior.setBackground(Color.decode("#374151"));

		JButton btnNewButton = new JButton("Next Question");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.setEnabled(false);
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				question = questionList.get(cont).getQuestionText();
				cont++;
			}
		});

		timer.scheduleAtFixedRate(new TimerTask() {
			int i = ReadXMLDomParser.getConfiguration().getTimeout();

			@Override
			public void run() {

				timerJLabel.setText(String.valueOf(i));

				i--;

				if (i < 0) {
					timer.cancel();
					btnNewButton.setEnabled(true);
				}
			}
		}, 0, 1000);

		timerJLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		timerJLabel.setForeground(new Color(175, 238, 238));
		panelInferior.add(timerJLabel);
		panelInferior.add(btnNewButton);

		JPanel panelCentral = new JPanel();
		panelPrincipal.add(panelCentral, BorderLayout.CENTER);
		panelCentral.setBackground(Color.decode("#374151"));
		panelCentral.setLayout(new GridLayout(2, 2, 5, 5));

		textField_2 = new JTextField();
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 22));
		textField_2.setBackground(Color.RED);
		panelCentral.add(textField_2);
		textField_2.setColumns(10);

		textField_4 = new JTextField();
		textField_4.setFont(new Font("Tahoma", Font.PLAIN, 22));
		textField_4.setBackground(Color.BLUE);
		panelCentral.add(textField_4);
		textField_4.setColumns(10);

		textField_5 = new JTextField();
		textField_5.setFont(new Font("Tahoma", Font.PLAIN, 22));
		textField_5.setBackground(Color.YELLOW);
		panelCentral.add(textField_5);
		textField_5.setColumns(10);

		textField_3 = new JTextField();
		textField_3.setFont(new Font("Tahoma", Font.PLAIN, 22));
		textField_3.setBackground(Color.GREEN);
		panelCentral.add(textField_3);
		textField_3.setColumns(10);
	}

}
