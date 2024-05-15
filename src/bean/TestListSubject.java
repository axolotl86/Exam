package bean;

import java.io.Serializable;
import java.util.Map;

public class TestListSubject implements Serializable {

    private int entYear;
    private String studentNo;
    private String studentName;
    private String classNum;
    private Map<Integer, Integer> points;

    public TestListSubject() {
    }

    public int getEntYear() {
        return entYear;
    }

    public void setEntYear(int entYear) {
        this.entYear = entYear;
    }

    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getClassNum() {
        return classNum;
    }

    public void setClassNum(String classNum) {
        this.classNum = classNum;
    }

    public Map<Integer, Integer> getPoints() {
        return points;
    }

    public void setPoints(Map<Integer, Integer> points) {
        this.points = points;
    }
    public Integer getPoint(int key) {
        return points.get(key);
    }

    public void putPoint(int value, int key) {
    	Integer integerValue = value;
    	Integer integerKey = key;
        // キーと値をMapに追加し、以前の値を出力
        Integer previousValue = points.put(integerKey, integerValue);
        System.out.println("Added: Key = " + integerValue + ", Value = " + integerKey);
        System.out.println("Previous value for this key was: " + previousValue);

        // Mapの内容を表示
        for (Map.Entry<Integer, Integer> entry : points.entrySet()) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }
    }

}
