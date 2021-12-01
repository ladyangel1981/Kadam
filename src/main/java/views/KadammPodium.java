package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.AbstractListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class KadammPodium extends JFrame {

	private static final long serialVersionUID = -5768139670483928821L;

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					KadammPodium frame = new KadammPodium();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public KadammPodium() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("PODIUM");
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
		flowLayout.setVgap(10);
		panelPrincipal.add(panelSuperior, BorderLayout.NORTH);
		panelSuperior.setBackground(Color.decode("#374151"));

		JLabel lblNewLabel = new JLabel("FINAL RESULTS");
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		try {
			Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("src" + File.separator + "main"
					+ File.separator + "java" + File.separator + "Utilities" + File.separator + "chiller.ttf"))
					.deriveFont(Font.BOLD, 70);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(customFont);
			lblNewLabel.setFont(customFont);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (FontFormatException e) {
			e.printStackTrace();
		}
		lblNewLabel.setForeground(Color.CYAN);
		panelSuperior.add(lblNewLabel);

		JPanel panelInferior = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panelInferior.getLayout();
		flowLayout_1.setVgap(15);
		panelPrincipal.add(panelInferior, BorderLayout.SOUTH);
		panelInferior.setBackground(Color.decode("#374151"));

		JButton btnNewButton = new JButton("FINNISH KADAMM");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				dispose();

			}
		});

		panelInferior.add(btnNewButton);

		JPanel panelCentral = new JPanel();
		panelPrincipal.add(panelCentral, BorderLayout.CENTER);
		panelCentral.setBackground(Color.decode("#374151"));
		panelCentral.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setIcon(new ImageIcon("src" + File.separator + "main" + File.separator + "java" + File.separator
				+ "images" + File.separator + "podium.png"));
		panelCentral.add(lblNewLabel_1);

		JPanel panel = new JPanel();
		panelCentral.add(panel, BorderLayout.NORTH);
		panel.setBackground(Color.decode("#374151"));
		panel.setLayout(new GridLayout(1, 0, 0, 0));

		JLabel lblWinner1 = new JLabel("Winner2");
		lblWinner1.setVerticalAlignment(SwingConstants.BOTTOM);
		lblWinner1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblWinner1.setFont(new Font("arial black", Font.PLAIN, 20));
		lblWinner1.setForeground(new Color(175, 238, 238));
		lblWinner1.setBounds(23, 20, 49, 17);
		panel.add(lblWinner1);

		JLabel lblWinner2 = new JLabel("Winner1");
		lblWinner2.setVerticalAlignment(SwingConstants.TOP);
		lblWinner2.setHorizontalAlignment(SwingConstants.CENTER);
		lblWinner2.setFont(new Font("arial black", Font.PLAIN, 24));
		lblWinner2.setForeground(new Color(175, 238, 238));
		lblWinner2.setBounds(23, 20, 9, 17);
		panel.add(lblWinner2);

		JLabel lblWinner3 = new JLabel("Winner3");
		lblWinner3.setVerticalAlignment(SwingConstants.BOTTOM);
		lblWinner3.setHorizontalAlignment(SwingConstants.LEFT);
		lblWinner3.setFont(new Font("arial black", Font.PLAIN, 18));
		lblWinner3.setForeground(new Color(175, 238, 238));
		lblWinner3.setBounds(23, 20, 49, 17);
		panel.add(lblWinner3);

		JList list = new JList();
		list.setModel(new AbstractListModel() {
			String[] values = new String[] { "Winner4 Winner5 Winner6 Winner7 Winner8 Winner9 Winner10" };

			@Override
			public int getSize() {
				return values.length;
			}

			@Override
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		list.setToolTipText("");
		list.setFont(new Font("arial black", Font.PLAIN, 15));
		list.setForeground(new Color(175, 238, 238));
		list.setBackground(Color.decode("#374151"));
		panelCentral.add(list, BorderLayout.SOUTH);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setIcon(new ImageIcon("src" + File.separator + "main" + File.separator + "java" + File.separator
				+ "images" + File.separator + "lConfeti.png"));
		panelCentral.add(lblNewLabel_2, BorderLayout.WEST);

		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_3.setIcon(new ImageIcon("src" + File.separator + "main" + File.separator + "java" + File.separator
				+ "images" + File.separator + "rConfeti.png"));
		panelCentral.add(lblNewLabel_3, BorderLayout.EAST);

	}

}
