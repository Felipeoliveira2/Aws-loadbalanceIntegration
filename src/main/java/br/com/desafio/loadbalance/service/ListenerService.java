package br.com.desafio.loadbalance.service;

import org.springframework.stereotype.Service;

import br.com.desafio.loadbalance.aws.elb.model.Listener;

@Service
public class ListenerService extends ServiceDefault{
	
	public void createListener(Listener listener) {
		try {
			String retorno = null;
		
		}catch(Exception e) {
			logger.error("Erro na camada service");
		}
	}

}