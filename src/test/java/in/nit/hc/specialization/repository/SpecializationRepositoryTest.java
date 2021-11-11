package in.nit.hc.specialization.repository;

import static org.assertj.core.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import in.nit.hc.entity.Specialization;
import in.nit.hc.repository.SpecializationRepository;

@DataJpaTest(showSql = true)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false) 
@TestMethodOrder(OrderAnnotation.class)
public class SpecializationRepositoryTest {
	
	@Autowired
	private SpecializationRepository repo;
	
	/**
	 * 1. Test save operation
	 */
	@Test
	@Order(1)
	public void testSpecCreate() {
		
		Specialization spec = new Specialization(null,"CRDLS","Cardiologists","They’re experts on the heart and blood vessels.");
		spec = repo.save(spec);
		//Long l = spec.getId();
		//l=null;
		//assertNotNull(l, "Spec is not created !");
		assertNotNull(spec.getId(), "spec not created !"); 
	}
	
	/**
	 * 2. Test display all operations
	 */
	@Test
	@Order(2)
	public void testSpecFindAll() {
		
		List<Specialization> list = repo.findAll();
	//	list=null;
		assertNotNull(list);
		
		if(list.isEmpty()) {
			fail("No data exist in database");
		}
		
		
	}
}	
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

