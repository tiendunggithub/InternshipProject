package edu.tiendung.InternshipProject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.tiendung.InternshipProject.entity.Category;
import edu.tiendung.InternshipProject.entity.Color;
import edu.tiendung.InternshipProject.service.BaseService;

@RestController
@CrossOrigin(origins= "*", maxAge = 4800, allowCredentials = "false")
@RequestMapping("/api/v1/color")
public class ColorController {
	
	@Autowired
	private BaseService<Color> colorService;

	@GetMapping("/list")
	private ResponseEntity<List<Color>> getAllColor(){
		return new ResponseEntity<>(colorService.getAll(), HttpStatus.OK);
	}
}
