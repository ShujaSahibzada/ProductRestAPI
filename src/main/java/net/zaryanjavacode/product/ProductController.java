package net.zaryanjavacode.product;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

	@Autowired
	private ProductService service;
	
	@RequestMapping("/list_product")
    public String listContact(Model model) {
        List<Product> productList = service.listAll();
        model  .addAttribute("product", productList);           
        return "product";
    }

	@GetMapping("/products")
	public List<Product> list() {
		return service.listAll();
	}
//    write in cmd commad prompt
//	curl http://localhost:8080/products

//	@GetMapping("/products/{id}")
//	public Product get(@PathVariable Integer id) {
//		return service.get(id);
//	}
//    write in cmd commad prompt
//	curl http://localhost:8080/products/1
	@GetMapping("/products/{id}")
	public ResponseEntity<Product> get(@PathVariable Integer id) {
		try {
			Product product = service.get(id);
			return new ResponseEntity<Product>(product, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
		}
	}

//    write in cmd commad prompt
//    curl -X POST -H "Content-Type: application/json" 
//    -d "{\"name\":\"XBox 360\",\"price\":299.99}" 
//    http://localhost:8080/products
	@PostMapping("/products")
	public void add(@RequestBody Product product) {
		service.save(product);

	}

//    write in cmd commad prompt
//	curl -X PUT -H "Content-Type: application/json" 
//	-d "{\"id\":1,\"name\":\"iPad\",\"price\":888}" 
//	http://localhost:8080/products/1
	@PutMapping("/products/{id}")
	public ResponseEntity<?> update(@RequestBody Product product, @PathVariable Integer id) {
		try {
			Product existProduct = service.get(id);
			service.save(product);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
//  write in cmd commad prompt
//	curl -X DELETE http://localhost:8080/products/1
	@DeleteMapping("/products/{id}")
	public void delete(@PathVariable Integer id) {
		service.delete(id);
	}
}
