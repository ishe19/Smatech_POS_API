package tech.ishe.smatech_pos.features.product.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tech.ishe.smatech_pos.features.product.services.interfaces.ProductsService;
import tech.ishe.smatech_pos.utils.PosResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductsService productsService;

    @GetMapping("/")
    public ResponseEntity<PosResponse> getAllProducts() {
        return productsService.getAllProducts();
    }


    @PostMapping(value = "/", consumes = {"multipart/form-data"})
    public ResponseEntity<PosResponse> uploadProduct(@RequestPart MultipartFile image, @RequestPart String productDataString) {
        return productsService.uploadProduct(image, productDataString);
    }

    @GetMapping("/{productSku}/image")
    public ResponseEntity<byte[]> getProductImage(@PathVariable String productSku) {
        return productsService.getProductImage(productSku);
    }
}
