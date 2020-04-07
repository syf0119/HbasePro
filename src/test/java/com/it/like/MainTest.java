package com.it.like;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class MainTest {
    @Test
    public void createTable() throws IOException {
        Configuration configuration = HBaseConfiguration.create();
        configuration.set("hbase.zookeeper.property.clientPort", "2181");
        configuration.set("hbase.zookeeper.quorum", "spring,summer,autumn");
        configuration.set("hbase.master", "spring:60000");
        Connection connection = ConnectionFactory.createConnection(configuration);
        Admin admin = connection.getAdmin();
        HTableDescriptor myuser = new HTableDescriptor(TableName.valueOf("myuser"));
        myuser.addFamily(new HColumnDescriptor("f1"));
        myuser.addFamily(new HColumnDescriptor("f2"));
        boolean tableExists = admin.tableExists(TableName.valueOf("myuser"));
        if (!tableExists) {
            admin.createTable(myuser);
        }

        admin.close();
    }

    @Test
    public void pre() {
        System.out.println("da".getBytes());
    }

    @Test
    public void addData() throws IOException {
        Configuration configuration = HBaseConfiguration.create();
        configuration.set("hbase.zookeeper.quorum", "spring:2181,summer:2181,autumn:2181");
        Connection connection = ConnectionFactory.createConnection(configuration);
        Table myuser = connection.getTable(TableName.valueOf("myuser"));
        Put put = new Put("0001".getBytes());
        put.addColumn("f1".getBytes(), "id".getBytes(), Bytes.toBytes(1));
        put.addColumn("f1".getBytes(), "name".getBytes(), Bytes.toBytes("张三"));
        put.addColumn("f1".getBytes(), "age".getBytes(), Bytes.toBytes(18));
        put.addColumn("f2".getBytes(), "address".getBytes(), Bytes.toBytes("地球人"));
        put.addColumn("f2".getBytes(), "phone".getBytes(), Bytes.toBytes("10086"));
        myuser.put(put);
        myuser.close();
    }

    @Test
    public void deleteByRowkey() throws IOException {
        Configuration configuration = HBaseConfiguration.create();
        configuration.set("hbase.zookeeper.quorum", "spring:2181");
        Connection connection = ConnectionFactory.createConnection(configuration);
        Table myuser = connection.getTable(TableName.valueOf("myuser"));
        Delete delete = new Delete("0001".getBytes());
        myuser.delete(delete);
        myuser.close();

    }

    @Test
    public void insertBatchData() throws IOException {

        //获取连接
        Configuration configuration = HBaseConfiguration.create();
        configuration.set("hbase.zookeeper.quorum", "spring:2181");
        Connection connection = ConnectionFactory.createConnection(configuration);
        //获取表
        Table myuser = connection.getTable(TableName.valueOf("myuser"));
        //创建put对象，并指定rowkey
        Put put = new Put("0002".getBytes());
        put.addColumn("f1".getBytes(), "id".getBytes(), Bytes.toBytes(1));
        put.addColumn("f1".getBytes(), "name".getBytes(), Bytes.toBytes("曹操"));
        put.addColumn("f1".getBytes(), "age".getBytes(), Bytes.toBytes(30));
        put.addColumn("f2".getBytes(), "sex".getBytes(), Bytes.toBytes("1"));
        put.addColumn("f2".getBytes(), "address".getBytes(), Bytes.toBytes("沛国谯县"));
        put.addColumn("f2".getBytes(), "phone".getBytes(), Bytes.toBytes("16888888888"));
        put.addColumn("f2".getBytes(), "say".getBytes(), Bytes.toBytes("helloworld"));

        Put put2 = new Put("0003".getBytes());
        put2.addColumn("f1".getBytes(), "id".getBytes(), Bytes.toBytes(2));
        put2.addColumn("f1".getBytes(), "name".getBytes(), Bytes.toBytes("刘备"));
        put2.addColumn("f1".getBytes(), "age".getBytes(), Bytes.toBytes(32));
        put2.addColumn("f2".getBytes(), "sex".getBytes(), Bytes.toBytes("1"));
        put2.addColumn("f2".getBytes(), "address".getBytes(), Bytes.toBytes("幽州涿郡涿县"));
        put2.addColumn("f2".getBytes(), "phone".getBytes(), Bytes.toBytes("17888888888"));
        put2.addColumn("f2".getBytes(), "say".getBytes(), Bytes.toBytes("talk is cheap , show me the code"));


        Put put3 = new Put("0004".getBytes());
        put3.addColumn("f1".getBytes(), "id".getBytes(), Bytes.toBytes(3));
        put3.addColumn("f1".getBytes(), "name".getBytes(), Bytes.toBytes("孙权"));
        put3.addColumn("f1".getBytes(), "age".getBytes(), Bytes.toBytes(35));
        put3.addColumn("f2".getBytes(), "sex".getBytes(), Bytes.toBytes("1"));
        put3.addColumn("f2".getBytes(), "address".getBytes(), Bytes.toBytes("下邳"));
        put3.addColumn("f2".getBytes(), "phone".getBytes(), Bytes.toBytes("12888888888"));
        put3.addColumn("f2".getBytes(), "say".getBytes(), Bytes.toBytes("what are you 弄啥嘞！"));

        Put put4 = new Put("0005".getBytes());
        put4.addColumn("f1".getBytes(), "id".getBytes(), Bytes.toBytes(4));
        put4.addColumn("f1".getBytes(), "name".getBytes(), Bytes.toBytes("诸葛亮"));
        put4.addColumn("f1".getBytes(), "age".getBytes(), Bytes.toBytes(28));
        put4.addColumn("f2".getBytes(), "sex".getBytes(), Bytes.toBytes("1"));
        put4.addColumn("f2".getBytes(), "address".getBytes(), Bytes.toBytes("四川隆中"));
        put4.addColumn("f2".getBytes(), "phone".getBytes(), Bytes.toBytes("14888888888"));
        put4.addColumn("f2".getBytes(), "say".getBytes(), Bytes.toBytes("出师表你背了嘛"));

        Put put5 = new Put("0005".getBytes());
        put5.addColumn("f1".getBytes(), "id".getBytes(), Bytes.toBytes(5));
        put5.addColumn("f1".getBytes(), "name".getBytes(), Bytes.toBytes("司马懿"));
        put5.addColumn("f1".getBytes(), "age".getBytes(), Bytes.toBytes(27));
        put5.addColumn("f2".getBytes(), "sex".getBytes(), Bytes.toBytes("1"));
        put5.addColumn("f2".getBytes(), "address".getBytes(), Bytes.toBytes("哪里人有待考究"));
        put5.addColumn("f2".getBytes(), "phone".getBytes(), Bytes.toBytes("15888888888"));
        put5.addColumn("f2".getBytes(), "say".getBytes(), Bytes.toBytes("跟诸葛亮死掐"));


        Put put6 = new Put("0006".getBytes());
        put6.addColumn("f1".getBytes(), "id".getBytes(), Bytes.toBytes(5));
        put6.addColumn("f1".getBytes(), "name".getBytes(), Bytes.toBytes("xiaobubu—吕布"));
        put6.addColumn("f1".getBytes(), "age".getBytes(), Bytes.toBytes(28));
        put6.addColumn("f2".getBytes(), "sex".getBytes(), Bytes.toBytes("1"));
        put6.addColumn("f2".getBytes(), "address".getBytes(), Bytes.toBytes("内蒙人"));
        put6.addColumn("f2".getBytes(), "phone".getBytes(), Bytes.toBytes("15788888888"));
        put6.addColumn("f2".getBytes(), "say".getBytes(), Bytes.toBytes("貂蝉去哪了"));

        List<Put> listPut = new ArrayList<Put>();
        listPut.add(put);
        listPut.add(put2);
        listPut.add(put3);
        listPut.add(put4);
        listPut.add(put5);
        listPut.add(put6);

        myuser.put(listPut);
        myuser.close();
    }

    @Test
    public void searchData() throws IOException {
        Configuration configuration = HBaseConfiguration.create();
        configuration.set("hbase.zookeeper.quorum", "spring:2181");
        Connection connection = ConnectionFactory.createConnection(configuration);
        Table myuser = connection.getTable(TableName.valueOf("myuser"));
        Get get = new Get("0001".getBytes());
        Result result = myuser.get(get);
        Cell[] cells = result.rawCells();
        for (Cell cell : cells) {
            String value = Bytes.toString(cell.getQualifierArray(), cell.getQualifierOffset(), cell.getQualifierLength());
            System.out.println(value);
            if ("age".equals(value) || "id".equals(value))
                System.out.println(Bytes.toInt(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength()));
            else
                System.out.println(Bytes.toString(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength()));

        }
        myuser.close();


    }
    @Test
    public void searchData2() throws IOException {
        Configuration configuration = HBaseConfiguration.create();
        configuration.set("hbase.zookeeper.quorum", "spring:2181");
        Connection connection = ConnectionFactory.createConnection(configuration);
        Table myuser = connection.getTable(TableName.valueOf("myuser"));
        Get get = new Get("0001".getBytes());
        get.addColumn("f1".getBytes(),"id".getBytes());
        Result result = myuser.get(get);
        System.out.println(Bytes.toInt(result.getValue("f1".getBytes(), "id".getBytes())));

        myuser.close();


    }
    @Test
    public void scanRowkey() throws IOException {
        Configuration configuration = HBaseConfiguration.create();
        configuration.set("hbase.zookeeper.quorum", "spring:2181");
        Connection connection = ConnectionFactory.createConnection(configuration);
        Table myuser = connection.getTable(TableName.valueOf("myuser"));
        Scan scan=new Scan();
        scan.setStartRow("0002".getBytes());
        scan.setStopRow("0004".getBytes());
        ResultScanner scanner = myuser.getScanner(scan);
        for (Result result : scanner) {
            byte[] row = result.getRow();
            System.out.println(Bytes.toString(row));
            Cell[] cells = result.rawCells();
            for (Cell cell : cells) {
                String value = Bytes.toString(cell.getQualifierArray(), cell.getQualifierOffset(), cell.getQualifierLength());
                System.out.println(value);
                if ("age".equals(value) || "id".equals(value)||1==2)
                    System.out.println(Bytes.toInt(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength()));
                else
                    System.out.println(Bytes.toString(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength()));
            }
            System.out.println("+++++++++++++++++++++++++++");
        }
        myuser.close();
    }
    @Test
    public void scanAll() throws IOException {
        Configuration configuration = HBaseConfiguration.create();
        configuration.set("hbase.zookeeper.quorum", "spring:2181");
        Connection connection = ConnectionFactory.createConnection(configuration);
        Table myuser = connection.getTable(TableName.valueOf("myuser2"));
        Scan scan=new Scan();

        ResultScanner scanner = myuser.getScanner(scan);
        for (Result result : scanner) {
            byte[] row = result.getRow();
            System.out.println(Bytes.toString(row));
            Cell[] cells = result.rawCells();
            for (Cell cell : cells) {
                String value = Bytes.toString(cell.getQualifierArray(), cell.getQualifierOffset(), cell.getQualifierLength());
                System.out.println(value);
                if ("age".equals(value) || "id".equals(value)||3==4)
                    System.out.println(Bytes.toInt(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength()));
                else
                    System.out.println(Bytes.toString(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength()));
            }
            System.out.println("+++++++++++++++++++++++++++");
        }
        myuser.close();
    }
@Test
    public void rowkeyFilter() throws IOException {
    Configuration configuration = HBaseConfiguration.create();
    configuration.set("hbase.zookeeper.quorum", "spring:2181");
    Connection connection = ConnectionFactory.createConnection(configuration);
    Table myuser = connection.getTable(TableName.valueOf("myuser"));
    Scan scan=new Scan();
    RowFilter rowFilter=new RowFilter(CompareFilter.CompareOp.EQUAL,new BinaryComparator("0003".getBytes()));
    QualifierFilter qualifierFilter=new QualifierFilter(CompareFilter.CompareOp.EQUAL,new SubstringComparator("d"));

   scan.setFilter(qualifierFilter);
    scan.setFilter(rowFilter);
    ResultScanner scanner = myuser.getScanner(scan);
    for (Result result : scanner) {
        byte[] row = result.getRow();
        System.out.println(Bytes.toString(row));
        Cell[] cells = result.rawCells();
        for (Cell cell : cells) {
            String value = Bytes.toString(cell.getQualifierArray(), cell.getQualifierOffset(), cell.getQualifierLength());
            System.out.println(value);
            if ("age".equals(value) || "id".equals(value)||1==4)
                System.out.println(Bytes.toInt(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength()));
            else
                System.out.println(Bytes.toString(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength()));
        }
        System.out.println("+++++++++++++++++++++++++++");
    }
    myuser.close();
}
@Test
    public void singleColumnFilter() throws IOException {
    Configuration configuration = HBaseConfiguration.create();
    configuration.set("hbase.zookeeper.quorum", "spring:2181");
    Connection connection = ConnectionFactory.createConnection(configuration);
    Table myuser = connection.getTable(TableName.valueOf("myuser"));
    Scan scan=new Scan();
    SingleColumnValueFilter singleColumnValueFilter=new SingleColumnValueFilter(
            "f1".getBytes(),
            "age".getBytes(),
            CompareFilter.CompareOp.EQUAL,
            Bytes.toBytes(32)
            );
    scan.setFilter(singleColumnValueFilter);

    ResultScanner scanner = myuser.getScanner(scan);
    for (Result result : scanner) {
        byte[] row = result.getRow();
        System.out.println(Bytes.toString(row));
        Cell[] cells = result.rawCells();
        for (Cell cell : cells) {
            String value = Bytes.toString(cell.getQualifierArray(), cell.getQualifierOffset(), cell.getQualifierLength());
            System.out.println(value);
            if ("age".equals(value) || "id".equals(value)||3==5)
                System.out.println(Bytes.toInt(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength()));
            else
                System.out.println(Bytes.toString(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength()));
        }
        System.out.println("+++++++++++++++++++++++++++");
    }
    myuser.close();
}
@Test
    public void preFilter() throws IOException {
    Configuration configuration = HBaseConfiguration.create();
    configuration.set("hbase.zookeeper.quorum", "spring:2181");
    Connection connection = ConnectionFactory.createConnection(configuration);
    Table myuser = connection.getTable(TableName.valueOf("myuser"));
    Scan scan=new Scan();
    PrefixFilter prefixFilter=new PrefixFilter("0003".getBytes());
    QualifierFilter qualifierFilter=new QualifierFilter(CompareFilter.CompareOp.EQUAL,new SubstringComparator("d"));
    FilterList filterList = new FilterList();
    filterList.addFilter(prefixFilter);
    filterList.addFilter(qualifierFilter);
    scan.setFilter(filterList);
    ResultScanner scanner = myuser.getScanner(scan);
    for (Result result : scanner) {
        byte[] row = result.getRow();
        System.out.println(Bytes.toString(row));
        Cell[] cells = result.rawCells();
        for (Cell cell : cells) {
            String value = Bytes.toString(cell.getQualifierArray(), cell.getQualifierOffset(), cell.getQualifierLength());
            System.out.println(value);
            if ("age".equals(value) || "id".equals(value)||3==9)
                System.out.println(Bytes.toInt(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength()));
            else
                System.out.println(Bytes.toString(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength()));
        }
        System.out.println("+++++++++++++++++++++++++++");
    }
    myuser.close();
}
    @Test
    public void pageFilter() throws IOException {
        Configuration configuration = HBaseConfiguration.create();
        configuration.set("hbase.zookeeper.quorum", "spring:2181");
        Connection connection = ConnectionFactory.createConnection(configuration);
        Table myuser = connection.getTable(TableName.valueOf("myuser"));

        int pageNum = 3;
        int pageSize = 2;
        Scan scan = new Scan();
            String startRowKey ="";
            PageFilter filter = new PageFilter((pageNum - 1) * pageSize + 1  );
            scan.setStartRow(startRowKey.getBytes());
            scan.setFilter(filter);
            ResultScanner scanner = myuser.getScanner(scan);
            for (Result result : scanner) {
                byte[] row = result.getRow();
                startRowKey =  new String(row);
            }
            Scan scan2 = new Scan();
            scan2.setStartRow(startRowKey.getBytes());
            PageFilter filter2 = new PageFilter(pageSize);
            scan2.setFilter(filter2);
            ResultScanner scanner1 = myuser.getScanner(scan2);
            for (Result result : scanner1) {
                byte[] row = result.getRow();
                System.out.println(new String(row));
            }
        myuser.close();

    }

}