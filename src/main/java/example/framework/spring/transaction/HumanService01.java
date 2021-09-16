package example.framework.spring.transaction;

import example.framework.mybatis.dao.HumanDao;
import example.framework.mybatis.entity.Human;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

/**
 * 描述: 业务类<br>
 *
 * @author LR<br>
 * @version 1.0 <br>
 * @date 2021/8/11 16:53
 */
@Service
public class HumanService01  {

	@Autowired
	private HumanDao humanDao;

	@Autowired
	private HumanService02 humanService02;


	public boolean insert() {
		Human zhangsan = new Human();
		zhangsan.setName("张三");
		zhangsan.setAge(23);
		zhangsan.setGender("男");
		Human lisi = new Human();
		lisi.setName("李思");
		lisi.setAge(24);
		lisi.setGender("女");
		humanDao.insert(zhangsan);
		humanDao.insert(lisi);
		return true;
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, timeoutString = "${spring.transaction.custom-time-out}", value = "tm")
	public void update() throws Exception {
		Human zhangsan = new Human();
		zhangsan.setId(1);
		zhangsan.setPhone("123");
		humanDao.updateByPrimaryKeySelective(zhangsan);
		humanService02.update();
		System.out.println("############ sleep ############");
		try {
			TimeUnit.SECONDS.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		throw new Exception("事务回滚");
	}


}
