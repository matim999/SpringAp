package app.service;

import app.entity.Rental;
import app.repository.CustomerRepository;
import app.repository.RentalRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Logger;

import static java.time.temporal.ChronoUnit.DAYS;

@Component
public class MailSenderImpl implements MailSender {
    private static final Logger logger = Logger.getLogger(CountryService.class.getName());
    private final CustomerRepository customerRepository;
    private final RentalRepository rentalRepository;

    public MailSenderImpl(CustomerRepository customerRepository, RentalRepository rentalRepository) {
        this.customerRepository = customerRepository;
        this.rentalRepository = rentalRepository;
    }

    @Override
    @Scheduled(fixedRate = 5000)
    public void sendEmailToCustomersAboutRetentionFee() {
        CurrentTime.updateTime();
        HashSet<Integer> customerSet = new HashSet<>();
        List<Rental> rentals = rentalRepository.findAllByReturnDateIsNull().orElse(new ArrayList<>());
        rentals.stream()
                .filter(rental -> DAYS.between(rental.getRentalDate(), CurrentTime.now) > rental.getInventory().getFilm().getRentalDuration())
                .forEach(rental -> customerSet.add(rental.getCustomer().getCustomerId()));
        System.out.println("List of customers who need to pay retention fee: ");
        System.out.println(customerSet.toString());
    }
}
