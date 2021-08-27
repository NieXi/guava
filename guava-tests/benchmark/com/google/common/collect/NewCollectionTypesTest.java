package com.google.common.collect;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

public class NewCollectionTypesTest {


    @Test
    public void tstCollections2() {

    }

    @Test
    public void tstOptional() {

        Optional<String> stringOptional = Optional.of("");
        Optional<String> empty = Optional.empty();
        Optional<String> nullable = Optional.ofNullable("a");

        boolean present = stringOptional.isPresent();
        String emptyStr = empty.get();// exception

        if (stringOptional.isPresent()) {
            stringOptional.get().toLowerCase();
        }

        stringOptional.ifPresent(str -> str.toLowerCase());
    }


    @Test
    public void tstBiMap() {
        BiMap<String, String> nation2CapitalMap = HashBiMap.create();
        nation2CapitalMap.put("China", "Beijing");
        nation2CapitalMap.put("South Korea", "Seoul");

        String beijing = nation2CapitalMap.get("China");
        String japan = nation2CapitalMap.inverse().get("Tokyo");

        nation2CapitalMap.put("South Korea", "Seoul_1");
        nation2CapitalMap.put("UUS", "Washington");

        nation2CapitalMap.put("South Korea", "Seoul");
        String seoul = nation2CapitalMap.get("South Korea");

        // nation2CapitalMap.put("US", "Washington");
        nation2CapitalMap.forcePut("US", "Washington");
        nation2CapitalMap.inverse().put("Washington", "US");
        String washington = nation2CapitalMap.get("US");

        Set<String> values = nation2CapitalMap.values();
        Set<String> keys = nation2CapitalMap.inverse().values();
        Set<Map.Entry<String, String>> entries = nation2CapitalMap.entrySet();
        Set<String> strings = nation2CapitalMap.keySet();

    }

    public void tstObjects() {
        Integer a = 1;
        Integer b = null;
        Integer c = 2;

        // JDK
        boolean isNull = Objects.isNull(b);
        boolean nonNull = Objects.nonNull(b);
        Objects.requireNonNull(b);// NullPointerException
        boolean equals = Objects.equals(a, b);// false
        int result = Objects.compare(a, c, Integer::compareTo);

    }

    @Test
    public void tstTable() {
        // StandardTable table：表对象
        //                     Map<R, Map<C, V>> backingMap: 数据的实际存储结构，嵌套的 Map
        //                     Map<R, Map<C, V>> -> Map<R, Row<C, V>> rowMap: 行视图，不存实际数据，
        //                                                                    Row row: 行数据，也是个 map，column => value
        //                                                                             Map<C, V> rowBackingMap => backingMap.get(rowKey)
        //                     Map<C, Map<R, V>> -> ColumnMap columnMap     : 列视图
        //                                                                    Column column，列数据，实际不存在，读写都是转换成对行的操作


        Map<String, Map<String, String>> customTable = new HashMap<>();
        Map<String, String> columnss = customTable.getOrDefault("R1", new HashMap<>());
        columnss.put("C1", "V_R1_C1");
        customTable.getOrDefault("R1", new HashMap<>()).get("C1");


        Table<String, String, String> table = HashBasedTable.create();
        table.put("R1", "C1", "V_R1_C1");
        table.put("R1", "C2", "V_R1_C2");
        table.put("R2", "C1", "V_R1_C2");
        table.put("R2", "C2", "V_R1_C2");

        String v = table.get("R1", "C1");// V_R1_C1
        boolean containsR1C1 = table.contains("R1", "C1");// true
        boolean containsColumn2 = table.containsColumn("C2");// true
        boolean containsRow2 = table.containsRow("R2");// true
        boolean containsValueR1C3 = table.containsValue("V_R1_C3");// false


        // rowMap(columnKye -> value)：backingMap.get(rowKey) 的 value
        // 只是记录一个 columnKey，真正取值的时候，还是通过 rowMap 去取
        // 其实还是按先行后列的方式去取值，map.get(rowKey).get(columnKey)

        // table.put(null, "C", "V");
        // table.put("R", null, "V");
        // table.put("R", "C", null);

        Map<String, String> row1 = table.row("R1");
        row1.put("C3", "V_R1_C3");

        Map<String, String> column1 = table.column("C1");
        String vR1C1 = column1.get("R1");


        Map<String, String> row3 = table.row("R3");// null?
        boolean isNull = row3 == null;// false
        if (!isNull) {
            boolean empty = row3.isEmpty();// true
        }


        Set<Table.Cell<String, String, String>> cells = table.cellSet();
        Map<String, Map<String, String>> rowMap = table.rowMap();// R,C,V
        Map<String, Map<String, String>> columnMap = table.columnMap();// C,R,V

    }
}
