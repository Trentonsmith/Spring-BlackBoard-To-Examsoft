package com.example.bbtoes;

import com.example.bbtoes.Services.TestConversionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BBtoEsApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void testIfMockTestIsConvertedCorrectly(){
		// Arrange
		TestConversionService testConversionService = new TestConversionService();
		boolean conversionSuccessful = false;
		com.example.bbtoes.Model.Test test = new com.example.bbtoes.Model.Test();
		test.setTestData(returnTestString());
		test.setTestTitle("TEST");
		String realResult = testConversionService.testConvert(test).trim();
		String desiredResult = returnDesiredResultString();
		Assertions.assertTrue(realResult.equals(desiredResult));



	}

	public static String returnDesiredResultString(){
		String pleaseWork = """
				Title: TEST 1. Which of the following actions would BEST assess the strength of the supraspinatus?1
				*a. Have the patient abduct the arm from the fully adducted position against resistance
				b. Have the patient adduct the arm from the fully abducted position against resistance
				c. Have the patient extend the elbow, abduct the arm and medially rotate against resistance
				d. Have the patient flex the elbow, abduct the arm and medially rotate against resistance
				    
				Title: TEST 2. Compression of which of the following nerves may result in weak thumb flexion?
				a. Deep Ulnar N
				b. Posterior Interosseus N
				*c. Recurrent branch of Median N
				d. Superficial Radial N
				    
				Title: TEST 3. A patient with suspected nerve entrapment experiences weakness with elbow extension and numbness over the dorsum of the wrist and hand. Where is the MOST LIKELY location of this entrapment?
				a. Cubital Fossa
				b. Cubital Tunnel
				c. Quadrangular Space
				*d. Triangular Interval
				    
				Title: TEST 4. A patient with a complete injury to the musculocutaneous nerve at the level of coracobrachialis would have some ability to flex at the elbow because of the action of which of the following muscles?
				a. Bicep brachii m.
				b. Brachialis m.
				*c. Brachioradialis m.
				d. Ulnar head of flexor carpi ulnaris m.
				    
				Title: TEST 5. With the origin fixed, what are the primary actions of the pectoralis major muscle on the humerus?
				a. Abduction and lateral rotation
				b. Abduction and medial rotation
				c. Adduction and lateral rotation
				*d. Adduction and medial rotation
				""".trim();
		return pleaseWork;
	}
	public static String returnTestString(){
		String testDataTest = """
Question 1 

1 

points 

Which of the following actions would BEST assess the strength of the supraspinatus? 

 

Answer 1 

Have the patient abduct the arm from the fully adducted position against resistance 

 

Correct answer 

Answer 2 

Have the patient adduct the arm from the fully abducted position against resistance 

 

Answer 3 

Have the patient extend the elbow, abduct the arm and medially rotate against resistance 

 

Answer 4 

Have the patient flex the elbow, abduct the arm and medially rotate against resistance 

 

Question 2 

1 

points 

Compression of which of the following nerves may result in weak thumb flexion? 

 

Answer 1 

Deep Ulnar N 

 

Answer 2 

Posterior Interosseus N 

 

Answer 3 

Recurrent branch of Median N 

 

Correct answer 

Answer 4 

Superficial Radial N 

 

Question 3 

1 

points 

A patient with suspected nerve entrapment experiences weakness with elbow extension and numbness over the dorsum of the wrist and hand. Where is the MOST LIKELY location of this entrapment? 

 

Answer 1 

Cubital Fossa 

 

Answer 2 

Cubital Tunnel 

 

Answer 3 

Quadrangular Space 

 

Answer 4 

Triangular Interval 

 

Correct answer 

Question 4 

1 

points 

A patient with a complete injury to the musculocutaneous nerve at the level of coracobrachialis would have some ability to flex at the elbow because of the action of which of the following muscles? 

 

Answer 1 

Bicep brachii m. 

 

Answer 2 

Brachialis m. 

 

Answer 3 

Brachioradialis m. 

 

Correct answer 

Answer 4 

Ulnar head of flexor carpi ulnaris m. 

 

Question 5 

1 

points 

With the origin fixed, what are the primary actions of the pectoralis major muscle on the humerus? 

 

Answer 1 

Abduction and lateral rotation 

 

Answer 2 

Abduction and medial rotation 

 

Answer 3 

Adduction and lateral rotation 

 

Answer 4 

Adduction and medial rotation 

 

Correct answer 
                   """.replaceAll("\n\n"," \r\n\r\n").replaceAll("\r\n\r\n\r\n\r\n", "\r\n\r\n \r\n\r\n").trim();
		return testDataTest;
	}

}
