package green.ui.mainWindow.component;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import green.object.Detector;
import green.util.InfoUtils;

@SuppressWarnings("serial")
public class IDInput extends Input {
	private JTextField textField;
	private JTextField textField_1;
	private ArrayList<Detector> detectors = new ArrayList<Detector>();
	private JLabel lblMessage;
	private JButton btnAdd;
	private ButtonGroup group;

	public IDInput() {
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);

		JSplitPane splitPane = new JSplitPane();
		splitPane.setResizeWeight(0.6);
		splitPane.setDividerSize(2);
		springLayout.putConstraint(SpringLayout.NORTH, splitPane, 0, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, splitPane, 0, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, splitPane, 0, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.EAST, splitPane, 0, SpringLayout.EAST, this);
		add(splitPane);

		group = new ButtonGroup();
		JPanel stationsPanel = new JPanel();
		splitPane.setLeftComponent(stationsPanel);
		stationsPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		JPanel controlsPanel = new JPanel();
		splitPane.setRightComponent(controlsPanel);
		SpringLayout sl_controlsPanel = new SpringLayout();
		controlsPanel.setLayout(sl_controlsPanel);

		textField = new JTextField();
		controlsPanel.add(textField);
		sl_controlsPanel.putConstraint(SpringLayout.WEST, textField, 10, SpringLayout.WEST, controlsPanel);
		sl_controlsPanel.putConstraint(SpringLayout.EAST, textField, -133, SpringLayout.WEST, textField_1);
		textField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnAdd.doClick(0);
			}
		});
		textField.setColumns(10);

		textField_1 = new JTextField();
		sl_controlsPanel.putConstraint(SpringLayout.NORTH, textField, 0, SpringLayout.NORTH, textField_1);
		sl_controlsPanel.putConstraint(SpringLayout.WEST, textField, 6, SpringLayout.EAST, textField_1);
		sl_controlsPanel.putConstraint(SpringLayout.EAST, textField, 85, SpringLayout.EAST, textField_1);
		sl_controlsPanel.putConstraint(SpringLayout.WEST, textField_1, 10, SpringLayout.WEST, controlsPanel);
		sl_controlsPanel.putConstraint(SpringLayout.EAST, textField_1, -95, SpringLayout.EAST, controlsPanel);
		textField_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				char[] chars = e.getActionCommand().toCharArray();
				for (char ch : chars) {
					try {
						Integer.parseInt(ch + "");
						btnAdd.doClick(0);
					} catch (Exception ex) {
						textField_1.setText(textField_1.getText().replace(ch, '\u0000'));
					}
				}
			}
		});
		controlsPanel.add(textField_1);
		textField_1.setColumns(10);

		btnAdd = new JButton("Add");
		sl_controlsPanel.putConstraint(SpringLayout.NORTH, btnAdd, 10, SpringLayout.NORTH, controlsPanel);
		sl_controlsPanel.putConstraint(SpringLayout.SOUTH, btnAdd, 35, SpringLayout.NORTH, controlsPanel);
		sl_controlsPanel.putConstraint(SpringLayout.NORTH, textField_1, 6, SpringLayout.SOUTH, btnAdd);
		sl_controlsPanel.putConstraint(SpringLayout.WEST, btnAdd, 10, SpringLayout.WEST, controlsPanel);
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Detector d = InfoUtils.getDetectorFromNameOrID(textField.getText(), textField_1.getText());
				if (d != null) {
					if (!detectors.contains(d)) {
						detectors.add(d);
						JRadioButton b = new JRadioButton(d.getName() + " #" + d.getId());
						b.setSelected(true);
						group.add(b);
						stationsPanel.add(b);
						revalidate();
						textField.setText("");
						textField_1.setText("");
					}
					else{
						lblMessage.setText("Station was already added.");
					}
				}
				else{
					lblMessage.setText("Station not found.");
				}
			}
		});
		controlsPanel.add(btnAdd);

		JButton btnRemoveSelected = new JButton("Remove");
		sl_controlsPanel.putConstraint(SpringLayout.WEST, btnRemoveSelected, 10, SpringLayout.WEST, controlsPanel);
		btnRemoveSelected.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Enumeration<AbstractButton> e = group.getElements();
				int id = 0;
				AbstractButton b = null;
				while(e.hasMoreElements()){
					b = e.nextElement();
					if(b.isSelected()){
						group.remove(b);
						id = Integer.parseInt(b.getText().split("#")[1]);
						}
					}
				Iterator<Detector> iter = detectors.iterator();
				while(iter.hasNext()){
					Detector ds = iter.next();
					if(ds.getId() == id){
						iter.remove();
						group.remove(b);
						stationsPanel.remove(b);
						revalidate();
						repaint();
					}
				}
			}
		});
		controlsPanel.add(btnRemoveSelected);

		JButton btnClear = new JButton("Clear");
		sl_controlsPanel.putConstraint(SpringLayout.SOUTH, btnRemoveSelected, -6, SpringLayout.NORTH, btnClear);
		sl_controlsPanel.putConstraint(SpringLayout.WEST, btnClear, 0, SpringLayout.WEST, textField_1);
		btnClear.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				group = new ButtonGroup();
				stationsPanel.removeAll();
				detectors.clear();
				revalidate();
				repaint();
			}
		});
		controlsPanel.add(btnClear);


		lblMessage = new JLabel("Add a detector");
		sl_controlsPanel.putConstraint(SpringLayout.SOUTH, btnClear, -6, SpringLayout.NORTH, lblMessage);
		sl_controlsPanel.putConstraint(SpringLayout.WEST, lblMessage, 0, SpringLayout.WEST, textField_1);
		sl_controlsPanel.putConstraint(SpringLayout.SOUTH, lblMessage, -10, SpringLayout.SOUTH, controlsPanel);
		lblMessage.setFont(new Font("Tahoma", Font.ITALIC, 11));
		controlsPanel.add(lblMessage);
	}
	
	@Override
	public Object getInfo() {
		return detectors;
	}
}

