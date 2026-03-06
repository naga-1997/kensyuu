package ch15;

public class ch15_6 {
	public boolean isValidPlayerName(String name) {
		return name.matches("[A-Z][a-Z0-9]{7}");
	}
}
