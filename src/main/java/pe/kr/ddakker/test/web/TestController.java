package pe.kr.ddakker.test.web;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import pe.kr.ddakker.test.svc.TestService;

@Controller
public class TestController {
	private final Logger logger = LoggerFactory.getLogger(TestController.class);

	@Autowired
	private TestService testService;

	@RequestMapping("/")
	public String index(HttpServletRequest request, Model model) {
		logger.info("index");
		return "index";
	}
	
	@RequestMapping("/put")
	public String put(HttpServletRequest request, Model model) {
		logger.info("put");
		testService.put();

		return "index";
	}
	
	@RequestMapping("/get")
	public String get(HttpServletRequest request, Model model) {
		logger.info("get");
		testService.get();

		return "index";
	}
}
