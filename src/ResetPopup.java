import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.JButton;

public class ResetPopup extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ResetPopup frame = new ResetPopup();
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
	public ResetPopup() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextPane txtpnDsfadsa = new JTextPane();
		txtpnDsfadsa.setText("dsfadsa");
		txtpnDsfadsa.setBounds(299, 101, -178, 20);
		contentPane.add(txtpnDsfadsa);
		
		JLabel ResetLabel = new JLabel("Are you sure you want to reset this program? This will permanently clear all of the scheduled competitions. ");
		ResetLabel.setBounds(131, 101, 158, 46);
		contentPane.add(ResetLabel);
		
		JButton YesButton = new JButton("Yes");
		YesButton.setBounds(82, 187, 89, 23);
		contentPane.add(YesButton);
		
		JButton NoButton = new JButton("No");
		NoButton.setBounds(234, 187, 89, 23);
		contentPane.add(NoButton);
	}
}


