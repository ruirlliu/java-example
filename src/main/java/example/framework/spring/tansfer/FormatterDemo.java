package example.framework.spring.tansfer;

import org.springframework.format.Formatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 描述: 类型转换，执行进行 String <--> 其它对象的转换  <br>
 *
 * @author LR<br>
 * @date 2021/7/21 14:52
 */
public class FormatterDemo implements Formatter<Date> {
	@Override
	public Date parse(String text, Locale locale) throws ParseException {
		return new SimpleDateFormat("yyyy-MM-dd", locale).parse(text);
	}

	@Override
	public String print(Date object, Locale locale) {
		return new SimpleDateFormat("yyyy-MM-dd", locale).format(object);
	}
}
