package com.extras;

import java.util.Map;

public interface LambdaPredicateGeneric<T extends Map<String, String>> {

	public abstract T getMap(T map);
}
