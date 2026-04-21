package com.icodian.careervia.job.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.icodian.careervia.job.dto.ApplicationRequestDTO;
import com.icodian.careervia.job.dto.ApplicationResponseDTO;
import com.icodian.careervia.job.entity.constant.ApplicationStatus;
import com.icodian.careervia.job.service.ApplicationService;

@RunWith(SpringRunner.class)
@WebMvcTest(ApplicationController.class)
@AutoConfigureMockMvc
public class ApplicationControllerTest {
	// This class is a placeholder for testing the ApplicationController.
	// You can add test methods here to test the end points of the
	// ApplicationController.	
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ApplicationService applicationService;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@BeforeEach
	public void setUp() {
		//Set up MockMvc for testing - Initialize Fake Web Server So we can run HTTP requests.
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	@Test
	public void testCreateApplication() throws Exception {
		// You can implement this test method to test the createApplication end point of
		// the ApplicationController.
		ApplicationRequestDTO request = new ApplicationRequestDTO();
		ApplicationResponseDTO response = new ApplicationResponseDTO();
		
//		Mock the applicationService to return the response when the createApplication method is called with the request.
		when(applicationService.createApplication(request)).thenReturn(response);

		// You can use mockMvc to perform a POST request to the /api/applications end
		// point with the request and verify the response.
		mockMvc.perform(post("/api/applications")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(request)))
				.andExpect(status().isCreated());
		
	}
	
	@Test
	public void testGetAllApplications() throws Exception {
		// You can implement this test method to test the getAllApplications end point
		// of
		// the ApplicationController.
		List<ApplicationResponseDTO> responseList = new ArrayList<>();
		ApplicationResponseDTO dto = new ApplicationResponseDTO();
		
		dto.setApplicationId(1L);
		dto.setJobId(1L);
		dto.setUserId(1L);
		dto.setApplicationStatus(ApplicationStatus.APPLIED);
		dto.setRemarks("Application selected");
		dto.setAppliedDate(LocalDate.parse("2026-04-04"));
		
		responseList.add(dto);
		
		when(applicationService.getAllApplications()).thenReturn(responseList);
		
		mockMvc.perform(get("/api/applications")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	
	@Test
	public void testGetApplicationByApplicationId() throws Exception {
		// You can implement this test method to test the getApplicationByApplicationId
		// end point of
		// the ApplicationController.
		Long applicationId = 1L;
		ApplicationResponseDTO response = new ApplicationResponseDTO();
		
		when(applicationService.getApplicationByApplicationId(applicationId)).thenReturn(List.of(response));
		
		mockMvc.perform(get("/api/applications/{applicationId}", applicationId).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

	}
	
	
	
	@Test
	public void testGetApplicationByJobId() throws Exception {
		// You can implement this test method to test the getApplicationByJobId end
		// point of
		// the ApplicationController.
		
	}

}
