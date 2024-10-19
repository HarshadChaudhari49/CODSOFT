import java.util.*;

// Class to represent a course
class Course {
    private String courseCode;
    private String title;
    private String description;
    private int capacity;
    private String schedule;
    private List<String> registeredStudents;

    public Course(String courseCode, String title, String description, int capacity, String schedule) {
        this.courseCode = courseCode;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
        this.registeredStudents = new ArrayList<>();
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getSchedule() {
        return schedule;
    }

    public int getAvailableSlots() {
        return capacity - registeredStudents.size();
    }

    public boolean registerStudent(String studentId) {
        if (getAvailableSlots() > 0) {
            registeredStudents.add(studentId);
            return true;
        } else {
            return false;
        }
    }

    public boolean removeStudent(String studentId) {
        return registeredStudents.remove(studentId);
    }

    public boolean isStudentRegistered(String studentId) {
        return registeredStudents.contains(studentId);
    }
}

// Class to represent a student
class Student {
    private String studentId;
    private String name;
    private List<Course> registeredCourses;

    public Student(String studentId, String name) {
        this.studentId = studentId;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    public String getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public List<Course> getRegisteredCourses() {
        return registeredCourses;
    }

    public void registerCourse(Course course) {
        if (course.registerStudent(studentId)) {
            registeredCourses.add(course);
            System.out.println("Successfully registered for the course: " + course.getTitle());
        } else {
            System.out.println("Registration failed. The course is full.");
        }
    }

    public void dropCourse(Course course) {
        if (registeredCourses.remove(course)) {
            course.removeStudent(studentId);
            System.out.println("Successfully dropped the course: " + course.getTitle());
        } else {
            System.out.println("You are not registered for this course.");
        }
    }
}

// Class to represent the course registration system
class CourseRegistrationSystem {
    private List<Course> courses;
    private List<Student> students;
    private Scanner scanner;

    public CourseRegistrationSystem() {
        this.courses = new ArrayList<>();
        this.students = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void listCourses() {
        System.out.println("\n--- Available Courses ---");
        for (Course course : courses) {
            System.out.println("Course Code: " + course.getCourseCode());
            System.out.println("Title: " + course.getTitle());
            System.out.println("Description: " + course.getDescription());
            System.out.println("Schedule: " + course.getSchedule());
            System.out.println("Available Slots: " + course.getAvailableSlots());
            System.out.println();
        }
    }

    public Course findCourseByCode(String courseCode) {
        for (Course course : courses) {
            if (course.getCourseCode().equalsIgnoreCase(courseCode)) {
                return course;
            }
        }
        return null;
    }

    public Student findStudentById(String studentId) {
        for (Student student : students) {
            if (student.getStudentId().equalsIgnoreCase(studentId)) {
                return student;
            }
        }
        return null;
    }

    public void registerStudentForCourse() {
        System.out.print("Enter Student ID: ");
        String studentId = scanner.next();
        Student student = findStudentById(studentId);

        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.print("Enter Course Code to Register: ");
        String courseCode = scanner.next();
        Course course = findCourseByCode(courseCode);

        if (course == null) {
            System.out.println("Course not found.");
            return;
        }

        student.registerCourse(course);
    }

    public void dropStudentFromCourse() {
        System.out.print("Enter Student ID: ");
        String studentId = scanner.next();
        Student student = findStudentById(studentId);

        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.print("Enter Course Code to Drop: ");
        String courseCode = scanner.next();
        Course course = findCourseByCode(courseCode);

        if (course == null) {
            System.out.println("Course not found.");
            return;
        }

        student.dropCourse(course);
    }

    public void start() {
        int choice;
        do {
            System.out.println("\n--- Course Registration System ---");
            System.out.println("1. List Available Courses");
            System.out.println("2. Register for a Course");
            System.out.println("3. Drop a Course");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    listCourses();
                    break;
                case 2:
                    registerStudentForCourse();
                    break;
                case 3:
                    dropStudentFromCourse();
                    break;
                case 4:
                    System.out.println("Thank you for using the Course Registration System. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 4);
    }
}

// Main class to run the course registration system
public class CourseRegistrationApp {
    public static void main(String[] args) {
        CourseRegistrationSystem system = new CourseRegistrationSystem();

        // Add some courses to the system
        system.addCourse(new Course("CS101", "Introduction to Computer Science", "Basic concepts of computer science", 3, "Mon & Wed 10:00-11:30 AM"));
        system.addCourse(new Course("MATH201", "Calculus I", "Introduction to calculus", 2, "Tue & Thu 9:00-10:30 AM"));
        system.addCourse(new Course("PHY101", "Physics I", "Fundamentals of physics", 3, "Mon & Wed 1:00-2:30 PM"));

        // Add some students to the system
        system.addStudent(new Student("S001", "Alice"));
        system.addStudent(new Student("S002", "Bob"));
        system.addStudent(new Student("S003", "Charlie"));

        // Start the registration system
        system.start();
    }
}
