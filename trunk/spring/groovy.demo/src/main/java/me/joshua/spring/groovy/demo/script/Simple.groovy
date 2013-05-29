package me.joshua.spring.groovy.demo.script;

import me.joshua.spring.groovy.demo.model.Member
import me.joshua.spring.groovy.demo.model.Order
import me.joshua.spring.groovy.demo.service.MemberService
import me.joshua.spring.groovy.demo.service.OrderService

import org.springframework.beans.factory.annotation.Autowired

class Simple implements Script {

//	@Autowired
//	private MemberService memberService
//
//	public void run() {
//		Member member = memberService.findMember("daonan.zhan@gmail.com");
//		System.out.println(member);
//	}
	
	@Autowired
	private OrderService orderService;
	
	public void run() {
		Order order = orderService.findOrder(1);
		System.out.println(order);
	}
}
