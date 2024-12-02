package sysc4806.group7.mini_shopify;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductRepository productRepository;

    @GetMapping("/{productId}/image")
    public ResponseEntity<byte[]> getProductImage(@PathVariable Long productId) {
        Optional<Product> product = productRepository.findById(productId);
        if (product.isPresent() && product.get().getImage() != null) {
            byte[] imageData = product.get().getImage();

            // Return the image as a byte array in the response
            return ResponseEntity.ok()
                    .header("Content-Type", "image/jpeg") // Adjust Content-Type based on your images
                    .body(imageData);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
