package br.com.nathanshigaki.v2.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.nathanshigaki.v2.Model.User;

@Repository
public interface UserRepository extends JpaRepository <User, Long>{

}
