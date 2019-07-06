package logindetails;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class CheckPassword {
	private char[] passwordchr;
	private Map<String, LinkedList<String>> map = new HashMap<String, LinkedList<String>>();
	private LinkedList<String> passwords = new LinkedList<String>();
	private boolean b1 = false, b2 = false, bdefault = false, bprevious = false, bupper = true;
	private boolean blower = true, bdigit = true, balpha = true, beight = true, bspace = false, b3 = true;
	private boolean b4 = false, bupdateprevious = false, bupdateupper = true, bother = false;
	private boolean bupdatelower = true, bupdatedigit = true, bupdatealpha = true, bupdateeight = true;
	private static String temppassword, defaultpassword;

	CheckPassword() {
		// only for this program 5 passwords are generated for user Programmer
		// and default password is also added if it was generated.
		if (!(defaultpassword == null || defaultpassword.length() == 0)) {// recent default password
			passwords.addFirst(defaultpassword);//
		}
		passwords.add("gh@1a#B3");// 1
		passwords.add("qp@5a#BV");// 2
		passwords.add("jK$1a%b3");// 3
		passwords.add("ht/1A&B3");// 4
		if (temppassword == null || temppassword.length() == 0) {// recent valid password
			passwords.addLast("SN@1a*b8");// 5
		} else {
			passwords.addLast(temppassword);
		}
		map.put("Programmer", passwords);
	}

	CheckPassword(char[] passwordchr) {
		this.passwordchr = passwordchr;
		// only for this program 5 passwords are generated for user Programmer
		if (!(defaultpassword == null || defaultpassword.length() == 0)) {// recent default password
			passwords.addFirst(defaultpassword);//
		}
		passwords.add("gh@1a#B3");// 1
		passwords.add("qp@5a#BV");// 2
		passwords.add("jK$1a%b3");// 3
		passwords.add("ht/1A&B3");// 4
		if (temppassword == null || temppassword.length() == 0) {// recent valid password
			passwords.addLast("SN@1a*b8");// 5
		} else {
			passwords.addLast(temppassword);
		}
		map.put("Programmer", passwords);
	}

	boolean checkvalidpassword() {// checks password validity for login
		int length = passwords.size() - 1;
		String str = passwords.get(length);
		String stra = new String(passwordchr);
		if (stra.equals(str)) {
			b1 = true;
		} else {
			b1 = false;
		}
		return b1;
	}

	boolean setnewpassword(char[] passwordchr, char[] confirmpassword) {
		// sets the new or updated password of the user.
		if (passwordchr.length == confirmpassword.length) {
			for (int i = 0; i < confirmpassword.length; i++) {
				if (passwordchr[i] == confirmpassword[i]) {
					b3 = true;
					continue;
				} else {
					b3 = false;
					break;
				}
			}
		} else {
			b3 = false;
		}

		if (b3) {
			passwords.remove(1);
			temppassword = new String(passwordchr);
			passwords.addLast(temppassword);
		}
		return b3;

	}

	boolean checknewpassword(char[] newpasswordchr) {
		// check new password with its criteria (includes checking with default).
		String str = new String(newpasswordchr);
		/*
		 * b2=false; bdefault= false; bprevious =false; bupper=true; blower=true;
		 * bdigit=true; balpha=true; beight=true, bother =false;
		 */
		int countupper = 0, countlower = 0, countdigit = 0, countalpha = 0;
		if (newpasswordchr.length == 8) {
			beight = false;
		} else {
			beight = true;
		}
		String stra = passwords.get(0);
		if (str.equals(stra)) {
			bdefault = true;
		} else {
			bdefault = false;
		}
		for (int i = 1; i < passwords.size(); i++) {
			stra = passwords.get(i);
			if (str.equals(stra)) {
				bprevious = true;
				break;
			} else {
				bprevious = false;
				continue;
			}
		}
		for (int j = 0; j < newpasswordchr.length; j++) {
			char ch = newpasswordchr[j];
			if (ch >= 65 && ch <= 90) {
				countupper = countupper + 1;
				continue;
			}
			if (ch >= 97 && ch <= 122) {
				countlower = countlower + 1;
				continue;
			}
			if (ch >= 48 && ch <= 57) {
				countdigit = countdigit + 1;
				continue;
			}

			if (ch == 64 || ch == 35 || ch == 36 || ch == 37 || ch == 38 || ch == 42 || ch == 47 || ch == 92) {
				countalpha = countalpha + 1;
				continue;

			}

		}
		if (countupper > 0) {
			bupper = false;
		} else {
			bupper = true;
		}
		if (countlower > 0) {
			blower = false;
		} else {
			blower = true;
		}
		if (countdigit > 0) {
			bdigit = false;
		} else {
			bdigit = true;
		}
		if (countalpha > 0) {
			balpha = false;
		} else {
			balpha = true;
		}

		for (int j = 0; j < newpasswordchr.length; j++) {
			// no other characters or space apart from (A-Z), (a-z), (0-9), (@ # $ % & * /
			// \\)
			char ch = newpasswordchr[j];
			if ((ch >= 65 && ch <= 90) || (ch >= 97 && ch <= 122) || (ch >= 48 && ch <= 57)
					|| (ch == 64 || ch == 35 || ch == 36 || ch == 37 || ch == 38 || ch == 42 || ch == 47 || ch == 92)) {
				continue;
			} else {
				bother = true;
				break;
			}
		}
		if (bdefault || beight || bprevious || bupper || blower || bdigit || balpha || bother) {
			b2 = true;
		} else {
			b2 = false;
		}
		return b2;
	}

	boolean checkupdatenewpassword(char[] newupdatepasswordchr) {
		// check new password with its criteria (Does not include checking with
		// default).
		String str = new String(newupdatepasswordchr);
		/*
		 * b4=false; bupdateprevious =false; bupdateupper=true; bupdatelower=true;
		 * bupdatedigit=true; bupdatealpha=true; bupdateeight=true, bother=false;
		 */
		int countupdateupper = 0, countupdatelower = 0, countupdatedigit = 0, countupdatealpha = 0;
		if (newupdatepasswordchr.length == 8) {
			bupdateeight = false;
		} else {
			bupdateeight = true;
		}

		for (int i = 0; i < passwords.size(); i++) {
			String stra = passwords.get(i);
			if (str.equals(stra)) {
				bupdateprevious = true;
				break;
			} else {
				bupdateprevious = false;
				continue;
			}
		}

		for (int j = 0; j < newupdatepasswordchr.length; j++) {
			char ch = newupdatepasswordchr[j];
			if (ch >= 65 && ch <= 90) {
				countupdateupper = countupdateupper + 1;
				continue;
			}
			if (ch >= 97 && ch <= 122) {
				countupdatelower = countupdatelower + 1;
				continue;
			}
			if (ch >= 48 && ch <= 57) {
				countupdatedigit = countupdatedigit + 1;
				continue;
			}

			if (ch == 64 || ch == 35 || ch == 36 || ch == 37 || ch == 38 || ch == 42 || ch == 47 || ch == 92) {
				countupdatealpha = countupdatealpha + 1;
				continue;

			}
		}
		if (countupdateupper > 0) {
			bupdateupper = false;
		} else {
			bupdateupper = true;
		}
		if (countupdatelower > 0) {
			bupdatelower = false;
		} else {
			bupdatelower = true;
		}
		if (countupdatedigit > 0) {
			bupdatedigit = false;
		} else {
			bupdatedigit = true;
		}
		if (countupdatealpha > 0) {
			bupdatealpha = false;
		} else {
			bupdatealpha = true;
		}
		for (int j = 0; j < newupdatepasswordchr.length; j++) {
			// no other characters or space apart from (A-Z), (a-z), (0-9), (@ # $ % & * /
			// \\)
			char ch = newupdatepasswordchr[j];
			if ((ch >= 65 && ch <= 90) || (ch >= 97 && ch <= 122) || (ch >= 48 && ch <= 57)
					|| (ch == 64 || ch == 35 || ch == 36 || ch == 37 || ch == 38 || ch == 42 || ch == 47 || ch == 92)) {
				continue;
			} else {
				bother = true;
				break;
			}
		}
		if (bupdateeight || bupdateprevious || bupdateupper || bupdatelower || bupdatedigit || bupdatealpha || bother) {
			b4 = true;
		} else {
			b4 = false;
		}
		return b4;
	}

	void setdefaultpassword() {
		defaultpassword = "HL@8a#b1";// default password generated
		passwords.addFirst(defaultpassword);
		map.put("Programmer", passwords);
	}
}

