package com.wisrc.microservice;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class MicroserviceApplicationTests {
//
//    @Autowired
//    private Cluster cluster;
//
//    @Test
//    public void contextLoads() {
//        Session session = cluster.connect();
//
//        String query = "CREATE KEYSPACE gateway WITH replication "
//                + "= {'class':'SimpleStrategy', 'replication_factor':1}; ";
//        session.execute(query);
//
//
//        session.close();
//    }
//
//    @Test
//    public void createTable() {
//
//        Session session = cluster.connect("gateway");
//
//        String dropTable = "drop table zuul_forward_logger;";
//
//        session.execute(dropTable);
//
//        String query = "CREATE TABLE zuul_forward_logger(id text PRIMARY KEY, "
//                + "method text, "
//                + "path text, "
//                + "username text, "
//                + "query text," +
//                "body text," +
//                "message text," +
//                "start_time text," +
//                "end_time text," +
//                "status text, );";
//
//        session.execute(query);
//
//        session.close();
//    }
//
//    @Test
//    public void addRow() {
//
//        Session session = cluster.connect("gateway");
//
//        String query1 = "INSERT INTO zuul_forward_logger (id, method, path, username, query) VALUES(1,'ram', 'Hyderabad', '9848022338', '50000');";
//
//
//
//        session.execute(query1);
//
//        session.close();
//
//    }
//
//    @Test
//    public void selectTable() {
//
//        Session session = cluster.connect("gateway");
//
//        String cql = "select * from zuul_forward_logger";
//        ResultSet resultSet = session.execute(cql);
//        Iterator<Row> iterator = resultSet.iterator();
//        while (iterator.hasNext()) {
//            Row row = iterator.next();
//            System.out.println(row);
//        }
//    }
//
//
//    @Test
//    public void query() {
//        Cluster cluster = Cluster.builder().addContactPoints("118.31.46.174").build();
//        Session session = cluster.connect("demo");
//
//        ReactiveCassandraOperations template = new ReactiveCassandraTemplate(new DefaultBridgedReactiveSession(session));
//
//
//    }
//

}

