public class ProjectAllocation {

    /**
     * The Problem instance will be populated with some default data
     * @param instance
     */
    public static void populate(Problem instance) {

        // STUDENTS                                                                   elementAt(   )
        instance.addStudent(new Student("Ionel", "ionel@info.uaic.ro"));       //   0
        instance.addStudent(new Student("Gigel", "gigel@info.uaic.ro"));       //   1
        instance.addStudent(new Student("Cornel", "cornel@info.uaic.ro"));     //   2
        instance.addStudent(new Student("Strambel", "strambel@info.uaic.ro")); //   3

        // PROJECTS/TEACHERS                                    elementAt(   )
        instance.addProject(new Project("Java", 2));   //   0
        instance.addProject(new Project("Python", 1)); //   1
        instance.addProject(new Project("C++", 1));    //   2

        // STUDENT PREFERENCES
        //   S1
        instance.getStudents().elementAt(0).getPreferences().addElement(instance.getProjects().elementAt(0));
        instance.getStudents().elementAt(0).getPreferences().addElement(instance.getProjects().elementAt(1));
        instance.getStudents().elementAt(0).getPreferences().addElement(instance.getProjects().elementAt(2));
        //   S2
        instance.getStudents().elementAt(1).getPreferences().addElement(instance.getProjects().elementAt(0));
        instance.getStudents().elementAt(1).getPreferences().addElement(instance.getProjects().elementAt(2));
        instance.getStudents().elementAt(1).getPreferences().addElement(instance.getProjects().elementAt(1));
        //   S3
        instance.getStudents().elementAt(2).getPreferences().addElement(instance.getProjects().elementAt(0));
        //   S4
        instance.getStudents().elementAt(3).getPreferences().addElement(instance.getProjects().elementAt(2));
        instance.getStudents().elementAt(3).getPreferences().addElement(instance.getProjects().elementAt(1));
        instance.getStudents().elementAt(3).getPreferences().addElement(instance.getProjects().elementAt(0));

        // TEACHER PREFERENCES
        //   P1
        instance.getProjects().elementAt(0).getPreferences().addElement(instance.getStudents().elementAt(2));
        instance.getProjects().elementAt(0).getPreferences().addElement(instance.getStudents().elementAt(0));
        instance.getProjects().elementAt(0).getPreferences().addElement(instance.getStudents().elementAt(1));
        instance.getProjects().elementAt(0).getPreferences().addElement(instance.getStudents().elementAt(3));
        //   P2
        instance.getProjects().elementAt(1).getPreferences().addElement(instance.getStudents().elementAt(0));
        instance.getProjects().elementAt(1).getPreferences().addElement(instance.getStudents().elementAt(1));
        instance.getProjects().elementAt(1).getPreferences().addElement(instance.getStudents().elementAt(2));
        instance.getProjects().elementAt(1).getPreferences().addElement(instance.getStudents().elementAt(3));
        //   P3
        instance.getProjects().elementAt(2).getPreferences().addElement(instance.getStudents().elementAt(3));
        instance.getProjects().elementAt(2).getPreferences().addElement(instance.getStudents().elementAt(2));
        instance.getProjects().elementAt(2).getPreferences().addElement(instance.getStudents().elementAt(0));
        instance.getProjects().elementAt(2).getPreferences().addElement(instance.getStudents().elementAt(1));
    }


    public static void main(String[] args) {

        Problem instance = new Problem(4, 3);

        populate(instance);

        System.out.println(instance.toString());

        instance.assignStudents();

        System.out.println("=====================================================================================\n\n");

        System.out.println(instance.assignmentToString());
    }
}
