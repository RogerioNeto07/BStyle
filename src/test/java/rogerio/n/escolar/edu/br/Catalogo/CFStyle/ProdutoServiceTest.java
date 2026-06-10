// package rogerio.n.escolar.edu.br.Catalogo.CFStyle;

// import static org.mockito.ArgumentMatchers.any;
// import static org.mockito.Mockito.*;
// import static org.junit.jupiter.api.Assertions.*;

// import java.util.List;
// import java.util.Optional;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.junit.jupiter.MockitoExtension;

// import rogerio.n.escolar.edu.br.Catalogo.CFStyle.dto.produto.ProdutoCreateDTO;
// import rogerio.n.escolar.edu.br.Catalogo.CFStyle.dto.produto.ProdutoResponseDTO;
// import rogerio.n.escolar.edu.br.Catalogo.CFStyle.models.Cor;
// import rogerio.n.escolar.edu.br.Catalogo.CFStyle.models.Produto;
// import rogerio.n.escolar.edu.br.Catalogo.CFStyle.models.Tag;
// import rogerio.n.escolar.edu.br.Catalogo.CFStyle.models.Tipo;
// import rogerio.n.escolar.edu.br.Catalogo.CFStyle.repositories.*;

// @ExtendWith(MockitoExtension.class)
// class ProdutoServiceTest {

//     @Mock
//     private ProdutoRepository produtoRepository;

//     @Mock
//     private TipoRepository tipoRepository;

//     @Mock
//     private TagRepository tagRepository;

//     @Mock
//     private CorRepository corRepository;

//     @InjectMocks
//     private rogerio.n.escolar.edu.br.Catalogo.CFStyle.services.ProdutoService produtoService;

//     private Produto produto;
//     private Tipo tipo;
//     private Cor cor;
//     private Tag tag;

//     @BeforeEach
//     void setup() {
//         tipo = new Tipo();
//         tipo.setId(1L);
//         tipo.setNome("Camisa");

//         cor = new Cor();
//         cor.setId(1L);
//         cor.setNome("azul");

//         tag = new Tag();
//         tag.setId(1L);
//         tag.setNome("Promoção");

//         produto = new Produto();
//         produto.setId(1L);
//         produto.setNome("Produto Teste");
//         produto.setDescricao("Desc");
//         produto.setPreco(100.0);
//         produto.setQuantidade(10);
//         produto.setAtivo(true);
//         produto.setTipo(tipo);
//         produto.setCores(List.of(cor));
//         produto.setTags(List.of(tag));
//     }

//     @Test
//     void testListar() {
//         when(produtoRepository.findAll()).thenReturn(List.of(produto));

//         List<ProdutoResponseDTO> lista = produtoService.listar();

//         assertEquals(1, lista.size());
//         assertEquals("Produto Teste", lista.get(0).getNome());
//     }

//     @Test
//     void testBuscar() {
//         when(produtoRepository.findById(1L)).thenReturn(Optional.of(produto));

//         ProdutoResponseDTO dto = produtoService.buscar(1L);

//         assertEquals("Produto Teste", dto.getNome());
//     }

//     @Test
//     void testCriar() {
//         ProdutoCreateDTO dto = new ProdutoCreateDTO();
//         dto.setNome("Produto Teste");
//         dto.setDescricao("Teste");
//         dto.setPreco(150.0);
//         dto.setQuantidade(5);
//         dto.setTipoId(1L);
//         dto.setCoresIds(List.of(1L));
//         dto.setTagsIds(List.of(1L));

//         when(tipoRepository.findById(1L)).thenReturn(Optional.of(tipo));
//         when(corRepository.findAllById(List.of(1L))).thenReturn(List.of(cor));
//         when(tagRepository.findAllById(List.of(1L))).thenReturn(List.of(tag));
//         when(produtoRepository.save(any(Produto.class))).thenReturn(produto);

//         ProdutoResponseDTO resp = produtoService.criar(dto);

//         assertEquals("Produto Teste", resp.getNome());
//     }

//     @Test
//     void testAtualizar() {
//         ProdutoCreateDTO dto = new ProdutoCreateDTO();
//         dto.setNome("Atualizado");
//         dto.setDescricao("Nova desc");
//         dto.setPreco(200.0);
//         dto.setQuantidade(7);
//         dto.setTipoId(1L);
//         dto.setCoresIds(List.of(1L));
//         dto.setTagsIds(List.of(1L));

//         when(produtoRepository.findById(1L)).thenReturn(Optional.of(produto));
//         when(tipoRepository.findById(1L)).thenReturn(Optional.of(tipo));
//         when(corRepository.findAllById(List.of(1L))).thenReturn(List.of(cor));
//         when(tagRepository.findAllById(List.of(1L))).thenReturn(List.of(tag));
//         when(produtoRepository.save(any(Produto.class))).thenReturn(produto);

//         ProdutoResponseDTO resp = produtoService.atualizar(1L, dto);

//         assertEquals("Atualizado", resp.getNome());
//     }

//     @Test
//     void testDeletar() {
//         doNothing().when(produtoRepository).deleteById(1L);

//         produtoService.deletar(1L);

//         verify(produtoRepository, times(1)).deleteById(1L);
//     }

//     @Test
//     void testListarPorTipo() {
//         when(produtoRepository.findByTipoId(1L)).thenReturn(List.of(produto));

//         List<ProdutoResponseDTO> lista = produtoService.listarPorTipo(1L);

//         assertEquals(1, lista.size());
//     }

//     @Test
//     void testListarPorTag() {
//         when(produtoRepository.findByTagId(1L)).thenReturn(List.of(produto));

//         List<ProdutoResponseDTO> lista = produtoService.listarPorTag(1L);

//         assertEquals(1, lista.size());
//     }
// }
