import com.gmdhody.tiendaonline.adapters.out.persistence.ClientRepositoryAdapter;
import com.gmdhody.tiendaonline.adapters.out.persistence.entity.ClientEntity;
import com.gmdhody.tiendaonline.adapters.out.persistence.mapper.ClientMapper;
import com.gmdhody.tiendaonline.adapters.out.persistence.repository.JpaClientRepository;
import com.gmdhody.tiendaonline.domain.model.Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClientRepositoryAdapterTest {

    @Mock
    private JpaClientRepository jpaClientRepository;

    private ClientRepositoryAdapter adapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        adapter = new ClientRepositoryAdapter(jpaClientRepository);
    }

    @Test
    void save_ShouldSaveClientAndReturnDomainObject() {
        Client client = new Client();
        client.setId(1L);
        client.setNombre("Juan");
        client.setApellido("Perez");
        client.setEmail("juan@example.com");
        client.setTelefono("123456789");
        client.setDireccion("Calle 123");

        ClientEntity entity = ClientMapper.toEntity(client);

        when(jpaClientRepository.save(any(ClientEntity.class))).thenReturn(entity);

        Client savedClient = adapter.save(client);

        assertNotNull(savedClient);
        assertEquals(client.getId(), savedClient.getId());
        assertEquals(client.getEmail(), savedClient.getEmail());

        verify(jpaClientRepository, times(1)).save(any(ClientEntity.class));
    }

    @Test
    void findById_ShouldReturnClientIfExists() {
        Long id = 1L;
        ClientEntity entity = new ClientEntity();
        entity.setId(id);
        entity.setNombre("Juan");
        entity.setApellido("Perez");
        entity.setEmail("juan@example.com");
        entity.setTelefono("123456789");
        entity.setDireccion("Calle 123");

        when(jpaClientRepository.findById(id)).thenReturn(Optional.of(entity));

        Optional<Client> result = adapter.findById(id);

        assertTrue(result.isPresent());
        assertEquals(id, result.get().getId());
        assertEquals("juan@example.com", result.get().getEmail());

        verify(jpaClientRepository, times(1)).findById(id);
    }

    @Test
    void findById_ShouldReturnEmptyIfNotFound() {
        Long id = 1L;

        when(jpaClientRepository.findById(id)).thenReturn(Optional.empty());

        Optional<Client> result = adapter.findById(id);

        assertTrue(result.isEmpty());

        verify(jpaClientRepository, times(1)).findById(id);
    }

    @Test
    void findAll_ShouldReturnListOfClients() {
        ClientEntity entity1 = new ClientEntity();
        entity1.setId(1L);
        entity1.setNombre("Juan");
        entity1.setApellido("Perez");
        entity1.setEmail("juan@example.com");
        entity1.setTelefono("123456789");
        entity1.setDireccion("Calle 123");

        ClientEntity entity2 = new ClientEntity();
        entity2.setId(2L);
        entity2.setNombre("Ana");
        entity2.setApellido("Lopez");
        entity2.setEmail("ana@example.com");
        entity2.setTelefono("987654321");
        entity2.setDireccion("Avenida 456");

        when(jpaClientRepository.findAll()).thenReturn(List.of(entity1, entity2));

        List<Client> clients = adapter.findAll();

        assertEquals(2, clients.size());
        assertEquals("juan@example.com", clients.get(0).getEmail());
        assertEquals("ana@example.com", clients.get(1).getEmail());

        verify(jpaClientRepository, times(1)).findAll();
    }

    @Test
    void deleteById_ShouldCallJpaDeleteById() {
        Long id = 1L;

        doNothing().when(jpaClientRepository).deleteById(id);

        adapter.deleteById(id);

        verify(jpaClientRepository, times(1)).deleteById(id);
    }
}

