/**
 * 
 */
package net.rickcee.jdcompare;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author rickcee
 *
 */
public class SampleDB2 {

    public static void main(String[] args) throws SQLException {
        String jdbcURL = "jdbc:h2:/Users/rickcee/sampledb2";
 
        Connection connection = DriverManager.getConnection(jdbcURL, "sa", "sa");
 
        System.out.println("Connected to H2 in-memory database.");
 
        String table1 = "CREATE TABLE IF NOT EXISTS students (ID int primary key, name varchar(50), age int, address varchar(50), phone varchar(20) )";
        String table2 = "CREATE TABLE IF NOT EXISTS trades (ID int primary key, cusip varchar(9), quantity bigint, price decimal )";
         
        Statement statement = connection.createStatement();
        
        statement.execute("DROP TABLE IF EXISTS students");
        statement.execute("DROP TABLE IF EXISTS trades");
        
        statement.execute(table1);
        statement.execute(table2);
         
        System.out.println("Created table students.");
         
        String[] values = {
        		"insert into students (ID, name, age, address, phone) values (1, 'Ricardo', 26, '666 Devils Lane', ' +1(203)555-7700');",
        		"insert into students (ID, name, age, address, phone) values (2, 'Erika', 26, '284 Palermo Street', '+54(911)4888-1945');",
        		"insert into students (ID, name, age, address, phone) values (3, 'Anita', 22, '188 Belgrano Avenue', '+1(203)495-8349 ');",
        		"insert into students (ID, name, age, address, phone) values (4, 'Pedrito', 33, '-', '-');",
        		"insert into students (ID, name, age, address, phone) values (5, 'Anita', 50, '-', '-');",
        		"insert into trades (ID, cusip, quantity, price) values (1, '912828CE5', 1000000, 99.1645);",
        		"insert into trades (ID, cusip, quantity, price) values (2, '912828CE5', 500000, 99.1257);",
        		"insert into trades (ID, cusip, quantity, price) values (3, '912828CA3', 600000, 99.110);",
        		"insert into trades (ID, cusip, quantity, price) values (4, '912828DB2', 200000, 99.345);",
        		"insert into trades (ID, cusip, quantity, price) values (5, '912828DB2', 150000, 99.345);",

        };

        statement.executeUpdate("DELETE FROM students");
        statement.executeUpdate("DELETE FROM trades");
        for(String _row : values) {
        	statement.executeUpdate(_row);
        }

 
        connection.close();

        while(true) {
        	try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				break;
			}
        }
        
 
    }

}
