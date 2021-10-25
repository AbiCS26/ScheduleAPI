package inputs;

import static org.junit.Assert.assertFalse;

import org.junit.jupiter.api.Test;

class IDGenerationTest {

	@Test
	void testForGenerateID() {
		String ID = IDGeneration.generateID();
		String ID2 = IDGeneration.generateID();
		assertFalse(ID.equals(ID2));
	}

}
