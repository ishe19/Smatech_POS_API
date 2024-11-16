package tech.ishe.smatech_pos.features.product.services.interfaces;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import tech.ishe.smatech_pos.utils.PosResponse;

public interface ProductsService {
    ResponseEntity<PosResponse> getAllProducts();

    ResponseEntity<PosResponse> uploadProduct(MultipartFile image, String productDataString);

    ResponseEntity<byte[]> getProductImage(String productSku);
}
