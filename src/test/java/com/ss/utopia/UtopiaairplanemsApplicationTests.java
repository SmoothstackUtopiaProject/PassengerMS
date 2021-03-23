package com.ss.utopia;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ss.utopia.repositories.PassengerRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@SpringBootTest
class PassengerMSTests {

  private final String SERVICE_PATH_PASSENGERS = "/passengers";

  private MockMvc mvc;

  private final ObjectMapper mapper = new ObjectMapper();

  @Mock
  private PassengerRepository repository;

  @Test
  void test_validPassengerTestModel() throws Exception {
    assertEquals(Integer.valueOf(1), MOCKPassengerRepository.getTestPassenger().getPassengerId());
    assertEquals(Integer.valueOf(6), MOCKPassengerRepository.getTestPassenger().getPassengerBookingId());
    assertEquals("AFHAJKFHKAJS", MOCKPassengerRepository.getTestPassenger().getPassengerPassportId());
    assertEquals("FirstName1", MOCKPassengerRepository.getTestPassenger().getPassengerFirstName());
    assertEquals("LastName1", MOCKPassengerRepository.getTestPassenger().getPassengerLastName());
    assertEquals("MALE", MOCKPassengerRepository.getTestPassenger().getPassengerSex());
    assertEquals("1987-3-14", MOCKPassengerRepository.getTestPassenger().getPassengerDateOfBirth());
    assertEquals("2342 Water Lane 4291 RockCity Virginia", MOCKPassengerRepository.getTestPassenger().getPassengerAddress());
    assertTrue( MOCKPassengerRepository.getTestPassenger().getPassengerIsVeteran());
  }

  @Test
  void test_findAllPassengers_withValidPassengers_thenStatus200() throws Exception {
    when(repository.findAll()).thenReturn(MOCKPassengerRepository.findAllWithResults());

    MvcResult response = mvc
      .perform(get(SERVICE_PATH_PASSENGERS).header("Accept", "application/json"))
      .andExpect(status().is(200))
      .andReturn();

    assertEquals(mapper.writeValueAsString(MOCKPassengerRepository.getTestPassengerList()), response.getResponse().getContentAsString());
  }

  @Test
  void test_findAllPassengers_withNoValidPassengers_thenStatus204() throws Exception {
    when(repository.findAll()).thenReturn(MOCKPassengerRepository.findAllWithNoResults());

    MvcResult response = mvc
      .perform(get(SERVICE_PATH_PASSENGERS).header("Accept", "application/json"))
      .andExpect(status().is(204))
      .andReturn();

    assertEquals("", response.getResponse().getContentAsString());
  }

  @Test
  void test_findById_withValidPassenger_thenStatus200() throws Exception {
    when(repository.findById(1)).thenReturn(MOCKPassengerRepository.findById(1));

    MvcResult response = mvc
      .perform(get(SERVICE_PATH_PASSENGERS + "/1").header("Accept", "application/json"))
      .andExpect(status().is(200))
      .andReturn();

    assertEquals(mapper.writeValueAsString(MOCKPassengerRepository.getTestPassenger()), response.getResponse().getContentAsString());
  }

  @Test
  void test_findById_withInvalidPassenger_thenStatus404() throws Exception {
    when(repository.findById(0)).thenReturn(MOCKPassengerRepository.findById(0));

    MvcResult response = mvc
      .perform(get(SERVICE_PATH_PASSENGERS + "/0").header("Accept", "application/json"))
      .andExpect(status().is(404))
      .andReturn();

    assertEquals("", response.getResponse().getContentAsString());
  }

  @Test
  void test_findByIataId_withBadParams_thenStatus400() throws Exception {
    when(repository.findById(0)).thenReturn(MOCKPassengerRepository.findById(0));

    MvcResult response = mvc
      .perform(get(SERVICE_PATH_PASSENGERS + "/NotAnInteger").header("Accept", "application/json"))
      .andExpect(status().is(400))
      .andReturn();

    assertEquals("", response.getResponse().getContentAsString());
  }
}
