/**
 * 
 */
package tien.java.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 */
@Controller
@RequestMapping("/homeController")
public class HomeController {
	
	@GetMapping
	public String taoMoiMay(Model model) {
		return "home";
	}
}
