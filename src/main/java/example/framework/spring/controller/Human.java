
package example.framework.spring.controller;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;


@Data
public class Human {
	@NotBlank(message = "姓名不可为空")
	private String name;

	@Min(value = 0, message = "年龄不能小于0")
	@Max(value = 150, message = "年龄不能大于150")
	private Integer age;
}
