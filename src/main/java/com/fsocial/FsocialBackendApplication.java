package com.fsocial;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FsocialBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(FsocialBackendApplication.class, args);
    }
    @Bean
    public Cloudinary cloudinary() {
        Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "ds3l6gx7b",
                "api_key", "136284838744531",
                "api_secret", "XYM0dNfOrh6McT9mv4sujVMkDno"));
        return cloudinary;
    }
}
