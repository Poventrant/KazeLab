package kaze.annotation;

@TesterInfo(
	priority = TesterInfo.Priority.HIGH,
	createdBy = "mkyong.com",  
	tags = {"sales","test" }
)
public class TestExample {

	@KazeTest
	void testA() {
	  if (true)
		throw new RuntimeException("This test always failed");
	}

	@KazeTest(enabled = false)
	void testB() {
	  if (false)
		throw new RuntimeException("This test always passed");
	}

	@KazeTest(enabled = true)
	void testC() {
	  if (10 > 1) {
		// do nothing, this test always passed.
	  }
	}

}