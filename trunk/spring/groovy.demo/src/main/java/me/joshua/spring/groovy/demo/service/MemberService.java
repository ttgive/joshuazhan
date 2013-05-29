package me.joshua.spring.groovy.demo.service;

import java.util.HashMap;
import java.util.Map;

import me.joshua.spring.groovy.demo.model.Member;

import org.springframework.stereotype.Service;

@Service
public class MemberService {

	private Map<String, Member> memberMap;

	public void init() {
		memberMap = new HashMap<String, Member>();
		Member member = new Member("joshua", "daonan.zhan@gmail.com");
		memberMap.put(member.getEmail(), member);
		member = new Member("daonan", "zdn880729@gmail.com");
		memberMap.put(member.getEmail(), member);
	}

	public Member findMember(String email) {
		return memberMap.get(email);
	}
}
