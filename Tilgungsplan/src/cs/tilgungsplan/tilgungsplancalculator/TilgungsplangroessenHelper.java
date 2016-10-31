package cs.tilgungsplan.tilgungsplancalculator;

import java.math.BigDecimal;

/**
 * Class that provides all relevant informations for calculating a Tilgungsplan with a fixed rate
 * 
 * @author Christoph
 * 
 */
public class TilgungsplangroessenHelper {

	private BigDecimal rate;
	private BigDecimal sollzinsPerPeriod;
	private int laufzeitInPeriods;
	private Tilgungsrhythmus tilgungsrhythmus;
	private RoundingRules roundingRules;

	public TilgungsplangroessenHelper(BigDecimal rate, BigDecimal sollzinsPerPeriod, int laufzeitInPeriods, Tilgungsrhythmus tilgungsrhythmus,
			RoundingRules roundingRules) {
		super();
		this.rate = rate;
		this.sollzinsPerPeriod = sollzinsPerPeriod;
		this.laufzeitInPeriods = laufzeitInPeriods;
		this.tilgungsrhythmus = tilgungsrhythmus;
		this.roundingRules = roundingRules;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	public BigDecimal getSollzinsPerPeriod() {
		return sollzinsPerPeriod;
	}

	public void setSollzinsPerPeriod(BigDecimal sollzinsPerPeriod) {
		this.sollzinsPerPeriod = sollzinsPerPeriod;
	}

	public int getLaufzeit() {
		return laufzeitInPeriods;
	}

	public void setLaufzeit(int laufzeit) {
		this.laufzeitInPeriods = laufzeit;
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
