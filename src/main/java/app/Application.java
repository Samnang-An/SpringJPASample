package app;

import domain.Book;
import domain.CD;
import domain.Customer;
import domain.DVD;
import domain.Order;
import domain.OrderLine;
import domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.Transactional;
import repositories.AddressRepository;
import repositories.CDRepository;
import repositories.CustomerRepository;
import repositories.OrderRepository;

@SpringBootApplication
@EnableJpaRepositories("repositories")
@EntityScan("domain") 
public class Application implements CommandLineRunner{

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	CDRepository cdRepository;

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	AddressRepository addressRepository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	@Transactional
	public void run(String... args) throws Exception {

		orderRepository.deleteAll();
		CD product = new CD();
		product.setArtist("Jenna");
		product.setName("Hibernate 3");
		product.setDescription("Good book on Hibernate");
		product.setPrice(35.50);
		OrderLine ol1 = new OrderLine(2, product);

		Book product2 = new Book();
		product2.setApy(12.0);
		product2.setName("The best of Queen");
		product2.setDescription("Album from 1995");
		product2.setPrice(12.98);
		OrderLine ol2 = new OrderLine(4, product2);

		DVD product3 = new DVD();
		product3.setGenre("Rock");
		product3.setName("Home Alone");
		product3.setDescription("New York Christmas");
		product3.setPrice(100);
		OrderLine ol3 = new OrderLine(4, product3);

		CD product4 = new CD();
		product4.setArtist("U2");
		product4.setName("Everything's Green");
		product4.setDescription("Best Album");
		product4.setPrice(9);
		OrderLine ol4 = new OrderLine(2, product4);

		Order o1 = new Order("234743", "12/10/06", "open");
		o1.addOrderLine(ol1);
		o1.addOrderLine(ol2);
		o1.addOrderLine(ol3);
		o1.addOrderLine(ol4);

		Customer c1 = new Customer("Frank", "Brown", "Mainstreet 1",
				"New York", "43221", "USA");
		c1.addOrder(o1);
		o1.setCustomer(c1);

		orderRepository.save(o1);


		DVD product5 = new DVD();
		product5.setGenre("Rock");
		product5.setName("Home Alone");
		product5.setDescription("New York Christmas");
		product5.setPrice(100);
		OrderLine ol5 = new OrderLine(4, product5);

		CD product6 = new CD();
		product6.setArtist("U2");
		product6.setName("Everything's Green");
		product6.setDescription("Best Album");
		product6.setPrice(9);
		OrderLine ol6 = new OrderLine(2, product6);

		Order o2 = new Order("234743", "12/10/06", "open");
		o2.addOrderLine(ol5);
		o2.addOrderLine(ol6);

		Customer c2 = new Customer("ana", "lee", "Mainstreet 1",
				"whoknow", "3221", "Amsterdam");
		c2.addOrder(o2);
		o2.setCustomer(c2);

		orderRepository.save(o2);

//		orderRepository.findAll().forEach(Application::printOrder);
//
//
//		customerRepository.findAll();
//		cdRepository.findByArtistAndPriceLessThan("U2",10);

		System.out.println("-------------------------");
		System.out.println("1. Method Name Query");
		System.out.println("-------------------------");
		System.out.println("Give All Customers");
		System.out.println("-------------------------");
		customerRepository.findAllBy().forEach(System.out::println);
		System.out.println("-------------------------");
		System.out.println("Give all CD’s from U2 with a price smaller than 10 euro");
		System.out.println("-------------------------");
		cdRepository.findByArtistAndPriceLessThan("U2",10).forEach(System.out::println);
		System.out.println("-------------------------");
		System.out.println("2. Query Named");
		System.out.println("-------------------------");
		System.out.println("Give all customers from the USA.");
		System.out.println("-------------------------");
		customerRepository.findByCounty("USA").forEach(System.out::println);
		System.out.println("-------------------------");
		System.out.println("Give all CD’s from a certain artist");
		System.out.println("-------------------------");
		cdRepository.findByArtist("Jenna").forEach(System.out::println);
		System.out.println("-------------------------");
		System.out.println("3. JPQL query with @Query");
		System.out.println("-------------------------");
		System.out.println("ordernumbers of all orders with status ‘closed’");
		System.out.println("-------------------------");
		orderRepository.findOrderNumberByClosedStatus().forEach(System.out::println);
		System.out.println("-------------------------");
		System.out.println("first and lastnames of all customers who live in Amsterdam");
		System.out.println("-------------------------");
		System.out.println("FirstName and LastName:");
		customerRepository.findFullnameByCountry("Amsterdam").forEach(System.out::println);
		System.out.println("-------------------------");
		System.out.println("ordernumbers of all orders from customers who live in a certain city");
		System.out.println("-------------------------");
		System.out.println("OrderNumber:");
		orderRepository.findOrderNumberByCustomerCountry("New York").forEach(System.out::println);
		System.out.println("-------------------------");
		System.out.println("CD’s from a certain artist with a price bigger than");
		System.out.println("-------------------------");
		cdRepository.findByArtistAndPriceGreaterThan("U2",5);
		System.out.println("-------------------------");
		System.out.println("4. Native Query");
		System.out.println("-------------------------");
		System.out.println("Give all addresses in Amsterdam");
		System.out.println("-------------------------");
		addressRepository.findByCountry("Amsterdam").forEach(System.out::println);
		System.out.println("-------------------------");
		System.out.println("Give all CD’s from U2");
		System.out.println("-------------------------");
		cdRepository.findByArtistNative("U2").forEach(System.out::println);
	}

	public static void printOrder(Order order) {
		System.out.println("Order with orderNumber: " + order.getOrderNumber());
		System.out.println("Order date: " + order.getDate());
		System.out.println("Order status: " + order.getStatus());
		Customer cust = order.getCustomer();
		System.out.println("Customer: " + cust.getFirstName() + " "
				+ cust.getLastName());
		for (OrderLine orderline : order.getOrderLines()) {
			System.out.println("Order line: quantity= "
					+ orderline.getQuantity());
			Product product = orderline.getProduct();
			System.out.println("Product: " + product.getName() + " "
					+ product.getDescription() + " " + product.getPrice() + " " + product.getGenre() + " " +
					" " + product.getApy() + " " + product.getArtist());
		}
	}
}
