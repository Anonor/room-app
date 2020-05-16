package room.Test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import room.service.PensionService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestFunc {

    @Autowired
    private PensionService pensionService;

    @Test
    public void isPensionExist() {
        boolean result = pensionService.isPensionExist(1, "「西西里」法式蓝-高端寓所/尊享品质3");
        System.out.println(result);
    }
}
