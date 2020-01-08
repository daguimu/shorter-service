package com.dagm.shorter.feign;

import feign.codec.Decoder;
import feign.form.spring.converter.SpringManyMultipartFilesReader;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author guimu
 */
@FeignClient(
    name = "file-service",
    configuration = DownloadClient.ClientConfiguration.class
)
public interface DownloadClient {

    @RequestMapping("/inner/api/download")
    MultipartFile download(@RequestParam(value = "filename") String filename);

    class ClientConfiguration {

        @Autowired
        private ObjectFactory<HttpMessageConverters> messageConverters;

        @Bean
        public Decoder feignDecoder() {
            List<HttpMessageConverter<?>> springConverters =
                messageConverters.getObject().getConverters();

            List<HttpMessageConverter<?>> decoderConverters =
                new ArrayList<>(springConverters.size() + 1);

            decoderConverters.addAll(springConverters);
            decoderConverters.add(new SpringManyMultipartFilesReader(4096));

            HttpMessageConverters httpMessageConverters = new HttpMessageConverters(
                decoderConverters);
            return new SpringDecoder(() -> httpMessageConverters);
        }
    }
}