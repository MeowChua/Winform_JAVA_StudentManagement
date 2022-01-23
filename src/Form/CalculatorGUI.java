/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Form;
import java.awt.*;  
import java.awt.event.*;  
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.BorderFactory;
import javax.swing.*;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import java.awt.BorderLayout;
import static java.awt.Component.RIGHT_ALIGNMENT;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Logger;
import javax.swing.JFrame;
import java.util.*; 

public class CalculatorGUI extends JFrame implements ActionListener, KeyListener {
    private static final long serialVersionUID = 1L;

	private int frameWidth = 300;
	private int frameHeight = 400;
	private int xframeShow = 400;
	private int yframeShow = 100;
	private JTextField tfDisplay;
	private JLabel lbAns, lbStats, lbDOH, lbB;
	private int mode = 0;
	private JFrame frame;

	private JPanel mainPanel;
	private JPanel disPlayPanel;
	private JPanel buttonPanel;

	private JButton btnArr[];
	private JButton btnArrSub[];
	private JRadioButton radDeg, radRad;
	private JRadioButton radBin, radOct, radDec, radHex;

	private String lbButton[];
	private String mathElement[];
	private String varElement[];
	private double ans = 0;

	private Color colorDisableStats = Color.lightGray,
			colorEnnabaleStar = Color.black;

	private boolean isSTO = false;
	private Balan balan;
	private HelpAndAbout help, about;

	public CalculatorGUI() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setResizable(false);
		frame.setBounds(xframeShow, yframeShow, frameWidth, frameHeight);
		frame.setJMenuBar(createMenuBar());
		resetValue(); // dat lai cac gia tri
		changeMode(); // che do hien thi
	}

	private void changeMode() {
		if (mode == 0) {
			frameWidth = 300;
			frameHeight = 380;
			frame.setTitle("Calculator - Basic");
		}
		if (mode == 1) {
			frameWidth = 460;
			frameHeight = 440;
			frame.setTitle("Calculator - Advanced");
		}
		if (mode == 2) {
			frameWidth = 460;
			frameHeight = 440;
			frame.setTitle("Calculator - Program");
		}
		createListLabelButton(mode);
		balan.setDegOrRad(true);
		balan.setRadix(10);

		frame.getContentPane().removeAll();
		frame.setSize(frameWidth, frameHeight);
		mainPanel = createMainPanel();
		frame.add(mainPanel);

		frame.getContentPane().validate();
		frame.setVisible(true);
		tfDisplay.requestFocus();
	}

	private JPanel createMainPanel() {
		JPanel mainPanel = new JPanel(new BorderLayout());
		disPlayPanel = createDisplayPanel();
		mainPanel.add(disPlayPanel, BorderLayout.NORTH);

		if (mode == 0) {
			buttonPanel = createButtonBasicPanel();
		}
		if (mode == 1) {
			buttonPanel = createButtonAdvancedPanel();
		}
		if (mode == 2) {
			buttonPanel = createButtonProgramPanel();
		}
		mainPanel.add(buttonPanel, BorderLayout.CENTER);
		mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		// mainPanel

		mainPanel.setVisible(true);
		return mainPanel;
	}

	// ----------------------- main panel content ----------------------- //
	private JMenuBar createMenuBar() {
		JMenuBar mb = new JMenuBar();

		// menu mode
		JMenu mm = createMenu("Mode", KeyEvent.VK_M);

		mm.add(createMenuItem("Basic", KeyEvent.VK_B));
		mm.add(createMenuItem("Advanced", KeyEvent.VK_A));
		mm.add(createMenuItem("Program", KeyEvent.VK_P));
		mm.addSeparator();
		mm.add(createMenuItem("Exit", KeyEvent.VK_X));
		mb.add(mm);

		// menu help
		JMenu mh = createMenu("Help", 0);

		mh.add(createMenuItem("Help", KeyEvent.VK_H));
		mh.add(createMenuItem("About", KeyEvent.VK_H));
		mb.add(mh);

		return mb;
	}

	// create Display Panel
	private JPanel createDisplayPanel() {

		JPanel panel = new JPanel(new BorderLayout());

		if (mode == 1) {
			lbStats = new JLabel("sto");
			Font fontStats = lbStats.getFont().deriveFont(Font.PLAIN, 12f);
			lbStats.setFont(fontStats);
			lbStats.setForeground(colorDisableStats);
			lbStats.setBackground(Color.white);
			lbStats.setOpaque(true);
			panel.add(lbStats, BorderLayout.NORTH);
		}

		tfDisplay = new JTextField(frameWidth);
		Font fontDisplay = tfDisplay.getFont().deriveFont(Font.PLAIN, 25f);
		tfDisplay.setFont(fontDisplay);
		tfDisplay.setHorizontalAlignment(JTextField.RIGHT);
		tfDisplay.setBorder(null);
		tfDisplay.addKeyListener(this);// bac su kien khi an phim
		panel.add(tfDisplay, BorderLayout.CENTER);

		lbAns = new JLabel("0");
		Font fontAns = lbAns.getFont().deriveFont(Font.PLAIN, 35f);
		lbAns.setFont(fontAns);
		lbAns.setHorizontalAlignment(JLabel.RIGHT);
		lbAns.setBackground(Color.white);
		lbAns.setOpaque(true);

		panel.add(lbAns, BorderLayout.SOUTH);
		panel.setBorder(new EmptyBorder(0, 0, 10, 0));

		return panel;
	}

	// create button Basic Panel
	private JPanel createButtonBasicPanel() {
		JPanel buttonBasicPanel = new JPanel(new GridLayout(5, 5, 3, 3));

		btnArr = addListButtonToPanel(lbButton, buttonBasicPanel);

		return buttonBasicPanel;
	}

	// create button Advanced panel
	private JPanel createButtonAdvancedPanel() {

		// panel top
		JPanel panelLeft = new JPanel(new FlowLayout(FlowLayout.LEFT));
		ButtonGroup btnGroup = new ButtonGroup();
		radDeg = createRadio("Deg", true, panelLeft);
		btnGroup.add(radDeg);
		radRad = createRadio("Rad", false, panelLeft);
		btnGroup.add(radRad);

		JPanel panelRight = new JPanel(new GridLayout(1, 7, 3, 3));
		panelRight.setBorder(new EmptyBorder(0, 15, 10, 0));

		String lb[] = { "STO", "vA", "vB", "vC", "vD", "vE", "vF" };
		varElement = lb;
		btnArrSub = addListButtonToPanel(lb, panelRight);

		JPanel panel1 = new JPanel(new BorderLayout());
		panel1.add(panelLeft, BorderLayout.WEST);
		panel1.add(panelRight, BorderLayout.CENTER);

		// panel bottom
		JPanel panel2 = new JPanel(new GridLayout(5, 8, 3, 3));

		btnArr = addListButtonToPanel(lbButton, panel2);

		JPanel buttonAdvancedPanel = new JPanel(new BorderLayout());
		buttonAdvancedPanel.add(panel1, BorderLayout.NORTH);
		buttonAdvancedPanel.add(panel2, BorderLayout.CENTER);
		return buttonAdvancedPanel;
	}

	// create panel program
	private JPanel createButtonProgramPanel() {

		// panel top
		JPanel panelLeft = new JPanel(new GridLayout(2, 2));
		// panelLeft.setPreferredSize(new Dimension(100, 50));
		ButtonGroup btnGroup = new ButtonGroup();
		radBin = createRadio("Bin", false, panelLeft);
		btnGroup.add(radBin);
		radOct = createRadio("Oct", false, panelLeft);
		btnGroup.add(radOct);
		radDec = createRadio("Dec", true, panelLeft);
		btnGroup.add(radDec);
		radHex = createRadio("Hex", false, panelLeft);
		btnGroup.add(radHex);

		JPanel panelRight = new JPanel(new BorderLayout());
		lbDOH = new JLabel("0₁₀ = 0₁₆ = 0₈");
		lbDOH.setHorizontalAlignment(JLabel.RIGHT);
		Font font = lbDOH.getFont().deriveFont(Font.PLAIN, 13f);
		lbDOH.setFont(font);
		panelRight.add(lbDOH, BorderLayout.PAGE_START);

		// panel binary
		String bin1 = "0000  0000  0000  0000  0000  0000  0000  0000₂";
		lbB = new JLabel(bin1);
		lbB.setHorizontalAlignment(JLabel.RIGHT);
		lbB.setFont(font);
		panelRight.add(lbB, BorderLayout.PAGE_END);
		panelRight.setBorder(new EmptyBorder(5, 5, 5, 5));
		panelRight.setAlignmentX(RIGHT_ALIGNMENT);

		JPanel panel1 = new JPanel(new BorderLayout());
		panel1.add(panelLeft, BorderLayout.WEST);
		panel1.add(panelRight, BorderLayout.CENTER);

		// panel bottom
		JPanel panel2 = new JPanel(new GridLayout(5, 8, 3, 3));

		btnArr = addListButtonToPanel(lbButton, panel2);

		JPanel buttonProgramPanel = new JPanel(new BorderLayout());
		buttonProgramPanel.add(panel1, BorderLayout.NORTH);
		buttonProgramPanel.add(panel2, BorderLayout.CENTER);
		return buttonProgramPanel;
	}

	// ----------------------- item of menu ----------------------- //
	// create menu
	private JMenu createMenu(String title, int key) {
		JMenu menu = new JMenu(title);
		menu.setMnemonic(key);
		return menu;
	}

	// create menu item
	private JMenuItem createMenuItem(String title, int key) {
		JMenuItem mi = new JMenuItem(title, key);
		mi.addActionListener(this);
		return mi;
	}

	// ----------------------- item of display ----------------------- //
	// -------------- item of buttonBasicPanel -------------- //
	// create button
	private JButton createButton(String title) {
		JButton btn = new JButton(title);
		btn.addActionListener(this);
		btn.setMargin(new Insets(0, 0, 0, 0));
		return btn;
	}

	// create button and add to panel
	private JButton createButton(String title, JPanel panel) {
		JButton btn = createButton(title);
		panel.add(btn);
		return btn;
	}

	// create Radio button
	private JRadioButton createRadio(String title, boolean isSelect,
			JPanel panel) {
		JRadioButton rad = new JRadioButton(title);
		rad.addActionListener(this);
		rad.setSelected(isSelect);
		panel.add(rad);
		return rad;
	}

	// create List label Button
	private void createListLabelButton(int mode) {
		if (mode == 0) {
			String s[] = { "C", "CE", "←", "(", ")", "7", "8", "9", "/", "√",
					"4", "5", "6", "*", "x²", "1", "2", "3", "–", "*10ⁿ", "0",
					"•", "Ans", "+", "=" };
			lbButton = s;

			String s1[] = { "", "", "", "(", ")", "7", "8", "9", "/", "√", "4",
					"5", "6", "*", "²", "1", "2", "3", "-", "*10^", "0", ".",
					"Ans", "+", "" };
			mathElement = s1;

			return;
		}
		if (mode == 1) {
			String s[] = { "C", "CE", "←", "(", ")", "!", "a*b", "log", "7",
					"8", "9", "/", "√", "ⁿ√", "nCr", "nPr", "4", "5", "6", "*",
					"x²", "xⁿ", "sin", "arsin", "1", "2", "3", "–", "*10ⁿ",
					"π", "cos", "arcos", "0", "•", "Ans", "+", "=", "e", "tan",
					"artan" };
			lbButton = s;

			String s1[] = { "", "", "", "(", ")", "!", "", "log ", "7", "8",
					"9", "/", "√", "ⁿ√", "ℂ", "ℙ", "4", "5", "6", "*", "²",
					"^", "sin ", "arcsin ", "1", "2", "3", "-", "*10^", "π",
					"cos ", "arccos ", "0", ".", "Ans", "+", "", "e", "tan ",
					"arctan " };
			mathElement = s1;
			return;
		}
		if (mode == 2) {
			String s[] = { "C", "CE", "←", "(", ")", "!", "a*b", "Mod", "7",
					"8", "9", "/", "√", "<<", ">>", "And", "4", "5", "6", "*",
					"x²", "A", "B", "Or", "1", "2", "3", "−", "*10ⁿ", "C", "D",
					"Xor", "0", "•", "Ans", "+", "=", "E", "F", "Not" };
			lbButton = s;

			String s1[] = { "", "", "", "(", ")", "!", "", "Mod", "7", "8",
					"9", "/", "√", "≪", "≫", "∧", "4", "5", "6", "*", "²", "A",
					"B", "∨", "1", "2", "3", "-", "*10^", "C", "D", "⊻", "0",
					".", "Ans", "+", "", "E", "F", "¬" };
			mathElement = s1;
			return;
		}
	}

	private JButton[] addListButtonToPanel(String lbArr[], JPanel panel) {
		JButton arr[] = new JButton[lbArr.length];
		for (int i = 0; i < lbArr.length; i++) {
			arr[i] = createButton(lbArr[i], panel);
		}
		return arr;
	}

	// ----------------------- Action ----------------------- //
	// dat lai gia tri cho balan
	private void resetValue() {
		balan = new Balan();
		balan.setError(false);
		if (mode == 2) {
			setRadix();
		}
		if (mode == 1) {
			setDegOrRad();
		}
	}

	// cho phep chen ky tu vao vi tri con tro
	private void insertMathString(String str) {
		int index = tfDisplay.getCaretPosition();
		StringBuilder s = new StringBuilder(tfDisplay.getText() + ""); // copy
		s.insert(index, str); // insert text at index control
		String s1 = new String(s); // convert to string
		tfDisplay.setText(s1); // set text for jtextField
		tfDisplay.requestFocus(); // focus jtextFiedl
		tfDisplay.setCaretPosition(index + str.length());
	}

	// tra ve ket qua
	private void result() {
		balan.setError(false);
		ans = balan.valueMath(tfDisplay.getText());
		if (!balan.isError()) {
			balan.var[0] = ans;
			lbAns.setText(balan.numberToString(ans, balan.getRadix(),
					balan.getSizeRound()));
		} else {
			lbAns.setText("Math error!");
		}
		// hien thi ket qua trong che do Program
		setLabelProgram();
	}

	// hien thi ket qua trong che do Program
	private void setLabelProgram() {
		if (mode == 2) {
			long num = 0;
			if (balan.isIntegerNumber(ans) && ans >= 0 && !balan.isError()) {
				num = (int) ans;
				lbDOH.setText(num + "₁₀ = "
						+ Long.toHexString(num).toUpperCase() + "₁₆ = "
						+ Long.toOctalString(num) + "₈");

				String bi = Long.toBinaryString(num);
				StringBuilder s = new StringBuilder(bi);
				int i = s.length();
				while (i < 32) {
					s.insert(0, "0");
					i++;
				}
				bi = "";
				for (i = 1; i <= s.length(); i++) {
					bi += s.charAt(i - 1);
					if (i % 4 == 0 && i < s.length()) {
						bi += "  ";
					}
				}
				bi += "₂";

				lbB.setText(bi);

				enabaleLabelProgram(true);
			} else {
				enabaleLabelProgram(false);
			}
		}
	}

	// dat che do hien thi hay khong hient thi cho Label Program
	private void enabaleLabelProgram(boolean is) {
		if (mode == 2) {
			lbDOH.setEnabled(is);
			lbB.setEnabled(is);
		}
	}

	private void actionCE() {
		balan.setError(false);
		isSTO = false;
		if (mode == 1) {
			lbStats.setForeground(colorDisableStats);
		}
		tfDisplay.setText("");
		tfDisplay.requestFocus();
		enabaleLabelProgram(true);
		lbAns.setText("0");
		if (mode == 2) {
			lbDOH.setText("0₁₀ = 0₁₆ = 0₈");
			lbB.setText("0000  0000  0000  0000  0000  0000  0000  0000₂");
		}

	}

	private void actionDel() {
		int index = tfDisplay.getCaretPosition();
		StringBuilder s = new StringBuilder(tfDisplay.getText() + ""); // copy
		if (index > 0) {
			s.deleteCharAt(index - 1);
			String s1 = new String(s); // convert to string
			tfDisplay.setText(s1); // set text for jtextField
			tfDisplay.setCaretPosition(index - 1);
		}
		tfDisplay.requestFocus(); // focus jtextFiedl
	}

	private void setDegOrRad() {
		if (radRad.isSelected()) {
			balan.setDegOrRad(false);
		}
		if (radDeg.isSelected()) {
			balan.setDegOrRad(true);
		}
		tfDisplay.requestFocus();
	}

	private void setRadix() {
		if (radBin.isSelected()) {
			balan.setRadix(2);
		}
		if (radOct.isSelected()) {
			balan.setRadix(8);
		}
		if (radDec.isSelected()) {
			balan.setRadix(10);
		}
		if (radHex.isSelected()) {
			balan.setRadix(16);
		}
		tfDisplay.requestFocus();
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		String command = evt.getActionCommand();

		// action on menu
		if (command == "Basic") {
			mode = 0;
			changeMode();
			return;
		}
		if (command == "Advanced") {
			mode = 1;
			changeMode();
			setDegOrRad();
			return;
		}
		if (command == "Program") {
			mode = 2;
			changeMode();
			setRadix();
			return;
		}
		if (command == "Exit") {
			System.exit(0);
		}

		if (command == "Help") {
			if (help == null) {
				help = new HelpAndAbout(0, "Calculator - Help");
			}
			help.setVisible(true);
		}

		if (command == "About") {
			if (about == null) {
				about = new HelpAndAbout(1, "Calculator - About");
			}
			about.setVisible(true);
		}

		// action on radioButton
		if (evt.getSource() == radRad || evt.getSource() == radDeg) {
			setDegOrRad();
			return;
		}

		// button dac biet
		if (evt.getSource() == btnArr[0]) {
			resetValue();
			actionCE();
			return;
		}
		if (evt.getSource() == btnArr[1]) {
			actionCE();
			return;
		}

		if (evt.getSource() == btnArr[2]) {
			actionDel();
			return;
		}

		// neu trong che do Advanced
		if (mode == 1) {
			if (evt.getSource() == btnArrSub[0]) {
				if (!isSTO) {
					isSTO = true;
					lbStats.setForeground(colorEnnabaleStar);
				} else {
					isSTO = false;
					lbStats.setForeground(colorDisableStats);
				}
			} else {

				for (int i = 1; i < btnArrSub.length; i++) {
					if (evt.getSource() == btnArrSub[i]) {
						if (isSTO) {
							result();
							tfDisplay.setCaretPosition(tfDisplay.getText()
									.length());
							insertMathString("→" + varElement[i]);
							balan.var[i] = ans;
							isSTO = false;
						} else {
							insertMathString(varElement[i]);
						}
					}
				}
				isSTO = false;
				lbStats.setForeground(colorDisableStats);
			}
		}

		if (mode == 2) {
			if (evt.getSource() == radBin || evt.getSource() == radOct
					|| evt.getSource() == radDec || evt.getSource() == radHex) {
				setRadix();
				return;
			}
		}

		for (int i = 0; i < btnArr.length; i++) {
			if (evt.getSource() == btnArr[i] && !mathElement[i].equals("")) {
				insertMathString(mathElement[i]);
				return;
			}
		}
		if (command == "=") {
			result();
			return;
		}
		if (command == "a*b") {
			result();
			if (!balan.isError()) {
				lbAns.setText(balan.primeMulti(ans));
			}
			return;
		}
	}

	@Override
	public void keyPressed(KeyEvent ke) {
		if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
			result();
		}
	}

	@Override
	public void keyReleased(KeyEvent ke) {

	}

	@Override
	public void keyTyped(KeyEvent ke) {

	}
}
