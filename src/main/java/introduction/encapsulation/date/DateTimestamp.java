package introduction.encapsulation.date;

public class DateTimestamp implements Date {

    private long secondsFrom1StJanuary1970;

    @Override
    public int getDay() {
        return 0;
    }

    @Override
    public int getMonth() {
        return 0;
    }

    @Override
    public int getYear() {
        return 0;
    }

    @Override
    public int getNumDayInYear() {
        return 0;
    }

    @Override
    public int computeDurationInDays(Date date2) {
        return 0;
    }
}
