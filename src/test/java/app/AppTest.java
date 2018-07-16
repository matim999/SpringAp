package app;

import app.repository.CustomerRepository;
import app.repository.requestDTO.RentDto;
import app.repository.PaymentRepository;
import app.repository.RentalRepository;
import app.service.CustomerService;
import org.javatuples.Pair;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@PropertySource("classpath:application1.properties")
public class AppTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    CustomerService customerServiceMock;
    @MockBean
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
        RentDto rentDto = new RentDto(64, 14);
        mvc.perform(MockMvcRequestBuilders.post("/customer/94/rent/78")).andDo(MockMvcResultHandlers.print());
        List<Integer> list = new ArrayList<>();
        list.add(78);
        System.out.println("List:");
        System.out.println(list.toString());
        List<Pair> pair = customerService.rentMultipleFilms(94, list);
        System.out.println(pair.size());

//                .andExpect(model().attribute());
    }
}