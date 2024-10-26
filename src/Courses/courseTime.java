package Courses;

public class courseTime {
    public int weekday, beginTime, endTime;
    public courseTime() {
        this(0, 0, 0);
    }
    public courseTime(int weekday, int beginTime, int endTime) {
        this.weekday = weekday;
        this.beginTime = beginTime;
        this.endTime = endTime;
    }

    public static courseTime extractTime(String time) {
        String[] timeArray = time.split("_");
        courseTime ret = new courseTime();
        ret.weekday = Integer.parseInt(timeArray[0]);
        String[] timeArray2 = timeArray[1].split("-");
        ret.beginTime = Integer.parseInt(timeArray2[0]);
        ret.endTime = Integer.parseInt(timeArray2[1]);
        return ret;
    }

}
