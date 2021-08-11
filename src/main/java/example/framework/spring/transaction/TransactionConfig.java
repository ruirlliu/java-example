package example.framework.spring.transaction;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.annotation.AnnotationTransactionAttributeSource;
import org.springframework.transaction.interceptor.TransactionAttributeSource;

/**
 * 描述: spring 事务配置，支持非 public 方法<br>
 *
 * @author LR<br>
 * @version 1.0 <br>
 * @date 2021/8/11 16:37
 * @see org.springframework.transaction.annotation.ProxyTransactionManagementConfiguration#transactionAttributeSource
 */
@Configuration
public class TransactionConfig {

	@Bean("configTransactionAttributeSource")
	@Primary
	public TransactionAttributeSource transactionAttributeSource() {
		return new AnnotationTransactionAttributeSource(false);
	}

}
