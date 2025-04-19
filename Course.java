package project;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class Course {
	    String courseName;
	    List<Module> modules = new ArrayList<>();
	    List<Assignment> assignments = new ArrayList<>();
	}

class Module {
	    String moduleName;
	    String content;
	}

class Assignment {
	    String title;
	    Date deadline;
	    Student submittedBy;
	    String feedback;
	}
