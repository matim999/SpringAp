package app;

import app.DTO.requestDTO.RentDto;
import app.DTO.responseDTO.RentResponseDto;
import app.entity.Customer;
import app.repository.CustomerRepository;
import app.repository.PaymentRepository;
import app.repository.RentalRepository;
import app.service.CustomerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.annotation.Resource;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AppTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void getHello() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/hello").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("hello")));
    }

//    @Resource
//    private PaymentRepository paymentRepository;
//    private RentalRepository rentalRepository;
//    private CustomerService customerService;
//
//    @Test
//    public void rentAFilmSaveIsOk() throws Exception {
//        RentDto rentDto = new RentDto(76, 14);
//        RentResponseDto rentResponseDto = customerService.rentAFilm(rentDto);
//        mvc.perform(MockMvcRequestBuilders.post("/customer/76/rent/14").accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
//    }
}