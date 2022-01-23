/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Form;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import java.awt.BorderLayout;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Logger;
public class HelpAndAbout extends JFrame {
    private static final long serialVersionUID = 1L;
	private String urlHelp = File.separator + "nguyenvanquan7826"
			+ File.separator + "file" + File.separator + "help";
	private String urlAbout = File.separator + "nguyenvanquan7826"
			+ File.separator + "file" + File.separator + "about";
	private int bound = 10;

	public HelpAndAbout(int type, String title) {
		add(createContent(type));
		setTitle(title);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);
	}

	private JPanel createContent(int type) {
		String url = "";
		if (type == 0) {
			url = urlHelp;
		} else if (type == 1) {
			url = urlAbout;
		}
		InputStream in = getClass().getResourceAsStream(url);
		// String text = in.
		JTextArea ta = new JTextArea();
		ta.setWrapStyleWord(true);
		ta.setLineWrap(true);
		ta.setBackground(null);
		ta.setEditable(false);
		ta.setColumns(30);
		ta.setRows(15);
		try {
			ta.read(new InputStreamReader(in), null);
		} catch (IOException e) {
		}
		JScrollPane scrollPanel = new JScrollPane(ta);
		scrollPanel.setBorder(BorderFactory
				.createEtchedBorder(EtchedBorder.LOWERED));
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(scrollPanel, BorderLayout.CENTER);
		panel.setBorder(new EmptyBorder(bound, bound, bound, bound / 2));
		return panel;
	}
}

