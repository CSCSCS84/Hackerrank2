package cs.tilgungsplan.tilgungsplancalculator;

import java.math.BigDecimal;

/**
 * Class that provides all relevant informations for a Tilgungsplan
 * 
 * @author Christoph
 * 
 */

public class Tilgungsplangroessen {

	private BigDecimal darlehensbetrag;
	private BigDecimal sollzins;
	private BigDecimal percentFirstTilgung;
	private short zinsbindungInYears;
	private Tilgungsrhythmus tilgungsrhythmus;
	private RoundingRules roundingRules;

	public Tilgungsplangroessen(BigDecimal darlehensbetrag, BigDecimal sollzins, BigDecimal percentFirstTilgung,
			short zinsbindungInYears, Tilgungsrhythmus tilgungsrhythmus, RoundingRules roundingRules) {
		super();
		this.darlehensbetrag = darlehensbetrag;
		this.sollzins = sollzins;
		this.percentFirstTilgung = percentFirstTilgung;
		this.zinsbindungInYears = zinsbindungInYears;
		this.tilgungsrhythmus = tilgungsrhythmus;
		this.roundingRules = roundingRules;
	}

	public BigDecimal getDarlehensbetrag() {
		return darlehensbetrag;
	}

	public void setDarlehensbetrag(BigDecimal darlehensbetrag) {
		this.darlehensbetrag = darlehensbetrag;
	}

	public BigDecimal getSollzins() {
		return sollzins;
	}

	public void setSollzins(BigDecimal sollzins) {
		this.sollzins = sollzins;
	}

	public BigDecimal getPercentFirstTilgung() {
		return percentFirstTilgung;
	}

	public void setPercentFirstTilgung(BigDecimal percentFirstTilgung) {
		this.percentFirstTilgung = percentFirstTilgung;
	}

	public short getZinsbindungInYears() {
		return zinsbindungInYears;
	}

	public void setZinsbindungInYears(short zinsbindungInYears) {
		this.zinsbindungInYears = zinsbindungInYears;
	}

	public Tilgungsrhythmus getTilgungsrhythmus() {
		return tilgungsrhythmus;
	}

	public void setTilgungsrhythmus(Tilgungsrhythmus tilgungsrhythmus) {
		this.tilgungsrhythmus = tilgungsrhythmus;
	}

	public RoundingRules getRoundingRules() {
		return roundingRules;
	}

	public void setRoundingRules(RoundingRules roundingRules) {
		this.roundingRules = roundingRules;
	}

}
