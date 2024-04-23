import java.util.ArrayList;
import java.util.Collections;

public class GradeList {
    private final ArrayList<Double> gradeList;
    public GradeList() {
        this.gradeList = new ArrayList<Double>();
    }
    public void addGrade(double grade) {
        this.gradeList.add(grade);
    }
    public boolean isEmpty() {
        return this.gradeList.isEmpty();
    }
    public double getAverageGrade() {
        double sum = 0;
        for (double grade : gradeList) {
            sum += grade;
        }
        return sum / gradeList.size();
    }
    public double getHighestGrade() {
        return Collections.max(this.gradeList);
    }
    public double getLowestGrade() {
        return Collections.min(this.gradeList);
    }
}
