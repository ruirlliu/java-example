package example.jdk.reflect.method;

/**
 * 描述: 桥接方法 https://www.cnblogs.com/zsg88/p/7588929.html <br>
 *
 * @author LR<br>
 * @date 2021/7/13 19:32
 */
public class BridgeDemoImpl implements IBridgeDemo<String> {
	@Override
	public String someThing(String t) {
		return "This is a [Method#Bridge] Demo";
	}
}
