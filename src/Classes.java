import java.util.LinkedList;

public class Classes {
	private String classname;

	private LinkedList<Section> sections = new LinkedList<Section>();
	
	  public Classes(String classname) {
		  //LinkedList<Section> sections = new LinkedList<Section>();
		  this.classname = classname;
		  Courses.addCourses(this);
	  }
	  
	  public void addSection(Section s) {
		  this.sections.add(s);
	  }
	  
	  public String getName() {
		  return classname;
	  }
	
}
