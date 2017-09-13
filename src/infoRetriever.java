	import java.io.BufferedReader;
	import java.io.FileReader;
	import java.io.IOException;
	import java.io.InputStream;
	import java.io.InputStreamReader;
	import java.net.URL;
	import java.net.URLConnection;

	public class infoRetriever {		
	    public static void main(String[] args) throws IOException {

	    	classList d = new classList(new TokenScanner(new FileReader("CourseList")));
	    	
	    	for (int i=0; i<d.getNumWords(); i++) {
	    	boolean coursefound = false;
	    	boolean recitation = false;
	    	boolean courseexists = false;
	    	String course = d.getElement(i);
	    	String classes = null;
	    	String tag = null;
	    	String coursetype = null;
	    	String days = null;
	    	int starttime  = 0;
	    	int endtime  = 0;
	    		for (int j=0; j<course.length(); j++) {
	    			if (Character.isLetter(course.charAt(j))) {
	    				coursetype = course.substring(0, j+1);
	    				coursetype = coursetype.toLowerCase();
	    			} else {
	    				break;
	    			}
	    		}
	    	URL url = null;	

	    	if (d.getSemester().toLowerCase().equals("fall") || d.getSemester().toLowerCase().equals("spring")) {
	    		url = new URL("http://www.upenn.edu/registrar/roster/" + coursetype + ".html");
	    	} else {
	    		url = new URL("http://www.upenn.edu/registrar/s-timetable/" + coursetype + ".html");	    		
	    	}

	        URLConnection con = url.openConnection();
	        InputStream is =con.getInputStream();

	        BufferedReader br = new BufferedReader(new InputStreamReader(is));

	        String line = null;

	        while ((line = br.readLine()) != null) {
	        	if (line.length()>=3) {
	        		if (line.substring(0, 3).equals("<p>")) {
	        			line = line.substring(3);
	        			line = line.trim();
	        		}
	        		if (line.length()>=coursetype.length()) {
	        			if (line.substring(0, coursetype.length()).equals(coursetype.toUpperCase())) {
	        			line = line.substring(coursetype.length());	
	        			line = line.trim();
	        			}	        			
	        		}
	        		if (line.length()>=1) {
	        			if (line.substring(0, 1).equals("-")) {
	        				line = line.substring(1);
		        			line = line.trim();
	        			}
	        		}
	        		
	        		if (line.length()>=course.length()-coursetype.length()) {
		        		//System.out.println(course.substring(coursetype.length(), course.length()));
		        		//System.out.println(line);
		        		//System.out.println(Courses.getCourses().size());
		        		//System.out.println(coursefound);
		        		//System.out.println(line.substring(0, course.length()-coursetype.length()));
	        			if (course.substring(coursetype.length(), course.length()).equals(line.substring(0, course.length()-coursetype.length()))) {
	            			//System.out.println("cdcdcdcd");
	        				coursefound = true;
	        				} else if (coursefound == true && line.substring(0, 1).equals(" ") && Character.isDigit(line.charAt(1))) {
	        						line = line.substring(1);
	        						line = line.replaceAll("-", " ");
	        						line = line.replaceAll(":", "");	        						
	        						String[] parts = line.split(" ");
	        						tag = course+ "-" +parts[0]+ "-" + parts[1];
	        						classes = course+ "-" + parts[1];
	        						//System.out.println(parts[1]);
	        						if (parts[1].equals("LEC") || parts[1].equals("SEM")) {
	        							days = parts[2];
	        							if (parts[4].substring(parts[4].length()-2, parts[4].length()).equals("PM")) {
	        								//System.out.println("abababab");
	        								parts[4] = parts[4].substring(0, parts[4].length()-2);
	        								//System.out.println(parts[3]);
	        								//System.out.println(parts[4]);
	        								if (Integer.parseInt(parts[3]) > Integer.parseInt(parts[4])) {
	        									if (parts[3].length()>=3) {
	        									starttime = Integer.parseInt(parts[3]);	        										
	        									} else {
		        								starttime = Integer.parseInt(parts[3])*100;	        											        										
	        									}
	        									if (parts[4].length()>=3) {
	        									endtime = Integer.parseInt(parts[4])+1200;	        										
	        									} else {
	        									endtime = (Integer.parseInt(parts[4])*100)+1200;	
	        									}
	        								} else {
	        									if (parts[3].length()>=3) {
	        									starttime = Integer.parseInt(parts[3])+1200;	        										
	        									} else {
		        								starttime = Integer.parseInt(parts[3])*100+1200;	        											        										
	        									}
	        									if (parts[4].length()>=3) {
	        									endtime = Integer.parseInt(parts[4])+1200;	        										
	        									} else {
	        									endtime = (Integer.parseInt(parts[4])*100)+1200;	
	        									}	        									
	        								}
	        							} else if (parts[4].substring(parts[4].length()-2, parts[4].length()).equals("AM") || parts[4].substring(parts[4].length()-4, parts[4].length()).equals("NOON")) {
        									//System.out.println("blahblahblah");
        									if (parts[4].substring(parts[4].length()-2, parts[4].length()).equals("AM")) {
        									parts[4] = parts[4].substring(0, parts[4].length()-2);	
        									} else if (parts[4].substring(parts[4].length()-4, parts[4].length()).equals("NOON")) {
            								parts[4] = parts[4].substring(0, parts[4].length()-4);	
            								}
        									//System.out.println(parts[3]);
	        								//System.out.println(parts[4]);
	        								if (parts[3].length()>=3) {
	        									starttime = Integer.parseInt(parts[3]);	        										
	        									} else {
		        								starttime = Integer.parseInt(parts[3])*100;	        											        										
	        									}
	        									if (parts[4].length()>=3) {
	        									endtime = Integer.parseInt(parts[4]);	        										
	        									} else {
	        									endtime = (Integer.parseInt(parts[4])*100);	
	        									}
	        							}
	        							if (Courses.getCourses().size()>=1) {
	        								if (Courses.courseExists(classes) == true) {
	      	        							new Section(classes, tag, days, starttime, endtime);
	        								} else {
	        									new Classes(classes);
		      	        						new Section(classes, tag, days, starttime, endtime);
	        								}	
		        						} else {
	      									new Classes(classes);
	      									new Section(classes, tag, days, starttime, endtime);	        								
		        						}
	        							//System.out.println(classes);
	        							//System.out.println(tag);
	        							//System.out.println(days);
	        							//System.out.println(starttime);
	        							//System.out.println(endtime);
	        						} else if (parts[1].equals("REC") || parts[1].equals("LAB")) {
	        							recitation = true;
	        							days = parts[2];
	        							if (parts[4].substring(parts[4].length()-2, parts[4].length()).equals("PM")) {
	        								parts[4] = parts[4].substring(0, parts[4].length()-2);
	        								if (Integer.parseInt(parts[3]) > Integer.parseInt(parts[4])) {
	        									if (parts[3].length()>=3) {
	        									starttime = Integer.parseInt(parts[3]);	        										
	        									} else {
		        								starttime = Integer.parseInt(parts[3])*100;	        											        										
	        									}
	        									if (parts[4].length()>=3) {
	        									endtime = Integer.parseInt(parts[4])+1200;	        										
	        									} else {
	        									endtime = (Integer.parseInt(parts[4])*100)+1200;	
	        									}
	        								} else {
	        									if (parts[3].length()>=3) {
	        									starttime = Integer.parseInt(parts[3])+1200;	        										
	        									} else {
		        								starttime = Integer.parseInt(parts[3])*100+1200;	        											        										
	        									}
	        									if (parts[4].length()>=3) {
	        									endtime = Integer.parseInt(parts[4])+1200;	        										
	        									} else {
	        									endtime = (Integer.parseInt(parts[4])*100)+1200;	
	        									}	        									
	        								}
	        							} else if (parts[4].substring(parts[4].length()-2, parts[4].length()).equals("AM") || parts[4].substring(parts[4].length()-4, parts[4].length()).equals("NOON")) {
	        								if (parts[4].substring(parts[4].length()-2, parts[4].length()).equals("AM")) {
        									parts[4] = parts[4].substring(0, parts[4].length()-2);	
        									} else if (parts[4].substring(parts[4].length()-4, parts[4].length()).equals("NOON")) {
            								parts[4] = parts[4].substring(0, parts[4].length()-4);	
            								}	        								
	        								if (parts[3].length()>=3) {
	        									starttime = Integer.parseInt(parts[3]);	        										
	        									} else {
		        								starttime = Integer.parseInt(parts[3])*100;	        											        										
	        									}
	        									if (parts[4].length()>=3) {
	        									endtime = Integer.parseInt(parts[4]);	        										
	        									} else {
	        									endtime = (Integer.parseInt(parts[4])*100);	
	        									}
	        							}
	        							if (Courses.getCourses().size()>=1) {
	        								if (Courses.courseExists(classes) == true) {
	      	        							new Section(classes, tag, days, starttime, endtime);
	        								} else {
	        									new Classes(classes);
		      	        						new Section(classes, tag, days, starttime, endtime);
	        								}	
		        						} else {
	      									new Classes(classes);
	      									new Section(classes, tag, days, starttime, endtime);	        								
		        						}
	        							//System.out.println(classes);
		        						//System.out.println(tag);
		        						//System.out.println(days);
		        						//System.out.println(starttime);
		        						//System.out.println(endtime); 	        							
	        						}
	        			} else if (Character.isDigit(line.charAt(0)) && Character.isDigit(line.charAt(1)) && Character.isDigit(line.charAt(2))) {
	        				coursefound = false;
	        			}
	        		}	        		
	        	}
	        }
	    	}
	    }
}
