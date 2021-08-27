package com.google.common.collect;

import org.junit.Test;

import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PowerCollectionUtilitiesTst {

    @Test
    public void tstUtil() {
        List<String> strings = Lists.newArrayList("a", "b", "c");
        LinkedList<String> linkedList = Lists.newLinkedList();
        CopyOnWriteArrayList<String> cowList = Lists.newCopyOnWriteArrayList();
        Lists.partition(strings, 1);

        // 初始化数组
        List<String> strings2 = Lists.newArrayListWithCapacity(5);
        // 期望值，不建议使用
        List<String> strings3 = Lists.newArrayListWithExpectedSize(5);
    }


    @Test
    public void tstSets() {
        Set<Integer> a = Sets.newHashSet(1, 2, 3);
        Set<Integer> b = Sets.newHashSet(3, 4, 5);


        Set<Integer> filter = Sets.filter(a, s -> s >= 2);
        System.out.println("filter: " + filter);

        Sets.SetView<Integer> intersection = Sets.intersection(a, b);
        System.out.println("intersection: " + intersection);

        Sets.SetView<Integer> union = Sets.union(a, b);
        System.out.println("union: " + union);

        Sets.SetView<Integer> a2bDiff = Sets.difference(a, b);
        System.out.println("a2bDiff: " + a2bDiff);
        Sets.SetView<Integer> b2aDiff = Sets.difference(b, a);
        System.out.println("b2aDiff: " + b2aDiff);

        // 对称差，不同时在 a 和 b 中的集合
        System.out.println("symmetricDiff: " + Sets.symmetricDifference(a, b));
        // 笛卡尔积
        Set<List<Integer>> cartesianProduct = Sets.cartesianProduct(a, b);
        System.out.println("cartesianProduct: " + cartesianProduct);

        // 组合，C几几的问题
        Set<Set<Integer>> combinations = Sets.combinations(a, 2);
        for (Set<Integer> combination : combinations) {
            System.out.println(combination);
        }
        System.out.println();

        Set<Set<Integer>> allSubSets = Sets.powerSet(a);
        for (Set<Integer> sub : allSubSets) {
            System.out.println(sub);
        }
    }

    @Test
    public void testMaps() {

        // identityHashMap key 会重复，因为比较的时候，只是 k
        Map<String, String> identityHashMap = Maps.newIdentityHashMap();

        Map<Integer, Integer> a = Maps.newHashMap();
        IntStream.range(0, 10).boxed().forEach(i -> a.put(i, i));
        Map<Integer, Integer> b = Maps.newLinkedHashMap();
        IntStream.range(6, 15).boxed().forEach(i -> b.put(i, i));

        MapDifference<Integer, Integer> a2bDiff = Maps.difference(a, b);
        System.out.println(a2bDiff.areEqual());
        System.out.println(a2bDiff.entriesDiffering());
        System.out.println(a2bDiff.entriesInCommon());
        System.out.println(a2bDiff.entriesOnlyOnLeft());
        System.out.println(a2bDiff.entriesOnlyOnRight());

        ImmutableMap<String, Integer> uniqueIndex = Maps.uniqueIndex(IntStream.range(1, 10).boxed().collect(Collectors.toList())
                , k -> k + "-" + k);
        System.out.println(uniqueIndex);
    }

    @Test
    public void tstMultisets(){

    }

}
