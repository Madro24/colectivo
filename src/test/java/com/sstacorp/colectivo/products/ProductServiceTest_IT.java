package com.sstacorp.colectivo.products;

import java.io.IOException;

import junit.framework.TestCase;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.sstacorp.colectivo.catalogs.ImageTypes;
import com.sstacorp.colectivo.catalogs.ProductTypes;
import com.sstacorp.colectivo.dto.ImageDTO;
import com.sstacorp.colectivo.dto.ProductDTO;
import com.sstacorp.colectivo.services.ProductService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/WEB-INF/spring/appServlet/servlet-context.xml"})
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class })
public class ProductServiceTest_IT  extends TestCase {

	private static final String PRODUCT_NAME_TEST = "Chilaquiles de Prueba";
	private static final String PRODUCT_DESCRIPTION_TEST = "Platillo para testing";
	private static final Double PRODUCT_PRICE_TEST = 89.90;
	private static final Long COMPANY_ID = 1L;
	
	@Autowired
	ProductService productService;
	
	@Test
	@Transactional
	public void addProduct_success() throws Exception{
		
		ProductDTO product = new ProductDTO(null,PRODUCT_NAME_TEST,PRODUCT_DESCRIPTION_TEST,PRODUCT_PRICE_TEST,null,ProductTypes.BREAKFAST.toString(),COMPANY_ID);
		
		ProductDTO productResult =  productService.addProduct(product);
		
		assertNotNull(productResult);
		assertNotNull(productResult.getId());
		
	}
	
    // helper methods
    
    private MultipartFile generateMultiPartFile(String fileName, String contentType, byte[] data) throws IOException {
        return new MockMultipartFile(fileName, fileName, contentType, data);
    }

    private MultipartFile generateValidMultiPartFile() throws IOException {
        return generateMultiPartFile(PRODUCT_NAME_TEST, "image/jpeg", "chilaquiles.jpg");
    }

    private MultipartFile generateMultiPartFile(String fileName, String contentType, String resourceName) throws IOException {
        return new MockMultipartFile(fileName, fileName, contentType, new ClassPathResource(resourceName).getInputStream());
    }
}
