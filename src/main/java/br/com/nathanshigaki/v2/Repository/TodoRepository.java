package br.com.nathanshigaki.v2.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.nathanshigaki.v2.Model.Todo;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long>{
    List<Todo> findByUserId(long userId);
}
