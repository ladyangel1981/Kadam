package views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

import javax.swing.AbstractListModel;
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

import Objects.Kahoot;
import modelDAO.KahootDao;

public class KadammManagement extends JFrame {

	private static final long serialVersionUID = 9176526853703877107L;
	private JPanel contentPane;
	public static String selected;

	/**
	 * Launch the application.
	 */
	public void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					KadammManagement frame = new KadammManagement();
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
	public KadammManagement() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("KADAMM MANAGEMENT");
		ImageIcon img = new ImageIcon("src" + File.separator + "main" + File.separator + "java" + File.separator
				+ "images" + File.separator + "logoKadamm.PNG");
		setIconImage(img.getImage());
		setResizable(false);
		setSize(675, 460);
		contentPane = new JPanel();
		contentPane.setBackground(Color.decode("#374151"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblKadamms = new JLabel("Kadamms");
		lblKadamms.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblKadamms.setForeground(new Color(175, 238, 238));
		lblKadamms.setBounds(15, 11, 60, 17);
		contentPane.add(lblKadamms);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(15, 33, 390, 300);
		contentPane.add(scrollPane);

		JButton btnNewButton = new JButton("View Detail");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewButton.setBounds(217, 345, 190, 27);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("PLAY");
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewButton_1.setBounds(124, 382, 190, 27);
		contentPane.add(btnNewButton_1);
		btnNewButton_1.setEnabled(false);
		btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {
				KadammWaitingRoom kadammWaitingRoom = new KadammWaitingRoom();
				kadammWaitingRoom.main(selected);
				dispose();
			}
		});

		JList list = new JList();
		list.setModel(new AbstractListModel() {
			KahootDao kahootDao = new KahootDao();
			Kahoot kahoot = new Kahoot();
			List<Kahoot> values = kahootDao.getAllKahoots();

			@Override
			public int getSize() {
				return values.size();
			}

			@Override
			public Object getElementAt(int index) {
				return values.get(index);
			}
		});
		scrollPane.setViewportView(list);
		list.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					selected = list.getSelectedValue().toString();
					btnNewButton_1.setEnabled(true);
				}
			}
		});
		JButton btnNewButton_2 = new JButton("Create Kadamm");
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewButton_2.setBounds(15, 345, 190, 27);
		contentPane.add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton("Filter by Topic");
		btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewButton_3.setBounds(420, 345, 225, 27);
		contentPane.add(btnNewButton_3);

		JButton btnNewButton_4 = new JButton("Edit Topics");
		btnNewButton_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewButton_4.setBounds(420, 382, 225, 27);
		contentPane.add(btnNewButton_4);

		JScrollPane scrollPane_1_1_1 = new JScrollPane();
		scrollPane_1_1_1.setBounds(420, 198, 225, 135);
		contentPane.add(scrollPane_1_1_1);

		JList list_2 = new JList();
		scrollPane_1_1_1.setViewportView(list_2);

		JLabel lblChosenTopics = new JLabel("Chosen Topics");
		lblChosenTopics.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblChosenTopics.setForeground(new Color(175, 238, 238));
		lblChosenTopics.setBounds(420, 178, 85, 17);
		contentPane.add(lblChosenTopics);

		JScrollPane scrollPane_1_1 = new JScrollPane();
		scrollPane_1_1.setBounds(420, 33, 225, 135);
		contentPane.add(scrollPane_1_1);

		JList list_1 = new JList();
		scrollPane_1_1.setViewportView(list_1);

		JLabel lblTopics = new JLabel("Topics");
		lblTopics.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTopics.setForeground(new Color(175, 238, 238));
		lblTopics.setBounds(420, 11, 38, 17);
		contentPane.add(lblTopics);
	}
}