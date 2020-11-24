package net.zaryanjavacode.ShujaRestAPI;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.el.stream.Stream;

import net.zaryanjavacode.product.Product;

@ExtendWith({ RestDocumentationExtension.class, SpringExtension.class})
@SpringBootTest
@AutoConfigureRestDocs(outputDir = "target/generated-snippets")
class ShujaRestApiApplicationTests {


    @Autowired
    private WebApplicationContext context;
    
    private MockMvc mockMvc;

    List<Product> products=null;
    
    @BeforeEach
    public void setUp(WebApplicationContext webApplicationContext,
                      RestDocumentationContextProvider restDocumentation) {

        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocumentation))
                .build();

        products = Stream.of(new Product(101, "Mobile", 100)
                ,new Product(102, "laptop", 104))
                .collect(Collectors.toList());
    }
	@Test
	public void testAddOrder() throws Exception {
        String productsJson=new ObjectMapper().writeValueAsString(products);
        mockMvc.perform(post("/products")
                .content(productsJson)
                .contentType("application/json")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(productsJson));
//                .andDo(document("{methodName}",
//                        preprocessRequest(prettyPrint()),
//                        preprocessResponse(prettyPrint())));
    }
		
	@Test
    public void testGetOrders() throws Exception {
           mockMvc.perform(get("/products")
                   .contentType("application/json")).andDo(print())
                   .andExpect(status().isOk())
                   .andExpect(MockMvcResultMatchers.content().json(new ObjectMapper().writeValueAsString(products)));     
//                   .andDo(document("{methodName}",
//                           preprocessRequest(prettyPrint()),
//                           preprocessResponse(prettyPrint())));
    }

}
