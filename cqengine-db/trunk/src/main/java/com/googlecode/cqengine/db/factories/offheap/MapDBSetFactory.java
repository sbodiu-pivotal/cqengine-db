package com.googlecode.cqengine.db.factories.offheap;

import java.util.Set;

import org.mapdb.DB;
import org.mapdb.DBMaker;

import com.googlecode.cqengine.index.common.Factory;

public class MapDBSetFactory<T> implements Factory<Set<T>> {

	private String setName;
	
	public MapDBSetFactory(String setName) {
		this.setName = setName;
	}

	@Override
	public Set<T> create() {

        /* These settings should be configurable */
        DB db = DBMaker
				.newDirectMemoryDB()
				.transactionDisable()
				.asyncWriteEnable()
				.asyncWriteFlushDelay(100)
				//.compressionEnable()
				.make();

		return db.<T>getHashSet(setName);
	}
}
