package com.googlecode.cqengine.db;

import static com.googlecode.cqengine.query.QueryFactory.deduplicate;
import static com.googlecode.cqengine.query.QueryFactory.equal;
import static com.googlecode.cqengine.query.QueryFactory.queryOptions;

import java.math.BigInteger;
import java.security.SecureRandom;

import com.googlecode.cqengine.CQEngine;
import com.googlecode.cqengine.IndexedCollection;
import com.googlecode.cqengine.db.factories.offheap.MapDBIndexMapFactory;
import com.googlecode.cqengine.db.factories.offheap.MapDBSetFactory;
import com.googlecode.cqengine.db.factories.offheap.MapDBValueSetFactory;
import com.googlecode.cqengine.db.model.Car;
import com.googlecode.cqengine.index.hash.HashIndex;
import com.googlecode.cqengine.query.Query;
import com.googlecode.cqengine.query.option.DeduplicationStrategy;
import com.googlecode.cqengine.query.option.QueryOption;
import com.googlecode.cqengine.resultset.ResultSet;


public class Test {

	private static SecureRandom random = new SecureRandom();
	
	// Should eventually convert to JUnit test case.
	public static void main(String[] args) {
		
		MapDBSetFactory<Car> fact1 = new MapDBSetFactory<Car>("Collection1");
		MapDBValueSetFactory<Car> fact2 = new MapDBValueSetFactory<Car>("Collection2");
		MapDBIndexMapFactory<String, Car> fact3 = new MapDBIndexMapFactory<String, Car>("Map1");
		
		HashIndex<String, Car> ix =	HashIndex.onAttribute(fact3, fact2, Car.MANUFACTURER);

		IndexedCollection<Car> coll = CQEngine.newInstance(fact1);
		coll.addIndex(ix);


        for(int i = 0; i < 1000; i++) {

            Car car = new Car();
            car.setManufacturer(randomString());
            coll.add(car);
            System.out.println("Size: " + coll.size());
        }


        /*
		Query<Car> query1 = equal(Car.MANUFACTURER, "Honda");
		ResultSet<Car> results = coll.retrieve(query1);

		for(Car carr : results) {
			System.out.println(car.getManufacturer());
		}
		*/
	}
	
	public static String randomString() {
		return new BigInteger(130, random).toString(32);
	}
}
