public class Section {
	private String classes;
	private String tag;
	private String days;
	private int starttime;
	private int endtime;

	  public Section(String classes, String tag, String d, int start, int end) {
		  this.classes = classes;
		  this.tag = tag;
		  this.days = d;
		  this.starttime = start;
		  this.endtime = end;
		  for (int i=0; i<Courses.getCourses().size(); i++) {
			  Classes c = Courses.getCourses().get(i);
			  if (c.getName() == classes) {
				  c.addSection(this);
			  }
		  }
	  }
	  
	  public String getDays() {
		  return days;
	  }
	  
	  public String getTag() {
		  return tag;
	  }
	  
	  public String getClasses() {
		  return classes;
	  }
	  
	  public int getStart() {
		  return starttime;
	  }	  
	  
	  public int getEnd() {
		  return endtime;
	  }	  
	  
	  
}
