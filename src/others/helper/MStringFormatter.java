package others.helper;

public class MStringFormatter {

	public static String firstLetter (String s) {
		if(s.isEmpty()) return "";
		String f = s.substring(0, 1);
		
		return f.toUpperCase();
	}
	
	public static String capetalize (String s) {
		if( s.isEmpty() ) return "";
		String[] split = s.split(" ");
		String temp = "";
		
		int iterate = 0;
		for(String as: split) {
			iterate++;
			String t = as.substring(0, 1).toUpperCase() + as.substring(1, as.length());
			temp += (iterate < as.length()) ? t + " " : t;
		}
		
		return temp;
	}
}
