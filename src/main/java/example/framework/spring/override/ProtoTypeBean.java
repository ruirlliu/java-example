

package example.framework.spring.override;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Random;


@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class ProtoTypeBean {

	public int getNum() {
		return new Random().nextInt(100);
	}

}
