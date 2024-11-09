package introduction.encapsulation.date;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class DateBasicTest {

    @Test
    public void test() {
        DateBasic date1 = new DateBasic(1, 1, 2024);
        DateBasic date2 = new DateBasic(2, 1, 2024);
        Assertions.assertThat(date1.computeDurationInDays(date2)).isEqualTo(1);
    }

}