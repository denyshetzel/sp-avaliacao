package br.com.avaliacao.infrastructure;

import io.minio.*;
import io.minio.errors.MinioException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@Service
@RequiredArgsConstructor
public class MinioService {

    private final MinioClient minioClient;

    @Value("${minio.bucket-name}") private String bucketName;


    public ObjectWriteResponse uploadFile(String objectName, MultipartFile file) throws Exception {
        try (InputStream inputStream = file.getInputStream()) {
            return minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucketName)
                            .object(objectName)
                            .stream(inputStream, inputStream.available(), -1)
                            .contentType(file.getContentType())
                            .build()
            );
        } catch (MinioException e) {
            throw new Exception("Erro ao enviar arquivo para o MinIO", e);
        }
    }

    public InputStream downloadFile(String objectName) throws Exception {
        try {
            return minioClient.getObject(
                    GetObjectArgs.builder()
                            .bucket(bucketName)
                            .object(objectName)
                            .build()
            );
        } catch (MinioException e) {
            throw new Exception("Erro ao baixar arquivo do MinIO", e);
        }
    }

    public void deleteFile(String objectName) throws Exception {
        try {
            minioClient.removeObject(
                    RemoveObjectArgs.builder()
                            .bucket(bucketName)
                            .object(objectName)
                            .build()
            );
        } catch (MinioException e) {
            throw new Exception("Erro ao deletar aquivo do MinIO", e);
        }
    }

}