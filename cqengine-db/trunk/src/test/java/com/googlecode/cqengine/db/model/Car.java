package com.googlecode.cqengine.db.model;

import com.googlecode.cqengine.attribute.Attribute;
import com.googlecode.cqengine.attribute.SimpleAttribute;

/**
 * Created by tassoa on 2/12/14.
 */
public class Car {

    private String manufacturer;

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public static final Attribute<Car, String> MANUFACTURER = new SimpleAttribute<Car, String>("manufacturer") {
        public String getValue(Car car) { return car.manufacturer; }
    };
}
