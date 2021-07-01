

package example.framework.spring.override;

import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;

@Component
public abstract class LookUpOverride {
	public void outPut() {
		ProtoTypeBean proto = getProto();
		System.out.println("prototype bean num is : " + proto.getNum());
	}

	/**
	 * 如果去掉这个注解，spring容器中就不会有这个bean，依赖注入会报错。因为抽象类是不支持创建bean实例的
	 * @return
	 */
	@Lookup
	protected abstract ProtoTypeBean getProto() ;
}
