package com.github.luisfeliperochamartins.rememberTools.domain.tools;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(path = "/tools")
public class ToolsController {

	private final ToolsRepository repository;

	@Autowired
	public ToolsController(ToolsRepository repository) {
		this.repository = repository;
	}

	@GetMapping(path = "/{tags}")
	public ResponseEntity<List<Tools>> getByTags(@PathVariable String tags) {
		var tagsList = Arrays.asList(tags.split(","));

		var tools = repository.findAll().stream().filter(tool -> tool.getTags().containsAll(tagsList)).toList();

		return ResponseEntity.ok(tools);
	}

	@GetMapping
	public ResponseEntity<Page<Tools>> getAll(@PageableDefault Pageable page) {
		var list = repository.findAll(page);

		return ResponseEntity.ok(list);
	}

	@PostMapping
	public ResponseEntity<Tools> insert(@RequestBody Tools tool, UriComponentsBuilder uriBuilder) {
		tool = repository.save(tool);

		var uri = uriBuilder.path("/tools/{id}").buildAndExpand(tool.getId()).toUri();

		return ResponseEntity.created(uri).body(tool);
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity delete(@PathVariable Integer id) {
		var tool = repository.findById(id);

		if (!tool.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		repository.deleteById(id);

		return ResponseEntity.noContent().build();
	}
}
