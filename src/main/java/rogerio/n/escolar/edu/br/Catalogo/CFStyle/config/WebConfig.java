package rogerio.n.escolar.edu.br.Catalogo.CFStyle.config;

import java.io.File;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        File diretorioUploads = new File("uploads");
        if (!diretorioUploads.exists()) {
            diretorioUploads.mkdirs();
            System.out.println("--> Pasta 'uploads' criada automaticamente na raiz do projeto!");
        }

        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:uploads/");
    }
}