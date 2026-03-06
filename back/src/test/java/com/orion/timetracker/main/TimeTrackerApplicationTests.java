package com.orion.timetracker.main;

import app.TimeTrackerApplication;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = TimeTrackerApplication.class)
class TimeTrackerApplicationTests {

	@Test
	void contextLoads() {
		Assertions.assertTrue(true);
	}

}
