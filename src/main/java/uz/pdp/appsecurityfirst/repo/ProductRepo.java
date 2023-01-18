package uz.pdp.appsecurityfirst.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appsecurityfirst.entity.Product;

public interface ProductRepo extends JpaRepository<Product , Integer> {
}
