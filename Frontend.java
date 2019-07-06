package logindetails;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Frontend implements ActionListener {
	static int count = 0, keycount = 0;
	JFrame jf, jfnewpassword, jfupdatenewpassword;
	JPanel jp, jpnewpassword, jpupdatenewpassword;
	JButton jblogin, jbforgot, setnewpassword, setupdatenewpassword;
	JTextField jtfusername;
	JPasswordField jtfpassword, jtfnewpassworda, jtfnewpasswordb, jtfupdatenewpassworda, jtfupdatenewpasswordb;
	JLabel j1, j2, j3, j4;
	JCheckBox showpassword, shownewpassword, showupdatenewpassword;
	private char[] passwordchr, newpasswordchr, tempnewpassword, confirmpassword;
	private boolean b1, b2, b3, b4;
	private CheckPassword obj2, obj3;
	private Map<String, Date> mapdaysdiff = new HashMap<String, Date>();
	private Date date, lastchange;
	private long days;

	Frontend() {
		DateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
		date = new Date();
		// just for this program the date of password last changed is set approx. 14
		// days before date from 14/11/2018
		try {
			lastchange = dateformat.parse("29/10/2018");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		mapdaysdiff.put("Programmer", lastchange);
	}

	void display() {
		jf = new JFrame();
		jf.setTitle("Login");
		jf.setBounds(100, 100, 500, 250);
		jf.getContentPane().setLayout(null);
		jp = new JPanel();
		jp.setBounds(0, 0, 500, 250);
		jp.setLayout(null);
		jblogin = new JButton("Login");
		jblogin.setBounds(185, 150, 130, 30);
		jbforgot = new JButton("Forgot Password");
		jbforgot.setBounds(325, 150, 140, 30);
		jtfusername = new JTextField(15);
		jtfusername.setBounds(185, 10, 165, 30);
		jtfusername.setText("Programmer");
		// just for this application have set the editable properties to false
		jtfusername.setEditable(false);
		jtfpassword = new JPasswordField(15);
		jtfpassword.setBounds(185, 60, 165, 30);
		jtfpassword.setEchoChar('*');
		j1 = new JLabel("Please enter your username:");
		j2 = new JLabel("Please enter the password:");
		j1.setBounds(10, 10, 165, 30);
		j2.setBounds(10, 60, 165, 30);
		showpassword = new JCheckBox("Show Password");
		showpassword.setBounds(185, 100, 150, 30);
		jp.add(j1);
		jp.add(jtfusername);
		jp.add(j2);
		jp.add(jtfpassword);
		jp.add(showpassword);
		jp.add(jblogin);
		jp.add(jbforgot);
		jf.getContentPane().add(jp);
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		passwordchr = jtfpassword.getPassword();
		jblogin.addActionListener(this);
		jbforgot.addActionListener(this);
		showpassword.addActionListener(this);
		jtfpassword.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				/*
				 * finding the number of days between today and the date of password last
				 * changed, subtracting the dates and then dividing it by number of milliseconds
				 * per day 24hr*60min*60sec*1000millisecs
				 */
				Date recent = mapdaysdiff.get("Programmer");
				days = Math.abs(((date.getTime() - recent.getTime()) / 86400000));
				if (days > 14) {
					JOptionPane.showMessageDialog(jf,
							"A lapse of 14 days has occured since the password was last changed.\nSo Please Change your Password.",
							"Old Password Warning", JOptionPane.WARNING_MESSAGE);
					displayupdatepassword();
				}
			}
		});
	}

	void displayupdatepassword() {
		jf.setVisible(false);
		jfupdatenewpassword = new JFrame();
		jfupdatenewpassword.setTitle("Password Update");
		jfupdatenewpassword.setBounds(100, 100, 500, 250);
		jfupdatenewpassword.getContentPane().setLayout(null);
		jpupdatenewpassword = new JPanel();
		jpupdatenewpassword.setBounds(0, 0, 500, 250);
		jpupdatenewpassword.setLayout(null);
		setupdatenewpassword = new JButton("Ok");
		setupdatenewpassword.setBounds(220, 150, 130, 30);
		jtfupdatenewpassworda = new JPasswordField(15);
		jtfupdatenewpassworda.setBounds(220, 10, 165, 30);
		jtfupdatenewpassworda.setEchoChar('*');
		jtfupdatenewpasswordb = new JPasswordField(15);
		jtfupdatenewpasswordb.setBounds(220, 60, 165, 30);
		jtfupdatenewpasswordb.setEchoChar('*');
		// jtfupdatenewpasswordb.setEnabled(false);
		j3 = new JLabel("Please enter the new password:");
		j4 = new JLabel("Please re-enter the new password:");
		j3.setBounds(10, 10, 200, 30);
		j4.setBounds(10, 60, 200, 30);
		showupdatenewpassword = new JCheckBox("Show Password");
		showupdatenewpassword.setBounds(220, 100, 150, 30);
		jpupdatenewpassword.add(j3);
		jpupdatenewpassword.add(jtfupdatenewpassworda);
		jpupdatenewpassword.add(j4);
		jpupdatenewpassword.add(jtfupdatenewpasswordb);
		jpupdatenewpassword.add(showupdatenewpassword);
		jpupdatenewpassword.add(setupdatenewpassword);
		jfupdatenewpassword.getContentPane().add(jpupdatenewpassword);
		jfupdatenewpassword.setVisible(true);
		jfupdatenewpassword.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jtfupdatenewpassworda.requestFocus(true);
		jtfupdatenewpassworda.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				newpasswordchr = jtfupdatenewpassworda.getPassword();
				obj3 = new CheckPassword();
				b4 = obj3.checkupdatenewpassword(newpasswordchr);
				if (b4) {
					JOptionPane.showMessageDialog(jfupdatenewpassword,
							"The password should be exactly 8 characters with no space. \nHaving at least one each from the followings (A-Z), (a-z), (0-9), (@ # $ % & * / \\) and no other characters are allowed. \nAnd it should not be same as previous 5 passwords set by you.",
							"Invalid Password", JOptionPane.ERROR_MESSAGE);
					jtfupdatenewpassworda.setText("");
				} // else {
					// jtfupdatenewpasswordb.setEnabled(true);
					// }
			}

		});
		setupdatenewpassword.addActionListener(this);
		showupdatenewpassword.addActionListener(this);
	}

	void displaynewpassword() {
		jf.setVisible(false);
		jfnewpassword = new JFrame();
		jfnewpassword.setTitle("Set New Password");
		jfnewpassword.setBounds(100, 100, 500, 250);
		jfnewpassword.getContentPane().setLayout(null);
		jpnewpassword = new JPanel();
		jpnewpassword.setBounds(0, 0, 500, 250);
		jpnewpassword.setLayout(null);
		setnewpassword = new JButton("Ok");
		setnewpassword.setBounds(220, 150, 130, 30);
		jtfnewpassworda = new JPasswordField(15);
		jtfnewpassworda.setBounds(220, 10, 165, 30);
		jtfnewpassworda.setEchoChar('*');
		jtfnewpasswordb = new JPasswordField(15);
		jtfnewpasswordb.setBounds(220, 60, 165, 30);
		jtfnewpasswordb.setEchoChar('*');
		// jtfnewpasswordb.setEnabled(false);
		j3 = new JLabel("Please enter the new password:");
		j4 = new JLabel("Please re-enter the new password:");
		j3.setBounds(10, 10, 200, 30);
		j4.setBounds(10, 60, 200, 30);
		shownewpassword = new JCheckBox("Show Password");
		shownewpassword.setBounds(220, 100, 150, 30);
		jpnewpassword.add(j3);
		jpnewpassword.add(jtfnewpassworda);
		jpnewpassword.add(j4);
		jpnewpassword.add(jtfnewpasswordb);
		jpnewpassword.add(shownewpassword);
		jpnewpassword.add(setnewpassword);
		jfnewpassword.getContentPane().add(jpnewpassword);
		jfnewpassword.setVisible(true);
		jfnewpassword.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jtfnewpassworda.requestFocus(true);
		jtfnewpassworda.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				newpasswordchr = jtfnewpassworda.getPassword();
				obj3 = new CheckPassword();
				b2 = obj3.checknewpassword(newpasswordchr);
				if (b2) {
					JOptionPane.showMessageDialog(jfnewpassword,
							"The password should be exactly 8 characters with no space. \nHaving at least one each from the followings (A-Z), (a-z), (0-9), (@ # $ % & * / \\) and no other characters are allowed. \nAnd it should not be same as default password and previous 5 passwords set by you.",
							"Invalid Password", JOptionPane.ERROR_MESSAGE);
					jtfnewpassworda.setText("");
				} // else {
					// jtfnewpasswordb.setEnabled(true);
					// }
			}

		});
		setnewpassword.addActionListener(this);
		shownewpassword.addActionListener(this);
	}

	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == jblogin) {// login button
			passwordchr = jtfpassword.getPassword();
			if (passwordchr == null || passwordchr.length == 0) {
				// checks if the password field is empty.
				count = count + 1;
				JOptionPane.showMessageDialog(jf,
						"Please enter a valid Password. \nWarning: Your Account will be Locked after 5 invalid login attempts.",
						"Error", JOptionPane.ERROR_MESSAGE);
				if (count == 5) {
					// checks if user has utilized five invalid attempts.
					JOptionPane.showMessageDialog(jf,
							"5 Invalid Attempts: Your Account has been Locked. Please contact your Admin.", "Warning",
							JOptionPane.WARNING_MESSAGE);
					jtfpassword.setEnabled(false);
					jblogin.setEnabled(false);
					showpassword.setEnabled(false);
					showpassword.setSelected(false);
					jbforgot.setEnabled(false);
				}
			} else {
				// checks the password validity
				count = count + 1;
				obj2 = new CheckPassword(passwordchr);
				b1 = obj2.checkvalidpassword();
				if (b1 == false) {
					jtfpassword.setText("");
					JOptionPane.showMessageDialog(jf,
							"Invalid Password.\nWarning: Your Account will be Locked after 5 invalid login attempts.",
							"Error", JOptionPane.ERROR_MESSAGE);
					jtfpassword.requestFocus(true);
					if (count == 5) {
						// checks if user has utilized five invalid attempts.
						JOptionPane.showMessageDialog(jf,
								"5 Invalid Attempts: Your Account has been Locked. Please contact your Admin.",
								"Warning", JOptionPane.WARNING_MESSAGE);
						jtfpassword.setEnabled(false);
						jblogin.setEnabled(false);
						showpassword.setEnabled(false);
						showpassword.setSelected(false);
						jbforgot.setEnabled(false);
					}
				} else {
					jf.dispose();
					JOptionPane.showMessageDialog(jf, "Successful Login.");
				}
			}
		} else if (ae.getSource() == jbforgot) {// forgot password button
			obj3 = new CheckPassword();
			obj3.setdefaultpassword(); // default password generated
			displaynewpassword();

		} else if (ae.getSource() == showpassword) { // show password
			if (showpassword.isSelected()) {
				jtfpassword.setEchoChar((char) 0);
			} else {
				jtfpassword.setEchoChar('*');
			}
		} else if (ae.getSource() == shownewpassword) { // show new password
			if (shownewpassword.isSelected()) {
				jtfnewpassworda.setEchoChar((char) 0);
				jtfnewpasswordb.setEchoChar((char) 0);
			} else {
				jtfnewpassworda.setEchoChar('*');
				jtfnewpasswordb.setEchoChar('*');
			}
		} else if (ae.getSource() == setnewpassword) {
			/*
			 * set new password and returns to login window again, after checking re-enter
			 * password with password entered in first field
			 */
			newpasswordchr = jtfnewpassworda.getPassword();
			confirmpassword = jtfnewpasswordb.getPassword();
			if (newpasswordchr.length == 0) {
				jtfnewpassworda.requestFocus(true);
			} else if (newpasswordchr.length == 8) {
				obj3 = new CheckPassword();
				b3 = obj3.setnewpassword(newpasswordchr, confirmpassword);
				if (b3) {
					mapdaysdiff.put(jtfusername.getText(), date);
					// updating the date when the password was last changed.
					jf.setVisible(true);
					jtfpassword.setText("");
					jfnewpassword.dispose();
					count = 0;
				} else {
					JOptionPane.showMessageDialog(jfnewpassword, "Both the passwords don't match.", "Error",
							JOptionPane.ERROR_MESSAGE);
					jtfnewpasswordb.setText("");
				}
			}
		} else if (ae.getSource() == showupdatenewpassword) { // show new update password
			if (showupdatenewpassword.isSelected()) {
				jtfupdatenewpassworda.setEchoChar((char) 0);
				jtfupdatenewpasswordb.setEchoChar((char) 0);
			} else {
				jtfupdatenewpassworda.setEchoChar('*');
				jtfupdatenewpasswordb.setEchoChar('*');
			}
		} else if (ae.getSource() == setupdatenewpassword) {
			/*
			 * set new password and returns to login window again, after checking re-enter
			 * password with password entered in first field
			 */
			newpasswordchr = jtfupdatenewpassworda.getPassword();
			confirmpassword = jtfupdatenewpasswordb.getPassword();
			if (newpasswordchr.length == 0) {
				jtfupdatenewpassworda.requestFocus(true);
			} else if (newpasswordchr.length == 8) {
				obj3 = new CheckPassword();
				b3 = obj3.setnewpassword(newpasswordchr, confirmpassword);
				if (b3) {
					mapdaysdiff.put(jtfusername.getText(), date);
					// updating the date when the password was last changed.
					jf.setVisible(true);
					jtfpassword.setText("");
					jfupdatenewpassword.dispose();
					count = 0;
				} else {
					JOptionPane.showMessageDialog(jfupdatenewpassword, "Both the passwords don't match.", "Error",
							JOptionPane.ERROR_MESSAGE);
					jtfupdatenewpasswordb.setText("");
				}
			}
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Frontend obj1 = new Frontend();
		obj1.display();

	}
}
