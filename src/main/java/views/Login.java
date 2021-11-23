package views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Exception.ErrorControl;
import Objects.User;
import Utilities.UserPassObject;
import XMLconfig.ReadXMLDomParser;
import modelDAO.UserDao;

@SuppressWarnings("serial")
public class Login extends JFrame {
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private static String pathUserPass = "src" + File.separator + "main" + File.separator + "java" + File.separator
			+ "Utilities" + File.separator + "user_pass";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					ReadXMLDomParser readXMLDomParser = new ReadXMLDomParser();
					readXMLDomParser.readXML();
					Login frame = new Login();
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
	 * 
	 * @throws IOException
	 */
	public Login() throws IOException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("KADAMM");
		ImageIcon img = new ImageIcon("src" + File.separator + "main" + File.separator + "java" + File.separator
				+ "images" + File.separator + "logoKadamm.PNG");
		setIconImage(img.getImage());
		setResizable(false);
		setSize(450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.decode("#374151"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon("src" + File.separator + "main" + File.separator + "java" + File.separator
				+ "images" + File.separator + "movilLoginDegr.png"));
		label_1.setBounds(240, -35, 235, 350);
		contentPane.add(label_1);

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("src" + File.separator + "main" + File.separator + "java" + File.separator
				+ "images" + File.separator + "trofeoLoginDegr.png"));
		label.setBounds(-25, -35, 235, 350);
		contentPane.add(label);

		JLabel lblKadamm = new JLabel("KADAMM");
		lblKadamm.setHorizontalAlignment(SwingConstants.CENTER);
		lblKadamm.setForeground(Color.CYAN);
		try {
			Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("src" + File.separator + "main"
					+ File.separator + "java" + File.separator + "Utilities" + File.separator + "chiller.ttf"))
					.deriveFont(Font.BOLD, 70);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(customFont);
			lblKadamm.setFont(customFont);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (FontFormatException e) {
			e.printStackTrace();
		}
		lblKadamm.setBounds(0, 21, 452, 61);
		contentPane.add(lblKadamm);

		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(216, 88, 100, 20);
		contentPane.add(textField);

		JLabel lblUser = new JLabel("User:");
		lblUser.setForeground(new Color(175, 238, 238));
		lblUser.setBounds(128, 92, 86, 17);
		contentPane.add(lblUser);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setForeground(new Color(175, 238, 238));
		lblPassword.setBounds(128, 127, 86, 17);
		contentPane.add(lblPassword);

		passwordField = new JPasswordField();
		passwordField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				try {
					List<UserPassObject> listUser = fileReader();
					for (UserPassObject x : listUser) {
						if (x.getUsername().equals(textField.getText())) {
							passwordField.setText(x.getPassword());
						}
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		passwordField.setBounds(216, 123, 100, 20);
		System.out.println(passwordField.getAction());
		contentPane.add(passwordField);

		JCheckBox chckbxRememberMyPassword = new JCheckBox("Remember my password");
		chckbxRememberMyPassword.setForeground(new Color(175, 238, 238));
		chckbxRememberMyPassword.setBounds(125, 157, 180, 25);
		chckbxRememberMyPassword.setBackground(Color.decode("#374151"));

		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(170, 192, 110, 35);
		contentPane.add(btnLogin);
		btnLogin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				UserDao userDao = new UserDao();
				String name = textField.getText();
				List<User> userList = userDao.getAllUsers();
				User user = new User();
				try {
					user = userList.stream().filter(x -> x.getUsername().equals(name)).findAny()
							.orElseThrow(() -> new ErrorControl("User doesn't exist", "Error"));
					if (user != null) {
						if (user.getPassword().equals(String.valueOf(passwordField.getPassword()))) {
							if (chckbxRememberMyPassword.isSelected()) {
								try {

									File filesavePass = new File(pathUserPass);
									FileWriter fw = new FileWriter(filesavePass, true);
									fw.write(textField.getText() + "," + String.valueOf(passwordField.getPassword())
											+ "\n");
									fw.close();
								} catch (IOException e1) {
									e1.printStackTrace();
								}
							}
							KadammManagement kadammManagemente = new KadammManagement();
							kadammManagemente.main(null);
							dispose();
						} else {
							new ErrorControl("Password incorrect", "Warning");
							passwordField.setText(null);
						}
					}

				} catch (ErrorControl e1) {
					e1.printStackTrace();
				}
			}
		});

		contentPane.add(chckbxRememberMyPassword);
	}

	@SuppressWarnings({ "resource" })
	private static List<UserPassObject> fileReader() throws FileNotFoundException, IOException {
		FileReader fileReader = new FileReader(new File(pathUserPass));
		List<UserPassObject> lineaList = new ArrayList<>();
		BufferedReader br = new BufferedReader(fileReader);
		String linea = br.readLine();
		while (linea != null) {
			List<String> lista = Arrays.stream(linea.split(",")).collect(Collectors.toList());
			lineaList.add(new UserPassObject(lista.get(0), lista.get(1)));
			linea = br.readLine();
		}
		return lineaList;
	}
}
