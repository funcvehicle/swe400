package gateway;


import static org.junit.Assert.*;

import org.junit.Test;

public class TestKeyGateway {

	@Test
	public void testGenerate() {
		KeyGateway f = new KeyGateway();
		assertTrue(f.generateKey() == 1);
	}

}
