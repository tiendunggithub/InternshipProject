package edu.tiendung.InternshipProject.service;

import java.util.List;

public interface BaseService<T> {
	public List<T> getAll();

	public T save(T object);

	public T getByID(Long id);

	public void deleteById(Long id) ;
	
}
