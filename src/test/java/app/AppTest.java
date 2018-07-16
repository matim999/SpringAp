package app;

import app.repository.CustomerRepository;
import app.repository.PaymentRepository;
import app.repository.RentalRepository;
import app.service.CustomerService;
import org.junit.Test;
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
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import javax.annotation.Resource;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

    @Test
    public void getHello() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/hello").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("hello")));
    }

    @Resource
    private PaymentRepository paymentRepository;
    private RentalRepository rentalRepository;
    private CustomerService customerService;

    @Test
    public void rentAFilmSaveIsOk() throws Exception {
//        RentDto rentDto = new RentDto(76, 14);
//        RentResponseDto rentResponseDto = customerService.rentAFilm(rentDto);
        mvc.perform(MockMvcRequestBuilders.post("/customer/66/rent/35").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testCreateRentalAndPayment() throws Exception {
//        RentDto rentDto = new RentDto(64, 14);
        MvcResult result = mvc.perform(MockMvcRequestBuilders.post("/customer/1/rent/2")).andDo(MockMvcResultHandlers.print()).andReturn();
//        System.out.println(rentalRepository.findById(3).toString());
        String resultStr = result.getResponse().getHeaderValue("Rental1").toString();
        System.out.println(resultStr);

//        List<Integer> list = new ArrayList<>();
//        list.add(1);
//        System.out.println("List:");
//        System.out.println(customerRepository.findById(1).toString());
//        System.out.println(list.toString());
//        List<Pair> pair = customerService.rentMultipleFilms(1, list);
//        System.out.println(pair.size());

//                .andExpect(model().attribute());
    }
}