package app;

import app.repository.CustomerRepository;
import app.repository.FilmRepository;
import app.repository.PaymentRepository;
import app.repository.RentalRepository;
import app.service.CustomerService;
import org.junit.Test;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.annotation.Resource;

import java.util.List;

import static app.ErrorCode.*;
import static org.hamcrest.Matchers.equalTo;
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
    @Autowired
    private FilmRepository filmRepository;

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
                .andExpect(header().string("Location", "/rental/5"));
        rentalRepository.findById(5);
        Assert.assertEquals(customerRepository.findById(1).get(), rentalRepository.findById(5).get().getCustomer());
        Assert.assertEquals(filmRepository.findById(2).get(), rentalRepository.findById(5).get().getInventory().getFilm());
        Assert.assertEquals(rentalRepository.findById(5).get(), paymentRepository.findByRentalRentalId(5).get().getRental());
    }

    @Test
    public void testCreateMultipleRentalAndPayment() throws Exception {
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/customer/1/rent/2,4"))
                .andExpect(status().isOk()).andReturn();
        List<String> headers = mvcResult.getResponse().getHeaders("Location");
        int i = 2;
        for (String s : headers){
            Assert.assertEquals("/rental/" + i++, );
            s.equals("/rental/" + i++);
            System.out.println(s + "/rental/" + i + s.equals("/rental/" + i));
        }
    }
}