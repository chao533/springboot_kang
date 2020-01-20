package com.chao.design.model.factory;

public class ShapeFactory extends AbstractFactory {

	@Override
	Shape getShape(String shape) {
		if (shape == null) {
			return null;
		}

		shape = shape.toLowerCase();

		switch (shape) {
		case "circle":
			return new Circle();
		case "rectangle":
			return new Rectangle();
		case "square":
			return new Square();
		default:
			return null;
		}

	}

	@Override
	Color getColor(String color) {
		return null;
	}

}
