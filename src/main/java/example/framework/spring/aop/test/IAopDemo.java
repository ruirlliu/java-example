/**
 * 版权申明: 苏州朗动科技有限公司<br>
 * 项目描述: 企查查云聚接口平台
 */

package example.framework.spring.aop.test;

import example.framework.spring.aop.LogAround;

/**
 * 描述:TOTO 请补全模块<br>
 *
 * @author LR<br>
 * @version 1.0 <br>
 * @date 2021/9/10 16:32
 */
public interface IAopDemo {

	@LogAround
	int sum(int i1, int i2);
	int sum2(int i1, int i2);
}
