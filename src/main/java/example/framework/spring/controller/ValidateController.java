

package example.framework.spring.controller;

import example.framework.spring.aware.AliasAware;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;


@RestController
@RequestMapping("validate")
//@Validated
public class ValidateController implements AliasAware {

	@GetMapping("/test1")
	public String test1(@RequestParam(value = "param", required = false) @NotBlank(message = "param不可为空") String param) {
		return "param: " + param;
	}

	@GetMapping("/test2")
	public String test2(@RequestParam(value = "param", required = true) @NotBlank(message = "param不可为空") String param) {
		return "param: " + param;
	}

	@GetMapping("/test3")
	public String test3(String param) {
		return "param:" + param;
	}

	@GetMapping("/test4")
	public String test4(@Validated @NotBlank(message = "param不可为空") String param) {
		return "param:" + param;
	}

	@PostMapping("/test5")
	public String test5(@RequestBody @Validated Human human) {
		return human.toString();
	}

	@Override
	public void setAlias(String[] alias) {
		this.alias = alias;
	}

	private String[] alias;
	public String[] getAlias() {
		return alias;
	}
}
