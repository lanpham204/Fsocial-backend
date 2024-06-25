package com.fsocial.utils;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import lombok.experimental.UtilityClass;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class CloudinaryUtil {
  private final Cloudinary cloudinary;

  public String saveToCloudinary(MultipartFile file) throws IOException {
    if (file.getOriginalFilename() == null) {
      throw new IOException("Invalid file format");
    }

    String contentType = file.getContentType();
    if (contentType == null) {
      throw new IOException("Unknown file type");
    }

    String resourceType;
    if (contentType.startsWith("image/")) {
      resourceType = "image";
    } else if (contentType.startsWith("video/")) {
      resourceType = "video";
    } else if (contentType.startsWith("audio/")) {
      resourceType = "audio";
    } else {
      resourceType = "auto";
    }

    Map<String, Object> params = ObjectUtils.asMap(
        "folder", "fsocial",
        "resource_type", resourceType,
        "format", getExtension(file.getOriginalFilename()));

    Map result = cloudinary.uploader().upload(file.getBytes(), params);
    return (String) result.get("secure_url");
  }

  private String getExtension(String filename) {
    if (filename == null) {
      return null;
    }
    String[] parts = filename.split("\\.");
    return parts.length > 1 ? parts[parts.length - 1] : "";
  }
}
