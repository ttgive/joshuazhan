package me.joshua.spring.groovy.demo.service;

import java.util.HashMap;
import java.util.Map;

import me.joshua.spring.groovy.demo.model.Order;

import org.springframework.stereotype.Service;

@Service
public class OrderService {
	private Map<Integer, Order> orderMap;

	public void init() {
		orderMap = new HashMap<Integer, Order>();
		Order order = new Order(1, "iPhone");
		orderMap.put(order.getId(), order);
		order = new Order(2, "iPad");
		orderMap.put(order.getId(), order);
	}

	public Order findOrder(int id) {
		return orderMap.get(id);
	}
}
