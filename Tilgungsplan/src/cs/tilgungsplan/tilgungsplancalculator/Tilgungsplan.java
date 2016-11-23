package cs.tilgungsplan.tilgungsplancalculator;

import java.util.LinkedList;

import cs.tilgungsplan.outputformat.OutputFormatTilgungsplaneintrag;

/**
 * Stores all Tilgungsplaneintraege in a List
 * 
 * @author christoph
 * 
 */
public class Tilgungsplan {

	private LinkedList<Tilgungsplaneintrag> tilgungsplaneintraege;

	public Tilgungsplan() {
		super();
		this.tilgungsplaneintraege = new LinkedList<>();
	}

	/**
	 * Adds a Tilgungsplaneintrag add the end of the list
	 * 
	 * @param tilgungsplaneintrag
	 */
	public void addTilgungsplaneintrag(Tilgungsplaneintrag tilgungsplaneintrag) {
		tilgungsplaneintraege.add(tilgungsplaneintrag);
	}

	/**
	 * Returns a string that contains all Tilgungsplaneintraege in the given format 
	 * @param outputFormat
	 * @return
	 */
	public String toString(OutputFormatTilgungsplaneintrag outputFormat) {
		StringBuffer tilgungsplan = new StringBuffer();
		for (Tilgungsplaneintrag tilgungsplaneintrag : tilgungsplaneintraege) {
			tilgungsplan.append(tilgungsplaneintrag.toString(outputFormat) + "\n");
		}
		return tilgungsplan.toString();
	}

	public LinkedList<Tilgungsplaneintrag> getTilgungsplaneintraege() {
		return tilgungsplaneintraege;
	}

	public void setTilgungsplaneintraege(LinkedList<Tilgungsplaneintrag> tilgungsplaneintraege) {
		this.tilgungsplaneintraege = tilgungsplaneintraege;
	}

}
