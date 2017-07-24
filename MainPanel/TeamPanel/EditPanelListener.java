package TeamPanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;

import FixedPanel.FixedPanel;

public class EditPanelListener implements MouseListener{

	FixedPanel fpanelREF;
	JLabel label;
	public EditPanelListener(FixedPanel f,JLabel j){
		this.fpanelREF=f;
		this.label=j;
	}
	
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		label.setIcon(TeamImageLoader.editteamPressed);
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		label.setIcon(TeamImageLoader.editteam);
		fpanelREF.teampanelREF.showEditPanel();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
