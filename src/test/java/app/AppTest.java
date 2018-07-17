package app;

import app.entity.Rental;
import app.exceptions.MyNotFoundException;
import app.repository.CustomerRepository;
import app.repository.PaymentRepository;
import app.repository.RentalRepository;
import app.service.CustomerService;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.Assert;
import app.exceptions.*;

import javax.annotation.Resource;

import java.util.Optional;

import static org.hamcrest.Matchers.equalTo;
import static app.ErrorCode.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@PropertySource("classpath:application.properties")
public class AppTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    CustomerService customerServiceMock;
    @Autowired
    CustomerRepository customerRepository;

    @Resource
    private PaymentRepository paymentRepository;

    @Autowired
    private RentalRepository rentalRepository;
    private CustomerService customerService;

    @Test
    public void getHello() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/hello").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("hello")));
    }

    @Test
    public void testRentAFilmCustomerNotFound() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/customer/6/rent/2").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.errorCode").value(FILM_RENT_CUSTOMER_ID_NOT_FOUND.getCode()));
    }

    @Test
    public void testRentAFilmFilmNotFound() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/customer/2/rent/12").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.errorCode").value(FILM_RENT_FILM_WITH_GIVEN_ID_NOT_FOUND.getCode()));
    }


    @Test
    public void testRentAFilmFilmNotAvailable() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/customer/4/rent/3").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.errorCode").value(FILM_RENT_INVENTORY_FOR_FILM_NOT_FOUND.getCode()));
    }

    @Test
    public void testRentAFilmAllInventoryOfThatFilmAlreadyRented() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/customer/3/rent/3").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.errorCode").value(FILM_RENT_NO_AVAILABLE_DVDS.getCode()));
    }

    @Test
    public void testCreateRentalAndPayment() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/customer/1/rent/2"))
                .andExpect(status().isOk())
                .andExpect(header().string("Rental1", "/rental/5"));
    }
}