package org.product.warranty.pro.repository.test;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.product.warranty.pro.entities.ProUser;
import org.product.warranty.pro.repository.ProUserRepository;
import org.product.warranty.pro.repository.exception.WPDataAccessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations={"classpath*:application-context.xml"})
public class ProUserRepositoryTest {
	
	/*@Autowired
	@Qualifier("ProUserRepository")
	ProUserRepository proUserRepository;*/
	
//	@Test
	/*public void getAllUsers() throws WPDataAccessException{
		List<ProUser> users = proUserRepository.getAllUsers();
		assertNotNull(users);
	}*/
}
