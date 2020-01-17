package com.chao.design.model.factory;

public class Red implements Color{

	@Override
    public void fill() {
        System.out.println("Inside Red::fille() method.");
    }
}
