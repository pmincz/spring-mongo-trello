package sample.util;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.web.context.WebApplicationContext;

import sample.Boot;

/**
 * Abstract entity for tests
 * @author pmincz
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = Boot.class)
@Transactional
public class AbstractRestTest {
	
	@Autowired
	WebApplicationContext context;
	
	protected MockMvc mvc;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mvc = MockMvcBuilders.webAppContextSetup(context).build();
		TransactionSynchronizationManager.initSynchronization();
	}
	
	@After
	public void tearDown() {

		for (Object key : TransactionSynchronizationManager.getResourceMap().keySet()) {
			TransactionSynchronizationManager.unbindResource(key);
		}

		TransactionSynchronizationManager.clearSynchronization();
	}

}
