package green.ui.mainWindow.component;

import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import java.awt.Font;

@SuppressWarnings("serial")
public class Overview extends JPanel {
	public Overview() {
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		
		JLabel lblHisparcOverview = new JLabel("HiSPARC Overview");
		lblHisparcOverview.setFont(new Font("Tahoma", Font.PLAIN, 38));
		springLayout.putConstraint(SpringLayout.NORTH, lblHisparcOverview, 10, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblHisparcOverview, 10, SpringLayout.WEST, this);
		add(lblHisparcOverview);
		
		JMultiLineLabel overviewText = new JMultiLineLabel();
		springLayout.putConstraint(SpringLayout.SOUTH, overviewText, -200, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.EAST, overviewText, 0, SpringLayout.EAST, this);
		overviewText.setText("The aim of the HiSPARC project is to provide a network of detectors so that we can effectively monitor and analyse earthbound cosmic radiation. \r\nThese incoming particles consist of mostly protons, but can also be electrons or even heavier atomic nuclei. These primary particles will inevitably collide with the nuclei in our atmosphere to split into a vast shower of secondary particles. There are two variations of shower: electromagnetic and hadronic. Electromagnetic showers consist of a repeating sequence of processes known as pair production and Bremsstrahlung. Hadronic showers take longer to develop and consist of a chain of various unstable particles that decay into muons and muon neutrinos.\r\nThese muons will travel to the ground at just under the speed of light (named \u2018c\u2019), and will hopefully hit the scintillator of a HiSPARC detector (of which there are two or four). The charged particle will cause a flash of light in the scintillator, which is converted into an electrical signal by the photomultiplier tube (pulse height, or the energy of the particle, is determined by the intensity of this flash of light). The hit is only regarded as an event if particles are detected on both/all scintillators at almost exactly the same time.\r\nUsing multiple detectors we can calculate the angle of the incoming particle, providing that they all detect the same event. An event such as this is known as a coincidence. Using the time delays (in nanoseconds) between detectors we can determine the orientation of the sheet of secondary particles and therefore the angle of the initial particle.\r\n\r\nNote: This program is neither fully functional nor complete, but should work in most circumstances");
		//Woohooo!
		springLayout.putConstraint(SpringLayout.NORTH, overviewText, 35, SpringLayout.SOUTH, lblHisparcOverview);
		springLayout.putConstraint(SpringLayout.WEST, overviewText, 0, SpringLayout.WEST, lblHisparcOverview);
		add(overviewText);
	}
}
