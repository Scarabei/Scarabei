//
// package com.jfixby.scarabei.red.serialize;
//
// import com.jfixby.scarabei.api.collections.Collections;
// import com.jfixby.scarabei.api.collections.Map;
// import com.jfixby.scarabei.api.serialize.MappingList;
// import com.jfixby.scarabei.api.serialize.Pair;
// import com.jfixby.scarabei.api.serialize.SerializeComponent;
//
// public class RedSerialize implements SerializeComponent {
//
// @Override
// public <P, Q> MappingList<Q> serialize (final Map<P, Q> input) {
// final MappingList<Q> result = new MappingList<Q>();
// for (final P key : input.keys()) {
//// new Pair(key, input.get(key))
// final Pair<Q> pair = new Pair<Q>();
// if (key != null) {
// pair.key = key.toString();
// }
// pair.value = input.get(key);
// result.list.add(pair);
// }
// return result;
// }
//
// @Override
// public <Q> Map<String, Q> deSerialize (final MappingList<Q> mapping, final Map<String, Q> map) {
// for (final Pair<Q> p : mapping.list) {
// map.put(p.key, p.value);
// }
// return map;
// }
//
// @Override
// public <Q> Map<String, Q> deSerialize (final MappingList<Q> mapping) {
// final Map<String, Q> result = Collections.newMap();
// return this.deSerialize(mapping, result);
// }
//
// }
