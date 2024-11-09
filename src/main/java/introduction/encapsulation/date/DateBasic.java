package introduction.encapsulation.date;

public class DateBasic implements Date {
    private int day;
    private int month;
    private int year;

    public DateBasic(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    @Override
    public int getDay() {
        return day;
    }

    @Override
    public int getMonth() {
        return month;
    }

    @Override
    public int getYear() {
        return year;
    }

    @Override
    public int getNumDayInYear() {
        // FIXME a corriger car probleme
        // mois et février
        return day + month * 30;
    }

    @Override
    public int computeDurationInDays(Date date2) {
        // FIXME a corriger
        return
                Math.abs(this.getYear() - date2.getYear() * 365 + (getNumDayInYear() - date2.getNumDayInYear()))
                ;
    }
}
