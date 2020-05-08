package org.birlasoft.usermanagement.controller;

import java.util.ArrayList;
import java.util.List;

import org.birlasoft.usermanagement.bean.HellowWorld;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin
@RestController
public class WelcomeContoller {
	@GetMapping("/welcome")
	public HellowWorld welcomeMsg() {
	return new HellowWorld(1,"welocome vivek");
	}
	
	@GetMapping("/welcomes")
	public List<HellowWorld> welcomeListMsg() {
		List<HellowWorld> list=new ArrayList<>();
	HellowWorld hellowWorld1 = new	HellowWorld(1,"welocome vivek");
	HellowWorld hellowWorld2 = new	HellowWorld(1,"welocome santosh");
	HellowWorld hellowWorld3 = new	HellowWorld(1,"welocome kamlesh");
	HellowWorld hellowWorld4 = new	HellowWorld(1,"welocome ajay");
	HellowWorld hellowWorld5 = new	HellowWorld(1,"welocome saurabh");
	list.add(hellowWorld1);
	list.add(hellowWorld2);
	list.add(hellowWorld3);
	list.add(hellowWorld4);
	list.add(hellowWorld5);
	return list;
	}
}
