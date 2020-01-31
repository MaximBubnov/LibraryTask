package max.bubnov.library.service;

import max.bubnov.library.domain.Client;
import max.bubnov.library.repo.ClientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    private final ClientRepo clientRepo;

    @Autowired
    public ClientService(ClientRepo clientRepo) {
        this.clientRepo = clientRepo;
    }

    public List<Client> getAll() {
        return clientRepo.findAll();
    }

    public Client getClientById(Long id) {
        return clientRepo.findById(id).stream().findFirst().orElse(null);
    }

    public Client createClient(Client client) {
        return clientRepo.save(client);
    }

    public Client updateClient(Long id, Client client) {

        Client clientById = getClientById(id);

        clientById.setName(client.getName());
        clientById.setPhoneNumber(client.getPhoneNumber());
        clientById.setOrders(client.getOrders());

        return clientRepo.save(clientById);
    }

    public void deleteClient(Client client) {
        clientRepo.delete(client);
    }

}
