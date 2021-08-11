package example.framework.spring.transaction;

import example.framework.mybatis.dao.HumanDao;
import example.framework.mybatis.entity.Human;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class HumanService02 {

	@Autowired
	private HumanDao humanDao;


	/**
	 * service01 中方法拥有事务，传播属性为 REQUIRED，当前方法的事务传播是 NOT_SUPPORTED，即当前方法不支持事务运行。
	 * 此方法如果是 public 修改，更新会不在事务下运行，直接更新数据库信息。
	 * 如果去掉public，无修饰符，当 service01 更新时，当前 lisi 方法更新的事务是使用 service01 中的事务，在 service01 事务中运行，不会直接更新。
	 * <p>
	 * 通过配置 AnnotationTransactionAttributeSource ，令非 public 方法也拥有事务。
	 * 结果：方法即使在无修饰符下，也使用了自定义的传播属性，不通过事务运行，直接更新了数据库
	 * <p>
	 *
	 *
	 * <p>
	 * 或者把当前事务传播行为设置为 NEVER，如果有事务会报错。
	 * public 方法修饰，直接报错。：org.springframework.transaction.IllegalTransactionStateException: Existing transaction found for transaction marked with propagation 'never'
	 * 无修饰符，当前方法正常运行。
	 * 配置非public 方法拥有事务后，结果同 public 方法修饰
	 *
	 * @see TransactionConfig
	 */
	// 无修改符方法
	@Transactional(propagation = Propagation.NEVER, rollbackFor = Exception.class)
	/*public */ void update() {
		Human lisi = new Human();
		lisi.setId(2);
		lisi.setPhone("234");
		humanDao.updateByPrimaryKeySelective(lisi);
	}

}
