package net.javaguides.springboot.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.concurrent.CompletableFuture;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class QrCodeService {
    private static final String QR_CODE_IMAGE_PATH = "src/main/resources/static/tempTicketQR.png";

    public static void generateQRCodeImage(String text, int width, int height, String filePath)
            throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);

        Path path = FileSystems.getDefault().getPath(filePath);
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
            }


        @Async    
        public CompletableFuture<Void>  createQr(String message){
            try {
                generateQRCodeImage(message, 350, 350, QR_CODE_IMAGE_PATH);
                return CompletableFuture.completedFuture(null);
              
            } catch (WriterException e) {
                System.out.println("Could not generate QR Code, WriterException :: " + e.getMessage());
                return CompletableFuture.completedFuture(null);
            } catch (IOException e) {
                System.out.println(e);
                return CompletableFuture.completedFuture(null);
                
            }
        }


}




        
