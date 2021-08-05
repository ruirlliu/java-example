package example.framework.spring.tansfer;

import org.springframework.core.convert.converter.Converter;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * 描述: Converter 可以进行多种类型之间的转换 <br>
 *
 * @author LR<br>
 * @date 2021/7/21 14:57
 */
public class ConverterDemo implements Converter<Date, LocalDateTime> {


	@Override
	public LocalDateTime convert(Date source) {
		return LocalDateTime.ofInstant(source.toInstant(), ZoneId.systemDefault());
	}


}
