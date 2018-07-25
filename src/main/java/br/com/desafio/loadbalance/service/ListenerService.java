package br.com.desafio.loadbalance.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.elasticloadbalancing.AmazonElasticLoadBalancingClient;
import com.amazonaws.services.elasticloadbalancing.model.CreateLoadBalancerListenersRequest;
import com.amazonaws.services.elasticloadbalancing.model.Listener;

import br.com.desafio.loadbalance.model.Result;

@Service
public class ListenerService extends ServiceDefault{
	
	public Result configurelistener(String loadBalanceName,Result result) {
		try {
			AWSCredentials credentials = new BasicAWSCredentials(ressources.getKey(), ressources.getSecrectKey());
			AmazonElasticLoadBalancingClient client = new AmazonElasticLoadBalancingClient(credentials);
			CreateLoadBalancerListenersRequest createLoadBalancerListenersRequest = new  CreateLoadBalancerListenersRequest();
			createLoadBalancerListenersRequest.setLoadBalancerName(loadBalanceName);
			Listener listener = new Listener();
			listener.setInstancePort(8081);
			listener.setInstanceProtocol("http");
			listener.setLoadBalancerPort(8081);
			listener.setProtocol("http");
			List<Listener> listeners = new ArrayList<Listener>();
			listeners.add(listener);
			createLoadBalancerListenersRequest.setListeners(listeners);
			client.createLoadBalancerListeners(createLoadBalancerListenersRequest);
		}catch(Exception e) {
			result.getMensagens().add(ressources.getMsgClasse()+this.getClass().getName()+ressources.getMsgService()+e.getMessage());
			logger.error(ressources.getMsgClasse()+this.getClass().getName()+ressources.getMsgService()+e.getMessage(),e);
		}
		return result;
		
	}
}
