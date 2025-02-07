package com.extras;

import java.util.HashMap;

public class LambdaTest {

	public static void main(String[] args) {
		LambdaPredicate pre = () -> {
			System.out.println("Predicate");
			return true;
		};
		System.out.println("Predicate compiled..");
		boolean re = pre.isLambdaExpression();
		System.out.println("Predicate result : " + re);
		
		LambdaPredicateArg preArg = e -> { System.out.println(e); return !e; };
		System.out.println("Predicate Arg Result : " + preArg.isLambdaExpression(re));
		
		LambdaPredicateGeneric<HashMap<String,String>> preGeneric = map -> {
			System.out.println(map);
			return map;
		};
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("ls", "value");
		
		System.out.println("Predicate Generic Result : " + preGeneric.getMap(map));
	}
}
