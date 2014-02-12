package com.googlecode.cqengine.db.factories.offheap;

import java.util.Collections;

import org.mapdb.DB;
import org.mapdb.DBMaker;

import com.googlecode.cqengine.index.common.Factory;
import com.googlecode.cqengine.resultset.stored.StoredResultSet;
import com.googlecode.cqengine.resultset.stored.StoredSetBasedResultSet;

public class MapDBValueSetFactory<O> implements Factory<StoredResultSet<O>> {

	private String mapName;
	
	public MapDBValueSetFactory(String mapName) {
		this.mapName = mapName;
	}

    @Override
    public StoredResultSet<O> create() {
        
        /* These settings should be configurable */
        DB db = DBMaker
				.newDirectMemoryDB()
				.transactionDisable()
				.asyncWriteEnable()
				.asyncWriteFlushDelay(100)
				//.compressionEnable()
				.make();

    	return new StoredSetBasedResultSet<O>(Collections.<O>newSetFromMap(db.<O,Boolean>getHashMap(mapName)));
    }
}
