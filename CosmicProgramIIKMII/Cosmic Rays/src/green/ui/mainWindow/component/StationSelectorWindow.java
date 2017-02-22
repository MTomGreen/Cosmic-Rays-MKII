package green.ui.mainWindow.component;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import green.ui.mainWindow.Tabs;
import green.util.InfoUtils;

@SuppressWarnings("serial")
public class StationSelectorWindow extends JFrame{
	private JTextField txtName;
	private JTextField txtId;
	public StationSelectorWindow() {
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		setTitle("Station chooser");
		setLocation(d.width/2-138, d.height/2-75);
		setSize(275, 150);
		setResizable(false);
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);
		
		JLabel lblSelectAStation = new JLabel("Select a station");
		lblSelectAStation.setFont(new Font("Tahoma", Font.PLAIN, 18));
		springLayout.putConstraint(SpringLayout.NORTH, lblSelectAStation, 10, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblSelectAStation, 10, SpringLayout.WEST, getContentPane());
		getContentPane().add(lblSelectAStation);
		
		txtName = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, txtName, 23, SpringLayout.SOUTH, lblSelectAStation);
		springLayout.putConstraint(SpringLayout.WEST, txtName, 0, SpringLayout.WEST, lblSelectAStation);
		txtName.setText("Name");
		getContentPane().add(txtName);
		txtName.setColumns(10);
		
		txtId = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, txtId, 0, SpringLayout.NORTH, txtName);
		springLayout.putConstraint(SpringLayout.WEST, txtId, 6, SpringLayout.EAST, txtName);
		txtId.setText("ID");
		getContentPane().add(txtId);
		txtId.setColumns(10);
		
		JLabel lblEnterEitherA = new JLabel("Enter either a name or a station ID");
		springLayout.putConstraint(SpringLayout.WEST, lblEnterEitherA, 0, SpringLayout.WEST, lblSelectAStation);
		springLayout.putConstraint(SpringLayout.SOUTH, lblEnterEitherA, -6, SpringLayout.NORTH, txtName);
		getContentPane().add(lblEnterEitherA);
		
		JButton btnNewButton = new JButton("OK");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					int id = InfoUtils.getDetectorFromNameOrID(txtName.getText(), txtId.getText()).getId();
					Tabs.settings.send(id);
					dispose();
				}
				catch(Exception e){
					txtName.setText("Invalid name or ID!");
					txtId.setText("");
				}
				
				
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, btnNewButton, 6, SpringLayout.SOUTH, txtName);
		springLayout.putConstraint(SpringLayout.WEST, btnNewButton, 0, SpringLayout.WEST, lblSelectAStation);
		getContentPane().add(btnNewButton);
		
		JButton btnCancel = new JButton("Cancel");
		springLayout.putConstraint(SpringLayout.NORTH, btnCancel, 6, SpringLayout.SOUTH, txtName);
		springLayout.putConstraint(SpringLayout.WEST, btnCancel, 6, SpringLayout.EAST, btnNewButton);
		btnCancel.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				Tabs.settings.send(-1);
				dispose();
			}
		});
		getContentPane().add(btnCancel);
		
	}


}
