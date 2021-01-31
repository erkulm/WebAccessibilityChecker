package edu.itu.wac.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

@Slf4j
public class ZipUtil {
    public static void zip(byte[] fileToZip, String path, String fileName) {

        byte[] buffer = new byte[1024];
        try (ZipOutputStream zos = new ZipOutputStream(
                new FileOutputStream(path + fileName + ".zip"), StandardCharsets.UTF_8)) {
            ZipEntry ze = new ZipEntry(fileName + ".xml");
            zos.putNextEntry(ze);
            ByteArrayInputStream bais = new ByteArrayInputStream(fileToZip);
            int len;
            while ((len = bais.read(buffer)) > 0) {
                zos.write(buffer, 0, len);
            }
        } catch (IOException e) {
            log.error(ExceptionUtils.getStackTrace(e));
        }
    }

    public static byte[] unzip(byte[] fileToUnzip) {
        byte[] out = null;
        try (ZipInputStream zis = new ZipInputStream(new ByteArrayInputStream(fileToUnzip))) {
            ZipEntry ze = zis.getNextEntry();

            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int length;
            while ((length = zis.read(buffer)) > 0) {
                bos.write(buffer, 0, length);
            }
            out = bos.toByteArray();
        } catch (IOException e) {
            log.error(ExceptionUtils.getStackTrace(e));
        }
        return out;
    }
}
