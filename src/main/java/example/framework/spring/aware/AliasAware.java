package example.framework.spring.aware;

import org.springframework.beans.factory.Aware;

/**
 * 自定义的aware，用于获取bean的别名
 * @author lr
 * @date 2021/4/30 18:00
 */
public interface AliasAware extends Aware {

	void setAlias(String[] alias);

}
