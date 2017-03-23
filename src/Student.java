import java.util.Vector;

public class Student {

    public final String NAME;
    public final String EMAIL;

    private Project enrolledIn;

    private Vector<Project> preferences;


    /**
     * Sets the NAME and EMAIL strings and initializes the class members with dummy values
     * @param NAME
     * @param EMAIL
     */
    Student (String NAME, String EMAIL) {
        this.NAME = new String(NAME);
        this.EMAIL = new String(EMAIL);
        this.enrolledIn = new Project("0", 0); // DUMMY PROJECT, MEANS THE STUDENT HAS NOT ENROLLED IN ANYTHING
        this.preferences = new Vector<>();
    }


    /**
     * Returns the EMAIL string of the class
     * @return
     */
    public String getEMAIL() {
        return EMAIL;
    }

    /**
     * Returns the NAME string of the class
     * @return
     */
    public String getNAME() {
        return NAME;
    }

    /**
     * Returns a reference to the Project object that the student is enrolled in
     * @return
     */
    public Project getEnrolledIn() {
        return enrolledIn;
    }

    /**
     * Returns the Vector of Project references in descending order of preference
     * @return
     */
    public Vector<Project> getPreferences() {
        return preferences;
    }


    /**
     * Sets the enrolledIn Project object to the given Project object
     * @param enrolledIn
     */
    public void setEnrolledIn(Project enrolledIn) {
        this.enrolledIn = enrolledIn;
    }

    /**
     * Sets the preferences Vector of Project objects for the Preferences object to the given Vector object
     * @param preferences
     */
    public void setPreferences(Vector<Project> preferences) {
        this.preferences = new Vector<>(preferences);
    }
}

