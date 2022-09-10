package edu.tiendung.InternshipProject.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.tiendung.InternshipProject.domain.Response;
import edu.tiendung.InternshipProject.entity.Admin;
import edu.tiendung.InternshipProject.entity.Category;
import edu.tiendung.InternshipProject.entity.Products;
import edu.tiendung.InternshipProject.repository.ProductRepository;
import edu.tiendung.InternshipProject.service.BaseService;
import edu.tiendung.InternshipProject.service.ProductService;

@RestController
@CrossOrigin(origins = "*", maxAge = 4800, allowCredentials = "false")
@RequestMapping("/api/v1/product")
public class ProductController {

	@Autowired
	ServletContext context;
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ProductRepository prodReponse;
	
	@GetMapping("/list")
	public List<Products> getAllProduct(){
		return productService.getAllProduct();
	}
	
	@GetMapping("/page")
	public ResponseEntity<?> pageProduct(@PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable){
		Page<Products> products = productService.findAllProduct(pageable);
		if(products.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(products ,HttpStatus.OK);
	}
	
	@GetMapping("/page/search/{name}")
	public ResponseEntity<?> searchByName(@PathVariable String name ,@PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable){
		Page<Products> products = productService.findAllByNameContaining(name, pageable);
		if(products.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(products ,HttpStatus.OK);
	}
	
	@GetMapping(path="/images/{id}")
	 public byte[] getPhoto(@PathVariable("id") Long id) throws Exception{
		 Products product = productService.findById(id);
		 return Files.readAllBytes(Paths.get(context.getRealPath("/Images/Product/")+product.getImage()));
	 }
	
//	@PostMapping("/create")
//	public ResponseEntity<Product> crateProduct(){
//		return new ResponseEntity<>();
//	}
//	@PostMapping("/create")
//	public ResponseEntity<Product> createProduct(@RequestBody Product product){
//		return new ResponseEntity<>(productService.save(product), HttpStatus.CREATED);
//	}
	
//	@PostMapping("/create")
//	public ResponseEntity<Response> createProduct(@RequestParam("file") MultipartFile file, 
//			@RequestParam("product") String product)throws JsonParseException, JsonMappingException, Exception{
//		Product prod = new ObjectMapper().readValue(product, Product.class);
//		prod.setImage(file.getOriginalFilename());
//		Product dbprod = productService.save(prod);
//		
//		if(dbprod != null) {
//			return new ResponseEntity<Response>(new Response("luu thanh cong"), HttpStatus.OK);
//		}else {
//			return new ResponseEntity<Response>(new Response("LOI dbprod null"), HttpStatus.BAD_REQUEST);
//		}
//	}
	@PostMapping("/create")
	public ResponseEntity<Response> createProduct(@RequestParam("file") MultipartFile image,
			@RequestParam(required= false, name="product") String product) throws JsonParseException, JsonMappingException, Exception{
		
		System.out.println("*******************OK****************");
		Products prod = new ObjectMapper().readValue(product, Products.class);
		System.out.println("prod =="+prod);
		boolean isExit = new File(context.getRealPath("/Images/Product/")).exists();
		if(!isExit) {
			new File (context.getRealPath("/Images/Product/")).mkdir();
			
		}
		String filename = image.getOriginalFilename();
		
//		String newFileName = FilenameUtils.getBaseName(filename)+"_"+System.currentTimeMillis()+"."+FilenameUtils.getExtension(filename);
//		
		String newFileName = FilenameUtils.getBaseName(filename)+"."+FilenameUtils.getExtension(filename);
		
		File serverFile = new File( context.getRealPath("/Images/Product/"+File.separator+newFileName));
		System.out.println("newFileName == "+newFileName);
		System.out.println("serverFile == "+serverFile);
		try {
			
			FileUtils.writeByteArrayToFile(serverFile, image.getBytes());
		}catch(Exception e){
			e.printStackTrace();
		}
		
		prod.setImage(newFileName);
		Products products = prodReponse.save(prod);
		if(products != null) {
			return new ResponseEntity<Response>(new Response("Luu thanh cong"), HttpStatus.OK);
		}else {
			return new ResponseEntity<Response>(new Response("Khong thanh cong"), HttpStatus.BAD_REQUEST);
			
		}
		
	}
	
//	@PostMapping("/create/image")
//	public ResponseEntity<?> upload(@RequestParam("id") Long id, HttpServletRequest request, HttpServletResponse response){
//		try {
//			Product product = productService.findById(id);
//			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
//			Iterator<String> it = multipartRequest.getFileNames();
//			MultipartFile multipartFile = multipartRequest.getFile(it.next());
//			String fileName = id+".png";
//			
//			byte[] bytes = multipartFile.getBytes();
//			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File("src/main/resources/static/image/book/"+fileName)));
//			stream.write(bytes);
//			stream.close();
//			
//			return new ResponseEntity<>("Upload Success!", HttpStatus.OK);
//		}catch(Exception e) {
//			e.printStackTrace();
//			return new ResponseEntity<>("Upload failed!", HttpStatus.BAD_REQUEST);
//		}	
//	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Products> getProduct(@PathVariable("id") Long id) {
		return new ResponseEntity<>(productService.findById(id), HttpStatus.OK);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Response> update(@PathVariable("id") Long id, @RequestParam("file") MultipartFile image, 
			@RequestParam("product") String product) throws JsonParseException, JsonMappingException, Exception{
		Products prod = new ObjectMapper().readValue(product, Products.class);
		deleteProductImage(prod);
		String fileName = image.getOriginalFilename();
		String newFileName = FilenameUtils.getBaseName(fileName)+"."+FilenameUtils.getExtension(fileName);
		prod.setImage(newFileName);
		productService.update(id, prod);		
		addProductImage(image);
		return new ResponseEntity<Response>(new Response("Cap nhat thanh cong"), HttpStatus.OK);
	}
	
	private void addProductImage(MultipartFile image) {
		boolean isExit = new File(context.getRealPath("/Images/Product/")).exists();
		if(!isExit) {
			new File (context.getRealPath("/Images/Product/")).mkdir();
			System.out.println("=========mk dir============");
		}
		String fileName = image.getOriginalFilename();
		String newFileName = FilenameUtils.getBaseName(fileName)+"."+FilenameUtils.getExtension(fileName);
		File serverFile = new File(context.getRealPath("/Images/Product/"+File.separator+newFileName));
		try {
			FileUtils.writeByteArrayToFile(serverFile, image.getBytes());
		}catch(Exception e){
			System.out.println("Thêm ảnh sản phẩm không thành công!!");
		}
	}

	private void deleteProductImage(Products prod) {
		System.out.println("========= Xóa ảnh sản phẩm =========");
		try {
			File file = new File (context.getRealPath("/Images/Product/"+prod.getImage()));
			System.out.println("prod.getImages(): "+prod.getImage());
			if(file.delete()) {
				System.out.println(file.getName() + " đang xóa");
			}else {
				System.out.println("Xóa không thành công");
			}
			
		}catch(Exception e) {
			System.out.println("Xóa ảnh sản phẩm không thành công!!");
		}
		
	}

//	@PutMapping("/update/{id}")
//	public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product){
//		Product product1 = productService.findById(id);
//		
//		if(product1 == null) {
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
//		try {
//			product1.setName(product.getName());
//			product1.setImage(product.getImage());
//			product1.setPrice(product.getPrice());
//			product1.setPromotion(product.getPromotion());
//			product1.setDescription(product.getDescription());
//			product1.setCategory(product.getCategory());
//			product1.setListColor(product.getListColor());
//			product1.setListSize(product.getListSize());
//			return new ResponseEntity<>(productService.save(product1), HttpStatus.CREATED);
//		}catch(DataAccessException e) {
//			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}
	
	@DeleteMapping("/delete/{id}")
	public Boolean deleteProduct(@PathVariable("id") Long id) {
		return productService.deleteById(id);
	}
	
//	@GetMapping("/getProductsByCate")
//	public List<Product> getProductsByCate(@RequestBody HashMap<String, String> request){
//		String cate_id = request.get("cate_id");
//		return productService.getByCategoryId(cate_id);
//	}
	
	@GetMapping("/getProductsByCate")
	public Page<Products> getProductsByCategory(@RequestBody HashMap<String, String> request, Pageable pageable){
		String cate_id = request.get("cate_id");
		return productService.getProductsByCategory(cate_id, pageable);
		
	}
	
	@GetMapping("/getProductsByCategory/{id}")
	public Page<Products> getProductsByCate(@PathVariable("id") Long id,@PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable){
		return productService.findAllProductByCategoryId(id, pageable);
		
	}
}
