package tech.ishe.smatech_pos.features.product.services.impls;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import tech.ishe.smatech_pos.features.product.models.ProductDto;
import tech.ishe.smatech_pos.features.product.models.entities.Product;
import tech.ishe.smatech_pos.features.product.models.entities.ProductImage;
import tech.ishe.smatech_pos.features.product.models.requests.ProductRequest;
import tech.ishe.smatech_pos.features.product.repositories.ProductImageRepository;
import tech.ishe.smatech_pos.features.product.repositories.ProductRepository;
import tech.ishe.smatech_pos.features.product.services.interfaces.ProductsService;
import tech.ishe.smatech_pos.utils.DtoMapper;
import tech.ishe.smatech_pos.utils.ImageUtils;
import tech.ishe.smatech_pos.utils.PosResponse;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductsServiceImpl implements ProductsService {
    private final ProductRepository productRepository;
    private final DtoMapper dtoMapper;
    private final ProductImageRepository productImageRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductsServiceImpl.class);

    @Override
    public ResponseEntity<PosResponse> getAllProducts() {
        try {
            List<ProductDto> productDtos = productRepository.findAll().stream().map(dtoMapper::mapToProductDto).toList();

            return new ResponseEntity<>(new PosResponse(productDtos), HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("Get products error: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<PosResponse> uploadProduct(MultipartFile image, String productDataString) {
        try {
            ProductRequest request = convertStringToRequest(productDataString);
            Product product = Product
                    .builder()
                    .description(request.getDescription())
                    .price(request.getPrice())
                    .name(request.getName())
                    .productSku(UUID.randomUUID().toString())
                    .build();

            Product savedProduct = productRepository.save(product);

            ProductImage productImage = ProductImage
                    .builder()
                    .product(savedProduct)
                    .name(image.getName())
                    .imageType(image.getContentType())
                    .data(ImageUtils.compressImage(image.getBytes()))
                    .build();

            productImageRepository.save(productImage);

            return new ResponseEntity<>(new PosResponse("Product uploaded successfully"), HttpStatus.CREATED);

        } catch (Exception e) {
            LOGGER.error("Upload product error: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    @Override
    public ResponseEntity<byte[]> getProductImage(String productSku) {
        try {
            Product product = productRepository.findByProductSku(productSku);
            if (product != null) {
                ProductImage productImage = productImageRepository.findByProductId(product.getId());
                if (productImage != null) {
                    return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf(productImage.getImageType()))
                            .body(ImageUtils.decompressImage(productImage.getData()));
                }
            }

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            LOGGER.error("GET PRODUCT IMAGE ERROR: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ProductRequest convertStringToRequest(String dataString) {
        ProductRequest productRequest = new ProductRequest();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            productRequest = objectMapper.readValue(dataString, ProductRequest.class);
        } catch (Exception e) {
            LOGGER.error("STRING CONVERSION ERROR: {} ", e.getMessage());
        }
        return productRequest;
    }
}
