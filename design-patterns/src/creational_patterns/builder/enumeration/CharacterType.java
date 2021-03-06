package creational_patterns.builder.enumeration;

public enum CharacterType {
	Goodness, Neutral, Evil;
	@Override
	public String toString() {
		switch (this) {
		case Goodness:
			return "善良";
		case Neutral:
			return "中立";
		case Evil:
			return "邪恶";

		default:
			return "error";
		}
	}
}
