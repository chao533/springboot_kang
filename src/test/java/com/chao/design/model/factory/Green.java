package com.chao.design.model.factory;

public class Green implements Color{

	@Override
    public void fill() {
		System.out.println("Inside Green::fille() method.");
    }
}
