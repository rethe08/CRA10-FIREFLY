import com.firefly.EmployeeInfo.EmployeeInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MainTest {
    @Test
    void accessingTest(){
        EmployeeInfo info = new EmployeeInfo(20123456, "GILDONG HONG", EmployeeInfo.CareerLevel.CL2,
                33334444, 19900130, EmployeeInfo.Certificate.ADV);

        Assertions.assertEquals(20123456, info.getEmployeeNum());
        Assertions.assertEquals("GILDONG HONG", info.getName());
        Assertions.assertEquals("GILDONG", info.getFirstName());
        Assertions.assertEquals("HONG", info.getLastName());
        Assertions.assertEquals(EmployeeInfo.CareerLevel.CL2, info.getCl());
        Assertions.assertEquals(33334444, info.getPhoneNum());
        Assertions.assertEquals(3333, info.getPhoneNumMid());
        Assertions.assertEquals(4444, info.getPhoneNumLast());
        Assertions.assertEquals(19900130, info.getBirthday());
        Assertions.assertEquals(1990, info.getBirthYear());
        Assertions.assertEquals(1, info.getBirthMonth());
        Assertions.assertEquals(30, info.getBirthDayOnly());
        Assertions.assertEquals(EmployeeInfo.Certificate.ADV, info.getCerti());
    }
}