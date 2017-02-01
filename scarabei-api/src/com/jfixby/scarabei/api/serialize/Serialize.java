//
// package com.jfixby.scarabei.api.serialize;
//
// import com.jfixby.scarabei.api.ComponentInstaller;
// import com.jfixby.scarabei.api.collections.Map;
//
// public class Serialize {
//
// static private ComponentInstaller<SerializeComponent> componentInstaller = new ComponentInstaller<SerializeComponent>(
// "Serialize");
//
// public static final void installComponent (final SerializeComponent component_to_install) {
// componentInstaller.installComponent(component_to_install);
// }
//
// public static void installComponent (final String className) {
// componentInstaller.installComponent(className);
// }
//
// public static final SerializeComponent invoke () {
// return componentInstaller.invokeComponent();
// }
//
// public static final SerializeComponent component () {
// return componentInstaller.getComponent();
// }
//
// public static <P, Q> MappingList<Q> serialize (final Map<P, Q> input) {
// return invoke().serialize(input);
// }
//
// public static <Q> Map<String, Q> deSerialize (final MappingList<Q> mapping, final Map<String, Q> map) {
// return invoke().deSerialize(mapping, map);
// }
//
// public static <Q> Map<String, Q> deSerialize (final MappingList<Q> mapping) {
// return invoke().deSerialize(mapping);
// }
// }
