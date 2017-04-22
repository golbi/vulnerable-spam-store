/*
 * Copyright (c) 2017 ICM Uniwersytet Warszawski All rights reserved.
 * See LICENCE.txt file for licensing information.
 */
package net.bixbit.vulnerablespam;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DAO
{	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	
	
	
	@PostConstruct
	public void initDB()
	{
		jdbcTemplate.execute("DROP TABLE users IF EXISTS");
		jdbcTemplate.execute("CREATE TABLE users(" +
				"id SERIAL, user VARCHAR(255) UNIQUE, pass VARCHAR(255), cc VARCHAR(255), bought INT)");

		jdbcTemplate.execute("INSERT INTO users(user, pass, cc, bought) "
				+ "VALUES('admin', 'a', '6666 9999 3329 1111', 0)");

		
		jdbcTemplate.execute("INSERT INTO users(user, pass, cc, bought) "
				+ "VALUES('bolek', 'bxc', '3456 9999 3329 1111', 0)");

		jdbcTemplate.execute("INSERT INTO users(user, pass, cc, bought) "
				+ "VALUES('lolek', 'xxv', '1234 2342 3329 2342', 0)");

		jdbcTemplate.execute("INSERT INTO users(user, pass, cc, bought) "
				+ "VALUES('tola', 'tro', '6453 8347 3569 3345', 0)");

		jdbcTemplate.execute("INSERT INTO users(user, pass, cc, bought) "
				+ "VALUES('reksio', 'fge', '1234 8347 3569 3345', 0)");

		jdbcTemplate.execute("INSERT INTO users(user, pass, cc, bought) "
				+ "VALUES('rumcajs', 'glp', '6453 1234 3569 3345', 0)");

		jdbcTemplate.execute("INSERT INTO users(user, pass, cc, bought) "
				+ "VALUES('boruta', 'fdg', '6453 8347 1244 3345', 0)");

		jdbcTemplate.execute("INSERT INTO users(user, pass, cc, bought) "
				+ "VALUES('gustaw', 'pal', '3453 8347 1444 3345', 0)");

		jdbcTemplate.execute("INSERT INTO users(user, pass, cc, bought) "
				+ "VALUES('micek', 'das', '23443 8337 1444 3341', 0)");
	}

	public User authenticate(String user, String password)
	{
		Map<String, Object> map = jdbcTemplate.queryForMap(
				"select * from users where user = '" + user + "' and pass = '" + password + "'");
		if (map == null)
			return null;
		return new User((String)map.get("user"), (String)map.get("cc"), (Integer)map.get("bought"));
	}
	
	public void buy(User user)
	{
		jdbcTemplate.execute("UPDATE users SET bought = " + user.getBought() 
				+ " where user = '" + user.getUsername() + "'");
	}

}
