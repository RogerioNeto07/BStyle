package rogerio.n.escolar.edu.br.Catalogo.CFStyle.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import rogerio.n.escolar.edu.br.Catalogo.CFStyle.models.*;
import rogerio.n.escolar.edu.br.Catalogo.CFStyle.repositories.*;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final CorRepository corRepository;
    private final TipoRepository tipoRepository;
    private final TagRepository tagRepository;

    public DatabaseSeeder(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder, 
                          CorRepository corRepository, TipoRepository tipoRepository, TagRepository tagRepository) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.corRepository = corRepository;
        this.tipoRepository = tipoRepository;
        this.tagRepository = tagRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        
        if (usuarioRepository.findByLogin("admin").isEmpty()) {
            Usuario admin = new Usuario();
            admin.setNome("Administrador");
            admin.setLogin("admin");
            admin.setSenha(passwordEncoder.encode("admin123"));
            admin.setCidade("Umarizal");
            admin.setEstado("RN");
            admin.setTelefone("(84) 99999-9999");
            admin.setRua("Av. Capitão Olinto Mancini");
            admin.setNumero("100");
            
            usuarioRepository.save(admin);
            System.out.println("--> Seed: Usuário Admin criado!");
        }

        if (corRepository.count() == 0) {
            Cor rosa = new Cor();
            rosa.setNome("Rosa");
            corRepository.save(rosa);

            Cor preto = new Cor();
            preto.setNome("Preto");
            corRepository.save(preto);

            Cor azul = new Cor();
            azul.setNome("Azul");
            corRepository.save(azul);
            
            System.out.println("--> Seed: Cores cadastradas!");
        }

        if (tipoRepository.count() == 0) {
            Tipo roupa = new Tipo();
            roupa.setNome("Roupa");
            tipoRepository.save(roupa);

            Tipo calcado = new Tipo();
            calcado.setNome("Calçado");
            tipoRepository.save(calcado);

            Tipo acessorio = new Tipo();
            acessorio.setNome("Acessório");
            tipoRepository.save(acessorio);
            
            System.out.println("--> Seed: Tipos cadastrados!");
        }

        if (tagRepository.count() == 0) {
            Tag basica = new Tag();
            basica.setNome("Básica");
            tagRepository.save(basica);

            Tag inverno = new Tag();
            inverno.setNome("Fitness");
            tagRepository.save(inverno);

            Tag desapego = new Tag();
            desapego.setNome("Plus size");
            tagRepository.save(desapego);
            
            System.out.println("--> Seed: Tags cadastradas!");
        }
    }
}