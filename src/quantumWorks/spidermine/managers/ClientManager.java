package quantumworks.spidermine.managers;

import quantumworks.spidermine.SpiderServer;
import quantumworks.spidermine.client.Client;

public class ClientManager implements Manager<Client,String>{
	public SpiderServer server;
	public ClientManager(SpiderServer server) {
		this.server=server;
	}
	@Override
	public Client get(String name) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean add(Client managee) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean remove(Client managee) {
		// TODO Auto-generated method stub
		return false;
	}
}
