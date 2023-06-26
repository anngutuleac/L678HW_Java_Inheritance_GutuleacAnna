public class StudentsManage {
    public static void main(String args[]) {
        String student1FirstName = "Анна";
        String student1LastName = "Гуцуляк";
        int student1CourseMark = 8;
        MasterStudent masterStudent = new MasterStudent(student1FirstName, student1LastName, student1CourseMark);

        String student2FirstName = "Виктор";
        String student2LastName = "Тихонов";
        int student2CourseMark = 9;
        BachelorStudent bachelorStudent = new BachelorStudent(student2FirstName, student2LastName, student2CourseMark);

        String courseName = "Математика";
        int courseDuration = 5;
        Course course = new Course(courseName, courseDuration);

        course.report();

        course.addStudent(masterStudent);
        System.out.println("===========================");
        course.studentsReport();
        course.avgMarkReport();

        course.addStudent(bachelorStudent);
        System.out.println("===========================");
        course.studentsReport();
        course.avgMarkReport();

        course.removeStudent(0);
        System.out.println("===========================");
        course.studentsReport();
        course.avgMarkReport();
    }
}

class StudentsArray {
    private Student arr[];
    private int count;

    public StudentsArray(int length) {
        arr = new Student[length];
    }

    Student[] getStudents() {
        return arr;
    }

    void add(Student student) {
        if (arr.length == count) {
            Student newArr[] = new Student[2 * count];
            for (int i = 0; i < count; i++) {
                newArr[i] = arr[i];
            }
            arr = newArr;
        }
        arr[count++] = student;
    }

    void remove(int index) {
        if (index < 0 || index >= arr.length) { return; }
        Student newArr[] = new Student[arr.length - 1];
        for (int i = 0, k = 0; i < arr.length; i++) {
            if (i == index) { continue; }
            newArr[k++] = arr[i];
        }
        arr = newArr;
        count = arr.length;
    }
}

class Course {
    String name;
    int duration;
    private StudentsArray studentsArray;

    public Course(String name, int duraton) throws IllegalArgumentException {
        if (name.length() == 0) { throw new IllegalArgumentException("Название курса не должно быть пустым!"); }
        if (duraton <= 0) { throw new IllegalArgumentException("Продолжительность курса должна быть положительным числом!"); }
        this.name = name;
        this.duration = duraton;
        studentsArray = new StudentsArray(1);
    }

    void addStudent(Student student) {
        studentsArray.add(student);
    }

    void removeStudent(int index) {
        studentsArray.remove(index);
    }

    void report() {
        System.out.println("Название курса: " + name);
        System.out.println("Продолжительность курса: " + duration);
    }

    void studentsReport() {
        Student arr[] = studentsArray.getStudents();
        for (int i = 0; i < arr.length; i++) {
            Student student = arr[i];
            student.report(i + 1);
        }
    }

    void avgMarkReport() {
        Student arr[] = studentsArray.getStudents();
        double sum = 0;
        for (int i = 0; i < arr.length; i++) {
            Student student = arr[i];
            sum += student.courseMark;
        }
        System.out.println("Средняя оценка по группе: " + sum / arr.length);
    }
}

abstract class Student {
    String firstName, lastName;
    String type;
    int courseMark;

    void report(int index) {
        System.out.println(index + ". " + "Студент: " + lastName + " " + firstName);
        System.out.println("Тип: " + type);
    }

    public Student(String firstName, String lastName, String type, int courseMark) throws IllegalArgumentException {
        if (firstName.length() == 0) { throw new IllegalArgumentException("Имя студента не должно быть пустым!"); }
        if (lastName.length() == 0) { throw new IllegalArgumentException("Фамилия студента не должна быть пустой!"); }
        if (type.length() == 0) { throw new IllegalArgumentException("Тип студента не должен быть пустым!"); }
        if (courseMark <= 0) { throw new IllegalArgumentException("Оценка студента должна быть положительной!"); }
        this.firstName = firstName;
        this.lastName = lastName;
        this.type = type;
        this.courseMark = courseMark;
    }
}

class MasterStudent extends Student {
    public MasterStudent(String firstName, String lastName, int courseMark) throws IllegalArgumentException {
        super(firstName, lastName, "Мастер", courseMark);
    }
}

class BachelorStudent extends Student {
    public BachelorStudent(String firstName, String lastName, int courseMark) throws IllegalArgumentException {
        super(firstName, lastName, "Бакалавр", courseMark);
    }
}
