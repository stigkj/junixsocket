/**
 * junixsocket
 *
 * Copyright (c) 2009 NewsClub, Christian Kohlschütter
 *
 * The author licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.newsclub.net.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Properties;

/**
 * Demonstrates how to connect to a local MySQL server
 * 
 * @author Christian Kohlschuetter
 */
public class AFUNIXDatabaseSocketFactoryDemo {
	public static void main(String[] args) throws Exception {
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Properties props = new Properties();
		props.put("user", "test");
		props.put("password", "test");
		props.put("socketFactory", AFUNIXDatabaseSocketFactory.class.getName());
		props.put("junixsocket.file", "/var/lib/mysql.sock");

		// SHOW DATABASES three times
		for (int i = 0; i < 3; i++) {
			Connection conn = DriverManager.getConnection("jdbc:mysql://",
					props);
			System.out.println(conn);

			ResultSet rs = conn.createStatement()
					.executeQuery("SHOW DATABASES");
			while (rs.next()) {
				System.out.println(rs.getString(1));
			}
			rs.close();
			conn.close();
		}
	}
}
