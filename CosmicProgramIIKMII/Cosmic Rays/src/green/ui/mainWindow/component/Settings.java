package green.ui.mainWindow.component;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import green.util.InfoUtils;

@SuppressWarnings("serial")
public class Settings extends JPanel {
	private JTextField txtWidth;
	private JTextField txtHeight;
	private JLabel localName;
	private JCheckBox chckbxUseConsole;

	public Settings() {
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);

		JLabel lblSettings = new JLabel("Settings");
		lblSettings.setFont(new Font("Tahoma", Font.PLAIN, 38));
		springLayout.putConstraint(SpringLayout.NORTH, lblSettings, 10, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblSettings, 10, SpringLayout.WEST, this);
		add(lblSettings);

		JLabel lblDisplaySettings = new JLabel("Display Settings");
		lblDisplaySettings.setFont(new Font("Tahoma", Font.PLAIN, 18));
		springLayout.putConstraint(SpringLayout.NORTH, lblDisplaySettings, 35, SpringLayout.SOUTH, lblSettings);
		springLayout.putConstraint(SpringLayout.WEST, lblDisplaySettings, 10, SpringLayout.WEST, this);
		add(lblDisplaySettings);

		ButtonGroup windowModeGroup = new ButtonGroup();
		JRadioButton rdbtnUseDefaultWindow = new JRadioButton("Use default window size (1280x720)");
		springLayout.putConstraint(SpringLayout.NORTH, rdbtnUseDefaultWindow, 6, SpringLayout.SOUTH,
				lblDisplaySettings);
		springLayout.putConstraint(SpringLayout.WEST, rdbtnUseDefaultWindow, 10, SpringLayout.WEST, this);
		rdbtnUseDefaultWindow.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				InfoUtils.setConfigValue("windowMode", 1);
			}
		});
		add(rdbtnUseDefaultWindow);

		JRadioButton rdbtnRememberMyWindow = new JRadioButton("Remember my window size");
		springLayout.putConstraint(SpringLayout.NORTH, rdbtnRememberMyWindow, 7, SpringLayout.SOUTH,
				rdbtnUseDefaultWindow);
		springLayout.putConstraint(SpringLayout.WEST, rdbtnRememberMyWindow, 0, SpringLayout.WEST, lblSettings);
		rdbtnRememberMyWindow.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				InfoUtils.setConfigValue("windowMode", 2);
			}
		});
		add(rdbtnRememberMyWindow);

		JRadioButton rdbtnUseASpecific = new JRadioButton("Use a specific size");
		springLayout.putConstraint(SpringLayout.NORTH, rdbtnUseASpecific, 10, SpringLayout.SOUTH,
				rdbtnRememberMyWindow);
		springLayout.putConstraint(SpringLayout.WEST, rdbtnUseASpecific, 0, SpringLayout.WEST, lblSettings);
		rdbtnUseASpecific.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				InfoUtils.setConfigValue("windowMode", 3);
				try{
					int width = Integer.parseInt(txtWidth.getText());
					int height = Integer.parseInt(txtHeight.getText());
					InfoUtils.setConfigValue("customSize", new Dimension(width,height));
				}
				catch(Exception ex){
					rdbtnRememberMyWindow.setSelected(true);
					InfoUtils.setConfigValue("windowMode", 2);
				}
			}
		});
		add(rdbtnUseASpecific);

		windowModeGroup.add(rdbtnUseASpecific);
		windowModeGroup.add(rdbtnRememberMyWindow);
		windowModeGroup.add(rdbtnUseDefaultWindow);

		txtWidth = new JTextField();
		txtWidth.setText("width");
		springLayout.putConstraint(SpringLayout.NORTH, txtWidth, 6, SpringLayout.SOUTH, rdbtnUseASpecific);
		springLayout.putConstraint(SpringLayout.WEST, txtWidth, 0, SpringLayout.WEST, lblSettings);
		add(txtWidth);
		txtWidth.setColumns(10);

		txtHeight = new JTextField();
		txtHeight.setText("height");
		springLayout.putConstraint(SpringLayout.NORTH, txtHeight, 6, SpringLayout.SOUTH, rdbtnUseASpecific);
		springLayout.putConstraint(SpringLayout.WEST, txtHeight, 6, SpringLayout.EAST, txtWidth);
		add(txtHeight);
		txtHeight.setColumns(10);

		JLabel lblLocalStation = new JLabel("Local Station");
		lblLocalStation.setFont(new Font("Tahoma", Font.PLAIN, 18));
		springLayout.putConstraint(SpringLayout.NORTH, lblLocalStation, 49, SpringLayout.SOUTH, txtWidth);
		springLayout.putConstraint(SpringLayout.WEST, lblLocalStation, 0, SpringLayout.WEST, lblSettings);
		add(lblLocalStation);

		int stationID = (int) InfoUtils.getConfigValueOf("lclID", 14004);
		String name = InfoUtils.getNameForID(stationID);
		localName = new JLabel(name + " #" + stationID);
		springLayout.putConstraint(SpringLayout.NORTH, localName, 6, SpringLayout.SOUTH, lblLocalStation);
		springLayout.putConstraint(SpringLayout.WEST, localName, 0, SpringLayout.WEST, lblSettings);
		add(localName);
		
		JButton btnChange = new JButton("Change");
		springLayout.putConstraint(SpringLayout.NORTH, btnChange, 6, SpringLayout.SOUTH, localName);
		springLayout.putConstraint(SpringLayout.WEST, btnChange, 0, SpringLayout.WEST, lblSettings);
		btnChange.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				StationSelectorWindow s = new StationSelectorWindow();
				s.setVisible(true);
			}
		});
		add(btnChange);

		JButton btnReset = new JButton("Reset");
		springLayout.putConstraint(SpringLayout.NORTH, btnReset, 6, SpringLayout.SOUTH, localName);
		springLayout.putConstraint(SpringLayout.WEST, btnReset, 6, SpringLayout.EAST, btnChange);
		btnReset.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				InfoUtils.setConfigValue("lclID", -1);
				localName.setText(InfoUtils.getNameForID(-1));
			}
		});
		add(btnReset);
		
		chckbxUseConsole = new JCheckBox("Use Console");
		chckbxUseConsole.setSelected((boolean)InfoUtils.getConfigValueOf("useConsole", true));
		springLayout.putConstraint(SpringLayout.NORTH, chckbxUseConsole, 6, SpringLayout.SOUTH, txtWidth);
		springLayout.putConstraint(SpringLayout.WEST, chckbxUseConsole, 0, SpringLayout.WEST, lblSettings);
		chckbxUseConsole.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(chckbxUseConsole.isSelected())
					InfoUtils.setConfigValue("useConsole", true);
				else
					InfoUtils.setConfigValue("useConsole", false);
			}
		});
		add(chckbxUseConsole);
	}
	
	public void send(int i){
		if(i != -1){
			InfoUtils.setConfigValue("lclID", i);
			localName.setText(InfoUtils.getNameForID(i)+" #" + i);
		}
	}
}
