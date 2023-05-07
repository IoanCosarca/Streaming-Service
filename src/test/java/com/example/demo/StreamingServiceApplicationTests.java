package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StreamingServiceApplicationTests {
	/*@Mock
	private DbOperation dbOperation;*/

	@Test
	void contextLoads() {
	}

	/*@Test
	void testDobandaMare()
	{
		OperatiiDobanda op = new OperatiiDobanda();
		int result = op.calculDobanda(45);
		int expected = 0;
		assertTrue(result == expected);
	}

	@Test
	void testUser1()
	{
		UserBank userBank = new UserBank("Andrei", Risk.BIG);
		when (dbOperation.getUser()).thenReturn(userBank);
		OperatiiDobanda op = new OperatiiDobanda(dbOperation);
		int result = op.riskCheck();
		int expected = 2;
		assertTrue(result == expected);
		verify(dbOperation).getUser();
	}

	@Test
	void testUser2()
	{
		UserBank userBank = new UserBank("Louise", Risk.MEDIUM);
		when (dbOperation.getUser()).thenReturn(userBank);
		OperatiiDobanda op = new OperatiiDobanda(dbOperation);
		int result = op.riskCheck();
		int expected = 1;
		assertTrue(result == expected);
		verify(dbOperation).getUser();
	}

	@Test
	void testUser3()
	{
		UserBank userBank = new UserBank("John", Risk.SMALL);
		when (dbOperation.getUser()).thenReturn(userBank);
		OperatiiDobanda op = new OperatiiDobanda(dbOperation);
		int result = op.riskCheck();
		int expected = 0;
		assertTrue(result == expected);
		verify(dbOperation).getUser();
	}*/
}
