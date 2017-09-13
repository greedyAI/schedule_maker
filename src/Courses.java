import java.util.LinkedList;

public class Courses {

	private static LinkedList<Classes> classes = new LinkedList<Classes>();
	
	  public Courses() {
		  //LinkedList<Classes> classes = new LinkedList<Classes>();
	  }
	  
	  public static LinkedList<Classes> getCourses() {
		  return classes;
	  }
	  	  
	  public static void addCourses(Classes c) {
		  classes.add(c);
	  }
	  
	  public static boolean courseExists(String c) {
		for (Classes d: classes) {
			if (d.getName().equals(c)) {
				return true;
			}
		}
		  return false;
	  }
	  
}
