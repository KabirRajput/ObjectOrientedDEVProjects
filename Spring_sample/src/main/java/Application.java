import com.kabir.service.CustomerService;
import com.kabir.service.CustomerServiceImpl;

public class Application {

	public static void main(String[] args) {

		CustomerService service = new CustomerServiceImpl();

		System.out.println(service.findAll().get(0).getFirstname());

	}

}
