import java.util.Vector;

public class Problem {

    private Vector<Student> students;
    private Vector<Project> projects;

    public final Integer STUDENT_NUMBER;
    public final Integer PROJECT_NUMBER;


    /**
     * Sets the maximum number of students and projects for the current instance of the problem
     * @param STUDENT_NUMBER
     * @param PROJECT_NUMBER
     */
    Problem (Integer STUDENT_NUMBER, Integer PROJECT_NUMBER) {
        this.students = new Vector<>();
        this.projects = new Vector<>();
        this.STUDENT_NUMBER = new Integer(STUDENT_NUMBER);
        this.PROJECT_NUMBER = new Integer(PROJECT_NUMBER);
    }


    /**
     * Returns the Vector of Projects in the current Problem
     * @return
     */
    public Vector<Project> getProjects() {
        return projects;
    }

    /**
     * Returns the Vector of Students in the current Problem
     * @return
     */
    public Vector<Student> getStudents() {
        return students;
    }


    /**
     *
     * @param projects
     */
    public void setProjects(Vector<Project> projects) {
        this.projects = projects;
    }

    /**
     * Sets the Vector of Students in the current Problem to the given Vector object
     * @param students
     */
    public void setStudents(Vector<Student> students) {
        this.students = students;
    }


    /**
     * Adds the given Project object to the Vector of participating students
     * @param project
     */
    public void addProject (Project project) {
        if (this.projects.size() < PROJECT_NUMBER)
            this.projects.addElement(project);
    }

    /**
     * Adds the given Student object to the Vector of participating students
     * @param student
     */
    public void addStudent (Student student) {
        if (this.students.size() < STUDENT_NUMBER)
            this.students.addElement(student);
    }


    /**
     * Parses the current contents of the instance to a sting for output and returns the string
     * @return
     */
    public String toString () {
        StringBuffer buff = new StringBuffer("");

        buff.append("PROBLEM\n\n");
        buff.append("students: " + this.STUDENT_NUMBER + "\t\t\t\tprojects: " + this.PROJECT_NUMBER + "\n\n");

        buff.append(" * Projects:\n");
        for (Project p : this.projects) {
            buff.append("\n\t * Project: " + p.getNAME() + "\n");
            buff.append("\t\t * Capacity: " + p.getCAPACITY() + "\n");
            buff.append("\t\t * Teacher preferences:");
            for (Student s : p.getPreferences()) {
                buff.append("  " + s.getNAME());
            }
            buff.append("\n");
        }

        buff.append("\n\n");

        buff.append(" * Students:\n");
        for (Student s : this.students) {
            buff.append("\n\t * Student: " + s.getNAME() + "\n");
            buff.append("\t\t * Email: " + s.getEMAIL() + "\n");
            buff.append("\t\t * Preferences:");
            for (Project p : s.getPreferences()) {
                buff.append("   " + p.getNAME());
            }
            buff.append("\n");
        }

        buff.append("\n\n");

        return new String(buff);
    }

    /**
     * Parses the current Student - Project assignation to a string for output and returns the string
     * @return
     */
    public String assignmentToString () {
        StringBuffer buff = new StringBuffer("");

        buff.append("Student - Project assignation:");

        for (Project p : this.projects) {
            buff.append("\n\n * Project: " + p.getNAME() + "\n");
            buff.append(" * Students:");
            for (Student s : p.getParticipating()) {
                buff.append("  " + s.getNAME());
            }
        }

        return new String(buff);
    }


    /**
     * Assigns the Students s to the Project p, setting the enrolledIn and participating members in the s and p objects
     * @param s
     * @param p
     */
    private void assignStudentToProject(Student s, Project p) {
            s.setEnrolledIn(p);
            p.addParticipatingStudent(s);
    }

    /**
     * Calculates the compatibility value of a certain Student - Project pair, returning it as a double value
     * @param stud
     * @param proj
     * @return
     */
    private double compatibility(Student stud, Project proj) {
        double score = 0.0;

        // COMPATIBILITY WILL BE COMPUTED AS AN OPTIMIZATION PROBLEM

        // IF A STUDENT/TEACHER DOES NOT HAVE A PREFERENCE FOR A PARTICULAR STUDENT/PROJECT
        // THEN THAT STUDENT/PROJECT WILL BE CONSIDERED THEIR LAST CHOICE

        // STUDENT PREFERENCE MATTERS MORE (2/3)
        if (stud.getPreferences().contains(proj))
            score += stud.getPreferences().indexOf(proj) * (-1) * (2.0 / 3.0);
        else
            score += stud.getPreferences().size() * (-1) * (2.0 / 3.0);

        // TEACHER PREFERENCES MATTER LESS (1/3)
        if (proj.getPreferences().contains(stud))
            score += proj.getPreferences().indexOf(stud) * (-1) * (1.0 / 3.0);
        else
            score += proj.getPreferences().size() * (-1) * (1.0 / 3.0);

        // THE MAXIMUM COMPATIBILITY SCORE WILL BE 100.0
        return score + 100.0;
    }

    /**
     * Returns a reference to the next Students that is not enrolled in anything(enrolledIn still has the initialized dummy value)
     * @return
     */
    private Student getNextStudent() {
        // RETURNS A REFERENCE TO THE NEXT STUDENT NOT ENROLLED IN A PROJECT
        for (Student s : this.students) {
            if (s.getEnrolledIn().getNAME().equals("0")) {
                return s;
            }
        }
        return new Student("0", "0");
    }

    /**
     * Returns true if there are still students not enrolled in any project, false otherwise
     * @return
     */
    private boolean unassignedStudents() {
        // RETURNS TRUE IF THERE ARE STILL STUDENTS NOT ENROLLED IN ANYTHING
        for (Student s : this.students) {
            if (s.getEnrolledIn().getNAME().equals("0")) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns true if there are still any free spots in any of the projects, false otherwise
     * @return
     */
    private boolean freeProjectSpots() {
        // RETURN TRUE IF THERE ARE STILL PROJECTS WITH FREE SPOTS LEFT
        for (Project p : this.projects) {
            if (p.getCAPACITY() > p.getParticipating().size()) {
                return true;
            }
        }
        return false;
    }

    /**
     * First attempt as a stable matching, preferred Projects, not a STABLE matching
     */
    private void assignNextStudentOld() {
        // WILL ASSIGN THE NEXT STUDENT TO THE BEST AVAILABLE PROJECT
        Student stud = getNextStudent(); // STORES A REFERENCE TO THE NEXT AVAILABLE STUDENT
        Project maxProject = new Project("0", 0);
        double maxScore = 0.0;

        for (Project proj : this.projects) {                             // ITERATING TROUGH PROJECTS
            if (proj.getCAPACITY() > proj.getParticipating().size()) {   // IF THERE IS STILL ROOM IN THE PROJECT
                if (maxScore < compatibility(stud, proj)) {              // IF THE PROJECT SEEMS LIKE THE BEST OPTION FOR THE STUDENT
                    maxProject = proj;                    // STORE THE NEW OPTIMAL PROJECT
                    maxScore = compatibility(stud, proj); // AND THE NEW COMPATIBILITY SCORE
                }
            }
        }

        if (maxProject.getNAME().equals("0") == false)  // A PROJECT WAS FOUND
            assignStudentToProject(stud, maxProject);
    }

    /**
     * Performs the assignation of the next best Student - Project pair
     */
    private void assignNextStudent() {
        // CREATES THE NEXT STUDENT - PROJECT ASSIGNATION USING THE MOST COMPATIBLE STUDENT - TEACHER COMBINATION
        Student maxStudent = new Student("0","0");
        Project maxProject = new Project("0", 0);
        Double  maxCompatibility = -100000.0;
        // ITERATE TROUGH ALL POSSIBLE STUDENT - PROJECT COMBINATIONS
        for (Student s : this.students) {
            for (Project p : this.projects) {
                if (s.getEnrolledIn().getNAME().equals("0")) {           // IF THE STUDENT IS NOT ENROLLED IN ANYTHING
                    if (p.getParticipating().size() < p.getCAPACITY()) { // IF THE PROJECT STILL HAS FREE SPOTS
                        if (compatibility(s, p) > maxCompatibility) {    // IF THIS IS THE BEST COMBINATION SO FAR
                            maxCompatibility = compatibility(s, p);      // STORE THE COMPATIBILITY AND THE STUDENT AND PROJECT
                            maxStudent = s;
                            maxProject = p;
                        }
                    }
                }
            }
        }
        assignStudentToProject(maxStudent,maxProject);
    }


    /**
     * First attempt as a stable matching, preferred Projects, not a STABLE matching
     */
    public void assignStudentsOld() {
        while (unassignedStudents() && freeProjectSpots()) { // WILL HALT IF THERE ARE NO MORE STUDENTS OR FREE SPOTS LEFT
            assignNextStudentOld();
        }
    }


    /**
     * Loops, assigning Students to Projects until there are either no Students, or free Project spots left
     */
    public void assignStudents() {
        while (unassignedStudents() && freeProjectSpots()) {
            assignNextStudent();
        }
    }
}
