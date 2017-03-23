import java.util.Vector;

public class Project {

    public final String NAME;
    public final Integer CAPACITY;

    private Vector<Student> participating;

    private Vector<Student> preferences;


    /**
     * Sets the NAME and CAPACITY members of the class to the values of the given parameters
     * @param NAME
     * @param CAPACITY
     */
    Project (String NAME, Integer CAPACITY) {
        this.NAME = new String(NAME);
        this.CAPACITY = new Integer(CAPACITY);
        this.participating = new Vector<>();
        this.preferences = new Vector<>();
    }


    /**
     * Returns the NAME string of the class
     * @return
     */
    public String getNAME() {
        return NAME;
    }

    /**
     * Returns the CAPACITY value of the class
     * @return
     */
    public Integer getCAPACITY() {
        return CAPACITY;
    }

    /**
     * Returns the Vector of Student references for the participating students
     * @return
     */
    public Vector<Student> getParticipating() {
        return participating;
    }

    /**
     * Returns the Vector of Student references in descending order of preference
     * @return
     */
    public Vector<Student> getPreferences() {
        return preferences;
    }


    /**
     * Sets the preference list of Students to the given Vector object
     * @param preferences
     */
    public void setPreferences(Vector<Student> preferences) {
        this.preferences = preferences;
    }

    /**
     * Sets the list of enrolled students to the given Vector object
     * @param participating
     */
    public void setParticipating(Vector<Student> participating) {
        this.participating = participating;
    }


    /**
     * Adds the given Student object to the Vector of participating students
     * @param stud
     */
    public void addParticipatingStudent(Student stud) {
        this.participating.addElement(stud);
    }
}
