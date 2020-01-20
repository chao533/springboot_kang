package com.chao.design.model.factory;

public class Blue implements Color{

	@Override
    public void fill() {
		System.out.println("Inside Blue::fille() method.");
    }
}
