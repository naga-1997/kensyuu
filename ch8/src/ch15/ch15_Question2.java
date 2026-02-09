package ch15;

public class ch15_Question2 {
	public String concatPath(String folder,String file) {
		if (!folder.endsWith("¥¥")) {
			folder += "¥¥";
		}
		return folder + file;
	}

}
