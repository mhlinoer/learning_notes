package com.linoer.dbsharding.strategy;

import com.dangdang.ddframe.rdb.sharding.api.ShardingValue;
import com.dangdang.ddframe.rdb.sharding.api.strategy.database.SingleKeyDatabaseShardingAlgorithm;
import com.google.common.collect.Range;

import java.util.Collection;
import java.util.LinkedHashSet;

public class ModuleDatabaseShardingAlgorithm implements SingleKeyDatabaseShardingAlgorithm<Long> {

    /**
     * 具体的策略，按数据库名取模
     * @param collection
     * @param shardingValue
     * @return
     */
    @Override
    public String doEqualSharding(Collection<String> collection, ShardingValue<Long> shardingValue) {
        for (String each: collection) {
            if(each.endsWith(Long.parseLong(shardingValue.getValue().toString()) % 2 + "")){
                return each;
            }
        }
        throw new IllegalArgumentException();
    }

    /**
     * @param collection
     * @param shardingValue
     * @return
     */
    @Override
    public Collection<String> doInSharding(Collection<String> collection, ShardingValue<Long> shardingValue) {
        Collection<String> result = new LinkedHashSet<>(collection.size());
        for (Long value: shardingValue.getValues()) {
            for (Object tableName:collection) {
                String name = String.valueOf(tableName);
                if(name.endsWith(value % 2 +"")){
                    result.add(name);
                }
            }
        }
        return result;
    }

    @Override
    public Collection<String> doBetweenSharding(Collection<String> collection, ShardingValue<Long> shardingValue) {
        Collection<String> result = new LinkedHashSet<>(collection.size());
        Range<Long> range = shardingValue.getValueRange();
        for (Long i = range.lowerEndpoint(); i <= range.upperEndpoint(); i++){
            for (Object each: collection) {
                String name = String.valueOf(each);
                if(name.endsWith(i % 2 + "")){
                    result.add(name);
                }
            }
        }
        return result;
    }
}
