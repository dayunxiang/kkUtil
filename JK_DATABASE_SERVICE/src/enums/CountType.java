package enums;

public enum CountType {

	RESAVE, AVERAGE, CUMULATIVE, CUT, GRADIENT, TIME, ID, ADD, SUB, MUL, DIV;

	private int interval;

	public int getInterval() {
		return interval;
	}

	public void setInterval(int interval) {
		this.interval = interval;
	}

}
