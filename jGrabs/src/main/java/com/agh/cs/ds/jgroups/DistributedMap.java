package com.agh.cs.ds.jgroups;

import java.util.HashMap;

public final class DistributedMap implements SimpleStringMap {
    private final HashMap<String, String> state = new HashMap<>();

    public DistributedMap() {
        state.put("xD", "dsa");
    }

    @Override
    public boolean containsKey(String key) {
        return state.containsKey(key);
    }

    @Override
    public String get(String key) {
        return state.get(key);
    }

    @Override
    public String put(String key, String value) {
        return state.put(key, value);
    }

    @Override
    public String remove(String key) {
        return state.remove(key);
    }
}
