import java.util.*;

class Student {
    int marks = 0;
}

interface Rule {
    void apply(Student s);
}

class AttendanceRule implements Rule {
    public void apply(Student s) {
        s.marks += 5;
    }
}

class DifficultyRule implements Rule {
    public void apply(Student s) {
        s.marks += 10;
    }
}

abstract class Evaluation {

    public final void evaluate(Student s) {
        theory(s);
        lab(s);
        applyRules(s);
        grade(s);
    }

    abstract void theory(Student s);

    abstract void lab(Student s);

    abstract void grade(Student s);

    List<Rule> rules = new ArrayList<>();

    void addRule(Rule r) {
        rules.add(r);
    }

    void applyRules(Student s) {
        for (Rule r : rules) {
            r.apply(s);
        }
    }
}

class BTechEvaluation extends Evaluation {

    void theory(Student s) {
        s.marks += 40;
    }

    void lab(Student s) {
        s.marks += 30;
    }

    void grade(Student s) {
        System.out.println("Total Marks: " + s.marks);
    }
}

public class Main {
    public static void main(String[] args) {

        Student student = new Student();

        Evaluation eval = new BTechEvaluation();
        eval.addRule(new AttendanceRule());
        eval.addRule(new DifficultyRule());

        eval.evaluate(student);
    }
}
