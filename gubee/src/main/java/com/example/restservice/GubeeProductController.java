package com.example.restservice;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import one.util.streamex.StreamEx;

@RestController
@RequestMapping(value="/guubeProducts")
public class GubeeProductController {
	
	private final List<GubeeProduct> listaProdutosGubee = new ArrayList<GubeeProduct>();
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody final GubeeProduct gubeeProduct){
		if(this.listaProdutosGubee.add(gubeeProduct)) {
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
					.path("/{productName}").buildAndExpand(gubeeProduct.getProductName()).toUri();
			return ResponseEntity.created(uri).build();
		} else {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@GetMapping(value="/{productName}")
	public ResponseEntity<?> getGubeeProduct(@PathVariable String productName){
		return ResponseEntity.ok().body(listaProdutosGubee.stream().filter((product) -> product.getProductName().equals(productName)).findFirst().get());
	}

	@GetMapping
	public ResponseEntity<?> getAllGubeeProducts() {
		return ResponseEntity.ok().body(listaProdutosGubee);
	}
	
	@GetMapping(value = "/getAllByStack")
	public ResponseEntity<?> getAllGubeeProductsByStack(@RequestBody List<String> stack){
		List<GubeeProduct> list = new ArrayList<GubeeProduct>();
		for(String stc : stack) {
			list.addAll(listaProdutosGubee.stream().filter((product) -> product.getStack().contains(stc)).collect(Collectors.toList()));
		}
		return ResponseEntity.ok().body(StreamEx.of(list).distinct(GubeeProduct::getProductName).toList());		
	}
}