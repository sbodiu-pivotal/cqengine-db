package com.googlecode.cqengine.db.factories.offheap;

import java.util.concurrent.ConcurrentMap;

import org.mapdb.DB;
import org.mapdb.DBMaker;

import com.googlecode.cqengine.index.common.Factory;
import com.googlecode.cqengine.resultset.stored.StoredResultSet;


public class MapDBIndexMapFactory<A, O> implements Factory<ConcurrentMap<A, StoredResultSet<O>>> {

	private String mapName;
	
	public MapDBIndexMapFactory(String mapName) {
		this.mapName = mapName;
	}
	
	@Override
	public ConcurrentMap<A, StoredResultSet<O>> create() {

        /* These settings should be configurable */
        DB db = DBMaker
				.newDirectMemoryDB()
				.transactionDisable()
				.asyncWriteEnable()
				.asyncWriteFlushDelay(100)
				//.compressionEnable()
				.make();

		return (ConcurrentMap)db.getHashMap(mapName);
	}
}
