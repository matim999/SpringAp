//package app;
//
//import app.repository.CustomerRepository;
//import app.repository.FilmRepository;
//import app.repository.PaymentRepository;
//import app.repository.RentalRepository;
//import app.service.CustomerService;
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.http.MediaType;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.annotation.Resource;
//import java.util.List;
//import java.util.stream.Collectors;
//
//import static app.ErrorCode.*;
//import static org.hamcrest.Matchers.equalTo;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest
//@AutoConfigureMockMvc
//@PropertySource("classpath:application.properties")
//@Transactional
//public class AppTest {
//
//    @Autowired
//    CustomerService customerServiceMock;
//    @Autowired
//    CustomerRepository customerRepository;
//    @Autowired
//    private MockMvc mvc;
//    @Resource
//    private PaymentRepository paymentRepository;
//
//    @Autowired
//    private RentalRepository rentalRepository;
//    @Autowired
//    private FilmRepository filmRepository;
//
//    @Test
//    public void getHello() throws Exception {
//        mvc.perform(MockMvcRequestBuilders.get("/hello").accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(content().string(equalTo("hello")));
//    }
//
//    @Test
//    public void testRentAFilmCustomerNotFound() throws Exception {
//        mvc.perform(MockMvcRequestBuilders.post("/customer/6/rent/2").accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isNotFound())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.errorCode").value(FILM_RENT_CUSTOMER_ID_NOT_FOUND.getCode()));
//    }
//
//    @Test
//    public void testRentAFilmFilmNotFound() throws Exception {
//        mvc.perform(MockMvcRequestBuilders.post("/customer/2/rent/12").accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isNotFound())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.errorCode").value(FILM_RENT_FILM_WITH_GIVEN_ID_NOT_FOUND.getCode()));
//    }
//
//
//    @Test
//    public void testRentAFilmFilmNotAvailable() throws Exception {
//        mvc.perform(MockMvcRequestBuilders.post("/customer/4/rent/3").accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isNotFound())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.errorCode").value(FILM_RENT_INVENTORY_FOR_FILM_NOT_FOUND.getCode()));
//    }
//
//    @Test
//    public void testRentAFilmAllInventoryOfThatFilmAlreadyRented() throws Exception {
//        mvc.perform(MockMvcRequestBuilders.post("/customer/3/rent/3").accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isNotFound())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.errorCode").value(FILM_RENT_NO_AVAILABLE_DVDS.getCode()));
//    }
//
//    @Test
//    public void testCreateRentalAndPayment() throws Exception {
//        int customerId = 1;
//        int filmId = 2;
//        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/customer/{id}/rent/{filmId}", customerId, filmId))
//                .andExpect(status().isOk()).andReturn();
//        int rentalId = Integer.parseInt(mvcResult.getResponse().getHeader("Location").substring(
//                mvcResult.getResponse().getHeader("Payload").lastIndexOf('/') + 1,
//                mvcResult.getResponse().getHeader("Payload").length()));
//        Assert.assertEquals(customerRepository.findById(customerId).get(), rentalRepository.findById(rentalId).get().getCustomer());
//        Assert.assertEquals(filmRepository.findById(filmId).get(), rentalRepository.findById(rentalId).get().getInventory().getFilm());
//        Assert.assertEquals(rentalRepository.findById(rentalId).get(), paymentRepository.findByRentalRentalId(rentalId).get().getRental());
//    }
//
//    @Test
//    public void testCreateMultipleRentalAndPayment() throws Exception {
//        int customerId = 1;
//        int[] filmIds = {4, 5};
//        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/customer/{id}/rent/{filmId1},{filmId2}", customerId, filmIds[0], filmIds[1]))
//                .andExpect(status().isOk()).andReturn();
//        List<Integer> headers = mvcResult.getResponse().getHeaders("Payload").stream()
//                .map(header -> Integer.parseInt(header.substring(
//                        header.lastIndexOf('/') + 1,
//                        header.length())))
//                .collect(Collectors.toList());
//
//        for (int i = 0; i < headers.size(); i++) {
//            Assert.assertEquals(customerRepository.findById(customerId).get(), rentalRepository.findById(headers.get(i)).get().getCustomer());
//            Assert.assertEquals(filmRepository.findById(filmIds[i]).get(), rentalRepository.findById(headers.get(i)).get().getInventory().getFilm());
//            Assert.assertEquals(rentalRepository.findById(headers.get(i)).get(), paymentRepository.findByRentalRentalId(headers.get(i)).get().getRental());
//        }
//    }
//
//}