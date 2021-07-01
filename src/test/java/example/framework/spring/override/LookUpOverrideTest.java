package example.framework.spring.override;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest
@RunWith(SpringRunner.class)
public class LookUpOverrideTest {

	@Autowired
	private LookUpOverride override;

	@Test
	public void testOverride() {
		for (int i = 0; i < 3; i++) {
			override.outPut();
		}
	}

}