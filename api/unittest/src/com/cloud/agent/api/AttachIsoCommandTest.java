package com.cloud.agent.api;

import static org.junit.Assert.*;
import org.junit.Test;

import com.cloud.agent.api.*;

public class AttachIsoCommandTest {

	@Test
	public void testGetVmName() {
		AttachIsoCommand aic = new AttachIsoCommand("x", "y", false);
		String vmName = aic.getVmName();
	        assertTrue(vmName.equals("x"));
	}

}
