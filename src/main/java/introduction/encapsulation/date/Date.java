package introduction.encapsulation.date;

public interface Date {

    int getDay();

    int getMonth();

    int getYear();

    int getNumDayInYear();

    int computeDurationInDays(Date date2);
}
