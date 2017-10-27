import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class GridbagLayout extends JFrame{
	private final GridBagLayout layout;
	private boolean newFileFlag ;
	private final JLabel PokemonLabel;
	private final JTextField NameLabel;
	private final ImageIcon icon;
	private final GridBagConstraints NameGrid;
	private final GridBagConstraints PokemonGrid;
	private final JPanel PokemonPanel;
	
	public GridbagLayout() {
		super("¯«©_Ä_¨©¤j®v");
		layout = new GridBagLayout();
		setLayout(layout);
	    icon = new ImageIcon(getClass().getResource("small.png"));
	    PokemonLabel = new JLabel(icon);
	    PokemonPanel = new JPanel();
	    PokemonPanel.add(PokemonLabel);
		PokemonGrid = new GridBagConstraints();
		PokemonGrid.gridx = 0;
		PokemonGrid.gridy = 0;
		PokemonGrid.gridwidth = 1;
		PokemonGrid.gridheight = 1;
		PokemonGrid.weightx = 0;
		PokemonGrid.weighty = 0;
		PokemonGrid.fill = GridBagConstraints.NONE;
		PokemonGrid.anchor = GridBagConstraints.NORTH;		
		add(PokemonPanel,PokemonGrid);
		PokemonPanel.setBackground(Color.WHITE);
		NameLabel = new JTextField();
		NameGrid = new GridBagConstraints();		
		NameGrid.gridx = 0;
		NameGrid.gridy = 1;
		NameGrid.gridwidth = GridBagConstraints.REMAINDER;
		NameGrid.gridheight = 1;
		NameGrid.weightx = 100;
		NameGrid.weighty = 0;
		NameGrid.fill = GridBagConstraints.HORIZONTAL;
		NameGrid.anchor = GridBagConstraints.CENTER;
		add(NameLabel,NameGrid);
//		JTextField NameLabel2 = new JTextField();
//		GridBagConstraints NameGrid2 = new GridBagConstraints();		
//		NameGrid2.gridx = 1;
//		NameGrid2.gridy = 2;
//		NameGrid2.gridwidth = 1;
//		NameGrid2.gridheight = 1;
//		NameGrid2.weightx = 0;
//		NameGrid2.weighty = 0;
//		NameGrid2.fill = GridBagConstraints.NONE;
//		NameGrid2.anchor = GridBagConstraints.CENTER;
//		add(NameLabel2,NameGrid2);
		

	}

}
